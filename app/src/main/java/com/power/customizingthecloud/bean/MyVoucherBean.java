package com.power.customizingthecloud.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/25.
 */

public class MyVoucherBean implements Serializable {
    private String money;
    private String manjian;
    private String name;
    private String use;
    private String date;
    private String isguoqi;

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getManjian() {
        return manjian;
    }

    public void setManjian(String manjian) {
        this.manjian = manjian;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIsguoqi() {
        return isguoqi;
    }

    public void setIsguoqi(String isguoqi) {
        this.isguoqi = isguoqi;
    }
}
