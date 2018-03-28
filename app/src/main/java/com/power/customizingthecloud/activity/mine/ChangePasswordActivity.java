package com.power.customizingthecloud.activity.mine;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.BaseBean;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.callback.JsonCallback;
import com.power.customizingthecloud.utils.SendSmsTimerUtils;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.TUtils;
import com.power.customizingthecloud.utils.Urls;

import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangePasswordActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.phone_et)
    EditText phoneEt;
    @BindView(R.id.code_et)
    EditText codeEt;
    @BindView(R.id.getcode_tv)
    TextView getcodeTv;
    @BindView(R.id.old_pwd_et)
    EditText oldPwdEt;
    @BindView(R.id.new_pwd_et)
    EditText newPwdEt;
    @BindView(R.id.query_pwd_et)
    EditText queryPwdEt;
    @BindView(R.id.change_tv)
    TextView changeTv;
    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleBackIv.setOnClickListener(this);
        titleContentTv.setText("修改密码");
        getcodeTv.setOnClickListener(this);
        changeTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.getcode_tv:
                if (TextUtils.isEmpty(phoneEt.getText().toString())){
                    TUtils.showShort(mContext,"请输入手机号");
                    return;
                }
                if (phoneEt.getText().length() != 11){
                    TUtils.showShort(mContext,"请输入正确的手机号");
                    return;
                }
                HttpHeaders headers = new HttpHeaders();
                headers.put("X-Header-Sms","HxP&sU1YFs78RL&Src@G3YnN5ne3HYvR");
                headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));

                HttpParams params = new HttpParams();
                params.put("mobile",phoneEt.getText().toString());

                OkGo.<BaseBean>post(Urls.BASEURL + "api/v2/send-code")
                        .tag(this)
                        .headers(headers)
                        .params(params)
                        .execute(new DialogCallback<BaseBean>(this,BaseBean.class) {
                            @Override
                            public void onSuccess(Response<BaseBean> response) {
                                BaseBean body = response.body();
                                if (body.getCode() == 1){
                                    TUtils.showShort(mContext,body.getMessage());
                                    SendSmsTimerUtils.sendSms(getcodeTv, R.color.green, R.color.green);
                                }else {
                                    TUtils.showShort(mContext,body.getMessage());
                                }
                            }
                        });

                break;
            case R.id.change_tv:
                commit();
                break;
        }
    }

    private void commit() {
        if (TextUtils.isEmpty(phoneEt.getText().toString().trim())){
            TUtils.showShort(mContext,"请输入手机号");
            return;
        }
        if (phoneEt.getText().toString().length() != 11){
            TUtils.showShort(mContext,"请输入正确的手机号");
            return;
        }
        if (TextUtils.isEmpty(codeEt.getText().toString().trim())){
            TUtils.showShort(mContext,"请输入验证码");
            return;
        }
        if (TextUtils.isEmpty(oldPwdEt.getText().toString().trim())){
            TUtils.showShort(mContext,"请输入原密码");
            return;
        }
        if (TextUtils.isEmpty(newPwdEt.getText().toString().trim())){
            TUtils.showShort(mContext,"请输入新密码");
            return;
        }
        if (TextUtils.isEmpty(queryPwdEt.getText().toString().trim())){
            TUtils.showShort(mContext,"请输入确认密码");
            return;
        }
        if (!TextUtils.equals(newPwdEt.getText().toString(),queryPwdEt.getText().toString())){
            TUtils.showShort(mContext,"两次密码输入不一致");
            return;
        }
        requestData();
    }

    private void requestData() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));
        HttpParams params = new HttpParams();
        params.put("code",codeEt.getText().toString());
        params.put("old_pwd",oldPwdEt.getText().toString());
        params.put("new_pwd",newPwdEt.getText().toString());

        OkGo.<BaseBean>post(Urls.BASEURL + "api/v2/user/edit-pwd")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<BaseBean>(this,BaseBean.class) {
                    @Override
                    public void onSuccess(Response<BaseBean> response) {
                        BaseBean body = response.body();
                        if (body.getCode() == 1){
                            TUtils.showShort(mContext,body.getMessage());
                            finish();
                        }else {
                            TUtils.showShort(mContext,body.getMessage());
                        }
                    }
                });
    }
}
