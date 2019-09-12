package order.mm.com.bean;

import java.io.Serializable;
import java.util.Date;

/**
 *
 */
public class Qrcode implements Serializable {
    public static  int status_WAIT_USE=0;
    public static  int status_WAIT_PAY=1;
    public int id;
    public String qrcodeAccount;
    public String back;
    public String attachId;
    public int status;
    public Double todayAmount;
    public Double totalAmount;
    public Date updateAt;
    public Date createAt;
    public Date lastOrderAt;
    public Date currentOrderExp;
    public String type;

}
