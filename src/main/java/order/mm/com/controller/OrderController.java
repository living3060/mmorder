package order.mm.com.controller;

import order.mm.com.service.OrderService;
import order.mm.com.vo.req.QrcodeSaveReq;
import order.mm.com.vo.resp.QrcodeSaveResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//@Controller
//@RequestMapping("boss/order")
public class OrderController {
    /*
    @Autowired
    OrderService orderService;

    @RequestMapping("/saveQrcode")
    @ResponseBody
    public Object saveQrcoe(@RequestBody QrcodeSaveReq req) {
        orderService.insertQrcode(req.fileId, req.type, req.back, req.qrcodeAccount);
        return new QrcodeSaveResp();
    }
    */
}
