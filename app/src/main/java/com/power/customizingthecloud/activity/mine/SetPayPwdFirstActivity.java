package com.power.customizingthecloud.activity.mine;

import android.content.Intent;
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
import butterknife.OnClick;

public class SetPayPwdFirstActivity extends BaseActivity {

    @BindView(R.id.phone_et)
    EditText phoneEt;
    @BindView(R.id.code_et)
    EditText codeEt;
    @BindView(R.id.getcode_tv)
    TextView getcodeTv;
    @BindView(R.id.jump_tv)
    TextView jumpTv;
    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_pay_pwd_first);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("手机号验证");
    }

    @OnClick({R.id.getcode_tv, R.id.jump_tv, R.id.title_back_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.getcode_tv:
                SendSmsTimerUtils.sendSms(getcodeTv, R.color.green, R.color.green);
                break;
            case R.id.jump_tv:
                startActivity(new Intent(mContext,SetPayPwdSecondActivity.class));
                break;
        }
    }
}
