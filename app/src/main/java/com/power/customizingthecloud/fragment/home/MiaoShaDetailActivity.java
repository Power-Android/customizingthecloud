package com.power.customizingthecloud.fragment.home;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.login.LoginActivity;
import com.power.customizingthecloud.utils.BannerUtils;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.view.BaseDialog;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MiaoShaDetailActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_message_iv)
    ImageView mTitleMessageIv;
    @BindView(R.id.title_back_iv)
    ImageView mTitleBackIv;
    @BindView(R.id.title_content_tv)
    TextView mTitleContentTv;
    @BindView(R.id.title_sign_in_iv)
    ImageView mTitleSignInIv;
    @BindView(R.id.title_setting_iv)
    ImageView mTitleSettingIv;
    @BindView(R.id.title_shopcar_iv)
    ImageView mTitleShopcarIv;
    @BindView(R.id.title_share_iv)
    ImageView mTitleShareIv;
    @BindView(R.id.title_content_right_tv)
    TextView mTitleContentRightTv;
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_price)
    TextView mTvPrice;
    @BindView(R.id.tv_shengyu)
    TextView mTvShengyu;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.detail_tv)
    TextView mDetailTv;
    @BindView(R.id.indicator_detail)
    View mIndicatorDetail;
    @BindView(R.id.detail_ll)
    LinearLayout mDetailLl;
    @BindView(R.id.canshu_tv)
    TextView mCanshuTv;
    @BindView(R.id.indicator_canshu)
    View mIndicatorCanshu;
    @BindView(R.id.canshu_ll)
    LinearLayout mCanshuLl;
    @BindView(R.id.pingjia_tv)
    TextView mPingjiaTv;
    @BindView(R.id.indicator_pingjia)
    View mIndicatorPingjia;
    @BindView(R.id.pingjia_ll)
    LinearLayout mPingjiaLl;
    @BindView(R.id.webview)
    WebView mWebview;
    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.tv_yuanjia)
    TextView mTvYuanjia;
    @BindView(R.id.recycler_canshu)
    RecyclerView mRecyclerCanshu;
    @BindView(R.id.ll_canshu)
    LinearLayout mLlCanshu;
    @BindView(R.id.tv_lianximaijia)
    TextView mTvLianximaijia;
    @BindView(R.id.tv_buy)
    TextView mTvBuy;
    private BaseDialog mDialog;
    private BaseDialog.Builder mBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miao_sha_detail);
        ButterKnife.bind(this);
        mTitleBackIv.setVisibility(View.VISIBLE);
        mTitleBackIv.setOnClickListener(this);
        mTitleContentTv.setText("商品详情");
        mTitleShareIv.setVisibility(View.VISIBLE);
        mTitleShareIv.setOnClickListener(this);
        mTvLianximaijia.setOnClickListener(this);
        mTvBuy.setOnClickListener(this);
        //添加删除线
        mTvYuanjia.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        BannerUtils.startBanner(mBanner, new ArrayList<String>());
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.setNestedScrollingEnabled(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.title_share_iv:
                showShareDialog();
                break;
            case R.id.tv_lianximaijia:
                String userid = SpUtils.getString(this, "userid", "");
                if (TextUtils.isEmpty(userid)){
                    startActivity(new Intent(this, LoginActivity.class));
                    overridePendingTransition(R.anim.push_bottom_in,R.anim.push_bottom_out);
                    return;
                }
                break;
            case R.id.tv_buy:
                String userid2 = SpUtils.getString(this, "userid", "");
                if (TextUtils.isEmpty(userid2)){
                    startActivity(new Intent(this, LoginActivity.class));
                    overridePendingTransition(R.anim.push_bottom_in,R.anim.push_bottom_out);
                    return;
                }
                startActivity(new Intent(this,MiaoConfirmOrderActivity.class));
                break;
        }
    }

    private void showShareDialog() {
        mBuilder = new BaseDialog.Builder(this);
        mDialog = mBuilder.setViewId(R.layout.dialog_share)
                //设置dialogpadding
                .setPaddingdp(0, 0, 0, 0)
                //设置显示位置
                .setGravity(Gravity.BOTTOM)
                //设置动画
                .setAnimation(R.style.Bottom_Top_aniamtion)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(true)
                //设置监听事件
                .builder();
        mDialog.show();
        mDialog.getView(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        mDialog.getView(R.id.tv_weixin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                startActivity(new Intent(MiaoShaDetailActivity.this,ShareSuccessActivity.class));
            }
        });
        mDialog.getView(R.id.tv_pengyouquan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                startActivity(new Intent(MiaoShaDetailActivity.this,ShareSuccessActivity.class));
            }
        });
        mDialog.getView(R.id.tv_zone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                startActivity(new Intent(MiaoShaDetailActivity.this,ShareSuccessActivity.class));
            }
        });
        mDialog.getView(R.id.tv_qq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                startActivity(new Intent(MiaoShaDetailActivity.this,ShareSuccessActivity.class));
            }
        });
        mDialog.getView(R.id.tv_sina).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                startActivity(new Intent(MiaoShaDetailActivity.this,ShareSuccessActivity.class));
            }
        });
    }

    @OnClick(R.id.detail_ll)
    public void detail() {
        initDetailColor();
        mWebview.setVisibility(View.VISIBLE);
        mRecycler.setVisibility(View.GONE);
        mLlCanshu.setVisibility(View.GONE);
    }

    private void initDetailColor() {
        mDetailTv.setTextColor(getResources().getColor(R.color.green));
        mIndicatorDetail.setBackgroundColor(getResources().getColor(R.color.green));
        mCanshuTv.setTextColor(getResources().getColor(R.color.gray));
        mIndicatorCanshu.setBackgroundColor(getResources().getColor(R.color.white));
        mPingjiaTv.setTextColor(getResources().getColor(R.color.gray));
        mIndicatorPingjia.setBackgroundColor(getResources().getColor(R.color.white));
    }

    @OnClick(R.id.canshu_ll)
    public void canshu() {
        initCanShuColor();
        mWebview.setVisibility(View.GONE);
        mRecycler.setVisibility(View.GONE);
        mLlCanshu.setVisibility(View.VISIBLE);
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        mRecyclerCanshu.setLayoutManager(new LinearLayoutManager(this));
        CanShuAdapter recordAdapter = new CanShuAdapter(R.layout.item_canshu, list);
        mRecyclerCanshu.setAdapter(recordAdapter);
    }

    private class CanShuAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public CanShuAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
        }
    }

    private void initCanShuColor() {
        mDetailTv.setTextColor(getResources().getColor(R.color.gray));
        mIndicatorDetail.setBackgroundColor(getResources().getColor(R.color.white));
        mCanshuTv.setTextColor(getResources().getColor(R.color.green));
        mIndicatorCanshu.setBackgroundColor(getResources().getColor(R.color.green));
        mPingjiaTv.setTextColor(getResources().getColor(R.color.gray));
        mIndicatorPingjia.setBackgroundColor(getResources().getColor(R.color.white));
    }

    @OnClick(R.id.pingjia_ll)
    public void pingjia() {
        initPingJiaColor();
        mWebview.setVisibility(View.GONE);
        mRecycler.setVisibility(View.VISIBLE);
        mLlCanshu.setVisibility(View.GONE);
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        PingJiaAdapter paiHangAdapter = new PingJiaAdapter(R.layout.item_pingjia, list);
        mRecycler.setAdapter(paiHangAdapter);
    }

    private class PingJiaAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public PingJiaAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }
    }

    private void initPingJiaColor() {
        mDetailTv.setTextColor(getResources().getColor(R.color.gray));
        mIndicatorDetail.setBackgroundColor(getResources().getColor(R.color.white));
        mCanshuTv.setTextColor(getResources().getColor(R.color.gray));
        mIndicatorCanshu.setBackgroundColor(getResources().getColor(R.color.white));
        mPingjiaTv.setTextColor(getResources().getColor(R.color.green));
        mIndicatorPingjia.setBackgroundColor(getResources().getColor(R.color.green));
    }

}
