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
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.LatestBean;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.TUtils;
import com.power.customizingthecloud.utils.Urls;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LatestActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latest);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("最新活动");
        titleBackIv.setOnClickListener(this);

        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));
        HttpParams params = new HttpParams();
        params.put("after","");
        params.put("type","2");

        OkGo.<LatestBean>get(Urls.BASEURL + "api/v2/article")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<LatestBean>(this,LatestBean.class) {
                    @Override
                    public void onSuccess(Response<LatestBean> response) {
                        LatestBean latestBean = response.body();
                        if (latestBean.getCode() == 1){
                            final List<LatestBean.DataBean> list = latestBean.getData();
                            recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                            recyclerView.setNestedScrollingEnabled(false);
                            LatestAdapter adapter = new LatestAdapter(R.layout.item_latest,list);
                            recyclerView.setAdapter(adapter);
                            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent = new Intent(mContext,LatestDetailActivity.class);
                                    intent.putExtra("id",list.get(position).getId()+"");
                                    startActivity(intent);
                                }
                            });
                        }else {
                            TUtils.showShort(mContext,latestBean.getMessage());
                        }
                    }
                });
    }

    private class LatestAdapter extends BaseQuickAdapter<LatestBean.DataBean,BaseViewHolder>{

        public LatestAdapter(@LayoutRes int layoutResId, @Nullable List<LatestBean.DataBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, LatestBean.DataBean item) {
            Glide.with(mContext).load(item.getThumb()).into((ImageView) helper.getView(R.id.item_img_iv));
            helper.setText(R.id.item_content_tv,item.getTitle());
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
