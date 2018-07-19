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
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.TUtils;
import com.power.customizingthecloud.utils.Urls;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.NormalAlertDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BindEmailActivity extends BaseActivity {

    @BindView(R.id.email_et)
    EditText emailEt;
    @BindView(R.id.bind_tv)
    TextView bindTv;
    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_email);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("邮箱绑定");
    }

    @OnClick({R.id.title_back_iv, R.id.bind_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.bind_tv:
                String emailStr = emailEt.getText().toString();
                if (TextUtils.isEmpty(emailStr) || emailStr.equals("")){
                    TUtils.showShort(mContext,"请填写邮箱地址");
                    return;
                }else {
//                    showTip(emailStr);
                    bindEmail();
                }
                break;
        }
    }

    private void bindEmail() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(this, "token", ""));
        HttpParams params = new HttpParams();
        params.put("bind_email", emailEt.getText().toString());
        OkGo.<BaseBean>post(Urls.BASEURL + "api/v2/user/bind-email")
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<BaseBean>(BindEmailActivity.this, BaseBean.class) {
                    @Override
                    public void onSuccess(Response<BaseBean> response) {
                        BaseBean bean = response.body();
                        int code = bean.getCode();
                        if (code == 0) {
                            Toast.makeText(BindEmailActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (code == 1) {
                            Toast.makeText(BindEmailActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });
    }

    private void showTip(final String str) {
        new NormalAlertDialog.Builder(this)
                .setTitleVisible(true).setTitleText("一封邮件已发送至").setTitleTextSize(16)
                .setTitleTextColor(R.color.text_black)
                .setContentText(str + "，请登录你的邮箱查收并通过邮件验证。")
                .setContentTextColor(R.color.text_black).setContentTextSize(16)
                .setSingleMode(true)
                .setSingleButtonText("确定").setSingleButtonTextColor(R.color.text_black)
                .setCanceledOnTouchOutside(false)
                .setSingleListener(new DialogInterface.OnSingleClickListener<NormalAlertDialog>() {
                    @Override
                    public void clickSingleButton(NormalAlertDialog dialog, View view) {
                        dialog.dismiss();
                    }
                })
                .build()
                .show();
    }
}
