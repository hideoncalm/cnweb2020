package com.cnweb2020.Json2Model;


public class UserJsonModel extends CodeAndMessage{

    private String userId;
    private String userName;

    public UserJsonModel(int code, String message, String userId) {
        super(code, message);
       this.userId = userId;
    }

    public UserJsonModel(int code, String message) {
        super(code, message);
    }
    public UserJsonModel(int code, String message, String userId, String userName){
        super(code, message);
        this.userId = userId;
        this.userName = userName;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
