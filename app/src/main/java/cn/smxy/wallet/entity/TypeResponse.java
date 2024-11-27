package cn.smxy.wallet.entity;

import java.util.List;

public class TypeResponse {
    private int code;
    private String msg;
    private List<CardType> dataobject;

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

    public List<CardType> getDataobject() {
        return dataobject;
    }

    public void setDataobject(List<CardType> dataobject) {
        this.dataobject = dataobject;
    }
}
