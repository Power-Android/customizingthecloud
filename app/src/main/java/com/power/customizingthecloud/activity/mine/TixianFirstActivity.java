package com.power.customizingthecloud.activity.mine;

import android.content.Intent;
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
import com.power.customizingthecloud.callback.JsonCallback;
import com.power.customizingthecloud.login.bean.RegisterBean;
import com.power.customizingthecloud.utils.SendSmsTimerUtils;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.Urls;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TixianFirstActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.phone_et)
    EditText phoneEt;
    @BindView(R.id.code_et)
    EditText codeEt;
    @BindView(R.id.getcode_tv)
    TextView getcodeTv;
    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.jump_tv)
    TextView jumpTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tixian_first);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleBackIv.setOnClickListener(this);
        titleContentTv.setText("手机号验证");
        getcodeTv.setOnClickListener(this);
        jumpTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.getcode_tv:
                if (TextUtils.isEmpty(phoneEt.getText().toString())) {
                    Toast.makeText(this, "请输入手机号~", Toast.LENGTH_SHORT).show();
                    return;
                }
                getCode(phoneEt.getText().toString());
                break;
            case R.id.jump_tv:
                if (TextUtils.isEmpty(phoneEt.getText().toString())) {
                    Toast.makeText(this, "请输入手机号~", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(codeEt.getText().toString())) {
                    Toast.makeText(this, "请输入验证码~", Toast.LENGTH_SHORT).show();
                    return;
                }
                checkMobile();
                break;
        }
    }

    private void checkMobile() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(this, "token", ""));
        HttpParams params = new HttpParams();
        params.put("mobile", phoneEt.getText().toString());
        params.put("code", codeEt.getText().toString());
        OkGo.<BaseBean>post(Urls.BASEURL + "api/v2/user/tx-mobile")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new JsonCallback<BaseBean>(BaseBean.class) {
                    @Override
                    public void onSuccess(Response<BaseBean> response) {
                        BaseBean bankNameBean = response.body();
                        int code = bankNameBean.getCode();
                        if (code == 0) {
                            Toast.makeText(mContext, bankNameBean.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (code == 1) {
                            Intent intent = new Intent(mContext, TixianThreeActivity.class);
                            intent.putExtra("type",getIntent().getStringExtra("type"));
                            startActivityForResult(intent,0);
                        }
                    }
                });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==1){
            finish();
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
                .execute(new DialogCallback<RegisterBean>(TixianFirstActivity.this, RegisterBean.class) {
                    @Override
                    public void onSuccess(Response<RegisterBean> response) {
                        int code = response.body().getCode();
                        Toast.makeText(TixianFirstActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        if (code == 0) {
                        } else if (code == 1) {
                            SendSmsTimerUtils.sendSms(getcodeTv, R.color.green, R.color.green);
                        }
                    }
                });
    }
}
