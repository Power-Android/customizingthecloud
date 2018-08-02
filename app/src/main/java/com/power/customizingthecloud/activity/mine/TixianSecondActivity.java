package com.power.customizingthecloud.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.utils.TUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TixianSecondActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.name_et)
    EditText nameEt;
    @BindView(R.id.card_et)
    EditText cardEt;
    @BindView(R.id.bank_et)
    EditText bankEt;
    @BindView(R.id.jump_tv)
    TextView jumpTv;
    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tixian_second);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleBackIv.setOnClickListener(this);
        titleContentTv.setText("个人信息");
        jumpTv.setOnClickListener(this);
        type = getIntent().getStringExtra("type");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.jump_tv:
                if (TextUtils.isEmpty(nameEt.getText().toString().trim())) {
                    TUtils.showShort(mContext, "请输入持卡人真实姓名");
                    return;
                }
                if (TextUtils.isEmpty(cardEt.getText().toString().trim())) {
                    TUtils.showShort(mContext, "请输入本人银行卡号");
                    return;
                }
                if (TextUtils.isEmpty(bankEt.getText().toString().trim())) {
                    TUtils.showShort(mContext, "请选择所在银行");
                    return;
                }
                if (TextUtils.equals("addCard", type)) {
                    Intent intent = new Intent(mContext, BindCartActivity.class);
                    intent.putExtra("card_name",nameEt.getText().toString().trim());
                    intent.putExtra("bank_card",cardEt.getText().toString().trim());
                    startActivity(intent);
                } else {
                    startActivity(new Intent(mContext, TixianThreeActivity.class));
                }
                break;
        }
    }
}
