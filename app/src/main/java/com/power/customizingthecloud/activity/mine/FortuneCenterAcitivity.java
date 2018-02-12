package com.power.customizingthecloud.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.fragment.home.renyang.RenYangListActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FortuneCenterAcitivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_back_iv) ImageView titleBackIv;
    @BindView(R.id.title_content_tv) TextView titleContentTv;
    @BindView(R.id.total_money_tv) TextView totalMoneyTv;
    @BindView(R.id.last_money_tv) TextView lastMoneyTv;
    @BindView(R.id.yi_jian_ti_xian_tv) TextView yiJianTiXianTv;
    @BindView(R.id.dai_fan_ben_jin_tv) TextView daiFanBenJinTv;
    @BindView(R.id.yu_qi_shou_yi_tv) TextView yuQiShouYiTv;
    @BindView(R.id.dai_fan_shou_yi_tv) TextView daiFanShouYiTv;
    @BindView(R.id.ren_yang_zong_shu_tv) TextView renYangZongShuTv;
    @BindView(R.id.ren_yang_zong_shu_rl) RelativeLayout renYangZongShuRl;
    @BindView(R.id.wo_de_ren_yang_rl) RelativeLayout woDeRenYangRl;
    @BindView(R.id.yi_jian_ren_yang_tv) TextView yiJianRenYangTv;
    @BindView(R.id.yangzhi_shouyi_minxi_rl) RelativeLayout yangzhiShouyiMinxiRl;
    @BindView(R.id.tixian_mingxi_rl) RelativeLayout tixianMingxiRl;
    @BindView(R.id.quanbu_ziji_jilu_rl) RelativeLayout quanbuZijiJiluRl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fortune_center_acitivity);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("财富中心");
        titleBackIv.setOnClickListener(this);
        yiJianTiXianTv.setOnClickListener(this);
        renYangZongShuRl.setOnClickListener(this);
        woDeRenYangRl.setOnClickListener(this);
        yiJianRenYangTv.setOnClickListener(this);
        yangzhiShouyiMinxiRl.setOnClickListener(this);
        tixianMingxiRl.setOnClickListener(this);
        quanbuZijiJiluRl.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.yi_jian_ti_xian_tv:
                startActivity(new Intent(mContext,TixianFirstActivity.class));
                break;
            case R.id.ren_yang_zong_shu_rl:
                startActivity(new Intent(mContext,MyRenyangCenterActivity.class));
                break;
            case R.id.wo_de_ren_yang_rl:
                startActivity(new Intent(mContext,MyRenyangCenterActivity.class));
                break;
            case R.id.yi_jian_ren_yang_tv:
                startActivity(new Intent(mContext, RenYangListActivity.class));
                break;
            case R.id.yangzhi_shouyi_minxi_rl:
                startActivity(new Intent(mContext,BreedIncomeDetailActivity.class));
                break;
            case R.id.tixian_mingxi_rl:
                break;
            case R.id.quanbu_ziji_jilu_rl:
                break;
        }
    }
}
