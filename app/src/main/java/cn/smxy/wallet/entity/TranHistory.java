package cn.smxy.wallet.entity;

import java.io.Serializable;

public class TranHistory implements Serializable {
    private int id;
    private int userId;
    private int cardId;
    private String pay_what;
    private String pay_address;
    private Float pay_money;
    private String date;
    private String cardNum;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getPay_what() {
        return pay_what;
    }

    public void setPay_what(String pay_what) {
        this.pay_what = pay_what;
    }

    public String getPay_address() {
        return pay_address;
    }

    public void setPay_address(String pay_address) {
        this.pay_address = pay_address;
    }

    public Float getPay_money() {
        return pay_money;
    }

    public void setPay_money(Float pay_money) {
        this.pay_money = pay_money;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }
}
