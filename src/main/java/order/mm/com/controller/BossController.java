package order.mm.com.controller;

import order.mm.com.bean.Order;
import order.mm.com.bean.OrderFilter;
import order.mm.com.service.OrderService;
import order.mm.com.vo.req.QrcodeSaveReq;
import order.mm.com.vo.resp.NotifyOrderResp;
import order.mm.com.vo.resp.OrderListResp;
import order.mm.com.vo.resp.QrcodeSaveResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("boss/order")
public class BossController {
    @Autowired
    OrderService orderService;

    @RequestMapping("/saveQrcode")
    @ResponseBody
    public Object saveQrcoe(@RequestBody QrcodeSaveReq req) {
        orderService.insertQrcode(req.fileId, req.type, req.back, req.qrcodeAccount);
        QrcodeSaveResp resp=new QrcodeSaveResp();
        resp.code="0";
        return resp;
    }

    @RequestMapping("/getOrderList")
    @ResponseBody
    public Object getOrderList(@RequestBody OrderFilter orderFilter) {
        orderFilter.status=Order.status_WAIT_PAY;
        orderFilter.endExptime= Calendar.getInstance().getTime();
        List<Order> order = orderService.getOrderList(orderFilter);
        OrderListResp resp = new OrderListResp();
        resp.code = "0";
        resp.data = order;
        return resp;
    }

    @RequestMapping("/orderNotify")
    @ResponseBody
    public Object orderNotify(@RequestBody Order order) {
        orderService.notifyOrder(order);
        NotifyOrderResp resp=new NotifyOrderResp();
        return resp;
    }
}
