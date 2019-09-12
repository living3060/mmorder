package order.mm.com.bean;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    public static  int status_WAIT_PAY=0;
    public static  int status_WAIT_NOTIFY=1;
    public static  int status_NOTIFY_OK=2;
    public static  int status_TIMOUT_OUT=3;


    public int id;
    public int gameChannel;
    public int gameId;
    public String gameAccountId;
    public String gameOrderNo;
    public Double amount;
    public String orderNo;
    public int  status;
    public Date expTime;
    public int qrCodeId;
    public String qrCodeAccount;
    public Date updateAt;
    public Date createAt;
    public Date hit;
    public String attachId;
    public String type;

    public String notifyUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGameChannel() {
        return gameChannel;
    }

    public void setGameChannel(int gameChannel) {
        this.gameChannel = gameChannel;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getGameAccountId() {
        return gameAccountId;
    }

    public void setGameAccountId(String gameAccountId) {
        this.gameAccountId = gameAccountId;
    }

    public String getGameOrderNo() {
        return gameOrderNo;
    }

    public void setGameOrderNo(String gameOrderNo) {
        this.gameOrderNo = gameOrderNo;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getExpTime() {
        return expTime;
    }

    public void setExpTime(Date expTime) {
        this.expTime = expTime;
    }

    public int getQrCodeId() {
        return qrCodeId;
    }

    public void setQrCodeId(int qrCodeId) {
        this.qrCodeId = qrCodeId;
    }

    public String getQrCodeAccount() {
        return qrCodeAccount;
    }

    public void setQrCodeAccount(String qrCodeAccount) {
        this.qrCodeAccount = qrCodeAccount;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getHit() {
        return hit;
    }

    public void setHit(Date hit) {
        this.hit = hit;
    }

    public String getAttachId() {
        return attachId;
    }

    public void setAttachId(String attachId) {
        this.attachId = attachId;
    }
}
