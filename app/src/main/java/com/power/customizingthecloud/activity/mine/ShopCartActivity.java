package com.power.customizingthecloud.activity.mine;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopCartActivity extends BaseActivity {

    @BindView(R.id.title_back_iv) ImageView titleBackIv;
    @BindView(R.id.title_content_tv) TextView titleContentTv;
    @BindView(R.id.title_content_right_tv) TextView titleContentRightTv;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.all_checkBox) CheckBox allCheckBox;
    @BindView(R.id.all_check_ll) LinearLayout allCheckLl;
    @BindView(R.id.heji_tv) TextView hejiTv;
    @BindView(R.id.jiesuan_tv) TextView jiesuanTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_cart);
        ButterKnife.bind(this);
    }
}
