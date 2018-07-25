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
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.AliPayBean;
import com.power.customizingthecloud.bean.BaseBean;
import com.power.customizingthecloud.bean.EventBean;
import com.power.customizingthecloud.bean.MyOderBean;
import com.power.customizingthecloud.bean.PayResult;
import com.power.customizingthecloud.bean.WXPayBean;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.callback.JsonCallback;
import com.power.customizingthecloud.utils.SpUtils;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyOrderActivity extends BaseActivity implements View.OnClickListener, BaseQuickAdapter.OnItemChildClickListener {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.title_content_right_tv)
    TextView titleContentRightTv;
    @BindView(R.id.indicator_quanbu)
    View indicatorQuanbu;
    @BindView(R.id.quanbu_ll)
    LinearLayout quanbuLl;
    @BindView(R.id.indicator_daifukuan)
    View indicatorDaifukuan;
    @BindView(R.id.daifukuan_ll)
    LinearLayout daifukuanLl;
    @BindView(R.id.indicator_daifahuo)
    View indicatorDaifahuo;
    @BindView(R.id.daifahuo_ll)
    LinearLayout daifahuoLl;
    @BindView(R.id.indicator_daishouhuo)
    View indicatorDaishouhuo;
    @BindView(R.id.daishouhuo_ll)
    LinearLayout daishouhuoLl;
    @BindView(R.id.indicator_daipingjia)
    View indicatorDaipingjia;
    @BindView(R.id.daipingjia_ll)
    LinearLayout daipingjiaLl;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.quanbu_tv)
    TextView quanbuTv;
    @BindView(R.id.daifukuan_tv)
    TextView daifukuanTv;
    @BindView(R.id.daifahuo_tv)
    TextView daifahuoTv;
    @BindView(R.id.daishouhuo_tv)
    TextView daishouhuoTv;
    @BindView(R.id.daipingjia_tv)
    TextView daipingjiaTv;
    private MyOrderAdapter adapter;
    private BaseDialog mDialog;
    private BaseDialog.Builder mBuilder;
    private String type;
    private Intent intent;
    private List<MyOderBean.DataEntity> list = new ArrayList<>();
    // IWXAPI 是第三方app和微信通信的openapi接口
    private IWXAPI api;
    private String WX_APPID = "wx5c1cdc0f4545b7b5";// 微信appid
    private static final int SDK_PAY_FLAG = 1;//支付宝
    private String payType = "1";
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
                        Toast.makeText(MyOrderActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        type = "0";
                        initData();
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        /*
                        "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，
                        最终交易是否成功以服务端异步通知为准（小概率状态）
                         */
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(MyOrderActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();
                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(MyOrderActivity.this, "支付宝支付取消", Toast.LENGTH_SHORT).show();
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
        setContentView(R.layout.activity_my_order);
        ButterKnife.bind(this);
        initView();
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
            type = "0";
            initData();
        }
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("我的订单");
        titleContentRightTv.setVisibility(View.VISIBLE);
        titleContentRightTv.setText("退款/售后");
        titleBackIv.setOnClickListener(this);
        titleContentRightTv.setOnClickListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setNestedScrollingEnabled(false);

        type = getIntent().getStringExtra("type");
        initData();
    }

    private void initData() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));
        HttpParams params = new HttpParams();
        params.put("type", type);
        params.put("after", "");
        OkGo.<MyOderBean>get(Urls.BASEURL + "api/v2/user/order-list")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<MyOderBean>(this, MyOderBean.class) {
                    @Override
                    public void onSuccess(Response<MyOderBean> response) {
                        MyOderBean body = response.body();
                        if (body.getCode() == 1) {
                            list = body.getData();
                            if (!TextUtils.isEmpty(type)) {
                                switchType(type);
                            }
                        }
                    }
                });
    }

    private void switchType(String type) {
        switch (type) {
            case "0":
                initQuanbuColor();
                break;
            case "1":
                initDaifukuanColor();
                break;
            case "2":
                initDaifahuoColor();
                break;
            case "3":
                initDaishouhuoColor();
                break;
            case "4":
                initDaipingjiaColor();
                break;
        }
        adapter = new MyOrderAdapter(R.layout.item_my_order, list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemChildClickListener(this);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.item_use_tv:
                switch (list.get(position).getOrder_state()) {
                    case 10:
                        showPayStyleDialog(position);
                        break;
                    case 20:
                        tixingfahuo(position);
                        break;
                    case 30:
                        confirmOrder(position);
                        break;
                    case 40:
                        Intent intent = new Intent(mContext, PingJiaActivity.class);
                        intent.putExtra("data", (Serializable) list.get(position).getGoods());
                        intent.putExtra("type", "myorder");
                        intent.putExtra("order_id", list.get(position).getId() + "");
                        //                        intent.putExtra("name", list.get(position).getGoods().get(0).getGoods_name());
                        //                        intent.putExtra("type", list.get(position).getGoods().get(0).getGoods_class());
                        //                        intent.putExtra("image", list.get(position).getGoods().get(0).getGoods_image());
                        //                        intent.putExtra("good_id", list.get(position).getGoods().get(0).getGoods_id() + "");
                        startActivityForResult(intent, 0);
                        break;
                }
                break;
            case R.id.item_cancle_order_tv:
                switch (list.get(position).getOrder_state()) {
                    case 10:
                        showTip(position);
                        break;
                    case 30:
                        Intent intent = new Intent(mContext, SelectTuiTypeActiviy.class);
                        intent.putExtra("name", list.get(position).getGoods().get(0).getGoods_name());
                        intent.putExtra("type", list.get(position).getGoods().get(0).getGoods_class());
                        intent.putExtra("image", list.get(position).getGoods().get(0).getGoods_image());
                        intent.putExtra("price", list.get(position).getGoods().get(0).getGoods_price());
                        intent.putExtra("order_id", list.get(position).getId() + "");
                        startActivity(intent);
                        break;
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) {
            type = "0";
            initData();
        }
    }

    private void showTip(final int position) {
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
                        cancelOrder(position);
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

    private void cancelOrder(final int position) {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(this, "token", ""));
        HttpParams params = new HttpParams();
        params.put("order_id", list.get(position).getId());
        OkGo.<BaseBean>get(Urls.BASEURL + "api/v2/user/order-cancel")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new JsonCallback<BaseBean>(BaseBean.class) {
                             @Override
                             public void onSuccess(Response<BaseBean> response) {
                                 int code = response.code();
                                 BaseBean body = response.body();
                                 Toast.makeText(MyOrderActivity.this, body.getMessage(), Toast.LENGTH_SHORT).show();
                                 type = "0";
                                 initData();
                             }

                             @Override
                             public void onError(Response<BaseBean> response) {
                                 super.onError(response);
                             }
                         }
                );
    }

    private void tixingfahuo(final int position) {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(this, "token", ""));
        HttpParams params = new HttpParams();
        params.put("order_id", list.get(position).getId());
        OkGo.<BaseBean>get(Urls.BASEURL + "api/v2/user/order-remind")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new JsonCallback<BaseBean>(BaseBean.class) {
                             @Override
                             public void onSuccess(Response<BaseBean> response) {
                                 int code = response.code();
                                 BaseBean body = response.body();
                                 Toast.makeText(MyOrderActivity.this, body.getMessage(), Toast.LENGTH_SHORT).show();
//                                 type = "0";
                                 initData();
                             }

                             @Override
                             public void onError(Response<BaseBean> response) {
                                 super.onError(response);
                             }
                         }
                );
    }

    private void confirmOrder(final int position) {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(this, "token", ""));
        HttpParams params = new HttpParams();
        params.put("order_id", list.get(position).getId());
        OkGo.<BaseBean>get(Urls.BASEURL + "api/v2/user/order-confirm")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new JsonCallback<BaseBean>(BaseBean.class) {
                             @Override
                             public void onSuccess(Response<BaseBean> response) {
                                 int code = response.code();
                                 BaseBean body = response.body();
                                 Toast.makeText(MyOrderActivity.this, body.getMessage(), Toast.LENGTH_SHORT).show();
                                 type = "0";
                                 initData();
                             }

                             @Override
                             public void onError(Response<BaseBean> response) {
                                 super.onError(response);
                             }
                         }
                );
    }

    private void showPayStyleDialog(final int position) {
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
                goPay(list.get(position).getOrder_sn() + "");
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
                        .execute(new DialogCallback<AliPayBean>(MyOrderActivity.this, AliPayBean.class) {
                                     @Override
                                     public void onSuccess(Response<AliPayBean> response) {
                                         int code = response.code();
                                         final AliPayBean aliPayBean = response.body();
                                         Runnable payRunnable = new Runnable() {

                                             @Override
                                             public void run() {
                                                 PayTask alipay = new PayTask(MyOrderActivity.this);
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
                        .execute(new DialogCallback<WXPayBean>(MyOrderActivity.this, WXPayBean.class) {
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
                        .execute(new DialogCallback<BaseBean>(MyOrderActivity.this, BaseBean.class) {
                                     @Override
                                     public void onSuccess(Response<BaseBean> response) {

                                     }
                                 }
                        );
                break;
        }
    }

    private class MyOrderAdapter extends BaseQuickAdapter<MyOderBean.DataEntity, BaseViewHolder> {

        public MyOrderAdapter(@LayoutRes int layoutResId, @Nullable List<MyOderBean.DataEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, MyOderBean.DataEntity item) {
            helper.addOnClickListener(R.id.item_use_tv)
                    .addOnClickListener(R.id.item_cancle_order_tv);
            TextView useTv = helper.getView(R.id.item_use_tv);
            TextView cancleTv = helper.getView(R.id.item_cancle_order_tv);
            switch (item.getOrder_state()) {
                case 0://已取消
                    cancleTv.setVisibility(View.VISIBLE);
                    cancleTv.setVisibility(View.GONE);
                    useTv.setText("已取消");
                    useTv.setClickable(false);
                    break;
                case 10://未付款
                    cancleTv.setVisibility(View.VISIBLE);
                    cancleTv.setText("取消订单");
                    useTv.setText("付款");
                    break;
                case 20://已付款
                    cancleTv.setVisibility(View.GONE);
                    useTv.setText("提醒发货");
                    break;
                case 30://已发货
                    cancleTv.setVisibility(View.VISIBLE);
                    cancleTv.setText("退款");
                    useTv.setText("确认收货");
                    int refund_state = item.getRefund_state();
                    if (refund_state == 1) {
                        cancleTv.setText("退款申请中");
                        cancleTv.setClickable(false);
                    } else if (refund_state == 2) {
                        cancleTv.setText("退款中");
                        cancleTv.setClickable(false);
                    } else if (refund_state == 3) {
                        cancleTv.setText("已退款");
                        cancleTv.setClickable(false);
                    }
                    break;
                case 40://已收货
                    cancleTv.setVisibility(View.GONE);
                    int evaluation_state = item.getEvaluation_state();
                    if (evaluation_state == 1) {
                        useTv.setText("已评价");
                        useTv.setClickable(false);
                    } else if (evaluation_state == 2) {
                        useTv.setText("已过期未评价");
                        useTv.setClickable(false);
                    } else {
                        useTv.setText("评价");
                    }
                    break;
            }
            RecyclerView itemRecycler = helper.getView(R.id.item_recycler);
            itemRecycler.setLayoutManager(new LinearLayoutManager(mContext));
            itemRecycler.setNestedScrollingEnabled(false);
            List<MyOderBean.DataEntity.GoodsEntity> itemList = item.getGoods();
            ItmeOrderAdapter adapter = new ItmeOrderAdapter(R.layout.item_itemorder_layout, itemList);
            itemRecycler.setAdapter(adapter);
            adapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    intent = new Intent(mContext, OrderDetailActivity.class);
                    intent.putExtra("order_id", list.get(helper.getAdapterPosition()).getId() + "");
                    intent.putExtra("evaluation_state", list.get(helper.getAdapterPosition()).getEvaluation_state());
                    intent.putExtra("refund_state", list.get(helper.getAdapterPosition()).getRefund_state());
                    startActivity(intent);
                }
            });
        }
    }

    private class ItmeOrderAdapter extends BaseQuickAdapter<MyOderBean.DataEntity.GoodsEntity, BaseViewHolder> {

        public ItmeOrderAdapter(int layoutResId, @Nullable List<MyOderBean.DataEntity.GoodsEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, MyOderBean.DataEntity.GoodsEntity item) {
            Glide.with(mContext).load(item.getGoods_image()).into((ImageView) helper.getView(R.id.item_img_iv));
            helper.setText(R.id.item_name_tv, item.getGoods_name())
                    .setText(R.id.item_fenlei_tv, "商品分类：" + item.getGoods_class())
                    .setText(R.id.item_money_tv, "￥" + item.getGoods_price())
                    .setText(R.id.item_num_tv, "x " + item.getGoods_num() + "")
                    .setText(R.id.item_des_tv, "共" + item.getGoods_num() + "件商品  合计：￥" + item.getGoods_price() + "（含运费￥0.00）");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.title_content_right_tv:
                startActivity(new Intent(mContext, RefundAfterActivity.class));
                break;
        }
    }

    @OnClick(R.id.quanbu_ll)
    public void quanbu() {
        list.clear();
        initQuanbuColor();
        type = "0";
        initData();
        //        adapter.notifyDataSetChanged();
    }

    private void initQuanbuColor() {
        quanbuTv.setTextColor(getResources().getColor(R.color.green));
        indicatorQuanbu.setBackgroundColor(getResources().getColor(R.color.green));
        daifukuanTv.setTextColor(getResources().getColor(R.color.gray));
        indicatorDaifukuan.setBackgroundColor(getResources().getColor(R.color.white));
        daifahuoTv.setTextColor(getResources().getColor(R.color.gray));
        indicatorDaifahuo.setBackgroundColor(getResources().getColor(R.color.white));
        daishouhuoTv.setTextColor(getResources().getColor(R.color.gray));
        indicatorDaishouhuo.setBackgroundColor(getResources().getColor(R.color.white));
        daipingjiaTv.setTextColor(getResources().getColor(R.color.gray));
        indicatorDaipingjia.setBackgroundColor(getResources().getColor(R.color.white));
    }

    @OnClick(R.id.daifukuan_ll)
    public void daifukuan() {
        list.clear();
        initDaifukuanColor();
        type = "1";
        initData();
        //        adapter.notifyDataSetChanged();
    }

    private void initDaifukuanColor() {
        quanbuTv.setTextColor(getResources().getColor(R.color.gray));
        indicatorQuanbu.setBackgroundColor(getResources().getColor(R.color.white));
        daifukuanTv.setTextColor(getResources().getColor(R.color.green));
        indicatorDaifukuan.setBackgroundColor(getResources().getColor(R.color.green));
        daifahuoTv.setTextColor(getResources().getColor(R.color.gray));
        indicatorDaifahuo.setBackgroundColor(getResources().getColor(R.color.white));
        daishouhuoTv.setTextColor(getResources().getColor(R.color.gray));
        indicatorDaishouhuo.setBackgroundColor(getResources().getColor(R.color.white));
        daipingjiaTv.setTextColor(getResources().getColor(R.color.gray));
        indicatorDaipingjia.setBackgroundColor(getResources().getColor(R.color.white));
    }

    @OnClick(R.id.daifahuo_ll)
    public void daifahuo() {
        initDaifahuoColor();
        list.clear();
        type = "2";
        initData();
        //        adapter.notifyDataSetChanged();
    }

    private void initDaifahuoColor() {
        quanbuTv.setTextColor(getResources().getColor(R.color.gray));
        indicatorQuanbu.setBackgroundColor(getResources().getColor(R.color.white));
        daifukuanTv.setTextColor(getResources().getColor(R.color.gray));
        indicatorDaifukuan.setBackgroundColor(getResources().getColor(R.color.white));
        daifahuoTv.setTextColor(getResources().getColor(R.color.green));
        indicatorDaifahuo.setBackgroundColor(getResources().getColor(R.color.green));
        daishouhuoTv.setTextColor(getResources().getColor(R.color.gray));
        indicatorDaishouhuo.setBackgroundColor(getResources().getColor(R.color.white));
        daipingjiaTv.setTextColor(getResources().getColor(R.color.gray));
        indicatorDaipingjia.setBackgroundColor(getResources().getColor(R.color.white));
    }

    @OnClick(R.id.daishouhuo_ll)
    public void daishouhuo() {
        initDaishouhuoColor();
        list.clear();
        type = "3";
        initData();
        //        adapter.notifyDataSetChanged();
    }

    private void initDaishouhuoColor() {
        quanbuTv.setTextColor(getResources().getColor(R.color.gray));
        indicatorQuanbu.setBackgroundColor(getResources().getColor(R.color.white));
        daifukuanTv.setTextColor(getResources().getColor(R.color.gray));
        indicatorDaifukuan.setBackgroundColor(getResources().getColor(R.color.white));
        daifahuoTv.setTextColor(getResources().getColor(R.color.gray));
        indicatorDaifahuo.setBackgroundColor(getResources().getColor(R.color.white));
        daishouhuoTv.setTextColor(getResources().getColor(R.color.green));
        indicatorDaishouhuo.setBackgroundColor(getResources().getColor(R.color.green));
        daipingjiaTv.setTextColor(getResources().getColor(R.color.gray));
        indicatorDaipingjia.setBackgroundColor(getResources().getColor(R.color.white));
    }

    @OnClick(R.id.daipingjia_ll)
    public void daipingjia() {
        initDaipingjiaColor();
        list.clear();
        type = "4";
        initData();
        //        adapter.notifyDataSetChanged();
    }

    private void initDaipingjiaColor() {
        quanbuTv.setTextColor(getResources().getColor(R.color.gray));
        indicatorQuanbu.setBackgroundColor(getResources().getColor(R.color.white));
        daifukuanTv.setTextColor(getResources().getColor(R.color.gray));
        indicatorDaifukuan.setBackgroundColor(getResources().getColor(R.color.white));
        daifahuoTv.setTextColor(getResources().getColor(R.color.gray));
        indicatorDaifahuo.setBackgroundColor(getResources().getColor(R.color.white));
        daishouhuoTv.setTextColor(getResources().getColor(R.color.gray));
        indicatorDaishouhuo.setBackgroundColor(getResources().getColor(R.color.white));
        daipingjiaTv.setTextColor(getResources().getColor(R.color.green));
        indicatorDaipingjia.setBackgroundColor(getResources().getColor(R.color.green));
    }

}
