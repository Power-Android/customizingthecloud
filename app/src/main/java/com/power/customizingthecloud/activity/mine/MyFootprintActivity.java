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

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.customizingthecloud.MyApplication;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.db.LookBean;
import com.power.customizingthecloud.db.LookUtils;
import com.power.customizingthecloud.fragment.home.GoodDetailActivity;
import com.power.customizingthecloud.utils.SpUtils;

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
    private List<LookBean> search;
    private FootprintAdapter adapter;
    private String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_footprint);
        ButterKnife.bind(this);
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("我的足迹");
        titleBackIv.setOnClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setNestedScrollingEnabled(false);
        userid = SpUtils.getString(mContext, "userid", "");
        initData();
    }

    private void initData() {
        search = LookUtils.search(userid);
        adapter = new FootprintAdapter(R.layout.item_foot_print, search);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemChildClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.content_rl:
                Intent intent = new Intent(mContext, GoodDetailActivity.class);
                intent.putExtra("id", search.get(position).getGood_id() + "");
                startActivity(intent);
                break;
            case R.id.shachu_tv:
                search = LookUtils.search(userid);
                if (search != null && search.size() > 0) {
                    LookUtils.deleteOne(search.get(position).getGood_id()+"");
                }
                initData();
                break;
        }
    }

    private class FootprintAdapter extends BaseQuickAdapter<LookBean, BaseViewHolder> {

        public FootprintAdapter(@LayoutRes int layoutResId, @Nullable List<LookBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, LookBean item) {
            Glide.with(MyApplication.getGloableContext()).load(item.getImage())
                    .into((ImageView) helper.getView(R.id.item_img_iv));
            helper.setText(R.id.item_name_tv, item.getName())
                    .setText(R.id.item_money_tv, "¥" + item.getPrice())
                    .setText(R.id.item_type_tv, "分类：" + item.getClass_name())
                    .addOnClickListener(R.id.content_rl)
                    .addOnClickListener(R.id.shachu_tv);
        }
    }
}
