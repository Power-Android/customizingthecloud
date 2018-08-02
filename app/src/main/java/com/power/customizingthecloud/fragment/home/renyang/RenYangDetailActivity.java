package com.power.customizingthecloud.fragment.home.renyang;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.MyApplication;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.activity.mine.MyOrderActivity;
import com.power.customizingthecloud.activity.mine.MyRenyangCenterActivity;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.AliPayBean;
import com.power.customizingthecloud.bean.BaseBean;
import com.power.customizingthecloud.bean.EventBean;
import com.power.customizingthecloud.bean.PayResult;
import com.power.customizingthecloud.bean.WXPayBean;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.fragment.home.ServiceAgreementActivity;
import com.power.customizingthecloud.fragment.home.bean.RenYangDetailBean;
import com.power.customizingthecloud.listener.SnappingStepperValueChangeListener;
import com.power.customizingthecloud.login.LoginActivity;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.Urls;
import com.power.customizingthecloud.view.BaseDialog;
import com.power.customizingthecloud.view.CustomViewPager;
import com.power.customizingthecloud.view.SnappingStepper;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RenYangDetailActivity extends BaseActivity implements View.OnClickListener {

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
    @BindView(R.id.title_content_right_tv)
    TextView mTitleContentRightTv;
    @BindView(R.id.iv_top)
    ImageView mIvTop;
    @BindView(R.id.recycler_top)
    RecyclerView mRecyclerTop;
    @BindView(R.id.tv_shengyu)
    TextView mTvShengyu;
    @BindView(R.id.tv_total_count)
    TextView mTvTotalCount;
    @BindView(R.id.item_stepper)
    SnappingStepper mItemStepper;
    @BindView(R.id.tv_price)
    TextView mTvPrice;
    @BindView(R.id.tv_lirun)
    TextView mTvLirun;
    @BindView(R.id.tv_xieyi)
    TextView mTvXieyi;
    @BindView(R.id.tablayout)
    TabLayout mTablayout;
    @BindView(R.id.viewpager)
    CustomViewPager mViewpager;
    @BindView(R.id.xiangmu_tv)
    TextView mXiangmuTv;
    @BindView(R.id.indicator_xiangmu)
    View mIndicatorXiangmu;
    @BindView(R.id.xiangmu_ll)
    LinearLayout mXiangmuLl;
    @BindView(R.id.record_tv)
    TextView mRecordTv;
    @BindView(R.id.indicator_record)
    View mIndicatorRecord;
    @BindView(R.id.record_ll)
    LinearLayout mRecordLl;
    @BindView(R.id.paihang_tv)
    TextView mPaihangTv;
    @BindView(R.id.indicator_paihang)
    View mIndicatorPaihang;
    @BindView(R.id.paihang_ll)
    LinearLayout mPaihangLl;
    @BindView(R.id.des_tv)
    TextView mDesTv;
    @BindView(R.id.indicator_des)
    View mIndicatorDes;
    @BindView(R.id.des_ll)
    LinearLayout mDesLl;
    @BindView(R.id.webview)
    WebView mWebview;
    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.tv_commit)
    TextView mTvCommit;
    @BindView(R.id.iv_shouqing)
    ImageView iv_shouqing;
    @BindView(R.id.iv_check)
    ImageView iv_check;
    @BindView(R.id.title_shopcar_iv)
    ImageView mTitleShopcarIv;
    @BindView(R.id.title_share_iv)
    ImageView mTitleShareIv;
    @BindView(R.id.lv_xiangqing)
    ImageView mLvXiangqing;
    @BindView(R.id.tv_intro)
    TextView mTvIntro;
    private BaseDialog mDialog;
    private BaseDialog.Builder mBuilder;
    private boolean isChecked = true;
    private RenYangDetailBean.DataEntity datas;
    private String payType = "1";
    private String order_type;
    private float mLirun;
    // IWXAPI 是第三方app和微信通信的openapi接口
    private IWXAPI api;
    private String WX_APPID = "wx5c1cdc0f4545b7b5";// 微信appid
    private static final int SDK_PAY_FLAG = 1;//支付宝
    //-------------------------------------支付宝支付---------------------------------------------
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    //                    Toast.makeText(ZhuanLanActivity.this, " " + payResult.getResultStatus(), Toast.LENGTH_SHORT).show();
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(RenYangDetailActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        setResult(1, new Intent());
                        finish();
                        Intent intent = new Intent(RenYangDetailActivity.this, MyRenyangCenterActivity.class);
                        startActivity(intent);
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        /*
                        "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，
                        最终交易是否成功以服务端异步通知为准（小概率状态）
                         */
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(RenYangDetailActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();
                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(RenYangDetailActivity.this, "支付宝支付取消", Toast.LENGTH_SHORT).show();
                            setResult(1, new Intent());
                            finish();
                            Intent intent = new Intent(RenYangDetailActivity.this, MyOrderActivity.class);
                            intent.putExtra("type", "1");
                            startActivity(intent);
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };
    private List<RenYangDetailBean.DataEntity.BuyUsersEntity> buy_users;
    private List<RenYangDetailBean.DataEntity.RankEntity> rank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ren_yang_detail);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, WX_APPID, false);
        // 将该app注册到微信
        api.registerApp(WX_APPID);
        mTitleBackIv.setVisibility(View.VISIBLE);
        mTitleBackIv.setOnClickListener(this);
        mTitleContentTv.setText("认养详情");
        mTvCommit.setOnClickListener(this);
        mTvXieyi.setOnClickListener(this);
        iv_check.setOnClickListener(this);
        mRecyclerTop.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerTop.setNestedScrollingEnabled(false);
        mRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        mRecycler.setNestedScrollingEnabled(false);
        mItemStepper.setContentBackground(R.drawable.bg_stepper_green);
        mItemStepper.setButtonBackGround(R.color.green);
        mItemStepper.setContentTextColor(R.color.green);
        mItemStepper.setLeftButtonResources(R.drawable.jianhao_white);
        mItemStepper.setRightButtonResources(R.drawable.jiahao_white);
        int state = getIntent().getIntExtra("state", 0);
        if (state == 1) {
            iv_shouqing.setVisibility(View.GONE);
            mTvCommit.setBackgroundResource(R.drawable.bg_yuanjiao_huise);
            mTvCommit.setClickable(false);
        } else if (state == 2) {
            iv_shouqing.setVisibility(View.GONE);
            mTvCommit.setBackgroundResource(R.drawable.bg_yuanjiao_green2);
        } else {
            iv_shouqing.setVisibility(View.VISIBLE);
            mTvCommit.setBackgroundResource(R.drawable.bg_yuanjiao_huise);
            mTvCommit.setClickable(false);
        }
        mItemStepper.setOnValueChangeListener(new SnappingStepperValueChangeListener() {
            @Override
            public void onValueChange(View view, int value) {
                if (datas.getLast_amount() == 0) {
                    return;
                }
                if (value > datas.getLast_amount()) {
                    value = datas.getLast_amount();
                }
                mTvPrice.setText(Float.parseFloat(datas.getPrice()) * value + "元");
                DecimalFormat decimalFormat = new DecimalFormat(".00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
                String p = decimalFormat.format(mLirun * value);//format 返回的是字符串
                mTvLirun.setText(p + "元");
                mTvShengyu.setText(datas.getLast_amount() - value + "");
            }
        });
        initData();
        initWeb();
    }

    private void initWeb() {
        WebSettings webSettings = mWebview.getSettings();
        webSettings.setJavaScriptEnabled(true); // 设置支持javascript脚本
        webSettings.setUseWideViewPort(true);//关键点
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //        webSettings.setBuiltInZoomControls(true); // 设置显示缩放按钮
        //        webSettings.setSupportZoom(true); // 支持缩放
        mWebview.setWebChromeClient(new WebChromeClient());
    }

    private void initData() {
        String id = getIntent().getStringExtra("id");
        order_type = getIntent().getStringExtra("order_type");
        if (TextUtils.isEmpty(order_type)) {
            order_type = "1";//普通
        } else {
            order_type = "2";//分销
        }
        HttpParams params = new HttpParams();
        params.put("id", id);
        OkGo.<RenYangDetailBean>get(Urls.BASEURL + "api/v2/adopt/show")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<RenYangDetailBean>(RenYangDetailActivity.this, RenYangDetailBean.class) {
                    @Override
                    public void onSuccess(Response<RenYangDetailBean> response) {
                        RenYangDetailBean bean = response.body();
                        int code = bean.getCode();
                        if (code == 0) {
                            Toast.makeText(mContext, bean.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (code == 1) {
                            datas = bean.getData();
                            List<String> list = new ArrayList<String>();
                            list.add(datas.getTitle());
                            list.add(datas.getPlace());
                            list.add(datas.getProfit() + "%");
                            list.add(datas.getPeriod() + "天");
                            DecimalFormat decimalFormat = new DecimalFormat(".00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
                            String price = datas.getPrice();
                            list.add(price + "元");
                            mLirun = Float.parseFloat(price) * datas.getProfit() * datas.getPeriod() / 36500;
                            list.add(decimalFormat.format(mLirun) + "元");
                            list.add(datas.getProfit_type());
                            TopAdapter topAdapter = new TopAdapter(R.layout.item_renyang_detail_top, list);
                            mRecyclerTop.setAdapter(topAdapter);
                            mTvShengyu.setText(datas.getLast_amount() + "");
                            if (datas.getLast_amount() <= 0) {
                                mTvCommit.setBackgroundResource(R.drawable.bg_yuanjiao_huise);
                                mTvCommit.setClickable(false);
                                mItemStepper.setValue(0);
                            }
                            mTvTotalCount.setText(datas.getAmount() + "");
                            mItemStepper.setMaxValue(datas.getLast_amount());
                            mTvPrice.setText(datas.getAmount() + "");
                            Glide.with(MyApplication.getGloableContext()).load(datas.getImage()).into(mIvTop);
                            mTvIntro.setText(datas.getIntroduce());
                            mTvPrice.setText(Float.parseFloat(datas.getPrice()) * mItemStepper.getValue() + "元");
                            float lirun = mLirun * mItemStepper.getValue();
                            String p = decimalFormat.format(lirun);//format 返回的是字符串
                            mTvLirun.setText(p + "元");
                            buy_users = datas.getBuy_users();
                            rank = datas.getRank();
                            xiangmu();
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void myEvent(EventBean eventBean) {
        if (eventBean.getMsg().equals("weixinpaysuccess")) {
            setResult(1, new Intent());
            finish();
            Intent intent = new Intent(RenYangDetailActivity.this, MyRenyangCenterActivity.class);
            startActivity(intent);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void myEvent1(EventBean eventBean) {
        if (eventBean.getMsg().equals("weixinpaycancel")) {
            setResult(1, new Intent());
            finish();
            Intent intent = new Intent(RenYangDetailActivity.this, MyOrderActivity.class);
            intent.putExtra("type", "1");
            startActivity(intent);
        }
    }

    private class TopAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public TopAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            switch (helper.getAdapterPosition()) {
                case 0:
                    helper.setText(R.id.tv_title, "第  " + datas.getId() + " 期：")
                            .setText(R.id.tv_content, "【" + item + "】");
                    break;
                case 1:
                    helper.setText(R.id.tv_title, "产        地：")
                            .setText(R.id.tv_content, item);
                    break;
                case 2:
                    helper.setText(R.id.tv_title, "年利润率：")
                            .setText(R.id.tv_content, item);
                    break;
                case 3:
                    helper.setText(R.id.tv_title, "养殖周期：")
                            .setText(R.id.tv_content, item);
                    break;
                case 4:
                    helper.setText(R.id.tv_title, "养殖成本：")
                            .setText(R.id.tv_content, item);
                    break;
                case 5:
                    helper.setText(R.id.tv_title, "养殖利润：")
                            .setText(R.id.tv_content, item);
                    break;
                case 6:
                    helper.setText(R.id.tv_title, "利润获取：")
                            .setText(R.id.tv_content, item);
                    break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.tv_commit:
                String userid = SpUtils.getString(mContext, "userid", "");
                if (TextUtils.isEmpty(userid)) {
                    startActivity(new Intent(mContext, LoginActivity.class));
                    overridePendingTransition(R.anim.push_bottom_in, R.anim.push_bottom_out);
                    return;
                }
                if (!isChecked) {
                    Toast.makeText(this, "请同意养驴啦服务协议~", Toast.LENGTH_SHORT).show();
                    return;
                }
                showPayStyleDialog();
                break;
            case R.id.tv_xieyi:
                startActivity(new Intent(this, ServiceAgreementActivity.class));
                break;
            case R.id.iv_check:
                if (isChecked) {
                    iv_check.setImageResource(R.drawable.weixuanzhong);
                } else {
                    iv_check.setImageResource(R.drawable.xuanzhong);
                }
                isChecked = !isChecked;
                break;
        }
    }

    private void commitOrder() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(RenYangDetailActivity.this, "token", ""));
        HttpParams params = new HttpParams();
        params.put("id", datas.getId() + "");
        params.put("pay_type", payType);
        params.put("order_type", order_type);
        params.put("number", mItemStepper.getValue() + "");
        switch (payType) {
            case "1": //支付宝
                OkGo.<AliPayBean>post(Urls.BASEURL + "api/v2/adopt/order")
                        .tag(this)
                        .headers(headers)
                        .params(params)
                        .execute(new DialogCallback<AliPayBean>(RenYangDetailActivity.this, AliPayBean.class) {
                            @Override
                            public void onSuccess(Response<AliPayBean> response) {
                                AliPayBean bean = response.body();
                                if (bean.getCode() == 0) {
                                    Toast.makeText(RenYangDetailActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                final AliPayBean aliPayBean = response.body();
                                Runnable payRunnable = new Runnable() {

                                    @Override
                                    public void run() {
                                        PayTask alipay = new PayTask(RenYangDetailActivity.this);
                                        String result = alipay.pay(aliPayBean.getData().getAlipay(), true);//调用支付接口，获取支付结果
                                        Message msg = new Message();
                                        msg.what = SDK_PAY_FLAG;
                                        msg.obj = result;
                                        mHandler.sendMessage(msg);
                                    }
                                };

                                // 必须异步调用，支付或者授权的行为需要在独立的非ui线程中执行
                                Thread payThread = new Thread(payRunnable);
                                payThread.start();
                            }

                            @Override
                            public void onError(Response<AliPayBean> response) {
                                super.onError(response);
                            }
                        });
                break;
            case "2": //微信支付
                OkGo.<WXPayBean>post(Urls.BASEURL + "api/v2/adopt/order")
                        .tag(this)
                        .headers(headers)
                        .params(params)
                        .execute(new DialogCallback<WXPayBean>(RenYangDetailActivity.this, WXPayBean.class) {
                                     @Override
                                     public void onSuccess(Response<WXPayBean> response) {
                                         int code = response.code();
                                         WXPayBean wxPayBean = response.body();
                                         if (wxPayBean.getCode() == 0) {
                                             Toast.makeText(RenYangDetailActivity.this, wxPayBean.getMessage(), Toast.LENGTH_SHORT).show();
                                             return;
                                         }
                                         WXPayBean.DataEntity data = wxPayBean.getData();
                                         PayReq req = new PayReq();
                                         req.appId = data.getAppid();// 微信开放平台审核通过的应用APPID
                                         req.partnerId = data.getPartnerid();// 微信支付分配的商户号
                                         req.prepayId = data.getPrepayid();// 预支付订单号，app服务器调用“统一下单”接口获取
                                         req.nonceStr = data.getNoncestr();// 随机字符串，不长于32位，服务器小哥会给咱生成
                                         req.timeStamp = data.getTimestamp() + "";// 时间戳，app服务器小哥给出
                                         req.packageValue = data.getPackage1();// 固定值Sign=WXPay，可以直接写死，服务器返回的也是这个固定值
                                         req.sign = data.getSign();// 签名，服务器小哥给出
                                         //                        req.extData = "app data"; // optional
                                         // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                                         api.sendReq(req);//调起支付
                                     }

                                     @Override
                                     public void onError(Response<WXPayBean> response) {
                                         super.onError(response);
                                     }
                                 }
                        );
                break;
            case "3": //银联支付
                OkGo.<BaseBean>post(Urls.BASEURL + "api/v2/adopt/order")
                        .tag(this)
                        .headers(headers)
                        .params(params)
                        .execute(new DialogCallback<BaseBean>(RenYangDetailActivity.this, BaseBean.class) {
                                     @Override
                                     public void onSuccess(Response<BaseBean> response) {

                                     }
                                 }
                        );
                break;
        }
    }

    private void showPayStyleDialog() {
        mBuilder = new BaseDialog.Builder(this);
        mDialog = mBuilder.setViewId(R.layout.dialog_paystyle)
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
        mDialog.getView(R.id.iv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        TextView tv_price = mDialog.getView(R.id.tv_price);
        tv_price.setText("¥" + mTvPrice.getText().toString());
        mDialog.getView(R.id.view_lastline).setVisibility(View.VISIBLE);
        mDialog.getView(R.id.ll_zhuanzhang).setVisibility(View.VISIBLE);
        mDialog.getView(R.id.tv_zhuanzhang).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RenYangDetailActivity.this, ZhuanZhangActivity.class));
            }
        });
        final CheckBox cb_alipay = mDialog.getView(R.id.cb_alipay);
        final CheckBox cb_weixin = mDialog.getView(R.id.cb_weixin);
        final CheckBox cb_yinlian = mDialog.getView(R.id.cb_yinlian);
        cb_alipay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cb_weixin.setChecked(false);
                    cb_yinlian.setChecked(false);
                    payType = "1";
                }
            }
        });
        cb_weixin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cb_alipay.setChecked(false);
                    cb_yinlian.setChecked(false);
                    payType = "2";
                }
            }
        });
        cb_yinlian.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cb_weixin.setChecked(false);
                    cb_alipay.setChecked(false);
                    payType = "3";
                }
            }
        });
        mDialog.getView(R.id.tv_pay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!cb_alipay.isChecked() && !cb_weixin.isChecked() && !cb_yinlian.isChecked()) {
                    Toast.makeText(RenYangDetailActivity.this, "至少选择一种支付方式~", Toast.LENGTH_SHORT).show();
                    return;
                }
                mDialog.dismiss();
                commitOrder();
            }
        });
    }

    @OnClick(R.id.xiangmu_ll)
    public void xiangmu() {
        initXiangMuColor();
        mWebview.setVisibility(View.VISIBLE);
        mLvXiangqing.setVisibility(View.GONE);
        mWebview.loadData(datas.getContent(), "text/html;charset=UTF-8", null);
        mRecycler.setVisibility(View.GONE);
        mTvIntro.setVisibility(View.GONE);
    }

    private void initXiangMuColor() {
        mXiangmuTv.setTextColor(getResources().getColor(R.color.green));
        mIndicatorXiangmu.setBackgroundColor(getResources().getColor(R.color.green));
        mRecordTv.setTextColor(getResources().getColor(R.color.gray));
        mIndicatorRecord.setBackgroundColor(getResources().getColor(R.color.white));
        mPaihangTv.setTextColor(getResources().getColor(R.color.gray));
        mIndicatorPaihang.setBackgroundColor(getResources().getColor(R.color.white));
        mDesTv.setTextColor(getResources().getColor(R.color.gray));
        mIndicatorDes.setBackgroundColor(getResources().getColor(R.color.white));
    }

    @OnClick(R.id.record_ll)
    public void record() {
        initRecordColor();
        mWebview.setVisibility(View.GONE);
        mRecycler.setVisibility(View.VISIBLE);
        RecordAdapter recordAdapter = new RecordAdapter(R.layout.item_record, buy_users);
        mRecycler.setAdapter(recordAdapter);
        mTvIntro.setVisibility(View.GONE);
        mLvXiangqing.setVisibility(View.GONE);
    }

    private class RecordAdapter extends BaseQuickAdapter<RenYangDetailBean.DataEntity.BuyUsersEntity, BaseViewHolder> {

        public RecordAdapter(@LayoutRes int layoutResId, @Nullable List<RenYangDetailBean.DataEntity.BuyUsersEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, RenYangDetailBean.DataEntity.BuyUsersEntity item) {
            Glide.with(MyApplication.getGloableContext()).load(item.getUser_avatar())
                    .into((ImageView) helper.getView(R.id.iv_face));
            helper.setText(R.id.tv_name, item.getUser_name())
                    .setText(R.id.tv_time, item.getCreated_at())
                    .setText(R.id.tv_count, item.getAmount() + "头");
        }
    }

    private void initRecordColor() {
        mXiangmuTv.setTextColor(getResources().getColor(R.color.gray));
        mIndicatorXiangmu.setBackgroundColor(getResources().getColor(R.color.white));
        mRecordTv.setTextColor(getResources().getColor(R.color.green));
        mIndicatorRecord.setBackgroundColor(getResources().getColor(R.color.green));
        mPaihangTv.setTextColor(getResources().getColor(R.color.gray));
        mIndicatorPaihang.setBackgroundColor(getResources().getColor(R.color.white));
        mDesTv.setTextColor(getResources().getColor(R.color.gray));
        mIndicatorDes.setBackgroundColor(getResources().getColor(R.color.white));
    }

    @OnClick(R.id.paihang_ll)
    public void paihang() {
        initPaiHangColor();
        mWebview.setVisibility(View.GONE);
        mRecycler.setVisibility(View.VISIBLE);
        PaiHangAdapter paiHangAdapter = new PaiHangAdapter(R.layout.item_paihangbang, rank);
        mRecycler.setAdapter(paiHangAdapter);
        mTvIntro.setVisibility(View.GONE);
        mLvXiangqing.setVisibility(View.GONE);
    }

    private class PaiHangAdapter extends BaseQuickAdapter<RenYangDetailBean.DataEntity.RankEntity, BaseViewHolder> {

        public PaiHangAdapter(@LayoutRes int layoutResId, @Nullable List<RenYangDetailBean.DataEntity.RankEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, RenYangDetailBean.DataEntity.RankEntity item) {
            int adapterPosition = helper.getAdapterPosition();
            ImageView iv_pai = helper.getView(R.id.iv_pai);
            TextView tv_paixu = helper.getView(R.id.tv_paixu);
            if (adapterPosition == 0) {
                iv_pai.setImageResource(R.drawable.jinpai);
            } else if (adapterPosition == 1) {
                iv_pai.setImageResource(R.drawable.yinpai);
            } else if (adapterPosition == 2) {
                iv_pai.setImageResource(R.drawable.tongpai);
            } else {
                iv_pai.setVisibility(View.GONE);
                tv_paixu.setVisibility(View.VISIBLE);
                tv_paixu.setText(adapterPosition + 1 + "");
            }
            ImageView iv_face = (ImageView) helper.getView(R.id.iv_face);
            if (!TextUtils.isEmpty(item.getUser_avatar())) {
                Glide.with(MyApplication.getGloableContext()).load(item.getUser_avatar()).into(iv_face);
            } else {
                iv_face.setImageResource(R.drawable.face);
            }
            helper.setText(R.id.tv_phone, item.getUser_name())
                    .setText(R.id.tv_count, item.getAmount() + "头");
        }
    }

    private void initPaiHangColor() {
        mXiangmuTv.setTextColor(getResources().getColor(R.color.gray));
        mIndicatorXiangmu.setBackgroundColor(getResources().getColor(R.color.white));
        mRecordTv.setTextColor(getResources().getColor(R.color.gray));
        mIndicatorRecord.setBackgroundColor(getResources().getColor(R.color.white));
        mPaihangTv.setTextColor(getResources().getColor(R.color.green));
        mIndicatorPaihang.setBackgroundColor(getResources().getColor(R.color.green));
        mDesTv.setTextColor(getResources().getColor(R.color.gray));
        mIndicatorDes.setBackgroundColor(getResources().getColor(R.color.white));
    }

    @OnClick(R.id.des_ll)
    public void des() {
        initDesColor();
        //        mWebview.setVisibility(View.VISIBLE);
        mWebview.setVisibility(View.GONE);
        mRecycler.setVisibility(View.GONE);


        mTvIntro.setVisibility(View.VISIBLE);
        mLvXiangqing.setVisibility(View.GONE);
    }

    private void initDesColor() {
        mXiangmuTv.setTextColor(getResources().getColor(R.color.gray));
        mIndicatorXiangmu.setBackgroundColor(getResources().getColor(R.color.white));
        mRecordTv.setTextColor(getResources().getColor(R.color.gray));
        mIndicatorRecord.setBackgroundColor(getResources().getColor(R.color.white));
        mPaihangTv.setTextColor(getResources().getColor(R.color.gray));
        mIndicatorPaihang.setBackgroundColor(getResources().getColor(R.color.white));
        mDesTv.setTextColor(getResources().getColor(R.color.green));
        mIndicatorDes.setBackgroundColor(getResources().getColor(R.color.green));
    }
}
