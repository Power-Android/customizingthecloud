package com.power.customizingthecloud.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by Administrator on 2018/3/13.
 */

public class MultiItemBean implements MultiItemEntity {
    public final static int NOIMAGE = 0;
    public final static int ONEIMAGE = 1;
    public final static int TWOIMAGE = 2;
    public final static int THREEIMAGE = 3;
    public final static int FOURIMAGE = 4;
    private int itemType;

    public MultiItemBean(int itemType){
        this.itemType=itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
