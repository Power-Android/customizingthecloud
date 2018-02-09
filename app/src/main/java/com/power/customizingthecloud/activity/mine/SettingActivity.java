package com.power.customizingthecloud.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {

    @BindView(R.id.title_back_iv) ImageView titleBackIv;
    @BindView(R.id.title_content_tv) TextView titleContentTv;
    @BindView(R.id.userinfo_rl) RelativeLayout userinfoRl;
    @BindView(R.id.shiming_rl) RelativeLayout shimingRl;
    @BindView(R.id.shopadress_rl) RelativeLayout shopadressRl;
    @BindView(R.id.acountsafe_rl) RelativeLayout acountsafeRl;
    @BindView(R.id.aboutyanglv_rl) RelativeLayout aboutyanglvRl;
    @BindView(R.id.kefu_rl) RelativeLayout kefuRl;
    @BindView(R.id.yijian_rl) RelativeLayout yijianRl;
    @BindView(R.id.logout_tv) TextView logoutTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("系统设置");
    }

    @OnClick(R.id.userinfo_rl)
    public void setUserinfoRl(){
        startActivity(new Intent(mContext,EditInfoActivity.class));
    }

    @OnClick(R.id.shiming_rl)
    public void setShimingRl(){
        startActivity(new Intent(mContext,CertificationActivity.class));
    }

    @OnClick(R.id.shopadress_rl)
    public void setShopadressRl(){

    }

    @OnClick(R.id.acountsafe_rl)
    public void setAcountsafeRl(){
        startActivity(new Intent(mContext,AccountSafeActivity.class));
    }

    @OnClick(R.id.aboutyanglv_rl)
    public void setAboutyanglvRl(){

    }

    @OnClick(R.id.kefu_rl)
    public void setKefuRl(){

    }

    @OnClick(R.id.yijian_rl)
    public void setYijianRl(){

    }

    @OnClick(R.id.logout_tv)
    public void setLogoutTv(){

    }
}
