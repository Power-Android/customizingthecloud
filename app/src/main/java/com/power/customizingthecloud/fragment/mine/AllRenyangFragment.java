package com.power.customizingthecloud.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.activity.mine.RengyangDetailActivity;
import com.power.customizingthecloud.adapter.MyRenYangAdapter;
import com.power.customizingthecloud.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/2/1.
 */

public class AllRenyangFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.recycler)
    RecyclerView mRecyclerRenyang;
    Unbinder unbinder;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_renyang, null);
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        mRecyclerRenyang.setLayoutManager(new LinearLayoutManager(mContext));
        MyRenYangAdapter renYangAdapter=new MyRenYangAdapter(R.layout.item_my_renyang,list,mContext,1);
        mRecyclerRenyang.setAdapter(renYangAdapter);
        renYangAdapter.setOnItemClickListener(this);
    }

    @Override
    protected void initLazyData() {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        startActivity(new Intent(mContext,RengyangDetailActivity.class));
    }
}
