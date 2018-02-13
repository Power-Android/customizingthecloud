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

public class BindAliPayActivity extends BaseActivity {

    @BindView(R.id.view_01)
    ImageView view01;
    @BindView(R.id.bind_show_tv)
    TextView bindShowTv;
    @BindView(R.id.content_tv)
    TextView contentTv;
    @BindView(R.id.is_bind_tv)
    TextView isBindTv;
    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_ali_pay);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("绑定支付宝");
    }

    @OnClick({R.id.title_back_iv, R.id.is_bind_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.is_bind_tv:
                bindShowTv.setVisibility(View.VISIBLE);
                contentTv.setText("支付宝帐号：139****6666");
                isBindTv.setText("解绑");
                break;
        }
    }
}
