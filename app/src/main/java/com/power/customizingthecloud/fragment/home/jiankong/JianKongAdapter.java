package com.power.customizingthecloud.fragment.home.jiankong;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.bean.EventBean;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by Administrator on 2018/1/26.
 */

public class JianKongAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private Context mContext;
    private int mPosition;
    private List<String> mData;

    public JianKongAdapter(@LayoutRes int layoutResId, @Nullable List<String> data, Context context, int position) {
        super(layoutResId, data);
        mContext = context;
        mPosition = position;
        mData = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_jiankong, "五号楼前");
    }

    @Override
    public void setOnItemClickListener(@Nullable OnItemClickListener listener) {
        super.setOnItemClickListener(listener);
        EventBus.getDefault().postSticky(new EventBean("player"));
    }
}
