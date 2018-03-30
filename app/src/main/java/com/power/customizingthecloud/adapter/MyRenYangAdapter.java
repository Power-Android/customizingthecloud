package com.power.customizingthecloud.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.bean.MyRenyangBean;
import com.power.customizingthecloud.fragment.home.jiankong.JianKongActivity;

import java.util.List;

/**
 * Created by Administrator on 2018/2/1.
 */

public class MyRenYangAdapter extends BaseQuickAdapter<MyRenyangBean.DataBean,BaseViewHolder> {

    private Context mContext;
    private int mPosition;
    private List<MyRenyangBean.DataBean> mData;

    public MyRenYangAdapter(@LayoutRes int layoutResId, @Nullable List<MyRenyangBean.DataBean> data, Context context, int position) {
        super(layoutResId, data);
        mContext = context;
        mPosition = position;
        mData = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, MyRenyangBean.DataBean item) {

        if (mPosition == 1){//全部

        }else if (mPosition == 2){//进行中

        }else if (mPosition == 3){//已结束

        }

        helper.setText(R.id.tv_state, item.getTitle())
                .setText(R.id.start_tv, "开始养殖："+item.getPayment_time())
                .setText(R.id.end_tv, "结束养殖："+item.getEnd_time())
                .setText(R.id.nianshouyi_tv,item.getProfit())
                .setText(R.id.yangzhichengben_tv,item.getPrice())
                .setText(R.id.yangzhizhouqi_tv,item.getPeriod())
                .setText(R.id.yangzhishouyi_tv,item.getIncome());

        helper.getView(R.id.tv_jiankong).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext,JianKongActivity.class));
            }
        });
    }
}
