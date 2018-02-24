package com.power.customizingthecloud.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.view.CollapsibleActionView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.RedPacketBean;
import com.power.customizingthecloud.fragment.home.GoodDetailActivity;
import com.power.customizingthecloud.fragment.shop.GoodConfirmOrderActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyRedPacketActivity extends BaseActivity implements View.OnClickListener, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private List<RedPacketBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_red_packet);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("我的红包");
        titleBackIv.setOnClickListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setNestedScrollingEnabled(false);
        list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            RedPacketBean bean = new RedPacketBean();
            bean.setName("驴奶粉");
            bean.setDate("2018.01.25-2018.02.25");
            bean.setMoney("￥20.00");
            bean.setNum("x 1");
            bean.setIsguoqi(i+"");
            list.add(bean);
        }
        MyRedPacketAdapter adapter = new MyRedPacketAdapter(R.layout.item_red_packet, list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        adapter.setOnItemChildClickListener(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        startActivity(new Intent(mContext, GoodDetailActivity.class));
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        startActivity(new Intent(mContext, GoodConfirmOrder1Activity.class));
    }

    private class MyRedPacketAdapter extends BaseQuickAdapter<RedPacketBean,BaseViewHolder>{

        public MyRedPacketAdapter(@LayoutRes int layoutResId, @Nullable List<RedPacketBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, RedPacketBean item) {
            helper.setText(R.id.item_name_tv,item.getName())
                    .setText(R.id.item_date_tv,item.getDate())
                    .setText(R.id.item_money_tv,item.getMoney())
                    .setText(R.id.item_num_tv,item.getNum())
                    .addOnClickListener(R.id.item_liji_lingqu_tv);
            TextView lijilingquTv = helper.getView(R.id.item_liji_lingqu_tv);
            ImageView yiguoqiIv = helper.getView(R.id.yi_guo_qi_iv);
            if (item.getIsguoqi().equals("1")){
                lijilingquTv.setVisibility(View.GONE);
                yiguoqiIv.setVisibility(View.VISIBLE);
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
