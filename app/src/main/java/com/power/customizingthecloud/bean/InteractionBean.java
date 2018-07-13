package com.power.customizingthecloud.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/2/1.
 */

public class InteractionBean implements Serializable {

    /**
     * code : 1
     * message : ok
     * data : [{"id":4,"user_avatar":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJJ2L9ckXsEY3ia5bYN1ZEljUG8d4u3wxoB9cFoHWeXtbWod8X8ribL5Pia8x43zBGvcde2ARic1icibMDA/0","user_name":"啊啊","body":"赞同","image":"http://ssyd.oss-cn-beijing.aliyuncs.com/feed/201803/1645646.jpg"}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 4
         * user_avatar : https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJJ2L9ckXsEY3ia5bYN1ZEljUG8d4u3wxoB9cFoHWeXtbWod8X8ribL5Pia8x43zBGvcde2ARic1icibMDA/0
         * user_name : 啊啊
         * body : 赞同
         * image : http://ssyd.oss-cn-beijing.aliyuncs.com/feed/201803/1645646.jpg
         */

        private int id;
        private int feed_id;
        private String user_avatar;
        private String user_name;
        private String body;
        private String image;

        public int getFeed_id() {
            return feed_id;
        }

        public void setFeed_id(int feed_id) {
            this.feed_id = feed_id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUser_avatar() {
            return user_avatar;
        }

        public void setUser_avatar(String user_avatar) {
            this.user_avatar = user_avatar;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
