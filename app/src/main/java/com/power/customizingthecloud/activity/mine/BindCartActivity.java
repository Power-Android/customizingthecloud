package com.power.customizingthecloud.activity.mine;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.BaseBean;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.login.RegisterActivity;
import com.power.customizingthecloud.login.bean.RegisterBean;
import com.power.customizingthecloud.utils.SendSmsTimerUtils;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.TUtils;
import com.power.customizingthecloud.utils.Urls;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BindCartActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.phone_et)
    EditText phoneEt;
    @BindView(R.id.code_et)
    EditText codeEt;
    @BindView(R.id.getcode_tv)
    TextView getcodeTv;
    @BindView(R.id.bind_tv)
    TextView bindTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_cart);
        ButterKnife.bind(this);
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("手机验证");
    }

    private void commit() {
        if (TextUtils.isEmpty(phoneEt.getText().toString().trim())) {
            TUtils.showShort(mContext, "请输入银行卡预留手机号");
            return;
        }
        if (TextUtils.isEmpty(codeEt.getText().toString().trim())) {
            TUtils.showShort(mContext, "请输入验证码");
            return;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));
        HttpParams params = new HttpParams();
        params.put("card_name", getIntent().getStringExtra("card_name"));
        params.put("bank_card", getIntent().getStringExtra("bank_card"));
        params.put("card_mobile", phoneEt.getText().toString());
        params.put("code", codeEt.getText().toString());
        OkGo.<BaseBean>post(Urls.BASEURL + "api/v2/user/bind-bankcard")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<BaseBean>(this, BaseBean.class) {
                    @Override
                    public void onSuccess(Response<BaseBean> response) {
                        BaseBean body = response.body();
                        if (body.getCode() == 1) {
                            TUtils.showShort(mContext, body.getMessage());
                            finish();
                        } else {
                            TUtils.showShort(mContext, body.getMessage());
                        }
                    }
                });
    }

    @OnClick({R.id.title_back_iv, R.id.getcode_tv, R.id.bind_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.getcode_tv:
                if (TextUtils.isEmpty(phoneEt.getText().toString().trim())) {
                    TUtils.showShort(mContext, "请输入银行卡预留手机号");
                    return;
                }
                getCode(phoneEt.getText().toString());
                break;
            case R.id.bind_tv:
                commit();
                break;
        }
    }

    private void getCode(String phone) {
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
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
                .execute(new DialogCallback<RegisterBean>(BindCartActivity.this, RegisterBean.class) {
                    @Override
                    public void onSuccess(Response<RegisterBean> response) {
                        int code = response.body().getCode();
                        Toast.makeText(BindCartActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        if (code == 0) {
                        } else if (code == 1) {
                            SendSmsTimerUtils.sendSms(getcodeTv, R.color.green, R.color.green);
                        }
                    }
                });
    }
}
