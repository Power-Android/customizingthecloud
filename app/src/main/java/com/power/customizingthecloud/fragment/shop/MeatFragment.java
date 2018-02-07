package com.power.customizingthecloud.fragment.shop;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseFragment;
import com.power.customizingthecloud.utils.BannerUtils;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/2/2.
 */

public class MeatFragment extends BaseFragment {
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.iv_buwei)
    ImageView mIvBuwei;
    @BindView(R.id.iv_eye)
    ImageView mIvEye;
    @BindView(R.id.iv_car)
    ImageView mIvCar;
    @BindView(R.id.recycler_meat)
    RecyclerView mRecyclerMeat;
    Unbinder unbinder;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopmeat, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initLazyData() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        BannerUtils.startBanner(mBanner, new ArrayList<String>());
        //开启在布局文件中设置的帧动画
        AnimationDrawable drawable = (AnimationDrawable) mIvEye.getDrawable();
        drawable.start();
        mRecyclerMeat.setLayoutManager(new GridLayoutManager(mContext,2));
        mRecyclerMeat.setNestedScrollingEnabled(false);
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        XianAdapter xianAdapter = new XianAdapter(R.layout.item_shengxian, list);
        mRecyclerMeat.setAdapter(xianAdapter);
    }

    private class XianAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public XianAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
