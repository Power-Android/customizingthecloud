package com.power.customizingthecloud.fragment.shop;

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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.GoodTypeBean;
import com.power.customizingthecloud.fragment.home.GoodDetailActivity;

import java.util.ArrayList;
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
    private List<GoodTypeBean> mTypeBeanList=new ArrayList<>();
    private TitleAdapter mTitleAdapter;
    private int scrollPosition;

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
        if (mTypeBeanList==null || mTypeBeanList.size()==0){
            mTypeBeanList.add(new GoodTypeBean("生鲜系列"));
            mTypeBeanList.add(new GoodTypeBean("养生系列"));
            mTypeBeanList.add(new GoodTypeBean("农特产品"));
            mTypeBeanList.add(new GoodTypeBean("休闲食品"));
            mTypeBeanList.add(new GoodTypeBean("大单预定"));
            mTypeBeanList.add(new GoodTypeBean("VIP私人定制专享"));
            mTypeBeanList.add(new GoodTypeBean("成为会员"));
        }
        mTypeBeanList.get(0).setChecked(true);
        mRecyclerTitle.setLayoutManager(new LinearLayoutManager(this));
        mTitleAdapter = new TitleAdapter(R.layout.good_title_item,mTypeBeanList);
        mRecyclerTitle.setAdapter(mTitleAdapter);
        mTitleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mTypeBeanList.get(scrollPosition).setChecked(false);
                scrollPosition = position;
                mTypeBeanList.get(scrollPosition).setChecked(true);
                mTitleAdapter.notifyDataSetChanged();
                switchData(mTypeBeanList.get(position).getName());
            }
        });
        List<String> list=new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        ContentAdapter contentAdapter=new ContentAdapter(R.layout.item_goodcontent,list);
        mRecyclerContent.setLayoutManager(new GridLayoutManager(this,3));
        mRecyclerContent.setAdapter(contentAdapter);
        contentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(GoodTypeActivity.this, GoodDetailActivity.class));
            }
        });
    }

    private void switchData(String name) {
        mTvContent.setText("——————"+name+"——————");
    }

    class TitleAdapter extends BaseQuickAdapter<GoodTypeBean, BaseViewHolder> {

        public TitleAdapter(@LayoutRes int layoutResId, @Nullable List<GoodTypeBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, final GoodTypeBean item) {
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

    class ContentAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public ContentAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.tv_jiankong,"精品驴奶粉");
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
