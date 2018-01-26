package com.power.customizingthecloud.fragment.home.renyang;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.customizingthecloud.R;

import java.util.List;

/**
 * Created by Administrator on 2018/1/26.
 */

public class RenYangAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private Context mContext;
    private int mPosition;
    private List<String> mData;

    public RenYangAdapter(@LayoutRes int layoutResId, @Nullable List<String> data, Context context, int position) {
        super(layoutResId, data);
        mContext = context;
        mPosition = position;
        mData = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ProgressBar progressBar = helper.getView(R.id.progressBar);
        TextView tv_shengyu = helper.getView(R.id.tv_shengyu);
        TextView tv_state = helper.getView(R.id.tv_state);
        ImageView iv_shouqing = helper.getView(R.id.iv_shouqing);
        if (mPosition == 1) {//全部
            if (helper.getAdapterPosition() == 2) {
                progressBar.setProgress(80);
                tv_shengyu.setText("剩余数量：6头");
                tv_shengyu.setTextColor(Color.parseColor("#ea4436"));
                tv_state.setText("进行中");
                tv_state.setBackgroundColor(Color.parseColor("#ea4436"));
            } else if (helper.getAdapterPosition() == 1) {
                iv_shouqing.setVisibility(View.VISIBLE);
                tv_state.setText("已结束");
                tv_state.setBackgroundColor(Color.parseColor("#F5F5F5"));
            }
        } else if (mPosition == 2) {//进行中
            progressBar.setProgress(80);
            tv_shengyu.setText("剩余数量：6头");
            tv_shengyu.setTextColor(Color.parseColor("#ea4436"));
            tv_state.setText("进行中");
                tv_state.setBackgroundColor(Color.parseColor("#ea4436"));
        } else if (mPosition == 3) {//即将开始

        } else if (mPosition == 4) {//已结束
            iv_shouqing.setVisibility(View.VISIBLE);
            tv_state.setText("已结束");
            tv_state.setBackgroundColor(Color.parseColor("#F5F5F5"));
        }
        if (helper.getAdapterPosition() == 2) {
            helper.setVisible(R.id.view_line, false);
        }
    }
}
