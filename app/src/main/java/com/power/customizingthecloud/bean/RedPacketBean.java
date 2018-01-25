package com.power.customizingthecloud.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/25.
 */

public class RedPacketBean implements Serializable {
    private String name;
    private String date;
    private String money;
    private String num;
    private String isguoqi;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getIsguoqi() {
        return isguoqi;
    }

    public void setIsguoqi(String isguoqi) {
        this.isguoqi = isguoqi;
    }
}
