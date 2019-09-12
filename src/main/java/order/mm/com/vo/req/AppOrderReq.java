package order.mm.com.vo.req;

import java.io.Serializable;

public class AppOrderReq implements Serializable {
    public String gameOrderNo;
    public int gameId;
    public int gameChannel;
    public String gameAccountId;
    public Double amount;
    public String type;
    public String sign;
}
