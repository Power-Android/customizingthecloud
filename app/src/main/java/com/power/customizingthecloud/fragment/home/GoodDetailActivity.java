package com.power.customizingthecloud.fragment.home;

import android.content.Intent;
import android.os.Bundle;
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
import com.power.customizingthecloud.db.LookBean;
import com.power.customizingthecloud.db.LookUtils;
import com.power.customizingthecloud.fragment.home.bean.GoodDetailBean;
import com.power.customizingthecloud.fragment.shop.GoodConfirmOrderActivity;
import com.power.customizingthecloud.login.LoginActivity;
import com.power.customizingthecloud.utils.BannerUtils;
import com.power.customizingthecloud.utils.CommonUtils;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.Urls;
import com.power.customizingthecloud.view.BaseDialog;
import com.power.customizingthecloud.view.SnappingStepper;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

public class GoodDetailActivity extends BaseActivity implements View.OnClickListener {

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
    TextView tv_price;
    @BindView(R.id.tv_good_type)
    TextView mTvGoodType;
    @BindView(R.id.tv_diyong)
    TextView mTvDiyong;
    @BindView(R.id.item_stepper)
    SnappingStepper mItemStepper;
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
    @BindView(R.id.recycler_canshu)
    RecyclerView mRecyclerCanshu;
    @BindView(R.id.ll_canshu)
    LinearLayout mLlCanshu;
    @BindView(R.id.tv_lianximaijia)
    TextView mTvLianximaijia;
    @BindView(R.id.tv_insertcar)
    TextView mTvInsertcar;
    @BindView(R.id.tv_buy)
    TextView mTvBuy;
    @BindView(R.id.tv_unit)
    TextView tv_unit;
    @BindView(R.id.lv_xiangqing)
    ImageView mLvXiangqing;
    private BaseDialog mDialog;
    private BaseDialog.Builder mBuilder;
    private List<String> imgList = new ArrayList<>();
    private List<String> mSpec_value = new ArrayList<>();
    private List<GoodDetailBean.DataEntity.CommentsEntity> mComments;
    private GoodDetailBean.DataEntity mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_detail);
        ButterKnife.bind(this);
        mTitleBackIv.setVisibility(View.VISIBLE);
        mTitleBackIv.setOnClickListener(this);
        mTitleContentTv.setText("商品详情");
        mTitleShareIv.setVisibility(View.VISIBLE);
        mTitleShareIv.setOnClickListener(this);
        mTvLianximaijia.setOnClickListener(this);
        mTvInsertcar.setOnClickListener(this);
        mTvBuy.setOnClickListener(this);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.setNestedScrollingEnabled(false);
        mItemStepper.setContentBackground(R.drawable.bg_stepper_green);
        mItemStepper.setButtonBackGround(R.color.green);
        mItemStepper.setContentTextColor(R.color.green);
        mItemStepper.setLeftButtonResources(R.drawable.jianhao_white);
        mItemStepper.setRightButtonResources(R.drawable.jiahao_white);
        initWeb();
        initData();
    }

    private void initWeb() {
        WebSettings webSettings = mWebview.getSettings();
        webSettings.setJavaScriptEnabled(true); // 设置支持javascript脚本
        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
//        webSettings.setDefaultFontSize(40);
        mWebview.setWebChromeClient(new WebChromeClient());
    }

    private void initData() {
        String id = getIntent().getStringExtra("id");
        HttpParams params = new HttpParams();
        params.put("good_id", id);
        params.put("type", "1");
        OkGo.<GoodDetailBean>post(Urls.BASEURL + "api/v2/good/good-show")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<GoodDetailBean>(GoodDetailActivity.this, GoodDetailBean.class) {
                    @Override
                    public void onSuccess(Response<GoodDetailBean> response) {
                        GoodDetailBean bean = response.body();
                        int code = bean.getCode();
                        if (code == 0) {
                            Toast.makeText(mContext, bean.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (code == 1) {
                            imgList.clear();
                            mData = bean.getData();
                            List<GoodDetailBean.DataEntity.ImagesEntity> images = mData.getImages();
                            if (images != null && images.size() > 0) {
                                for (int i = 0; i < images.size(); i++) {
                                    imgList.add(images.get(i).getImag());
                                }
                            }
                            mItemStepper.setMaxValue(mData.getGood_storage());
                            BannerUtils.startBanner(mBanner, imgList);
                            mTvName.setText(mData.getName());
                            tv_unit.setText("/" + mData.getUnit());
                            tv_price.setText(mData.getPrice());
                            mTvDiyong.setText("可用" + mData.getEselsohr_deduction() + "驴耳朵抵用" + mData.getEselsohr_deduction() + "元");
                            mTvGoodType.setText("商品分类：" + mData.getClass_name());
                            String spec_value = mData.getSpec_value();
                            String[] split = spec_value.split("@");
                            for (int i = 0; i < split.length; i++) {
                                mSpec_value.add(split[i]);
                            }
                            mComments = mData.getComments();
                            detail();
                            //添加数据库，我的足迹
                            String userid = SpUtils.getString(mContext, "userid", "");
                            if (!TextUtils.isEmpty(userid)) {
                                LookBean lookBean = new LookBean();
                                lookBean.setUser_id(Integer.parseInt(userid));
                                lookBean.setGood_id(mData.getId());
                                lookBean.setClass_name(mData.getClass_name());
                                lookBean.setName(mData.getName());
                                lookBean.setPrice(mData.getPrice());
                                lookBean.setImage(mData.getThumb());
                                lookBean.setTime(System.currentTimeMillis());
                                LookUtils.add(lookBean);
                            }
                        }
                    }
                });
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
                String userid = SpUtils.getString(mContext, "userid", "");
                if (TextUtils.isEmpty(userid)) {
                    startActivity(new Intent(mContext, LoginActivity.class));
                    overridePendingTransition(R.anim.push_bottom_in, R.anim.push_bottom_out);
                    return;
                }
                RongIM.getInstance().startConversation(this, Conversation.ConversationType.PRIVATE, "admin_1", "客服牧小童");
                break;
            case R.id.tv_insertcar:
                String userid2 = SpUtils.getString(mContext, "userid", "");
                if (TextUtils.isEmpty(userid2)) {
                    startActivity(new Intent(mContext, LoginActivity.class));
                    overridePendingTransition(R.anim.push_bottom_in, R.anim.push_bottom_out);
                    return;
                }
                CommonUtils.insertCar2(this, mData.getId() + "", "good", mItemStepper.getValue());
                break;
            case R.id.tv_buy:
                String userid3 = SpUtils.getString(mContext, "userid", "");
                if (TextUtils.isEmpty(userid3)) {
                    startActivity(new Intent(mContext, LoginActivity.class));
                    overridePendingTransition(R.anim.push_bottom_in, R.anim.push_bottom_out);
                    return;
                }
                Intent intent = new Intent(GoodDetailActivity.this, GoodConfirmOrderActivity.class);
                intent.putExtra("good_quantity", mData.getId() + "=" + mItemStepper.getValue());
                intent.putExtra("buy_type", "2");
                startActivity(intent);
                break;
        }
    }

    private void showShareDialog() {
        final String url=Urls.BASEURL+"wap/scCommodityDetails.html?goodId="+getIntent().getStringExtra("id")+"&share=0";
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
                UMShareActivity.shareWebUrl(url, mData.getName(), mData.getThumb(), mData.getClass_name(), GoodDetailActivity.this, SHARE_MEDIA.WEIXIN);
                //                startActivity(new Intent(GoodDetailActivity.this, ShareSuccessActivity.class));
            }
        });
        mDialog.getView(R.id.tv_pengyouquan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                UMShareActivity.shareWebUrl(url, mData.getName(), mData.getThumb(), mData.getClass_name(), GoodDetailActivity.this, SHARE_MEDIA.WEIXIN_CIRCLE);
                //                startActivity(new Intent(GoodDetailActivity.this, ShareSuccessActivity.class));
            }
        });
        mDialog.getView(R.id.tv_zone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                UMShareActivity.shareWebUrl(url, mData.getName(), mData.getThumb(), mData.getClass_name(), GoodDetailActivity.this, SHARE_MEDIA.QZONE);
                //                startActivity(new Intent(GoodDetailActivity.this, ShareSuccessActivity.class));
            }
        });
        mDialog.getView(R.id.tv_qq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                UMShareActivity.shareWebUrl(url, mData.getName(), mData.getThumb(), mData.getClass_name(), GoodDetailActivity.this, SHARE_MEDIA.QQ);
                //                startActivity(new Intent(GoodDetailActivity.this, ShareSuccessActivity.class));
            }
        });
        mDialog.getView(R.id.tv_sina).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                UMShareActivity.shareWebUrl(url, mData.getName(), mData.getThumb(), mData.getClass_name(), GoodDetailActivity.this, SHARE_MEDIA.SINA);
                //                startActivity(new Intent(GoodDetailActivity.this, ShareSuccessActivity.class));
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

    private class PingJiaAdapter extends BaseQuickAdapter<GoodDetailBean.DataEntity.CommentsEntity, BaseViewHolder> {

        public PingJiaAdapter(@LayoutRes int layoutResId, @Nullable List<GoodDetailBean.DataEntity.CommentsEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, GoodDetailBean.DataEntity.CommentsEntity item) {
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
