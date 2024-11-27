package cn.smxy.wallet.entity;

import java.io.Serializable;

public class CardList implements Serializable {
    private int id;
    private String name;
    private String cardNumber;
    private String phone;
    private String img;
    private float money;

    public CardList() {
    }

    public CardList(int id, String name, String cardNumber, String phone, String img) {
        this.id = id;
        this.name = name;
        this.cardNumber = cardNumber;
        this.phone = phone;
        this.img = img;
    }

    public CardList(String cardNumber, String img) {
        this.cardNumber = cardNumber;
        this.img = img;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
