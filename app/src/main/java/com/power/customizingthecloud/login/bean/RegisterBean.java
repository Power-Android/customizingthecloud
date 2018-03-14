package com.power.customizingthecloud.login.bean;

/**
 * Created by Administrator on 2018/3/13.
 */

public class RegisterBean {

    /**
     * code : 1
     * message : 注册成功
     */
    private int code;
    private String message;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
