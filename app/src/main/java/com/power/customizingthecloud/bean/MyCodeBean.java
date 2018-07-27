package com.power.customizingthecloud.bean;

/**
 * Created by Administrator on 2018/7/27.
 */

public class MyCodeBean {

    /**
     * code : 1
     * data : {"image":"http://39.107.91.92:84/qrcodes/35.png","inviter_code":"10000035"}
     * message : 请求成功
     */
    private int code;
    private DataEntity data;
    private String message;

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public DataEntity getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public static class DataEntity {
        /**
         * image : http://39.107.91.92:84/qrcodes/35.png
         * inviter_code : 10000035
         */
        private String image;
        private String inviter_code;

        public void setImage(String image) {
            this.image = image;
        }

        public void setInviter_code(String inviter_code) {
            this.inviter_code = inviter_code;
        }

        public String getImage() {
            return image;
        }

        public String getInviter_code() {
            return inviter_code;
        }
    }
}
