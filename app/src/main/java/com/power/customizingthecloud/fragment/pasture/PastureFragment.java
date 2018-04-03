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
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseFragment;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.fragment.home.bean.MuChangTypeBean;
import com.power.customizingthecloud.fragment.home.jiankong.JianKongActivity;
import com.power.customizingthecloud.fragment.home.renyang.RenYangListActivity;
import com.power.customizingthecloud.utils.Urls;

import java.util.List;

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
    private List<MuChangTypeBean.DataEntity> mData;
    private Intent mIntent1;

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
        mIntent1 = new Intent(mContext, JianKongActivity.class);
        OkGo.<MuChangTypeBean>get(Urls.BASEURL + "api/v2/muchang")
                .tag(this)
                .execute(new DialogCallback<MuChangTypeBean>(mActivity, MuChangTypeBean.class) {
                    @Override
                    public void onSuccess(Response<MuChangTypeBean> response) {
                        MuChangTypeBean typeBean = response.body();
                        int code = typeBean.getCode();
                        if (code == 0) {
                            Toast.makeText(mContext, typeBean.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (code == 1) {
                            mData = typeBean.getData();
                        }
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_lv:
                startActivity(new Intent(mContext, RenYangListActivity.class));
                break;
            case R.id.ll_sportA:
                mIntent1.putExtra("class_id", mData.get(0).getId());
                startActivity(mIntent1);
                break;
            case R.id.ll_sportB:
                mIntent1.putExtra("class_id", mData.get(1).getId());
                startActivity(mIntent1);
                break;
            case R.id.ll_cusiliao:
                mIntent1.putExtra("class_id", mData.get(2).getId());
                startActivity(mIntent1);
                break;
            case R.id.ll_lvmumsiyang:
                mIntent1.putExtra("class_id", mData.get(3).getId());
                startActivity(mIntent1);
                break;
            case R.id.ll_jingliao:
                mIntent1.putExtra("class_id", mData.get(4).getId());
                startActivity(mIntent1);
                break;
            case R.id.ll_lvmumfanzhi:
                mIntent1.putExtra("class_id", mData.get(5).getId());
                startActivity(mIntent1);
                break;
            case R.id.ll_lvbaby:
                mIntent1.putExtra("class_id", mData.get(6).getId());
                startActivity(mIntent1);
                break;
            case R.id.ll_gelijianyi:
                mIntent1.putExtra("class_id", mData.get(7).getId());
                startActivity(mIntent1);
                break;
            case R.id.ll_tuzaichejian:
                mIntent1.putExtra("class_id", mData.get(8).getId());
                startActivity(mIntent1);
                break;
            case R.id.ll_lvmeat:
                mIntent1.putExtra("class_id", mData.get(9).getId());
                startActivity(mIntent1);
                break;
        }
    }
}
