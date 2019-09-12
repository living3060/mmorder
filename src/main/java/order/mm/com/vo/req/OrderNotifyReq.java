package order.mm.com.vo.req;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.Serializable;

public class OrderNotifyReq implements Serializable {
    public String orderNo;
    public String gameOrderNo;
    public String gameAccountId;
    public String amount;
    public String sign;

    public String getSign(String key) {
        String string =amount +"&"+gameAccountId  +"&"+ gameOrderNo  +"&"+ orderNo + key;
        return DigestUtils.md5Hex(string);
    }
}
