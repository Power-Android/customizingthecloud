package com.power.customizingthecloud.activity.mine;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.utils.TUtils;
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
                    showTip(emailStr);
                }
                break;
        }
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
