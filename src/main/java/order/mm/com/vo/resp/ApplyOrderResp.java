package order.mm.com.vo.resp;

import java.io.Serializable;

/**
 *
 */
public class ApplyOrderResp implements Serializable {
    public String orderNo;
    public double amount;
    public String gameAccountId;
    public byte[] qrCode;
}
