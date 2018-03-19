package com.power.customizingthecloud.fragment.market.bean;

/**
 * Created by Administrator on 2018/3/16.
 */

public class UploadPhotoBean {

    /**
     * code : 1
     * data : {"width":500,"fileurl":"feed/201803/a4oUSP94k5wqFmFJelzB.jpg","height":383}
     * message : ok
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
         * width : 500
         * fileurl : feed/201803/a4oUSP94k5wqFmFJelzB.jpg
         * height : 383
         */
        private int width;
        private String fileurl;
        private int height;

        public void setWidth(int width) {
            this.width = width;
        }

        public void setFileurl(String fileurl) {
            this.fileurl = fileurl;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public String getFileurl() {
            return fileurl;
        }

        public int getHeight() {
            return height;
        }
    }
}
