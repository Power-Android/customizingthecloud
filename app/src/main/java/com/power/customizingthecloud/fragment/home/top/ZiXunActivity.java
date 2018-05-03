package com.power.customizingthecloud.fragment.home.top;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
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
import com.power.customizingthecloud.activity.mine.LatestActivity;
import com.power.customizingthecloud.activity.mine.LatestDetailActivity;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.LatestBean;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.fragment.home.ZiXunDetailActivity;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.TUtils;
import com.power.customizingthecloud.utils.Urls;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZiXunActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_message_iv)
    ImageView mTitleMessageIv;
    @BindView(R.id.title_back_iv)
    ImageView mTitleBackIv;
    @BindView(R.id.title_content_tv)
    TextView mTitleContentTv;
    @BindView(R.id.title_sign_in_iv)
    ImageView mTitleSignInIv;
    @BindView(R.id.title_setting_iv)
    ImageView mTitleSettingIv;
    @BindView(R.id.title_content_right_tv)
    TextView mTitleContentRightTv;
    @BindView(R.id.recycler_zixun)
    RecyclerView mRecyclerZixun;
    private ZiXunAdapter mZiXunAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zi_xun);
        ButterKnife.bind(this);
        mTitleBackIv.setVisibility(View.VISIBLE);
        mTitleBackIv.setOnClickListener(this);
        mTitleContentTv.setText("资讯");

        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));
        HttpParams params = new HttpParams();
        params.put("after","");
        params.put("type","1");

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
                            mRecyclerZixun.setLayoutManager(new GridLayoutManager(mContext,2));
                            mZiXunAdapter=new ZiXunAdapter(R.layout.item_zixun,list);
                            mRecyclerZixun.setAdapter(mZiXunAdapter);
                            mZiXunAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent = new Intent(mContext,ZiXunDetailActivity.class);
                                    intent.putExtra("id",list.get(position).getId());
                                    startActivity(intent);
                                }
                            });
                        }else {
                            TUtils.showShort(mContext,latestBean.getMessage());
                        }
                    }
                });
    }

    private class ZiXunAdapter extends BaseQuickAdapter<LatestBean.DataBean, BaseViewHolder> {

        public ZiXunAdapter(@LayoutRes int layoutResId, @Nullable List<LatestBean.DataBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, LatestBean.DataBean item) {
            Glide.with(mContext).load(item.getThumb()).into((ImageView) helper.getView(R.id.iv_top));
            helper.setText(R.id.tv_name,item.getTitle());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
        }
    }
}
