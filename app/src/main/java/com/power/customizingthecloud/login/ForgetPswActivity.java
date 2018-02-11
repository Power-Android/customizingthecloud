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

import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.utils.SendSmsTimerUtils;

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
                SendSmsTimerUtils.sendSms(mTvGetcode,R.color.green,R.color.green);
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
        if (TextUtils.isEmpty(psw2)){
            Toast.makeText(this, "请输入确认新密码~", Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(new Intent(this,LoginActivity.class));
    }
}
