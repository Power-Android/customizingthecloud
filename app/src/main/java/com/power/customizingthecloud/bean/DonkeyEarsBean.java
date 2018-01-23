package com.power.customizingthecloud.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/23.
 */

public class DonkeyEarsBean implements Serializable {
    private String name;
    private String yuanjia;
    private String xianjia;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYuanjia() {
        return yuanjia;
    }

    public void setYuanjia(String yuanjia) {
        this.yuanjia = yuanjia;
    }

    public String getXianjia() {
        return xianjia;
    }

    public void setXianjia(String xianjia) {
        this.xianjia = xianjia;
    }
}
