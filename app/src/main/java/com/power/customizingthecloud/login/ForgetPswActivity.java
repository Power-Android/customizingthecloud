package com.power.customizingthecloud.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.login.bean.RegisterBean;
import com.power.customizingthecloud.utils.MyUtils;
import com.power.customizingthecloud.utils.SendSmsTimerUtils;
import com.power.customizingthecloud.utils.Urls;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForgetPswActivity extends BaseActivity implements View.OnClickListener {

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
    @BindView(R.id.edt_phone)
    EditText mEdtPhone;
    @BindView(R.id.edt_code)
    EditText mEdtCode;
    @BindView(R.id.tv_getcode)
    TextView mTvGetcode;
    @BindView(R.id.edt_psw)
    EditText mEdtPsw;
    @BindView(R.id.edt_psw2)
    EditText mEdtPsw2;
    @BindView(R.id.tv_submit)
    TextView mTvSubmit;
    @BindView(R.id.activity_login)
    LinearLayout mActivityLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_psw);
        ButterKnife.bind(this);
        mTitleBackIv.setVisibility(View.VISIBLE);
        mTitleBackIv.setOnClickListener(this);
        mTitleContentTv.setText("找回密码");
        mTvGetcode.setOnClickListener(this);
        mTvSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.tv_getcode:
                getCode(mEdtPhone.getText().toString());
                break;
            case R.id.tv_submit:
                submit();
                break;
        }
    }

    private void submit() {
        String phone = mEdtPhone.getText().toString();
        String code = mEdtCode.getText().toString();
        String psw1 = mEdtPsw.getText().toString();
        String psw2 = mEdtPsw2.getText().toString();
        if (TextUtils.isEmpty(phone)){
            Toast.makeText(this, "请输入手机号~", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(code)){
            Toast.makeText(this, "请输入验证码~", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(psw1)){
            Toast.makeText(this, "请输入新密码~", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!MyUtils.ispsd(psw1)){
            Toast.makeText(this, "请输入6-20位字母+数字组合！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(psw2)){
            Toast.makeText(this, "请输入确认新密码~", Toast.LENGTH_SHORT).show();
            return;
        }
        HttpParams params = new HttpParams();
        params.put("user_mobile", phone);
        params.put("password", psw1);
        params.put("code", code);
        OkGo.<RegisterBean>post(Urls.BASEURL + "api/v2/find-password")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<RegisterBean>(ForgetPswActivity.this, RegisterBean.class) {
                    @Override
                    public void onSuccess(Response<RegisterBean> response) {
                        int code = response.body().getCode();
                        Toast.makeText(ForgetPswActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        if (code == 0) {
                        } else if (code == 1) {
                            Intent intent = new Intent(ForgetPswActivity.this, LoginActivity.class);
                            intent.putExtra("type","main");
                            startActivity(intent);
                        }
                    }
                });
    }

    private void getCode(String phone) {
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请输入手机号~", Toast.LENGTH_SHORT).show();
            return;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.put("X-Header-Sms", "HxP&sU1YFs78RL&Src@G3YnN5ne3HYvR");
        HttpParams params = new HttpParams();
        params.put("mobile", phone);
        OkGo.<RegisterBean>post(Urls.BASEURL + "api/v2/verifycodes")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<RegisterBean>(ForgetPswActivity.this, RegisterBean.class) {
                    @Override
                    public void onSuccess(Response<RegisterBean> response) {
                        int code = response.body().getCode();
                        Toast.makeText(ForgetPswActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        if (code == 0) {
                        } else if (code == 1) {
                            SendSmsTimerUtils.sendSms(mTvGetcode, R.color.green, R.color.green);
                        }
                    }
                });
    }
}
