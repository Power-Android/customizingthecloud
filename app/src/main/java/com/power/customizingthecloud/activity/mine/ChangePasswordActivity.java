package com.power.customizingthecloud.activity.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.utils.SendSmsTimerUtils;

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
                SendSmsTimerUtils.sendSms(getcodeTv, R.color.green, R.color.green);
                break;
            case R.id.change_tv:
                finish();
                break;
        }
    }
}
