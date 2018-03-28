package com.power.customizingthecloud.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.power.customizingthecloud.MainActivity;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.utils.SpUtils;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.NormalAlertDialog;

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

    @OnClick(R.id.title_back_iv)
    public void setTitleBackIv(){
        finish();
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
        startActivity(new Intent(mContext,AddressManagerActivity.class));
    }

    @OnClick(R.id.acountsafe_rl)//账户安全
    public void setAcountsafeRl(){
        startActivity(new Intent(mContext,AccountSafeActivity.class));
    }

    @OnClick(R.id.aboutyanglv_rl)
    public void setAboutyanglvRl(){
        startActivity(new Intent(mContext,AboutUsActivity.class));
    }

    @OnClick(R.id.kefu_rl)
    public void setKefuRl(){
        startActivity(new Intent(mContext, CustomerCenterActivity.class));
    }

    @OnClick(R.id.yijian_rl)
    public void setYijianRl(){
        startActivity(new Intent(mContext,FeedbackActivity.class));
    }

    @OnClick(R.id.logout_tv)
    public void setLogoutTv(){
        showTip();
    }

    private void showTip() {
        new NormalAlertDialog.Builder(this)
                .setTitleVisible(false).setTitleText("退出登录").setTitleTextSize(16)
                .setTitleTextColor(R.color.text_black)
                .setContentText("是否退出")
                .setContentTextSize(18)
                .setContentTextColor(R.color.text_black)
                .setLeftButtonText("是")
                .setLeftButtonTextColor(R.color.text_black)
                .setRightButtonText("否")
                .setRightButtonTextColor(R.color.text_black)
                .setCanceledOnTouchOutside(false)
                .setOnclickListener(new DialogInterface.OnLeftAndRightClickListener<NormalAlertDialog>() {
                    @Override
                    public void clickLeftButton(NormalAlertDialog dialog, View view) {
                        SpUtils.putString(mContext,"userid","");
                        dialog.dismiss();
                        finish();
                        removeAllActivitys();
                        startActivity(new Intent(mContext, MainActivity.class));
                    }

                    @Override
                    public void clickRightButton(NormalAlertDialog dialog, View view) {
                        dialog.dismiss();
                    }
                })
                .build()
                .show();
    }
}
