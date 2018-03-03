package com.power.customizingthecloud.bean;

/**
 * Created by Administrator on 2018/3/3.
 */

public class PlayerBean {
    private String title;
    private String content;
    private boolean isPlaying;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public PlayerBean(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
