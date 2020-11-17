package com.cnweb2020.Json2Model;


public class UserJsonModel{

    private String userId;
    private int code;
    private String message;
    private String userName;

    public UserJsonModel(int code, String message, String userId) {
        this.userId = userId;
        this.code = code;
        this.message = message;
    }

    public UserJsonModel(int code, String message) {
        this.code = code;
        this.message = message;
    }
    public UserJsonModel(int code, String message, String userId, String userName){
        this.code = code;
        this.message = message;
        this.userId = userId;
        this.userName = userName;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
