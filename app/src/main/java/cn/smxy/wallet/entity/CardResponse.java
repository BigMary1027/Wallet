package cn.smxy.wallet.entity;

import java.util.List;

public class CardResponse {
    private int code;
    private String msg;
    private List<Card> dataobject;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Card> getDataobject() {
        return dataobject;
    }

    public void setDataobject(List<Card> dataobject) {
        this.dataobject = dataobject;
    }
}
