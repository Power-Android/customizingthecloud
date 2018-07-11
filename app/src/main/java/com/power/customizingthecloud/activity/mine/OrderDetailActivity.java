package com.power.customizingthecloud.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.AliPayBean;
import com.power.customizingthecloud.bean.BaseBean;
import com.power.customizingthecloud.bean.EventBean;
import com.power.customizingthecloud.bean.OrderDetailBean;
import com.power.customizingthecloud.bean.PayResult;
import com.power.customizingthecloud.bean.WXPayBean;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.TUtils;
import com.power.customizingthecloud.utils.Urls;
import com.power.customizingthecloud.view.BaseDialog;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.NormalAlertDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderDetailActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView mTitleBackIv;
    @BindView(R.id.title_content_tv)
    TextView mTitleContentTv;
    @BindView(R.id.type_content_tv)
    TextView mTypeContentTv;
    @BindView(R.id.name_tv)
    TextView mNameTv;
    @BindView(R.id.phone_tv)
    TextView mPhoneTv;
    @BindView(R.id.address_tv)
    TextView mAddressTv;
    @BindView(R.id.peisong_tv)
    TextView mPeisongTv;
    @BindView(R.id.liuyan_tv)
    TextView mLiuyanTv;
    @BindView(R.id.item_shifukuan_tv)
    TextView mItemShifukuanTv;
    @BindView(R.id.item_yunfei_tv)
    TextView mItemYunfeiTv;
    @BindView(R.id.order_bianhao_tv)
    TextView mOrderBianhaoTv;
    @BindView(R.id.date_tv)
    TextView mDateTv;
    @BindView(R.id.wuliu_bianhao_tv)
    TextView mWuliuBianhaoTv;
    @BindView(R.id.recycler_order)
    RecyclerView recycler_order;
    @BindView(R.id.item_use_tv)
    TextView mItemUseTv;
    @BindView(R.id.item_cancle_order_tv)
    TextView mItemCancleOrderTv;
    @BindView(R.id.top_bg)
    RelativeLayout topBg;
    private BaseDialog mDialog;
    private BaseDialog.Builder mBuilder;
    private int order_state;
    private String order_id;
    private List<OrderDetailBean.DataEntity.GoodsEntity> goods;
    private int evaluation_state;
    private String payType = "1";
    private OrderDetailBean.DataEntity data;
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
                        Toast.makeText(OrderDetailActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        initData();
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        /*
                        "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，
                        最终交易是否成功以服务端异步通知为准（小概率状态）
                         */
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(OrderDetailActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();
                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(OrderDetailActivity.this, "支付宝支付取消", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        ButterKnife.bind(this);
        initView();
        initData();
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, WX_APPID, false);
        // 将该app注册到微信
        api.registerApp(WX_APPID);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void myEvent(EventBean eventBean) {
        if (eventBean.getMsg().equals("weixinpaysuccess")) {
            initData();
        }
    }

    private void initData() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));
        HttpParams params = new HttpParams();
        order_id = getIntent().getStringExtra("order_id");
        evaluation_state = getIntent().getIntExtra("evaluation_state", 0);
        params.put("order_id", order_id);
        OkGo.<OrderDetailBean>get(Urls.BASEURL + "api/v2/user/order-show")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<OrderDetailBean>(this, OrderDetailBean.class) {
                    @Override
                    public void onSuccess(Response<OrderDetailBean> response) {
                        OrderDetailBean body = response.body();
                        data = body.getData();
                        order_state = data.getOrder_state();
                        switch (order_state) {
                            case 0://待付款
                                mTypeContentTv.setText("订单已取消");
                                mItemUseTv.setText("已取消");
                                break;
                            case 10://待付款
                                mTypeContentTv.setText("等待买家付款");
                                mItemCancleOrderTv.setVisibility(View.VISIBLE);
                                mItemCancleOrderTv.setText("取消订单");
                                mItemUseTv.setText("付款");
                                break;
                            case 20://待发货
                                mTypeContentTv.setText("等待卖家发货");
                                topBg.setBackground(getResources().getDrawable(R.drawable.daifahuo_top_iv));
                                mItemUseTv.setText("提醒发货");
                                break;
                            case 30://待收货
                                mTypeContentTv.setText("交易成功");
                                topBg.setBackground(getResources().getDrawable(R.drawable.yifahuo_top_iv));
                                mItemCancleOrderTv.setVisibility(View.VISIBLE);
                                mItemCancleOrderTv.setText("退款");
                                mItemUseTv.setText("确认收货");
                                break;
                            case 40://待评价
                                if (evaluation_state == 1) {
                                    mTypeContentTv.setText("买家已评价");
                                    mItemUseTv.setText("已评价");
                                    mItemUseTv.setClickable(false);
                                } else if (evaluation_state == 2) {
                                    mTypeContentTv.setText("已过期未评价");
                                    mItemUseTv.setText("已过期未评价");
                                    mItemUseTv.setClickable(false);
                                } else {
                                    mTypeContentTv.setText("等待买家评价");
                                    mItemUseTv.setText("评价");
                                }
                                break;
                        }
                        mNameTv.setText(data.getReciver_name());
                        mPhoneTv.setText(data.getMobile());
                        mAddressTv.setText(data.getAddress());
                        mPeisongTv.setText(data.getShipping_time());
                        mLiuyanTv.setText(data.getOrder_message());
                        goods = data.getGoods();
                        OrderAdapter orderAdapter=new OrderAdapter(R.layout.item_orderdetail,goods);
                        recycler_order.setAdapter(orderAdapter);
                        mOrderBianhaoTv.setText("订单编号：" + data.getOrder_sn() + "");
                        mDateTv.setText("创建时间：" + data.getCreated_at());
                        mWuliuBianhaoTv.setText("物流编号：" + data.getShipping_code());
                        mItemShifukuanTv.setText("实付款：￥"+data.getOrder_amount());
                    }
                });
    }


    private class OrderAdapter extends BaseQuickAdapter<OrderDetailBean.DataEntity.GoodsEntity,BaseViewHolder>{

        public OrderAdapter(@LayoutRes int layoutResId, @Nullable List<OrderDetailBean.DataEntity.GoodsEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, OrderDetailBean.DataEntity.GoodsEntity item) {
            Glide.with(MyApplication.getGloableContext()).load(item.getGoods_image()).into((ImageView) helper.getView(R.id.item_img_iv));
            helper.setText(R.id.item_name_tv,item.getGoods_name())
                    .setText(R.id.item_fenlei_tv,item.getGoods_class())
                    .setText(R.id.item_money_tv,"￥" + item.getGoods_price())
                    .setText(R.id.item_num_tv,"x" + item.getGoods_num());
        }
    }

    private void cancelOrder() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(this, "token", ""));
        HttpParams params = new HttpParams();
        params.put("order_id", order_id);
        OkGo.<BaseBean>get(Urls.BASEURL + "api/v2/user/order-cancel")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<BaseBean>(OrderDetailActivity.this, BaseBean.class) {
                             @Override
                             public void onSuccess(Response<BaseBean> response) {
                                 int code = response.code();
                                 BaseBean body = response.body();
                                 Toast.makeText(OrderDetailActivity.this, body.getMessage(), Toast.LENGTH_SHORT).show();
                                 initData();
                             }

                             @Override
                             public void onError(Response<BaseBean> response) {
                                 super.onError(response);
                             }
                         }
                );
    }

    private void tixingfahuo() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(this, "token", ""));
        HttpParams params = new HttpParams();
        params.put("order_id", order_id);
        OkGo.<BaseBean>get(Urls.BASEURL + "api/v2/user/order-remind")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<BaseBean>(OrderDetailActivity.this, BaseBean.class) {
                             @Override
                             public void onSuccess(Response<BaseBean> response) {
                                 int code = response.code();
                                 BaseBean body = response.body();
                                 Toast.makeText(OrderDetailActivity.this, body.getMessage(), Toast.LENGTH_SHORT).show();
                                 initData();
                             }

                             @Override
                             public void onError(Response<BaseBean> response) {
                                 super.onError(response);
                             }
                         }
                );
    }

    private void confirmOrder() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(this, "token", ""));
        HttpParams params = new HttpParams();
        params.put("order_id", order_id);
        OkGo.<BaseBean>get(Urls.BASEURL + "api/v2/user/order-confirm")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<BaseBean>(OrderDetailActivity.this, BaseBean.class) {
                             @Override
                             public void onSuccess(Response<BaseBean> response) {
                                 int code = response.code();
                                 BaseBean body = response.body();
                                 Toast.makeText(OrderDetailActivity.this, body.getMessage(), Toast.LENGTH_SHORT).show();
                                 initData();
                             }

                             @Override
                             public void onError(Response<BaseBean> response) {
                                 super.onError(response);
                             }
                         }
                );
    }

    private void initView() {
        mTitleBackIv.setVisibility(View.VISIBLE);
        mTitleContentTv.setText("订单详情");
        recycler_order.setLayoutManager(new LinearLayoutManager(this));
        recycler_order.setNestedScrollingEnabled(false);
    }

    @OnClick({R.id.title_back_iv, R.id.item_use_tv, R.id.item_cancle_order_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.item_use_tv:
                switch (order_state) {
                    case 10://待付款
                        showPayStyleDialog();
                        break;
                    case 20://待发货
                        tixingfahuo();
                        break;
                    case 30://待收货
                        confirmOrder();
                        break;
                    case 40://待评价
                        Intent intent = new Intent(mContext, PingJiaActivity.class);
                        intent.putExtra("name", goods.get(0).getGoods_name());
                        intent.putExtra("type", goods.get(0).getGoods_class());
                        intent.putExtra("image", goods.get(0).getGoods_image());
                        intent.putExtra("order_id", order_id);
                        intent.putExtra("good_id", goods.get(0).getGoods_id() + "");
                        startActivity(intent);
                        break;
                }
                break;
            case R.id.item_cancle_order_tv:
                switch (order_state) {
                    case 10://待付款
                        showTip();
                        break;
                    case 20://待发货
                        break;
                    case 30://待收货
                        TUtils.showShort(mContext, "点击了---退款");
                        break;
                    case 40://待评价
                        break;
                }
                break;
        }
    }

    private void showPayStyleDialog() {
        payType = "1";
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
        mDialog.getView(R.id.tv_pay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goPay(data.getOrder_sn() + "");
                mDialog.dismiss();
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
    }

    private void goPay(String order_sn) {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(this, "token", ""));
        HttpParams params = new HttpParams();
        params.put("order_sn", order_sn);
        params.put("pay_type", payType);
        switch (payType) {
            case "1": //支付宝
                OkGo.<AliPayBean>post(Urls.BASEURL + "api/v2/buy/pay")
                        .tag(this)
                        .headers(headers)
                        .params(params)
                        .execute(new DialogCallback<AliPayBean>(OrderDetailActivity.this, AliPayBean.class) {
                                     @Override
                                     public void onSuccess(Response<AliPayBean> response) {
                                         int code = response.code();
                                         final AliPayBean aliPayBean = response.body();
                                         Runnable payRunnable = new Runnable() {

                                             @Override
                                             public void run() {
                                                 PayTask alipay = new PayTask(OrderDetailActivity.this);
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
                                 }
                        );
                break;
            case "2": //微信支付
                OkGo.<WXPayBean>post(Urls.BASEURL + "api/v2/buy/pay")
                        .tag(this)
                        .headers(headers)
                        .params(params)
                        .execute(new DialogCallback<WXPayBean>(OrderDetailActivity.this, WXPayBean.class) {
                                     @Override
                                     public void onSuccess(Response<WXPayBean> response) {
                                         int code = response.code();
                                         WXPayBean wxPayBean = response.body();
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
                OkGo.<BaseBean>post(Urls.BASEURL + "api/v2/buy/pay")
                        .tag(this)
                        .headers(headers)
                        .params(params)
                        .execute(new DialogCallback<BaseBean>(OrderDetailActivity.this, BaseBean.class) {
                                     @Override
                                     public void onSuccess(Response<BaseBean> response) {

                                     }
                                 }
                        );
                break;
        }
    }

    private void showTip() {
        new NormalAlertDialog.Builder(this)
                .setTitleVisible(false).setTitleText("提示")
                .setTitleTextColor(R.color.text_black)
                .setContentText("您确定要取消此订单吗？")
                .setContentTextColor(R.color.text_black)
                .setLeftButtonText("确定")
                .setLeftButtonTextColor(R.color.text_black)
                .setRightButtonText("取消")
                .setRightButtonTextColor(R.color.text_black)
                .setCanceledOnTouchOutside(false)
                .setOnclickListener(new DialogInterface.OnLeftAndRightClickListener<NormalAlertDialog>() {
                    @Override
                    public void clickLeftButton(NormalAlertDialog dialog, View view) {
                        cancelOrder();
                        dialog.dismiss();
                    }

                    @Override
                    public void clickRightButton(NormalAlertDialog dialog, View view) {
                        dialog.dismiss();
                    }
                })
                .build()
                .show();
    }

}
