package com.power.customizingthecloud.activity.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.ReserveBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReserveDetailActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.name_tv)
    TextView nameTv;
    @BindView(R.id.phone_tv)
    TextView phoneTv;
    @BindView(R.id.date1_tv)
    TextView date1Tv;
    @BindView(R.id.num_tv)
    TextView numTv;
    @BindView(R.id.seat_tv)
    TextView seatTv;
    @BindView(R.id.date2_tv)
    TextView date2Tv;
    @BindView(R.id.beizhu_tv)
    TextView beizhuTv;
    @BindView(R.id.tel_tv)
    TextView telTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_detail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("我的预订");

        getIntentData();
    }

    private void getIntentData() {
        ReserveBean.DataBeanX.DataBean detail = (ReserveBean.DataBeanX.DataBean) getIntent().getSerializableExtra("detail");
        titleTv.setText(detail.getName());
        nameTv.setText(detail.getUser_name());
        phoneTv.setText(detail.getMobile());
        date1Tv.setText(detail.getRestaurant_time());
        numTv.setText(detail.getNumber());
        seatTv.setText(detail.getSeat());
        date2Tv.setText(detail.getAdd_time());
        beizhuTv.setText(detail.getRemarks());
    }

    @OnClick(R.id.title_back_iv)
    public void onViewClicked() {
        finish();
    }
}
