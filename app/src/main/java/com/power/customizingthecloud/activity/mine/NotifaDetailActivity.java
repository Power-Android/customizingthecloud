package com.power.customizingthecloud.activity.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotifaDetailActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView mTitleBackIv;
    @BindView(R.id.title_content_tv)
    TextView mTitleContentTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifa_detail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mTitleBackIv.setVisibility(View.VISIBLE);
        mTitleContentTv.setText("消息详情");
    }

    @OnClick(R.id.title_back_iv)
    public void onViewClicked() {
        finish();
    }
}
