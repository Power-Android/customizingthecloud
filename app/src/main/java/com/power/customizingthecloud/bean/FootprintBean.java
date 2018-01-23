package com.power.customizingthecloud.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/23.
 */

public class FootprintBean implements Serializable {
    private String name;
    private String money;
    private String xiaoliang;
    private String pingjia;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getXiaoliang() {
        return xiaoliang;
    }

    public void setXiaoliang(String xiaoliang) {
        this.xiaoliang = xiaoliang;
    }

    public String getPingjia() {
        return pingjia;
    }

    public void setPingjia(String pingjia) {
        this.pingjia = pingjia;
    }
}
