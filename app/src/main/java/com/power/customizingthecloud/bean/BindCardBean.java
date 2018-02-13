package com.power.customizingthecloud.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/2/13.
 */

public class BindCardBean implements Serializable {
    private String name;
    private String cardNum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }
}
