package com.power.customizingthecloud.activity.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyTransferAccountsActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_back_iv) ImageView titleBackIv;
    @BindView(R.id.title_content_tv) TextView titleContentTv;
    @BindView(R.id.money_et) EditText moneyEt;
    @BindView(R.id.danhao_et) EditText danhaoEt;
    @BindView(R.id.name_et) EditText nameEt;
    @BindView(R.id.paystyle_tv) TextView paystyleTv;
    @BindView(R.id.paystyle_rl) RelativeLayout paystyleRl;
    @BindView(R.id.tijiao_tv) TextView tijiaoTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_transfer_accounts);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleBackIv.setOnClickListener(this);
        titleContentTv.setText("我的转账");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.paystyle_rl:
                break;
            case R.id.tijiao_tv:
                break;
        }
    }
}
