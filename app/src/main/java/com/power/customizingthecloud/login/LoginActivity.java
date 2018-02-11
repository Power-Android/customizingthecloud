package com.power.customizingthecloud.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.power.customizingthecloud.MainActivity;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.utils.SendSmsTimerUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.iv_finish)
    ImageView mIvFinish;
    @BindView(R.id.edt_zhanghao)
    EditText mEdtZhanghao;
    @BindView(R.id.edt_psw)
    EditText mEdtPsw;
    @BindView(R.id.tv_forget_psw)
    TextView mTvForgetPsw;
    @BindView(R.id.tv_login)
    TextView mTvLogin;
    @BindView(R.id.tv_regist)
    TextView mTvRegist;
    @BindView(R.id.tv_loginby_phone)
    TextView mTvLoginbyPhone;
    @BindView(R.id.tv_loginby_weixin)
    TextView mTvLoginbyWeixin;
    @BindView(R.id.activity_login)
    LinearLayout mActivityLogin;
    @BindView(R.id.iv_psw_status)
    ImageView mIvPswStatus;
    @BindView(R.id.edt_code)
    EditText mEdtCode;
    @BindView(R.id.tv_getcode)
    TextView mTvGetcode;
    @BindView(R.id.ll_code)
    LinearLayout mLlCode;
    @BindView(R.id.view_line_code)
    View mViewLineCode;
    @BindView(R.id.ll_mima)
    LinearLayout mLlMima;
    @BindView(R.id.view_line_mima)
    View mViewLineMima;
    private boolean isPswVisible;
    private boolean isPhoneLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mTvLogin.setOnClickListener(this);
        mTvRegist.setOnClickListener(this);
        mTvForgetPsw.setOnClickListener(this);
        mIvPswStatus.setOnClickListener(this);
        mTvLoginbyPhone.setOnClickListener(this);
        mTvGetcode.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
            case R.id.tv_regist:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.tv_forget_psw:
                startActivity(new Intent(this, ForgetPswActivity.class));
                break;
            case R.id.iv_psw_status:
                if (isPswVisible) {
                    mIvPswStatus.setImageResource(R.drawable.login_eye_open);
                    //设置密码是否可见
                    mEdtPsw.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    mIvPswStatus.setImageResource(R.drawable.login_eye_close);
                    mEdtPsw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                isPswVisible = !isPswVisible;
                break;
            case R.id.tv_loginby_phone:
                isPhoneLogin=true;
                mLlCode.setVisibility(View.VISIBLE);
                mViewLineCode.setVisibility(View.VISIBLE);
                mLlMima.setVisibility(View.GONE);
                mViewLineMima.setVisibility(View.GONE);
                break;
            case R.id.tv_getcode:
                SendSmsTimerUtils.sendSms(mTvGetcode,R.color.green,R.color.green);
                break;
        }
    }
}
