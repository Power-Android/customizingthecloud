package com.power.customizingthecloud.bean;

/**
 * Created by Administrator on 2018/7/20.
 */

public class VideoDetailBean {

    /**
     * code : 1
     * data : {"imgurl":"http://ssyd.oss-cn-beijing.aliyuncs.com/kitchens/201803/20180308173212.png","video_url":"http://www.170mv.com/tool/jiexi/ajax/pid/13053/vid/3155386.mp4","updated_at":"2018-03-08 03:06:09","created_at":"2018-03-08 03:06:09","id":2,"title":"测试美厨2","body":"测试美厨测试美厨测试美厨"}
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
         * imgurl : http://ssyd.oss-cn-beijing.aliyuncs.com/kitchens/201803/20180308173212.png
         * video_url : http://www.170mv.com/tool/jiexi/ajax/pid/13053/vid/3155386.mp4
         * updated_at : 2018-03-08 03:06:09
         * created_at : 2018-03-08 03:06:09
         * id : 2
         * title : 测试美厨2
         * body : 测试美厨测试美厨测试美厨
         */
        private String imgurl;
        private String video_url;
        private String updated_at;
        private String created_at;
        private int id;
        private String title;
        private String body;

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public void setVideo_url(String video_url) {
            this.video_url = video_url;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getImgurl() {
            return imgurl;
        }

        public String getVideo_url() {
            return video_url;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public String getCreated_at() {
            return created_at;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getBody() {
            return body;
        }
    }
}
