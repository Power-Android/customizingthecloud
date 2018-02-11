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

public class EditAddressActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_back_iv) ImageView titleBackIv;
    @BindView(R.id.title_content_tv) TextView titleContentTv;
    @BindView(R.id.title_content_right_tv) TextView titleContentRightTv;
    @BindView(R.id.name_et) EditText nameEt;
    @BindView(R.id.phone_et) EditText phoneEt;
    @BindView(R.id.adress_tv) TextView adressTv;
    @BindView(R.id.address_rl) RelativeLayout addressRl;
    @BindView(R.id.jiedao_tv) TextView jiedaoTv;
    @BindView(R.id.jiedao_rl) RelativeLayout jiedaoRl;
    @BindView(R.id.detail_address_et) EditText detailAddressEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleBackIv.setOnClickListener(this);
        titleContentTv.setText("编辑收货地址");
        titleContentRightTv.setVisibility(View.VISIBLE);
        titleContentRightTv.setText("保存");
        titleContentRightTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.title_content_right_tv:
                break;
            case R.id.address_rl:
                break;
            case R.id.jiedao_rl:
                break;
        }
    }
}
