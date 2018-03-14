package com.power.customizingthecloud.fragment.home.jiankong;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.customizingthecloud.MyApplication;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.bean.PlayerBean;
import com.power.customizingthecloud.fragment.home.bean.MuChangListBean;
import com.power.customizingthecloud.utils.MyUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by Administrator on 2018/1/26.
 */

public class JianKongAdapter extends BaseQuickAdapter<MuChangListBean.DataEntity, BaseViewHolder> {
    private Context mContext;
    private List<MuChangListBean.DataEntity> mData;

    public JianKongAdapter(@LayoutRes int layoutResId, @Nullable List<MuChangListBean.DataEntity> data, Context context) {
        super(layoutResId, data);
        mContext = context;
        mData = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, final MuChangListBean.DataEntity item) {
        helper.setText(R.id.tv_jiankong, item.getTitle());
        final ImageView iv_top = helper.getView(R.id.iv_jiankong);
        int width = MyUtils.getScreenWidth(mContext) - MyUtils.dip2px(mContext, 50);
        ViewGroup.LayoutParams layoutParams = iv_top.getLayoutParams();
        layoutParams.height = width / 3;
        iv_top.setLayoutParams(layoutParams);
        iv_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerBean playerBean = new PlayerBean();
                playerBean.setImage(item.getImage());
                playerBean.setVideo_url(item.getVideo_url());
                EventBus.getDefault().postSticky(playerBean);
            }
        });
        Glide.with(MyApplication.getGloableContext()).load(item.getImage()).into(iv_top);
    }

    /*这个回调不调用是怎么回事*/
    @Override
    public void setOnItemClickListener(@Nullable OnItemClickListener listener) {
        super.setOnItemClickListener(listener);
//        EventBus.getDefault().postSticky(new EventBean("player"));
    }
}
