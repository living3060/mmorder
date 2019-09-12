package order.mm.com.service;

import lombok.extern.slf4j.Slf4j;
import order.mm.com.bean.Order;
import order.mm.com.bean.OrderFilter;
import order.mm.com.bean.Qrcode;
import order.mm.com.coomm.AppException;
import order.mm.com.coomm.Cache;
import order.mm.com.mapper.AttachMapper;
import order.mm.com.mapper.OrderMapper;
import order.mm.com.mapper.QrCodeMapper;
import order.mm.com.vo.req.AppOrderReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j
public class OrderService {

    @Autowired
    QrCodeMapper qrCodeMapper;

    @Autowired
    AttachMapper attachMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    Cache cache;

    @Value("notfiyUrl")
    public String notfiyUrl;

    public AtomicInteger atomicInteger = new AtomicInteger();

    public Order selectOrder(String orderNo) {
        return orderMapper.selectByOrderNo(orderNo);
    }

    public List<Order> getOrderList(OrderFilter orderFilter) {
        return orderMapper.selectByFilter(orderFilter);
    }

    public void insertQrcode(String attachId, String type, String back, String account) {

        Qrcode qrcode = new Qrcode();
        qrcode.attachId = attachId;
        qrcode.back = back;
        qrcode.qrcodeAccount = account;
        qrcode.createAt = new Date();
        qrcode.updateAt = new Date();
        qrcode.todayAmount = 0d;
        qrcode.totalAmount = 0d;
        qrcode.currentOrderExp = new Date();
        qrcode.type = type;
        qrcode.updateAt = new Date();
        qrCodeMapper.insert(qrcode);
        try {
            cache.updateCache(qrcode);
        }catch (Exception e){
            log.error("error",e);
        }
    }

    /**
     * 下单
     *
     * @param appOrderReq
     * @return
     * @throws Exception
     */
    public Order genOrder(AppOrderReq appOrderReq)  {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS");

        Qrcode qrcode = cache.genQrcode(appOrderReq.type);
        Order order = new Order();
        //
        order.qrCodeAccount = qrcode.qrcodeAccount;
        order.qrCodeId = qrcode.id;

        order.attachId = qrcode.attachId;
        //
        order.amount = appOrderReq.amount;
        order.createAt = new Date();
        order.gameId = appOrderReq.gameId;
        order.gameChannel = appOrderReq.gameChannel;
        order.gameAccountId = appOrderReq.gameAccountId;

        order.hit = new Date();
        order.orderNo = simpleDateFormat.format(new Date()) + "" + atomicInteger.incrementAndGet();
        order.gameOrderNo = appOrderReq.gameOrderNo;
        order.type=appOrderReq.type;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 6);
        order.expTime = calendar.getTime();
        order.notifyUrl=notfiyUrl;

        orderMapper.insert(order);
        qrcode.status=Qrcode.status_WAIT_PAY;
        cache.updateCache(qrcode);
        return order;
        /*
        OrderFilter orderFilter=new OrderFilter();
        orderFilter.status=0;
        orderFilter.amount=10000d;
        orderFilter.account="w";
        */
    }

    /**
     * 下单
     *
     * @return
     * @throws Exception
     */
    public void notifyOrder(Order order) {

        Order existOrder=orderMapper.selectById(order.id);
        if(existOrder==null){
            throw  new AppException("001","订单不存在");
        }
        if(existOrder.status!=Order.status_WAIT_PAY){
            throw  new AppException("002","订单已处理");
        }
        if(existOrder.expTime.after(Calendar.getInstance().getTime())){
            throw  new AppException("003","订单已超时");
        }
        orderMapper.updateStatus(order.status,Order.status_WAIT_NOTIFY,existOrder.orderNo);
        Qrcode qrCodeExist=qrCodeMapper.selectById(existOrder.qrCodeId);
        if(qrCodeExist!=null){
            qrCodeExist.status=Qrcode.status_WAIT_USE;
            cache.updateCache(qrCodeExist);
        }
        /*
        OrderFilter orderFilter=new OrderFilter();
        orderFilter.status=0;
        orderFilter.amount=10000d;
        orderFilter.account="w";
        */
    }

}
