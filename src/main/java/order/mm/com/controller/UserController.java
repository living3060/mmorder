package order.mm.com.controller;

import order.mm.com.vo.req.LoginReq;
import order.mm.com.vo.resp.LoginResp;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("boss/user")
public class UserController {

    @RequestMapping("/login")
    @ResponseBody
    public Object login(LoginReq loginReq){
        LoginResp resp=new LoginResp();
        resp.token= UUID.randomUUID().toString();
        resp.name=loginReq.userName;
        resp.code="0";
       return resp;
    }
}
