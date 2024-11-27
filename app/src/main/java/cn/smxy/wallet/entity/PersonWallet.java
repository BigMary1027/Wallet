package cn.smxy.wallet.entity;

public class PersonWallet {
    private String name;
    private String headImg;
    private String walletCad;
    private Double money;

    public PersonWallet(String name, String headImg, String walletCad, Double money) {
        this.name = name;
        this.headImg = headImg;
        this.walletCad = walletCad;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getWalletCad() {
        return walletCad;
    }

    public void setWalletCad(String walletCad) {
        this.walletCad = walletCad;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
