package com.power.customizingthecloud.bean;

import java.io.Serializable;

/**
 * Created by power on 2018/3/27.
 */

public class PicBean implements Serializable {

    /**
     * code : 1
     * message : ok
     * data : {"fileurl":"feed/201803/a4oUSP94k5wqFmFJelzB.jpg","width":500,"height":383}
     */

    private int code;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * fileurl : feed/201803/a4oUSP94k5wqFmFJelzB.jpg
         * width : 500
         * height : 383
         */

        private String fileurl;
        private int width;
        private int height;

        public String getFileurl() {
            return fileurl;
        }

        public void setFileurl(String fileurl) {
            this.fileurl = fileurl;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }
}
