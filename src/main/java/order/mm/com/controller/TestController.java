package order.mm.com.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class TestController {

    @RequestMapping("/test")
    @ResponseBody
    public Object test(){
        return "ss";
    }
}
