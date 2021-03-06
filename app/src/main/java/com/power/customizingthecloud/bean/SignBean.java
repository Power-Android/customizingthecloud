package com.power.customizingthecloud.bean;

import java.io.Serializable;

/**
 * Created by power on 2018/3/22.
 */

public class SignBean implements Serializable {

    /**
     * code : 1
     * message : 签到成功
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
