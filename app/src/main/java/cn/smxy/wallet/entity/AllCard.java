package cn.smxy.wallet.entity;

public class AllCard {
    private String iconImg;
    private String cardNum;
    private Double totalMoney;

    public AllCard(String iconImg, String cardNum, Double totalMoney) {
        this.iconImg = iconImg;
        this.cardNum = cardNum;
        this.totalMoney = totalMoney;
    }

    public String getIconImg() {
        return iconImg;
    }

    public void setIconImg(String iconImg) {
        this.iconImg = iconImg;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }
}
