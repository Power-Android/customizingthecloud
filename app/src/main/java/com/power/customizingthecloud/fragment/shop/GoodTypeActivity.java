package com.power.customizingthecloud.fragment.shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.MyApplication;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.fragment.home.GoodDetailActivity;
import com.power.customizingthecloud.fragment.shop.bean.GoodTypeListBean;
import com.power.customizingthecloud.fragment.shop.bean.ShopTypeBean;
import com.power.customizingthecloud.utils.MyUtils;
import com.power.customizingthecloud.utils.Urls;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoodTypeActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_message_iv)
    ImageView mTitleMessageIv;
    @BindView(R.id.title_back_iv)
    ImageView mTitleBackIv;
    @BindView(R.id.title_list_iv)
    ImageView mTitleListIv;
    @BindView(R.id.title_content_tv)
    TextView mTitleContentTv;
    @BindView(R.id.title_sign_in_iv)
    ImageView mTitleSignInIv;
    @BindView(R.id.title_setting_iv)
    ImageView mTitleSettingIv;
    @BindView(R.id.title_qrcode_iv)
    ImageView mTitleQrcodeIv;
    @BindView(R.id.title_shopcar_iv)
    ImageView mTitleShopcarIv;
    @BindView(R.id.title_share_iv)
    ImageView mTitleShareIv;
    @BindView(R.id.title_search_iv)
    ImageView mTitleSearchIv;
    @BindView(R.id.title_jia_iv)
    ImageView mTitleJiaIv;
    @BindView(R.id.title_content_right_tv)
    TextView mTitleContentRightTv;
    @BindView(R.id.recycler_title)
    RecyclerView mRecyclerTitle;
    @BindView(R.id.tv_content)
    TextView mTvContent;
    @BindView(R.id.recycler_content)
    RecyclerView mRecyclerContent;
    private TitleAdapter mTitleAdapter;
    private int scrollPosition;
    private ContentAdapter mContentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_type);
        ButterKnife.bind(this);
        mTitleBackIv.setVisibility(View.VISIBLE);
        mTitleBackIv.setOnClickListener(this);
        mTitleContentTv.setText("全部分类");
        initData();
    }

    private void initData() {
        final List<ShopTypeBean.DataEntity.SeriesClassEntity> data=
                (List<ShopTypeBean.DataEntity.SeriesClassEntity>) getIntent().getSerializableExtra("data");
        mRecyclerContent.setLayoutManager(new GridLayoutManager(this,3));
        mRecyclerTitle.setLayoutManager(new LinearLayoutManager(this));
        data.get(0).setChecked(true);
        mTitleAdapter = new TitleAdapter(R.layout.good_title_item,data);
        mRecyclerTitle.setAdapter(mTitleAdapter);
        changeRightData(data.get(0).getId()+"");
        mTitleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                data.get(scrollPosition).setChecked(false);
                scrollPosition = position;
                data.get(scrollPosition).setChecked(true);
                mTitleAdapter.notifyDataSetChanged();
                switchData(data.get(position).getName());
                changeRightData(data.get(position).getId()+"");
            }
        });
    }

    private void changeRightData(String id){
        HttpParams params = new HttpParams();
        params.put("id", id);
        params.put("after", "");
        params.put("limit", "10");
        OkGo.<GoodTypeListBean>get(Urls.BASEURL + "api/v2/good/series-list")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<GoodTypeListBean>(GoodTypeActivity.this, GoodTypeListBean.class) {
                    @Override
                    public void onSuccess(Response<GoodTypeListBean> response) {
                        GoodTypeListBean listBean = response.body();
                        int code = listBean.getCode();
                        if (code == 0) {
                            Toast.makeText(mContext, listBean.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (code == 1) {
                            final List<GoodTypeListBean.DataEntity> rightData = listBean.getData();
                            mContentAdapter = new ContentAdapter(R.layout.item_goodcontent,rightData);
                            mRecyclerContent.setAdapter(mContentAdapter);
                            mContentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent = new Intent(GoodTypeActivity.this, GoodDetailActivity.class);
                                    intent.putExtra("id",rightData.get(position).getId()+"");
                                    startActivity(intent);
                                }
                            });
                        }
                    }
                });
    }

    private void switchData(String name) {
        mTvContent.setText(name);
    }

    class TitleAdapter extends BaseQuickAdapter<ShopTypeBean.DataEntity.SeriesClassEntity, BaseViewHolder> {

        public TitleAdapter(@LayoutRes int layoutResId, @Nullable List<ShopTypeBean.DataEntity.SeriesClassEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, final ShopTypeBean.DataEntity.SeriesClassEntity item) {
            helper.setText(R.id.tv_title, item.getName());
            TextView tv = helper.getView(R.id.tv_title);
            if (item.isChecked()) {
                helper.getView(R.id.title_left_view).setBackgroundResource(R.color.green);
                tv.setTextColor(getResources().getColor(R.color.green));
            } else {
                helper.getView(R.id.title_left_view).setBackgroundResource(R.color.white);
                tv.setTextColor(getResources().getColor(R.color.text_black));
            }
        }
    }

    class ContentAdapter extends BaseQuickAdapter<GoodTypeListBean.DataEntity, BaseViewHolder> {

        public ContentAdapter(@LayoutRes int layoutResId, @Nullable List<GoodTypeListBean.DataEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, GoodTypeListBean.DataEntity item) {
            ImageView iv_top=helper.getView(R.id.iv_top);
            int width = MyUtils.getScreenWidth(mContext)*3/4 - MyUtils.dip2px(mContext, 25);
            ViewGroup.LayoutParams layoutParams = iv_top.getLayoutParams();
            layoutParams.height=width/3;
            iv_top.setLayoutParams(layoutParams);
            Glide.with(MyApplication.getGloableContext()).load(item.getImage()).into(iv_top);
            helper.setText(R.id.tv_title, item.getName());
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
