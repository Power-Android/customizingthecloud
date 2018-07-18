package com.power.customizingthecloud.fragment.mine;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
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
 * Created by Administrator on 2018/2/12.
 */

public class OverBreedIncomeFragment extends BaseFragment {

    @BindView(R.id.recycler)
    RecyclerView mRecyclerRenyang;
    Unbinder unbinder;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_breed_income, null);
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
//        MyBreedIncomeAdapter breedIncomeAdapter=new MyBreedIncomeAdapter(R.layout.item_breed_income,list,mContext,3);
//        mRecyclerRenyang.setAdapter(breedIncomeAdapter);
    }

    @Override
    protected void initLazyData() {

    }
}
