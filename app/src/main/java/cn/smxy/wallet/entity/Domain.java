package cn.smxy.wallet.entity;

import java.util.ArrayList;

public class Domain {
    private String name;
    private String symbol;
    private Double price;
    private Double changePercent;
    private ArrayList<Integer> lineData;
    private Double propertyAmount;
    private Double propertySize;

    public Domain(String name, String symbol, Double price, Double changePercent, ArrayList<Integer> lineData, Double propertyAmount, Double propertySize) {
        this.name = name;
        this.symbol = symbol;
        this.price = price;
        this.changePercent = changePercent;
        this.lineData = lineData;
        this.propertyAmount = propertyAmount;
        this.propertySize = propertySize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getChangePercent() {
        return changePercent;
    }

    public void setChangePercent(Double changePercent) {
        this.changePercent = changePercent;
    }

    public ArrayList<Integer> getLineData() {
        return lineData;
    }

    public void setLineData(ArrayList<Integer> lineData) {
        this.lineData = lineData;
    }

    public Double getPropertyAmount() {
        return propertyAmount;
    }

    public void setPropertyAmount(Double propertyAmount) {
        this.propertyAmount = propertyAmount;
    }

    public Double getPropertySize() {
        return propertySize;
    }

    public void setPropertySize(Double propertySize) {
        this.propertySize = propertySize;
    }
}
