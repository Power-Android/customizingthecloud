package com.power.customizingthecloud.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.bean.ReserveBean;
import com.power.customizingthecloud.fragment.home.ShopDetailActivity;
import com.power.customizingthecloud.fragment.home.jiankong.JianKongActivity;
import com.power.customizingthecloud.utils.TUtils;

import java.util.List;

/**
 * Created by Administrator on 2018/2/5.
 */

public class MyReserveAdapter extends BaseQuickAdapter<ReserveBean.DataBeanX.DataBean,BaseViewHolder> {

    private Context mContext;
    private int mPosition;
    private List<ReserveBean.DataBeanX.DataBean> mData;

    public MyReserveAdapter(@LayoutRes int layoutResId, @Nullable List<ReserveBean.DataBeanX.DataBean> data, Context context, int position) {
        super(layoutResId, data);
        mContext = context;
        mPosition = position;
        mData = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, ReserveBean.DataBeanX.DataBean item) {
        ImageView waitIv = helper.getView(R.id.item_wait_iv);
        ImageView successIv = helper.getView(R.id.item_success_iv);
        ImageView filedIv = helper.getView(R.id.item_filed_iv);
        ImageView faceIv = helper.getView(R.id.item_face_iv);
        TextView titleTv = helper.getView(R.id.item_name_tv);
        TextView timeTv = helper.getView(R.id.item_time_tv);
        TextView addressTv = helper.getView(R.id.item_address_tv);
        helper.addOnClickListener(R.id.content_rl)
                .addOnClickListener(R.id.shachu_tv);

        Glide.with(mContext).load(item.getImgurl()).into(faceIv);
        titleTv.setText(item.getName());
        timeTv.setText("预约时间：" + item.getRestaurant_time());
        addressTv.setText(item.getPosition());

        if (mPosition == 1){//待确认
        }else if (mPosition == 2){//预订成功
            waitIv.setVisibility(View.GONE);
            successIv.setVisibility(View.VISIBLE);
        }else if (mPosition == 3){//预订失败
            waitIv.setVisibility(View.GONE);
            successIv.setVisibility(View.GONE);
            filedIv.setVisibility(View.VISIBLE);
        }

    }
}
