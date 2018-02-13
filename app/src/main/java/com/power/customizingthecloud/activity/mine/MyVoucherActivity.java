package com.power.customizingthecloud.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.MyVoucherBean;
import com.power.customizingthecloud.fragment.home.GoodListActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyVoucherActivity extends BaseActivity implements View.OnClickListener, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private List<MyVoucherBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_voucher);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("我的代金券");
        titleBackIv.setOnClickListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setNestedScrollingEnabled(false);

        list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            MyVoucherBean bean = new MyVoucherBean();
            bean.setMoney("20");
            bean.setManjian("满￥199使用");
            bean.setName("驴奶粉代金券");
            bean.setUse("仅限购物使用");
            bean.setDate("使用期限：2018.01.25-2018.02.25");
            bean.setIsguoqi(i+"");
            list.add(bean);
        }
        MyVoucherAdapter adapter = new MyVoucherAdapter(R.layout.item_my_voucher, list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (!list.get(position).getIsguoqi().equals("1")){
            startActivity(new Intent(mContext, GoodListActivity.class));
        }
    }

    private class MyVoucherAdapter extends BaseQuickAdapter<MyVoucherBean,BaseViewHolder>{

        public MyVoucherAdapter(@LayoutRes int layoutResId, @Nullable List<MyVoucherBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, MyVoucherBean item) {
            helper.setText(R.id.item_money_tv,item.getMoney())
                    .setText(R.id.item_man_jian_tv,item.getManjian())
                    .setText(R.id.item_name_tv,item.getName())
                    .setText(R.id.item_use_tv,item.getUse())
                    .setText(R.id.item_date_tv,item.getDate());
            if (item.getIsguoqi().equals("1")){
                helper.getView(R.id.yi_guo_qi_iv).setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_back_iv:
                finish();
                break;
        }
    }
}
