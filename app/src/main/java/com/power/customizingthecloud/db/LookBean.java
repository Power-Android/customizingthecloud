package com.power.customizingthecloud.db;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/12/28.
 */

public class LookBean extends DataSupport{
    private int id;
    private String class_name;
    private String name;
    private String image;
    private String price;
    private long time;//用时间戳来控制排列顺序

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
