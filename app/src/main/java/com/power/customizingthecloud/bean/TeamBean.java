package com.power.customizingthecloud.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/2/6.
 */

public class TeamBean implements Serializable {
    private String name;
    private String gongxian;
    private String date;
    private String yongjin;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getYongjin() {
        return yongjin;
    }

    public void setYongjin(String yongjin) {
        this.yongjin = yongjin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGongxian() {
        return gongxian;
    }

    public void setGongxian(String gongxian) {
        this.gongxian = gongxian;
    }
}
