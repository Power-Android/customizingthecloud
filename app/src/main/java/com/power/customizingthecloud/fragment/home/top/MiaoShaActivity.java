package com.power.customizingthecloud.fragment.home.top;

import android.content.Intent;
import android.graphics.Paint;
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
import com.power.customizingthecloud.fragment.home.MiaoShaDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.iwgang.countdownview.CountdownView;

public class MiaoShaActivity extends BaseActivity implements View.OnClickListener {

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
    @BindView(R.id.recycler_miaosha)
    RecyclerView mRecyclerMiaosha;
    private MiaoshaAdapter mMiaoshaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miao_sha);
        ButterKnife.bind(this);
        mTitleBackIv.setVisibility(View.VISIBLE);
        mTitleBackIv.setOnClickListener(this);
        mTitleContentTv.setText("限量秒杀");
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        mRecyclerMiaosha.setLayoutManager(new LinearLayoutManager(mContext));
        mMiaoshaAdapter = new MiaoshaAdapter(R.layout.item_home_miaosha,list);
        mRecyclerMiaosha.setAdapter(mMiaoshaAdapter);
        mMiaoshaAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(MiaoShaActivity.this, MiaoShaDetailActivity.class));
            }
        });
    }

    private class MiaoshaAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public MiaoshaAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            TextView tv_yuanjia = helper.getView(R.id.tv_yuanjia);
            //添加删除线
            tv_yuanjia.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            CountdownView cv_countdownView=helper.getView(R.id.cv_countdownView);
            cv_countdownView.start(995550000); // Millisecond
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
