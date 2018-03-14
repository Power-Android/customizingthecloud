package com.power.customizingthecloud.bean;

/**
 * Created by Administrator on 2018/3/3.
 */

public class PlayerBean {

    /**
     * image : http://ssyd.oss-cn-beijing.aliyuncs.com/restaurants/201802/20180227753.png
     * video_url : http://www.170mv.com/tool/jiexi/ajax/pid/13053/vid/3155386.mp4
     * class_id : 2
     * id : 2
     * title : 毛驴运动B区
     */
    private String image;
    private String video_url;
    private int class_id;
    private int id;
    private String title;

    public void setImage(String image) {
        this.image = image;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public String getVideo_url() {
        return video_url;
    }

    public int getClass_id() {
        return class_id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
