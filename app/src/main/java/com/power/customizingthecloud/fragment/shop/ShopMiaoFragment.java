package com.power.customizingthecloud.fragment.shop;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

public class ShopMiaoFragment extends BaseFragment {
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.recycler_miao)
    RecyclerView mRecyclerMiao;
    Unbinder unbinder;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopmiao, null);
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
        mRecyclerMiao.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerMiao.setNestedScrollingEnabled(false);
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        MiaoShaAdapter miaoShaAdapter = new MiaoShaAdapter(R.layout.item_home_miaosha, list);
        mRecyclerMiao.setAdapter(miaoShaAdapter);
    }

    private class MiaoShaAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public MiaoShaAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            TextView tv_yuanjia = helper.getView(R.id.tv_yuanjia);
            //添加删除线
            tv_yuanjia.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
