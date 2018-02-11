package com.power.customizingthecloud.fragment.home.jiankong;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.bean.EventBean;
import com.power.customizingthecloud.utils.MyUtils;

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
        ImageView iv_top=helper.getView(R.id.iv_jiankong);
        int width = MyUtils.getScreenWidth(mContext) - MyUtils.dip2px(mContext, 50);
        ViewGroup.LayoutParams layoutParams = iv_top.getLayoutParams();
        layoutParams.height=width/3;
        iv_top.setLayoutParams(layoutParams);
    }

    @Override
    public void setOnItemClickListener(@Nullable OnItemClickListener listener) {
        super.setOnItemClickListener(listener);
        EventBus.getDefault().postSticky(new EventBean("player"));
    }
}
