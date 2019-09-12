package order.mm.com.intercept;

import lombok.extern.slf4j.Slf4j;
import order.mm.com.coomm.AppException;
import order.mm.com.vo.resp.BaseResp;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ExceptionHandler implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception ex) {

        log.error("",ex);
        String viewName = "forward:/serverError";

        Map<String,Object> objectMap=new HashMap<>();
        String code = "500";
        String msg = ex.getMessage();
        Throwable throwable = ex;
        while (throwable.getCause() != null) {
            throwable = throwable.getCause();
        }
        if (throwable instanceof AppException) {
            AppException app = (AppException) throwable;
           code = app.code;
           msg = app.msg;
        }
        if (o instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) o;
            Annotation annotation = handlerMethod.getMethod().getAnnotation(ResponseBody.class);
            if (annotation != null) {
                viewName = "forward:/jsonError";
            }
        }
        objectMap.put("code",code);
        objectMap.put("msg",msg);
        ModelAndView view = new ModelAndView(viewName);
        view.addObject("exp", objectMap);
        httpServletRequest.setAttribute("exp", objectMap);
        return view;
    }
}
