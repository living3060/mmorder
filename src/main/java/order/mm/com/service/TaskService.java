package order.mm.com.service;

import lombok.extern.slf4j.Slf4j;
import order.mm.com.bean.Order;
import order.mm.com.bean.OrderFilter;
import order.mm.com.bean.Qrcode;
import order.mm.com.coomm.Cache;
import order.mm.com.mapper.OrderMapper;
import order.mm.com.mapper.QrCodeMapper;
import order.mm.com.util.HttpUtil;
import order.mm.com.vo.req.OrderNotifyReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;

@Component
@Slf4j
public class TaskService {
    String key = "sssvvss098!!";

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    QrCodeMapper qrCodeMapper;


    @Autowired
    Cache cache;

    //每隔5秒执行一次定时任务
    @Scheduled(cron = "0/30 * * * * ?")
    public void reaseQrcode() {
        OrderFilter orderFilter = new OrderFilter();
        orderFilter.start = 0;
        orderFilter.end = 30;
        orderFilter.status = Order.status_WAIT_PAY;
        orderFilter.endExptime = Calendar.getInstance().getTime();
        List<Order> orderList = orderMapper.selectByFilter(orderFilter);
        for (Order order : orderList) {
            Qrcode qrcode = qrCodeMapper.selectById(order.qrCodeId);
            qrcode.status = Qrcode.status_WAIT_USE;
            try {
                cache.updateCache(qrcode);
            } catch (Exception e) {
                e.printStackTrace();
            }
            orderMapper.updateStatus(order.status, Order.status_TIMOUT_OUT, order.orderNo);
        }
    }

    //每隔5秒执行一次定时任务
    @Scheduled(cron = "0/30 * * * * ?")
    public void notifyOrder() {
        OrderFilter orderFilter = new OrderFilter();
        orderFilter.start = 0;
        orderFilter.end = 30;
        orderFilter.status = Order.status_WAIT_NOTIFY;
        orderFilter.endExptime = Calendar.getInstance().getTime();
        List<Order> orderList = orderMapper.selectByFilter(orderFilter);
        for (Order order : orderList) {
            postOrder(order);
        }
    }

    private void postOrder(Order order) {
        try {
            OrderNotifyReq req = new OrderNotifyReq();
            req.orderNo = order.orderNo;
            req.gameOrderNo = order.gameOrderNo;
            req.gameAccountId = order.gameAccountId;
            req.amount = String.valueOf(order.amount);
            req.sign = req.getSign(key);
            HttpUtil.postJson(req, order.notifyUrl);
        } catch (Exception e) {
            log.error("post order error order=" + order.orderNo, e);
        }
    }
}
