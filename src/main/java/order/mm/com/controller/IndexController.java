package order.mm.com.controller;

import order.mm.com.coomm.AppException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @RequestMapping("/index")
    public ModelAndView index(ModelAndView model){
        if(1==1){
            throw new AppException("sss","-200");
        }
        model.setViewName("index");
        return model;
    }
}
