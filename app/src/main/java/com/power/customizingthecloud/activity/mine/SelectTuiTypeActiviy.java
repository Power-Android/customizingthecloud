package com.power.customizingthecloud.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.power.customizingthecloud.MyApplication;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectTuiTypeActiviy extends BaseActivity {

    @BindView(R.id.title_message_iv)
    ImageView titleMessageIv;
    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_list_iv)
    ImageView titleListIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.title_sign_in_iv)
    ImageView titleSignInIv;
    @BindView(R.id.title_setting_iv)
    ImageView titleSettingIv;
    @BindView(R.id.title_qrcode_iv)
    ImageView titleQrcodeIv;
    @BindView(R.id.title_shopcar_iv)
    ImageView titleShopcarIv;
    @BindView(R.id.title_share_iv)
    ImageView titleShareIv;
    @BindView(R.id.title_search_iv)
    ImageView titleSearchIv;
    @BindView(R.id.title_jia_iv)
    ImageView titleJiaIv;
    @BindView(R.id.title_kefu_iv)
    ImageView titleKefuIv;
    @BindView(R.id.title_content_right_tv)
    TextView titleContentRightTv;
    @BindView(R.id.face_iv)
    ImageView faceIv;
    @BindView(R.id.name_tv)
    TextView nameTv;
    @BindView(R.id.fenlei_tv)
    TextView fenleiTv;
    @BindView(R.id.view_01)
    ImageView view01;
    @BindView(R.id.ll_tuikuan)
    RelativeLayout llTuikuan;
    @BindView(R.id.view_03)
    ImageView view03;
    @BindView(R.id.ll_tuihuotuikuan)
    RelativeLayout llTuihuotuikuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_tui_type_activiy);
        ButterKnife.bind(this);
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("退款类型");
        String image = getIntent().getStringExtra("image");
        Glide.with(MyApplication.getGloableContext()).load(image).into(faceIv);
    }

    @OnClick({R.id.title_back_iv, R.id.ll_tuihuotuikuan, R.id.ll_tuikuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.ll_tuihuotuikuan:
                Intent intent = new Intent(mContext, OnlyTuiMoneyActiviy.class);
                intent.putExtra("name", getIntent().getStringExtra("name"));
                intent.putExtra("type", getIntent().getStringExtra("type"));
                intent.putExtra("image", getIntent().getStringExtra("image"));
                intent.putExtra("price", getIntent().getStringExtra("price"));
                intent.putExtra("order_id", getIntent().getStringExtra("order_id"));
                startActivity(intent);
                break;
            case R.id.ll_tuikuan:
                Intent intent2 = new Intent(mContext, TuiHuoAndMoneyActivity.class);
                intent2.putExtra("name", getIntent().getStringExtra("name"));
                intent2.putExtra("type", getIntent().getStringExtra("type"));
                intent2.putExtra("image", getIntent().getStringExtra("image"));
                intent2.putExtra("price", getIntent().getStringExtra("price"));
                intent2.putExtra("order_id", getIntent().getStringExtra("order_id"));
                startActivity(intent2);
                break;
        }
    }
}
