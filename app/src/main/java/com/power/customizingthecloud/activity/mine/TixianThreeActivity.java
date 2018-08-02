package com.power.customizingthecloud.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TixianThreeActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.card_name_tv)
    TextView cardNameTv;
    @BindView(R.id.money_et)
    EditText moneyEt;
    @BindView(R.id.tixian_tv)
    TextView tixianTv;
    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tixian_three);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleBackIv.setOnClickListener(this);
        titleContentTv.setText("提现");
        cardNameTv.setOnClickListener(this);
        tixianTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.card_name_tv:
                startActivity(new Intent(mContext,SelectorBankCardActivity.class));
                break;
            case R.id.tixian_tv:

                break;
        }
    }
}
