package com.power.customizingthecloud.fragment.pasture;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseFragment;
import com.power.customizingthecloud.fragment.home.jiankong.JianKongActivity;
import com.power.customizingthecloud.fragment.home.renyang.RenYangListActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/1/19.
 * 牧场
 */

public class PastureFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.title_message_iv)
    ImageView mTitleMessageIv;
    @BindView(R.id.title_back_iv)
    ImageView mTitleBackIv;
    @BindView(R.id.title_list_iv)
    ImageView mTitleListIv;
    @BindView(R.id.title_content_tv)
    TextView mTitleContentTv;
    @BindView(R.id.title_sign_in_iv)
    ImageView mTitleSignInIv;
    @BindView(R.id.title_setting_iv)
    ImageView mTitleSettingIv;
    @BindView(R.id.title_qrcode_iv)
    ImageView mTitleQrcodeIv;
    @BindView(R.id.title_shopcar_iv)
    ImageView mTitleShopcarIv;
    @BindView(R.id.title_share_iv)
    ImageView mTitleShareIv;
    @BindView(R.id.title_search_iv)
    ImageView mTitleSearchIv;
    @BindView(R.id.title_jia_iv)
    ImageView mTitleJiaIv;
    @BindView(R.id.title_kefu_iv)
    ImageView mTitleKefuIv;
    @BindView(R.id.title_content_right_tv)
    TextView mTitleContentRightTv;
    Unbinder unbinder;
    @BindView(R.id.iv_lv)
    ImageView mIvLv;
    @BindView(R.id.ll_sportA)
    LinearLayout mLlSportA;
    @BindView(R.id.ll_sportB)
    LinearLayout mLlSportB;
    @BindView(R.id.ll_cusiliao)
    LinearLayout mLlCusiliao;
    @BindView(R.id.ll_lvmumsiyang)
    LinearLayout mLlLvmumsiyang;
    @BindView(R.id.ll_lvmumfanzhi)
    LinearLayout mLlLvmumfanzhi;
    @BindView(R.id.ll_lvbaby)
    LinearLayout mLlLvbaby;
    @BindView(R.id.ll_jingliao)
    LinearLayout mLlJingliao;
    @BindView(R.id.ll_gelijianyi)
    LinearLayout mLlGelijianyi;
    @BindView(R.id.ll_tuzaichejian)
    LinearLayout mLlTuzaichejian;
    @BindView(R.id.ll_lvmeat)
    LinearLayout mLlLvmeat;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pasture, null);
        unbinder = ButterKnife.bind(this, view);
        mIvLv.setOnClickListener(this);
        mLlSportA.setOnClickListener(this);
        mLlSportB.setOnClickListener(this);
        mLlCusiliao.setOnClickListener(this);
        mLlLvmumsiyang.setOnClickListener(this);
        mLlLvmumfanzhi.setOnClickListener(this);
        mLlLvbaby.setOnClickListener(this);
        mLlJingliao.setOnClickListener(this);
        mLlGelijianyi.setOnClickListener(this);
        mLlTuzaichejian.setOnClickListener(this);
        mLlLvmeat.setOnClickListener(this);
        return view;
    }

    @Override
    protected void initLazyData() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mTitleContentTv.setText("云端牧场");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(mContext, JianKongActivity.class);
        switch (v.getId()) {
            case R.id.iv_lv:
                startActivity(new Intent(mContext, RenYangListActivity.class));
                break;
            case R.id.ll_sportA:
                intent.putExtra("position", 0);
                startActivity(intent);
                break;
            case R.id.ll_sportB:
                intent.putExtra("position", 1);
                startActivity(intent);
                break;
            case R.id.ll_cusiliao:
                intent.putExtra("position", 2);
                startActivity(intent);
                break;
            case R.id.ll_lvmumsiyang:
                intent.putExtra("position", 3);
                startActivity(intent);
                break;
            case R.id.ll_lvmumfanzhi:
                intent.putExtra("position", 4);
                startActivity(intent);
                break;
            case R.id.ll_lvbaby:
                intent.putExtra("position", 5);
                startActivity(intent);
                break;
            case R.id.ll_jingliao:
                intent.putExtra("position", 6);
                startActivity(intent);
                break;
            case R.id.ll_gelijianyi:
                intent.putExtra("position", 7);
                startActivity(intent);
                break;
            case R.id.ll_tuzaichejian:
                intent.putExtra("position", 8);
                startActivity(intent);
                break;
            case R.id.ll_lvmeat:
                intent.putExtra("position", 9);
                startActivity(intent);
                break;
        }
    }
}
