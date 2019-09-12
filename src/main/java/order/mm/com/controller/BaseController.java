package order.mm.com.controller;

import order.mm.com.vo.resp.BaseResp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class BaseController {

    @RequestMapping("/serverError")
    public ModelAndView error(@RequestAttribute("exp") Map<String,Object> baseResp) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("exp", baseResp);
        return modelAndView;
    }

    @RequestMapping("/jsonError")
    @ResponseBody
    public Object jsonError(@RequestAttribute("exp") Map<String,Object> baseResp) {
        return baseResp;
    }
}
