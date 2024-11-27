package cn.smxy.wallet.entity;

import java.io.Serializable;

public class Messages implements Serializable {
    private String title;
    private String contacts;
    private String time;
    private String img;
    private String author;

    public Messages(String title, String contacts, String time, String img, String author) {
        this.title = title;
        this.contacts = contacts;
        this.time = time;
        this.img = img;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
