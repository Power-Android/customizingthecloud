package com.power.customizingthecloud.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2018/2/23.
 */

public class ProductListAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    private Context mContext;
    private int mPosition;
    private List<String> mData;

    public ProductListAdapter(@LayoutRes int layoutResId, @Nullable List<String> data, Context context, int position) {
        super(layoutResId, data);
        mContext = context;
        mPosition = position;
        mData = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        if (mPosition == 1){//商品

        }else if (mPosition == 2){//毛驴

        }
    }
}
