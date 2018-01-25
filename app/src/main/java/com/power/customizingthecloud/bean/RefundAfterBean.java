package com.power.customizingthecloud.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/25.
 */

public class RefundAfterBean implements Serializable {
    private String name;
    private String feilei;
    private String money;
    private String num;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFeilei() {
        return feilei;
    }

    public void setFeilei(String feilei) {
        this.feilei = feilei;
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
}
