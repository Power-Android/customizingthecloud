package com.power.customizingthecloud.activity.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AccountSafeActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_back_iv) ImageView titleBackIv;
    @BindView(R.id.title_content_tv) TextView titleContentTv;
    @BindView(R.id.mmxg_rl) RelativeLayout mmxgRl;
    @BindView(R.id.szzfmm_rl) RelativeLayout szzfmmRl;
    @BindView(R.id.yxbd_rl) RelativeLayout yxbdRl;
    @BindView(R.id.zfbbd_rl) RelativeLayout zfbbdRl;
    @BindView(R.id.yhkbd_rl) RelativeLayout yhkbdRl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_safe);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleBackIv.setOnClickListener(this);
        titleContentTv.setText("账户安全");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.mmxg_rl://密码修改
                break;
            case R.id.szzfmm_rl://设置支付密码
                break;
            case R.id.yxbd_rl://邮箱绑定
                break;
            case R.id.zfbbd_rl://支付宝绑定
                break;
            case R.id.yhkbd_rl://银行卡绑定
                break;
        }
    }
}
