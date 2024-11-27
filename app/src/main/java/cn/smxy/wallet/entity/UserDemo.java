package cn.smxy.wallet.entity;

public class UserDemo {
    private String headImg;
    private String name;
    private String contacts;

    public UserDemo(String headImg, String name, String contacts) {
        this.headImg = headImg;
        this.name = name;
        this.contacts = contacts;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }
}
