package com.power.customizingthecloud.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.customizingthecloud.MyApplication;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.fragment.home.bean.RenYangListBean;
import com.power.customizingthecloud.fragment.home.jiankong.JianKongActivity;

import java.util.List;

/**
 * Created by Administrator on 2018/1/26.
 */

public class RenYangAdapter extends BaseQuickAdapter<RenYangListBean.DataEntity, BaseViewHolder> {
    private Context mContext;
    private int mPosition;
    private List<RenYangListBean.DataEntity> mData;

    public RenYangAdapter(@LayoutRes int layoutResId, @Nullable List<RenYangListBean.DataEntity> data, Context context, int position) {
        super(layoutResId, data);
        mContext = context;
        mPosition = position;
        mData = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, RenYangListBean.DataEntity item) {
        ProgressBar progressBar = helper.getView(R.id.progressBar);
        TextView tv_shengyu = helper.getView(R.id.tv_shengyu);
        TextView tv_state = helper.getView(R.id.tv_state);
        tv_shengyu.setText("剩余数量：" + item.getLast_amount());
        helper.setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_totalcount, "总数量：" + item.getAmount())
                .setText(R.id.tv_nianshouyi, item.getProfit())
                .setText(R.id.tv_yangzhichengben, item.getPrice())
                .setText(R.id.tv_touzizhouqi, item.getPeriod());
        ImageView iv_img = helper.getView(R.id.iv_img);
        ImageView iv_shouqing = helper.getView(R.id.iv_shouqing);
        Glide.with(MyApplication.getGloableContext()).load(item.getImage()).into(iv_img);
        int bili = item.getLast_amount() / item.getAmount();
        if (item.getState() == 2) {
            progressBar.setProgress(bili * 100);
            tv_shengyu.setTextColor(mContext.getResources().getColor(R.color.red1));
            tv_state.setText("进行中");
            tv_state.setBackgroundColor(mContext.getResources().getColor(R.color.red1));
        } else if (item.getState() == 1) {
            progressBar.setProgress(0);
            tv_state.setText("即将开始");
        } else if (item.getState() == 3) {
            progressBar.setProgress(0);
            tv_state.setText("已结束");
            tv_state.setBackgroundColor(mContext.getResources().getColor(R.color.huise));
            iv_shouqing.setVisibility(View.VISIBLE);
        }
        if (helper.getAdapterPosition() == mData.size() - 1) {
            helper.setVisible(R.id.view_line, false);
        }
        helper.getView(R.id.tv_jiankong).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, JianKongActivity.class));
            }
        });
    }
}
