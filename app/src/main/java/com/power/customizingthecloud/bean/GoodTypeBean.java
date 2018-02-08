package com.power.customizingthecloud.bean;

/**
 * Created by Administrator on 2018/2/8.
 */

public class GoodTypeBean {
    private String name;
    private boolean isChecked;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public GoodTypeBean(String name) {
        this.name = name;
    }
}
