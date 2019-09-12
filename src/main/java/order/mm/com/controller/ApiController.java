package order.mm.com.controller;

import order.mm.com.bean.Order;
import order.mm.com.service.OrderService;
import order.mm.com.vo.req.AppOrderReq;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api")
public class ApiController {

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/applyOrder")
    public ModelAndView appliyOrder(@Param("gameId") Integer gameId,
                                    @Param("gameChannel") Integer gameChannel,
                                    @Param("gameOrderNo") String gameOrderNo,
                                    @Param("gameAccountId") String gameAcccountId,
                                    @Param("type") String type,
                                    @Param("amount") Double amount,
                                    ModelAndView modelAndView,
                                    @CookieValue(name = "orderNo", defaultValue = "") String orderNo
            , HttpServletResponse response) {

            AppOrderReq appOrderReq = new AppOrderReq();
            appOrderReq.amount = amount;
            if (gameId == null) {
                gameId = 0;
            }
            if (gameChannel == null) {
                gameChannel = 0;
            }
            appOrderReq.gameId = gameId;
            appOrderReq.gameAccountId = gameAcccountId;
            appOrderReq.type = String.valueOf(type);
            appOrderReq.gameChannel = gameChannel;
            appOrderReq.gameOrderNo = gameOrderNo;
            Order order = null;
            if (orderNo != null && orderNo.length() > 0) {
                order = orderService.selectOrder(orderNo);
            }
            if (order == null){
                order = orderService.genOrder(appOrderReq);
            }
            long endTime = order.expTime.getTime() - System.currentTimeMillis();
            modelAndView.addObject("lessTime", ((endTime / 1000) - 60));
            modelAndView.addObject("order", order);
            modelAndView.setViewName("qrcode_show");
            response.addCookie(new Cookie("orderNo", order.orderNo));
        return modelAndView;
    }

    @RequestMapping("/orderRedirect")
    public ModelAndView orderRedirect(@Param("gameId") String gameId, @Param("gameAccountId") String gameAcccountId,
                                      ModelAndView modelAndView,HttpServletResponse response) {
        modelAndView.addObject("gameId", gameId);
        modelAndView.addObject("gameAccountId", gameAcccountId);
        modelAndView.setViewName("to_apply");
        response.addCookie(new Cookie("orderNo", ""));
        return modelAndView;
    }
}
