package com.power.customizingthecloud.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/2/24.
 */

public class IssuesListBean implements Serializable {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
