package com.power.customizingthecloud.bean;

import java.io.Serializable;

/**
 * Created by power on 2018/3/28.
 */

public class EditInfoBean implements Serializable {

    /**
     * code : 1
     * message : ok
     * data : {"user_name":"啊啊","user_avatar":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJJ2L9ckXsEY3ia5bYN1ZEljUG8d4u3wxoB9cFoHWeXtbWod8X8ribL5Pia8x43zBGvcde2ARic1icibMDA/0","user_sex":1,"user_age":29,"user_areaid":2,"user_cityid":3,"user_provinceid":4,"user_areainfo":"北京市 直辖区 东城区"}
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
         * user_name : 啊啊
         * user_avatar : https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJJ2L9ckXsEY3ia5bYN1ZEljUG8d4u3wxoB9cFoHWeXtbWod8X8ribL5Pia8x43zBGvcde2ARic1icibMDA/0
         * user_sex : 1
         * user_age : 29
         * user_areaid : 2
         * user_cityid : 3
         * user_provinceid : 4
         * user_areainfo : 北京市 直辖区 东城区
         */

        private String user_name;
        private String user_avatar;
        private int user_sex;
        private int user_age;
        private int user_areaid;
        private int user_cityid;
        private int user_provinceid;
        private String user_areainfo;

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getUser_avatar() {
            return user_avatar;
        }

        public void setUser_avatar(String user_avatar) {
            this.user_avatar = user_avatar;
        }

        public int getUser_sex() {
            return user_sex;
        }

        public void setUser_sex(int user_sex) {
            this.user_sex = user_sex;
        }

        public int getUser_age() {
            return user_age;
        }

        public void setUser_age(int user_age) {
            this.user_age = user_age;
        }

        public int getUser_areaid() {
            return user_areaid;
        }

        public void setUser_areaid(int user_areaid) {
            this.user_areaid = user_areaid;
        }

        public int getUser_cityid() {
            return user_cityid;
        }

        public void setUser_cityid(int user_cityid) {
            this.user_cityid = user_cityid;
        }

        public int getUser_provinceid() {
            return user_provinceid;
        }

        public void setUser_provinceid(int user_provinceid) {
            this.user_provinceid = user_provinceid;
        }

        public String getUser_areainfo() {
            return user_areainfo;
        }

        public void setUser_areainfo(String user_areainfo) {
            this.user_areainfo = user_areainfo;
        }
    }
}
