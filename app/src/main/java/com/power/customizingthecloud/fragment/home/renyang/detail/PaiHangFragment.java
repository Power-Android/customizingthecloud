package com.power.customizingthecloud.fragment.home.renyang.detail;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
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

public class PaiHangFragment extends BaseFragment {
    @BindView(R.id.recycler)
    RecyclerView mRecyclerRenyang;
    Unbinder unbinder;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_renyang, null);
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
        mRecyclerRenyang.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerRenyang.setNestedScrollingEnabled(false);
        PaiHangAdapter paiHangAdapter = new PaiHangAdapter(R.layout.item_paihangbang, list);
        mRecyclerRenyang.setAdapter(paiHangAdapter);
    }

    private class PaiHangAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public PaiHangAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            int adapterPosition = helper.getAdapterPosition();
            ImageView iv_pai=helper.getView(R.id.iv_pai);
            TextView tv_paixu=helper.getView(R.id.tv_paixu);
            if (adapterPosition==0){
                iv_pai.setImageResource(R.drawable.jinpai);
            }else if (adapterPosition==1){
                iv_pai.setImageResource(R.drawable.yinpai);
            }else if (adapterPosition==2){
                iv_pai.setImageResource(R.drawable.tongpai);
            }else {
                iv_pai.setVisibility(View.GONE);
                tv_paixu.setVisibility(View.VISIBLE);
                tv_paixu.setText(adapterPosition+1+"");
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
