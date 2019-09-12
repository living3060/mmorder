package order.mm.com.coomm;

public class AppException extends RuntimeException {
    public String msg;
    public String code;

    public AppException(String msg,String code){
        super(msg);
        this.msg=msg;
        this.code=code;
    }
}
