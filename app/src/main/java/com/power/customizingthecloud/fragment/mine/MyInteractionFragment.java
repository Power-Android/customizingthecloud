package com.power.customizingthecloud.fragment.mine;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/1/26.
 */

public class MyInteractionFragment extends BaseFragment {
    @BindView(R.id.pinglun_tv)
    TextView pinglunTv;
    @BindView(R.id.huifu_tv)
    TextView huifuTv;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(mContext, R.layout.fragment_interraction, null);
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {

    }

    @Override
    protected void initLazyData() {

    }

    @OnClick(R.id.pinglun_tv)
    public void pinglun(){
        pinglunTv.setTextColor(mContext.getResources().getColor(R.color.green));
        huifuTv.setTextColor(mContext.getResources().getColor(R.color.text_black));
    }

    @OnClick(R.id.huifu_tv)
    public void huifu(){
        huifuTv.setTextColor(mContext.getResources().getColor(R.color.green));
        pinglunTv.setTextColor(mContext.getResources().getColor(R.color.text_black));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
