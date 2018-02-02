package com.power.customizingthecloud.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/2/1.
 */

public class InteractionBean implements Serializable {
    private String name;
    private String content;
    private String isPic;

    public String getIsPic() {
        return isPic;
    }

    public void setIsPic(String isPic) {
        this.isPic = isPic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
