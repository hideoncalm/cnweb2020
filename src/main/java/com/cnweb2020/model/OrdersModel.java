package com.cnweb2020.model;

import java.sql.Timestamp;

public class OrdersModel {
    private int id;
    private int userId;
    private int type;
    private Timestamp time;
    private String address;
    private String phone;
    private String personTakeOrder;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPersonTakeOrder() {
        return personTakeOrder;
    }

    public void setPersonTakeOrder(String personTakeOrder) {
        this.personTakeOrder = personTakeOrder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
