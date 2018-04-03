package com.power.customizingthecloud.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.power.customizingthecloud.fragment.market.bean.MyDongTaiBean;

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
    private MyDongTaiBean.DataEntity.FeedEntity feedEntity;

    public MyDongTaiBean.DataEntity.FeedEntity getFeedEntity() {
        return feedEntity;
    }

    public void setFeedEntity(MyDongTaiBean.DataEntity.FeedEntity feedEntity) {
        this.feedEntity = feedEntity;
    }

    public MultiItemBean(int itemType){
        this.itemType=itemType;
    }

    public MultiItemBean(int itemType, MyDongTaiBean.DataEntity.FeedEntity feedEntity) {
        this.itemType = itemType;
        this.feedEntity = feedEntity;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
