package com.power.customizingthecloud.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.utils.SendSmsTimerUtils;

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
        switch (v.getId()){
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.getcode_tv:
                SendSmsTimerUtils.sendSms(getcodeTv, R.color.green, R.color.green);
                break;
            case R.id.jump_tv:
                if (TextUtils.isEmpty(phoneEt.getText().toString())){
                    Toast.makeText(this, "请输入手机号~", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(codeEt.getText().toString())){
                    Toast.makeText(this, "请输入验证码~", Toast.LENGTH_SHORT).show();
                    return;
                }
                startActivity(new Intent(mContext,TixianThreeActivity.class));
                break;
        }
    }
}
