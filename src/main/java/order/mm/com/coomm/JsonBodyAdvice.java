package order.mm.com.coomm;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

public class JsonBodyAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        Annotation annotation=methodParameter.getMethod().getAnnotation(ResponseBody.class);
        if(annotation!=null){
            return true;
        }
        return false;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        String code="0";
        String msg="";
        Object data=null;
        if( o instanceof AppException){
            AppException exception=(AppException)o;
            code=exception.code;
            msg=exception.msg;
        }else{
            data=o;
        }
        Map<String,Object> ret=new HashMap<>();
        ret.put("code",code);
        ret.put("msg",msg);
        ret.put("data",data);
        return ret;
    }

}
