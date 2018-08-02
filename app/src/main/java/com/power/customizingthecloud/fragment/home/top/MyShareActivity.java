package com.power.customizingthecloud.fragment.home.top;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.activity.mine.ShareRuleActivity;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.fragment.home.ShareSuccessActivity;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.view.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyShareActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.face_iv)
    CircleImageView faceIv;
    @BindView(R.id.tv_invite)
    TextView tv_invite;
    @BindView(R.id.title_message_iv)
    ImageView titleMessageIv;
    @BindView(R.id.title_list_iv)
    ImageView titleListIv;
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
    @BindView(R.id.title_content_right_tv)
    TextView titleContentRightTv;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.iv_haolvyou)
    ImageView ivHaolvyou;
    @BindView(R.id.tv_rule)
    TextView tvRule;
    @BindView(R.id.tv_11)
    TextView tv11;
    @BindView(R.id.activity_my_share)
    LinearLayout activityMyShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_share);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleBackIv.setOnClickListener(this);
        tv_invite.setOnClickListener(this);
        ivClose.setOnClickListener(this);
        tvRule.setOnClickListener(this);
        titleContentTv.setText("分享");
        tvName.setText(SpUtils.getString(mContext, "userName", ""));
        Glide.with(this).load(SpUtils.getString(mContext, "userHead", "")).into(faceIv);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.iv_close:
                finish();
                break;
            case R.id.tv_rule:
                startActivity(new Intent(mContext,ShareRuleActivity.class));
                break;
            case R.id.tv_invite:
                startActivity(new Intent(mContext, ShareSuccessActivity.class));
                break;
        }
    }
}
