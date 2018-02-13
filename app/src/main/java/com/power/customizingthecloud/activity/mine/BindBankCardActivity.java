package com.power.customizingthecloud.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.BindCardBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BindBankCardActivity extends BaseActivity implements View.OnClickListener, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.add_bank_rl)
    RelativeLayout addBankRl;
    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_bank_card);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleBackIv.setOnClickListener(this);
        titleContentTv.setText("银行卡管理");
        addBankRl.setOnClickListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setNestedScrollingEnabled(false);

        List<BindCardBean> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            BindCardBean bean = new BindCardBean();
            bean.setName("中国各种银行");
            bean.setCardNum("6220 **** **** 666" + i);
            list.add(bean);
        }
        BindCardAdapter adapter = new BindCardAdapter(R.layout.item_bind_cart,list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        startActivity(new Intent(mContext,BindCardDetailActivity.class));
    }

    private class BindCardAdapter extends BaseQuickAdapter<BindCardBean,BaseViewHolder>{

        public BindCardAdapter(@LayoutRes int layoutResId, @Nullable List<BindCardBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, BindCardBean item) {
            ImageView picIv = helper.getView(R.id.item_pic_iv);
            TextView nameTv = helper.getView(R.id.item_name_tv);
            TextView typeTv = helper.getView(R.id.item_type_tv);
            TextView numTv = helper.getView(R.id.item_num_tv);

            if (helper.getAdapterPosition() == 1) Glide.with(mContext).load(R.drawable.zhongxin_iv).into(picIv);
            nameTv.setText(item.getName());
            numTv.setText(item.getCardNum());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.add_bank_rl:
                Intent intent = new Intent(mContext,TixianSecondActivity.class);
                intent.putExtra("type","addCard");
                startActivity(intent);
                break;
        }
    }
}
