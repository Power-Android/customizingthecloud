package com.power.customizingthecloud.activity.mine;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.ShopcartBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopCartActivity extends BaseActivity implements View.OnClickListener, BaseQuickAdapter.OnItemChildClickListener {

    @BindView(R.id.title_back_iv) ImageView titleBackIv;
    @BindView(R.id.title_content_tv) TextView titleContentTv;
    @BindView(R.id.title_content_right_tv) TextView titleContentRightTv;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.all_checkBox) CheckBox allCheckBox;
    @BindView(R.id.all_check_ll) LinearLayout allCheckLl;
    @BindView(R.id.heji_tv) TextView hejiTv;
    @BindView(R.id.jiesuan_tv) TextView jiesuanTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_cart);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("购物车");
        titleContentRightTv.setVisibility(View.VISIBLE);
        titleContentRightTv.setText("管理");
        titleBackIv.setOnClickListener(this);
        titleContentRightTv.setOnClickListener(this);

        List<ShopcartBean> list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            ShopcartBean bean = new ShopcartBean();
            bean.setName("驴奶粉");
            bean.setFenlei("商品分类：驴奶粉");
            bean.setMoney("￥99.00");
            list.add(bean);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setNestedScrollingEnabled(false);
        ShopCartAdapter adapter = new ShopCartAdapter(R.layout.item_shop_cart,list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemChildClickListener(this);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    private class ShopCartAdapter extends BaseQuickAdapter<ShopcartBean,BaseViewHolder>{

        public ShopCartAdapter(@LayoutRes int layoutResId, @Nullable List<ShopcartBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ShopcartBean item) {

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.title_content_right_tv:
                break;
            case R.id.all_check_ll:
                break;
            case R.id.jiesuan_tv:
                break;
        }
    }
}
