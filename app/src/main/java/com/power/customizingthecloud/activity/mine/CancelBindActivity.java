package com.power.customizingthecloud.activity.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CancelBindActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_message_iv)
    ImageView titleMessageIv;
    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_list_iv)
    ImageView titleListIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.title_sign_in_iv)
    ImageView titleSignInIv;
    @BindView(R.id.title_setting_iv)
    ImageView titleSettingIv;
    @BindView(R.id.title_qrcode_iv)
    ImageView titleQrcodeIv;
    @BindView(R.id.title_shopcar_iv)
    ImageView titleShopcarIv;
    @BindView(R.id.title_share_iv)
    ImageView titleShareIv;
    @BindView(R.id.title_search_iv)
    ImageView titleSearchIv;
    @BindView(R.id.title_jia_iv)
    ImageView titleJiaIv;
    @BindView(R.id.title_kefu_iv)
    ImageView titleKefuIv;
    @BindView(R.id.title_point_iv)
    ImageView titlePointIv;
    @BindView(R.id.title_content_right_tv)
    TextView titleContentRightTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_bind);
        ButterKnife.bind(this);
        titleBackIv.setVisibility(View.VISIBLE);
        titleBackIv.setOnClickListener(this);
        titleContentTv.setText("银行卡解绑");
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
