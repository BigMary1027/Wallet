package cn.smxy.wallet.entity;

import java.util.List;

public class TranHistoryResponse {
    private int code;
    private String msg;
    private List<TranHistory> dataobject;

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

    public List<TranHistory> getDataobject() {
        return dataobject;
    }

    public void setDataobject(List<TranHistory> dataobject) {
        this.dataobject = dataobject;
    }
}
