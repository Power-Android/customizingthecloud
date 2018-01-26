package com.power.customizingthecloud.fragment.home.jiankong;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/1/26.
 */

public class TwoQuFragment extends BaseFragment {
    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    Unbinder unbinder;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jiankong, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initLazyData() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        mRecycler.setLayoutManager(new GridLayoutManager(mContext,3));
        JianKongAdapter jianKongAdapter=new JianKongAdapter(R.layout.item_jiankong,list,mContext,3);
        mRecycler.setAdapter(jianKongAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
