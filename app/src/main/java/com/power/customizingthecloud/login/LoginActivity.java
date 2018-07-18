package com.power.customizingthecloud.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
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
import com.power.customizingthecloud.MainActivity;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.UMLoginActivity;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.login.bean.LoginBean;
import com.power.customizingthecloud.login.bean.RegisterBean;
import com.power.customizingthecloud.utils.MyUtils;
import com.power.customizingthecloud.utils.SendSmsTimerUtils;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.Urls;
import com.umeng.socialize.UMShareAPI;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends UMLoginActivity implements View.OnClickListener {

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
        mIvFinish.setOnClickListener(this);
        mTvLoginbyWeixin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login:
                login();
                break;
            case R.id.tv_regist:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.tv_forget_psw:
                startActivity(new Intent(this, ForgetPswActivity.class));
                break;
            case R.id.iv_psw_status:
                //设置密码是否可见
                if (isPswVisible) {
                    mIvPswStatus.setImageResource(R.drawable.login_eye_close);
                    mEdtPsw.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    mIvPswStatus.setImageResource(R.drawable.login_eye_open);
                    mEdtPsw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                isPswVisible = !isPswVisible;
                break;
            case R.id.tv_loginby_phone:
                if (!isPhoneLogin) {
                    isPhoneLogin = true;
                    mLlCode.setVisibility(View.VISIBLE);
                    mViewLineCode.setVisibility(View.VISIBLE);
                    mLlMima.setVisibility(View.GONE);
                    mViewLineMima.setVisibility(View.GONE);
                    mTvLoginbyPhone.setText("手机登录");
                    mTvForgetPsw.setVisibility(View.INVISIBLE);
                }else {
                    isPhoneLogin = false;
                    mLlCode.setVisibility(View.GONE);
                    mViewLineCode.setVisibility(View.GONE);
                    mLlMima.setVisibility(View.VISIBLE);
                    mViewLineMima.setVisibility(View.VISIBLE);
                    mTvLoginbyPhone.setText("快速登录");
                    mTvForgetPsw.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.tv_loginby_weixin:
                loginByWeiXin(this);
                break;
            case R.id.tv_getcode:
                getCode(mEdtZhanghao.getText().toString());
                break;
            case R.id.iv_finish:
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    private void login() {
        String username = mEdtZhanghao.getText().toString();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "请输入手机号~", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!MyUtils.isMobileNO(username)) {
            Toast.makeText(this, "请输入正确格式的手机号~", Toast.LENGTH_SHORT).show();
            return;
        }
        if (isPhoneLogin) {
            String code = mEdtCode.getText().toString();
            if (TextUtils.isEmpty(code)) {
                Toast.makeText(this, "请输入验证码~", Toast.LENGTH_SHORT).show();
                return;
            }
        } else {
            String psw = mEdtPsw.getText().toString();
            if (TextUtils.isEmpty(psw)) {
                Toast.makeText(this, "请输入密码~", Toast.LENGTH_SHORT).show();
                return;
            }
            if (psw.length() < 6) {
                Toast.makeText(this, "请6位数及以上的密码~", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        HttpParams params = new HttpParams();
        params.put("user_mobile", username);
        String url = "";
        if (isPhoneLogin) {
            params.put("code", mEdtCode.getText().toString());
            url = Urls.BASEURL + "api/v2/code-login";
        } else {
            params.put("password", mEdtPsw.getText().toString());
            url = Urls.BASEURL + "api/v2/mobile-login";
        }
        params.put("device_token", "111");
        OkGo.<LoginBean>post(url)
                .tag(this)
                .params(params)
                .execute(new DialogCallback<LoginBean>(LoginActivity.this, LoginBean.class) {
                    @Override
                    public void onSuccess(Response<LoginBean> response) {
                        LoginBean loginBean = response.body();
                        int code = loginBean.getCode();
                        Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        if (code == 0) {
                        } else if (code == 1) {
                            LoginBean.DataEntity dataEntity = loginBean.getData().get(0);
                            SpUtils.putString(LoginActivity.this, "userid", dataEntity.getUser_id() + "");
                            SpUtils.putString(LoginActivity.this, "token", dataEntity.getToken());
                            long ttlMs = dataEntity.getTtl() * 60 * 1000L;
                            long timeMillis = System.currentTimeMillis();
                            long totalMs = ttlMs + timeMillis;
                            SpUtils.putString(LoginActivity.this, "totalMs", totalMs + "");
                            if (getIntent().getType() != null && getIntent().getType().equals("mian")) {
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            } else {
                                finish();
                            }
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
                .execute(new DialogCallback<RegisterBean>(LoginActivity.this, RegisterBean.class) {
                    @Override
                    public void onSuccess(Response<RegisterBean> response) {
                        int code = response.body().getCode();
                        Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        if (code == 0) {
                        } else if (code == 1) {
                            SendSmsTimerUtils.sendSms(mTvGetcode, R.color.green, R.color.green);
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
