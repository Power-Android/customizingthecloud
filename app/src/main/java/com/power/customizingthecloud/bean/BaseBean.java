package com.power.customizingthecloud.bean;

import java.io.Serializable;

/**
 * Created by power on 2018/3/23.
 */

public class BaseBean implements Serializable {

    /**
     * code : 1
     * message : 添加成功
     * data : []
     */

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
