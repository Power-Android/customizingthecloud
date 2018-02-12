package com.power.customizingthecloud.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2018/2/12.
 */

public class MyBreedIncomeAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    private Context mContext;
    private int mPosition;
    private List<String> mData;

    public MyBreedIncomeAdapter(@LayoutRes int layoutResId, @Nullable List<String> data, Context context, int position) {
        super(layoutResId, data);
        mContext = context;
        mPosition = position;
        mData = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        if (mPosition == 1){//全部

        }else if (mPosition == 2){//进行中

        }else if (mPosition == 3){//已结束

        }
    }
}
