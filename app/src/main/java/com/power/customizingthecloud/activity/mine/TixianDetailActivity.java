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
import com.power.customizingthecloud.bean.TixianDetailBean;
import com.power.customizingthecloud.utils.TUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TixianDetailActivity extends BaseActivity implements View.OnClickListener, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tixian_detail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleBackIv.setOnClickListener(this);
        titleContentTv.setText("提现明细");

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setNestedScrollingEnabled(false);

        List<TixianDetailBean> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            TixianDetailBean bean = new TixianDetailBean();
            bean.setDate("2018.02.1"+i);
            bean.setContent("您向招商银行提现100元");
            list.add(bean);
        }
        TixianDetailAdapter adapter = new TixianDetailAdapter(R.layout.item_tixian_detail,list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        TUtils.showShort(mContext,"点击了---item"+position);
        startActivity(new Intent(mContext,TixianMingxiDetailActivity.class));
    }

    private class TixianDetailAdapter extends BaseQuickAdapter<TixianDetailBean,BaseViewHolder>{

        public TixianDetailAdapter(@LayoutRes int layoutResId, @Nullable List<TixianDetailBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, TixianDetailBean item) {
            helper.setText(R.id.item_date_tv,item.getDate())
                    .setText(R.id.item_content_tv,item.getContent());
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
