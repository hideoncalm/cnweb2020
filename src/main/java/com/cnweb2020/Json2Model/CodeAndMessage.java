package com.cnweb2020.Json2Model;

public class CodeAndMessage {

    public static final int SUCCESS = 200;
    public static final int DATA_INVALID = 401;
    public static final int DATA_DUPLICATED = 402;
    public static final int DATA_NOTFOUND = 404;

    public static final String SUCCESS_MESSAGE = "Success";
    public static final String DATA_INVALID_USERNAME = "User name is invalid";
    public static final String DATA_INVALID_PASSWORD = "Password is invalid ";
    public static final String DATA_INVALID_PHONE = "Phone is invalid";
    public static final String DATA_INVALID_EMAIL = "Email is invalid";
    public static final String DATA_DUPLICATED_USERNAME = "Username has been used";
    public static final String DATA_DUPLICATED_PHONE = "Phone has been used";
    public static final String DATA_NOTFOUND_USERNAME = "Username is not found";
    public static final String DATA_NOTFOUND_PASSWORD = "Password is incorrect";
    public static final String DATA_NOTFOUND_USERID = "User ID not found";
    public static final String DATA_NOTFOUND_CATEGORY = "Category not found";
    public static final String DATA_NOTFOUND_PRODUCT = "Product not found";
    public static final String DATA_NOTFOUND_PRODUCTINCART = "Product is not exit in cart";

    private int code;
    private String message;

    public CodeAndMessage(int code, String message) {
        this.code = code;
        this.message = message;
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
}
