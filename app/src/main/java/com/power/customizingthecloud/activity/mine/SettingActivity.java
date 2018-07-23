package com.power.customizingthecloud.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.MainActivity;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.EventBean;
import com.power.customizingthecloud.bean.PersonCardBean;
import com.power.customizingthecloud.callback.JsonCallback;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.Urls;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.NormalAlertDialog;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.userinfo_rl)
    RelativeLayout userinfoRl;
    @BindView(R.id.shiming_rl)
    RelativeLayout shimingRl;
    @BindView(R.id.shopadress_rl)
    RelativeLayout shopadressRl;
    @BindView(R.id.acountsafe_rl)
    RelativeLayout acountsafeRl;
    @BindView(R.id.aboutyanglv_rl)
    RelativeLayout aboutyanglvRl;
    @BindView(R.id.kefu_rl)
    RelativeLayout kefuRl;
    @BindView(R.id.yijian_rl)
    RelativeLayout yijianRl;
    @BindView(R.id.logout_tv)
    TextView logoutTv;
    @BindView(R.id.tv_isrenzheng)
    TextView tv_isrenzheng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(this, "token", ""));
        OkGo.<PersonCardBean>get(Urls.BASEURL + "api/v2/user/card-show")
                .headers(headers)
                .execute(new JsonCallback<PersonCardBean>(PersonCardBean.class) {
                    @Override
                    public void onSuccess(Response<PersonCardBean> response) {
                        PersonCardBean bean = response.body();
                        int code = bean.getCode();
                        if (code == 0) {
                            Toast.makeText(SettingActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (code == 1) {
                            PersonCardBean.DataEntity data = bean.getData();
                            if (data.getIs_card_bind() == 1) {
                                tv_isrenzheng.setText("已认证");
                            } else if (data.getIs_card_bind() == 2) {
                                tv_isrenzheng.setText("审核未通过");
                            } else if (data.getIs_card_bind() == 0) {
                                if (!TextUtils.isEmpty(data.getTrue_name())) {
                                    tv_isrenzheng.setText("审核中");
                                }else {
                                    tv_isrenzheng.setText("未认证");
                                }
                            }
                        }
                    }
                });
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("系统设置");
    }

    @OnClick(R.id.title_back_iv)
    public void setTitleBackIv() {
        finish();
    }

    @OnClick(R.id.userinfo_rl)
    public void setUserinfoRl() {
        startActivity(new Intent(mContext, EditInfoActivity.class));
    }

    @OnClick(R.id.shiming_rl)
    public void setShimingRl() {
        startActivity(new Intent(mContext, CertificationActivity.class));
    }

    @OnClick(R.id.shopadress_rl)
    public void setShopadressRl() {
        startActivity(new Intent(mContext, AddressManagerActivity.class));
    }

    @OnClick(R.id.acountsafe_rl)//账户安全
    public void setAcountsafeRl() {
        startActivity(new Intent(mContext, AccountSafeActivity.class));
    }

    @OnClick(R.id.aboutyanglv_rl)
    public void setAboutyanglvRl() {
        startActivity(new Intent(mContext, AboutUsActivity.class));
    }

    @OnClick(R.id.kefu_rl)
    public void setKefuRl() {
        startActivity(new Intent(mContext, CustomerCenterActivity.class));
    }

    @OnClick(R.id.yijian_rl)
    public void setYijianRl() {
        startActivity(new Intent(mContext, FeedbackActivity.class));
    }

    @OnClick(R.id.logout_tv)
    public void setLogoutTv() {
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
                        SpUtils.putString(mContext, "userid", "");
                        dialog.dismiss();
                        finish();
                        removeAllActivitys();
                        startActivity(new Intent(mContext, MainActivity.class));
                        EventBus.getDefault().postSticky(new EventBean("loginout"));
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
