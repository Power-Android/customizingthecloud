package com.power.customizingthecloud.fragment.home;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.MyApplication;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.base.UMShareActivity;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.fragment.home.bean.MiaoDetailBean;
import com.power.customizingthecloud.login.LoginActivity;
import com.power.customizingthecloud.utils.BannerUtils;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.Urls;
import com.power.customizingthecloud.view.BaseDialog;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

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
    @BindView(R.id.lv_xiangqing)
    ImageView mLvXiangqing;
    @BindView(R.id.tv_unit)
    TextView tv_unit;
    private BaseDialog mDialog;
    private BaseDialog.Builder mBuilder;
    private List<String> imgList = new ArrayList<>();
    private List<String> mSpec_value = new ArrayList<>();
    private List<MiaoDetailBean.DataEntity.CommentsEntity> mComments;
    private MiaoDetailBean.DataEntity mData;
    private CountDownTimer timer;

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
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.setNestedScrollingEnabled(false);
        initWeb();
        initData();
    }

    private void initWeb() {
        WebSettings webSettings = mWebview.getSettings();
        webSettings.setJavaScriptEnabled(true); // 设置支持javascript脚本
        //设置自适应屏幕，两者合用
//        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
//        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
//        webSettings.setDefaultFontSize(40);
        mWebview.setWebChromeClient(new WebChromeClient());
    }

    private void initData() {
        String id = getIntent().getStringExtra("id");
        HttpParams params = new HttpParams();
        params.put("good_id", id);
        params.put("type", "2");
        OkGo.<MiaoDetailBean>post(Urls.BASEURL + "api/v2/good/good-show")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<MiaoDetailBean>(MiaoShaDetailActivity.this, MiaoDetailBean.class) {
                    @Override
                    public void onSuccess(Response<MiaoDetailBean> response) {
                        MiaoDetailBean bean = response.body();
                        int code = bean.getCode();
                        if (code == 0) {
                            Toast.makeText(mContext, bean.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (code == 1) {
                            imgList.clear();
                            mData = bean.getData();
                            List<MiaoDetailBean.DataEntity.ImagesEntity> images = mData.getImages();
                            if (images != null && images.size() > 0) {
                                for (int i = 0; i < images.size(); i++) {
                                    imgList.add(images.get(i).getImag());
                                }
                            }
                            BannerUtils.startBanner(mBanner, imgList);
                            mTvName.setText(mData.getName());
                            tv_unit.setText(mData.getUnit());
                            mTvShengyu.setText(mData.getSeckill_storage() + "");
                            mTvPrice.setText(mData.getSeckill_price());
                            long time1 = mData.getSeckill_end_time() * 1000L - System.currentTimeMillis();
                            long time = time1 / 1000 / 60 / 60;
                            if (time1 <= 0) {
                                mTvTime.setText("秒杀已结束");
                                mTvBuy.setBackgroundColor(getResources().getColor(R.color.huise));
                                mTvBuy.setClickable(false);
                            } else if (time >= 24) {
                                int day = (int) (time / 24);
                                int hour = (int) (time % 24);
                                mTvTime.setText("距离秒杀结束还有" + day + "天" + hour + "小时");
                                startTimer(time1);
                            } else {
                                mTvTime.setText("距离秒杀结束还有0天" + time + "小时");
                                startTimer(time1);
                            }
                            String spec_value = mData.getSpec_value();
                            String[] split = spec_value.split("@");
                            for (int i = 0; i < split.length; i++) {
                                mSpec_value.add(split[i]);
                            }
                            mComments = mData.getComments();
                            detail();
                        }
                    }
                });
    }

    private void startTimer(long time) {
        timer = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // TODO Auto-generated method stub
                //                tv.setText("还剩"+millisUntilFinished/1000+"秒");
            }

            @Override
            public void onFinish() {
                //                tv.setText("倒计时完毕了");
                mTvTime.setText("秒杀已结束");
                mTvBuy.setBackgroundColor(getResources().getColor(R.color.huise));
                mTvBuy.setClickable(false);
            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null)
            timer.cancel();
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
                if (TextUtils.isEmpty(userid)) {
                    startActivity(new Intent(this, LoginActivity.class));
                    overridePendingTransition(R.anim.push_bottom_in, R.anim.push_bottom_out);
                    return;
                }
                RongIM.getInstance().startConversation(this, Conversation.ConversationType.PRIVATE, "admin_1", "客服牧小童");
                break;
            case R.id.tv_buy:
                String userid2 = SpUtils.getString(this, "userid", "");
                if (TextUtils.isEmpty(userid2)) {
                    startActivity(new Intent(this, LoginActivity.class));
                    overridePendingTransition(R.anim.push_bottom_in, R.anim.push_bottom_out);
                    return;
                }
                Intent intent = new Intent(this, MiaoConfirmOrderActivity.class);
                if (mData != null)
                    intent.putExtra("good_quantity", mData.getId() + "=1");
                startActivity(intent);
                break;
        }
    }

    private void showShareDialog() {
        final String url=Urls.BASEURL+"wap/msCommodityDetails.html?goodId="+getIntent().getStringExtra("id")+"&share=0";
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
                UMShareActivity.shareWebUrl(url, mData.getName(), mData.getImages().get(0).getImag(), mData.getClass_name(), MiaoShaDetailActivity.this, SHARE_MEDIA.WEIXIN);
                //                startActivity(new Intent(MiaoShaDetailActivity.this, ShareSuccessActivity.class));
            }
        });
        mDialog.getView(R.id.tv_pengyouquan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                UMShareActivity.shareWebUrl(url, mData.getName(), mData.getImages().get(0).getImag(), mData.getClass_name(), MiaoShaDetailActivity.this, SHARE_MEDIA.WEIXIN_CIRCLE);
                //                startActivity(new Intent(MiaoShaDetailActivity.this, ShareSuccessActivity.class));
            }
        });
        mDialog.getView(R.id.tv_zone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                UMShareActivity.shareWebUrl(url, mData.getName(), mData.getImages().get(0).getImag(), mData.getClass_name(), MiaoShaDetailActivity.this, SHARE_MEDIA.QZONE);
                //                startActivity(new Intent(MiaoShaDetailActivity.this, ShareSuccessActivity.class));
            }
        });
        mDialog.getView(R.id.tv_qq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                UMShareActivity.shareWebUrl(url, mData.getName(), mData.getImages().get(0).getImag(), mData.getClass_name(), MiaoShaDetailActivity.this, SHARE_MEDIA.QQ);
                //                startActivity(new Intent(MiaoShaDetailActivity.this, ShareSuccessActivity.class));
            }
        });
        mDialog.getView(R.id.tv_sina).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                UMShareActivity.shareWebUrl(url, mData.getName(), mData.getImages().get(0).getImag(), mData.getClass_name(), MiaoShaDetailActivity.this, SHARE_MEDIA.SINA);
                //                startActivity(new Intent(MiaoShaDetailActivity.this, ShareSuccessActivity.class));
            }
        });
    }

    @OnClick(R.id.detail_ll)
    public void detail() {
        initDetailColor();
        mWebview.setVisibility(View.VISIBLE);
//        mWebview.loadData(mData.getBody(), "text/html;charset=UTF-8", null);
        mWebview.loadDataWithBaseURL("",mData.getBody()+js,  "text/html", "UTF-8", null);
        mLvXiangqing.setVisibility(View.GONE);
        mRecycler.setVisibility(View.GONE);
        mLlCanshu.setVisibility(View.GONE);
    }

    //这个是控制加载富文本时候图片适应屏幕
    private String js = "<script type=\"text/javascript\">"+

            "var imgs = document.getElementsByTagName('img');" + // 找到img标签

            "for(var i = 0; i<imgs.length; i++){" + // 逐个改变

            "imgs[i].style.width = '100%';" + // 宽度改为100%

            "imgs[i].style.height = 'auto';" +

            "}" + "</script>";

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
        mRecyclerCanshu.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerCanshu.setNestedScrollingEnabled(false);
        CanShuAdapter recordAdapter = new CanShuAdapter(R.layout.item_canshu, mSpec_value);
        mRecyclerCanshu.setAdapter(recordAdapter);
        mLvXiangqing.setVisibility(View.GONE);
    }

    private class CanShuAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public CanShuAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.tv_content, item);
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
        PingJiaAdapter paiHangAdapter = new PingJiaAdapter(R.layout.item_pingjia2, mComments);
        mRecycler.setAdapter(paiHangAdapter);
        mLvXiangqing.setVisibility(View.GONE);
    }

    private class PingJiaAdapter extends BaseQuickAdapter<MiaoDetailBean.DataEntity.CommentsEntity, BaseViewHolder> {

        public PingJiaAdapter(@LayoutRes int layoutResId, @Nullable List<MiaoDetailBean.DataEntity.CommentsEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, MiaoDetailBean.DataEntity.CommentsEntity item) {
            Glide.with(MyApplication.getGloableContext()).load(item.getUser_avatar()).into((ImageView) helper.getView(R.id.iv_head));
            helper.setText(R.id.tv_name, item.getUser_name())
                    .setText(R.id.tv_time, item.getTime())
                    .setText(R.id.tv_content, item.getContent());
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
