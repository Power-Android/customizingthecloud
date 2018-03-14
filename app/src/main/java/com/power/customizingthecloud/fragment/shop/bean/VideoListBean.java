package com.power.customizingthecloud.fragment.shop.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/14.
 */

public class VideoListBean {

    /**
     * code : 1
     * data : [{"imgurl":"http://ssyd.oss-cn-beijing.aliyuncs.com/kitchens/201803/20180308173212.png","video_url":"http://www.170mv.com/tool/jiexi/ajax/pid/13053/vid/3155386.mp4","id":2,"title":"测试美厨2"},{"imgurl":"http://ssyd.oss-cn-beijing.aliyuncs.com/kitchens/201803/20180308173212.png","video_url":"http://www.170mv.com/tool/jiexi/ajax/pid/13053/vid/3155386.mp4","id":1,"title":"测试美厨"}]
     * message : ok
     */
    private int code;
    private List<DataEntity> data;
    private String message;

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public static class DataEntity {
        /**
         * imgurl : http://ssyd.oss-cn-beijing.aliyuncs.com/kitchens/201803/20180308173212.png
         * video_url : http://www.170mv.com/tool/jiexi/ajax/pid/13053/vid/3155386.mp4
         * id : 2
         * title : 测试美厨2
         */
        private String imgurl;
        private String video_url;
        private int id;
        private String title;
        private boolean isPlaying;//加上的

        public boolean isPlaying() {
            return isPlaying;
        }

        public void setPlaying(boolean playing) {
            isPlaying = playing;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public void setVideo_url(String video_url) {
            this.video_url = video_url;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImgurl() {
            return imgurl;
        }

        public String getVideo_url() {
            return video_url;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }
    }
}
