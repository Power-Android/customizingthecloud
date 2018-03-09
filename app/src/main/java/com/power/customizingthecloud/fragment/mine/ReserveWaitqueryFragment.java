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
import com.power.customizingthecloud.activity.mine.ReserveDetailActivity;
import com.power.customizingthecloud.adapter.MyRenYangAdapter;
import com.power.customizingthecloud.adapter.MyReserveAdapter;
import com.power.customizingthecloud.base.BaseFragment;
import com.power.customizingthecloud.fragment.home.GoodDetailActivity;
import com.power.customizingthecloud.fragment.home.ShopDetailActivity;
import com.power.customizingthecloud.utils.TUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/2/5.
 */

public class ReserveWaitqueryFragment extends BaseFragment implements BaseQuickAdapter.OnItemChildClickListener {
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
        MyReserveAdapter adapter = new MyReserveAdapter(R.layout.item_my_reserve,list,mContext,1);
        mRecyclerRenyang.setAdapter(adapter);
        adapter.setOnItemChildClickListener(this);
    }

    @Override
    protected void initLazyData() {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()){
            case R.id.content_rl:
                startActivity(new Intent(mContext, ReserveDetailActivity.class));
                break;
            case R.id.shachu_tv:
                TUtils.showShort(mContext,"点击了---删除"+position);
                break;
        }
    }
}
