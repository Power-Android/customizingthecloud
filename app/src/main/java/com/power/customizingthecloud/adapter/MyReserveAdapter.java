package com.power.customizingthecloud.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.fragment.home.ShopDetailActivity;
import com.power.customizingthecloud.fragment.home.jiankong.JianKongActivity;
import com.power.customizingthecloud.utils.TUtils;

import java.util.List;

/**
 * Created by Administrator on 2018/2/5.
 */

public class MyReserveAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    private Context mContext;
    private int mPosition;
    private List<String> mData;

    public MyReserveAdapter(@LayoutRes int layoutResId, @Nullable List<String> data, Context context, int position) {
        super(layoutResId, data);
        mContext = context;
        mPosition = position;
        mData = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView waitIv = helper.getView(R.id.item_wait_iv);
        ImageView successIv = helper.getView(R.id.item_success_iv);
        ImageView filedIv = helper.getView(R.id.item_filed_iv);
        helper.addOnClickListener(R.id.content_rl)
                .addOnClickListener(R.id.shachu_tv);

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
