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
import com.power.customizingthecloud.bean.FootprintBean;
import com.power.customizingthecloud.fragment.home.GoodDetailActivity;
import com.power.customizingthecloud.utils.TUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyFootprintActivity extends BaseActivity implements View.OnClickListener, BaseQuickAdapter.OnItemChildClickListener {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_footprint);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("我的足迹");
        titleBackIv.setOnClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setNestedScrollingEnabled(false);

        List<FootprintBean> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            FootprintBean bean = new FootprintBean();
            bean.setName("驴奶粉");
            bean.setMoney("￥50");
            bean.setXiaoliang("月销量：100斤");
            bean.setPingjia("累计评价：100");
            list.add(bean);
        }
        FootprintAdapter adapter = new FootprintAdapter(R.layout.item_foot_print,list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemChildClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_back_iv:
                finish();
                break;
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()){
            case R.id.content_rl:
                startActivity(new Intent(mContext, GoodDetailActivity.class));
                break;
            case R.id.shachu_tv:
                TUtils.showShort(mContext,"点击了---删除"+position);
                break;
        }
    }

    private class FootprintAdapter extends BaseQuickAdapter<FootprintBean,BaseViewHolder>{

        public FootprintAdapter(@LayoutRes int layoutResId, @Nullable List<FootprintBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, FootprintBean item) {
            helper.setText(R.id.item_name_tv,item.getName())
                    .setText(R.id.item_money_tv,item.getMoney())
                    .setText(R.id.item_xiaoliang_tv,item.getXiaoliang())
                    .setText(R.id.item_pingjia_tv,item.getPingjia())
                    .addOnClickListener(R.id.content_rl)
                    .addOnClickListener(R.id.shachu_tv);
        }
    }
}
