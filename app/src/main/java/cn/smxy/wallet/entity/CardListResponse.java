package cn.smxy.wallet.entity;

import java.util.List;

public class CardListResponse {
    private int code;
    private String msg;
    private List<CardList> dataobject;

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

    public List<CardList> getDataobject() {
        return dataobject;
    }

    public void setDataobject(List<CardList> dataobject) {
        this.dataobject = dataobject;
    }

    @Override
    public String toString() {
        return "CardResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", dataobject=" + dataobject +
                '}';
    }
}
