package com.power.customizingthecloud.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.bean.ProfitDetailBean;

import java.util.List;

/**
 * Created by Administrator on 2018/2/12.
 */

public class MyBreedIncomeAdapter extends BaseQuickAdapter<ProfitDetailBean.DataEntity, BaseViewHolder> {

    private Context mContext;
    private int mPosition;
    private List<ProfitDetailBean.DataEntity> mData;

    public MyBreedIncomeAdapter(@LayoutRes int layoutResId, @Nullable List<ProfitDetailBean.DataEntity> data, Context context, int position) {
        super(layoutResId, data);
        mContext = context;
        mPosition = position;
        mData = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, ProfitDetailBean.DataEntity item) {
        if (mPosition == 1) {//全部
            helper.setText(R.id.item_date_tv, item.getCreated_at())
                    .setText(R.id.item_content_tv, item.getNote());
        } else if (mPosition == 2) {//进行中

        } else if (mPosition == 3) {//已结束

        }
    }
}
