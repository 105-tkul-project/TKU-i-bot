package com.example.myapplication;

public class Chat {
    private int user;
    private String message;

    // 建構子(存資料)
    Chat(int user, String message) {
        this.message = message;
        this.user = user;
    }

    // 建構子(預設)
    public Chat() {
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // 取資料
    public String getMessage() {
        return message;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }
}
