package com.power.customizingthecloud.fragment.shop;

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
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.activity.mine.AddressManagerActivity;
import com.power.customizingthecloud.activity.mine.MyOrderActivity;
import com.power.customizingthecloud.activity.mine.MyVoucherActivity;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.AddressManageBean;
import com.power.customizingthecloud.bean.AliPayBean;
import com.power.customizingthecloud.bean.BaseBean;
import com.power.customizingthecloud.bean.EventBean;
import com.power.customizingthecloud.bean.MyVoucherBean;
import com.power.customizingthecloud.bean.PayResult;
import com.power.customizingthecloud.bean.WXPayBean;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.fragment.home.bean.PushOrderBean;
import com.power.customizingthecloud.fragment.home.bean.QuickBuyBean;
import com.power.customizingthecloud.login.LoginActivity;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.Urls;
import com.power.customizingthecloud.view.BaseDialog;
import com.power.customizingthecloud.view.CommonPopupWindow;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoodConfirmOrderActivity extends BaseActivity implements View.OnClickListener {

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
    @BindView(R.id.title_content_right_tv)
    TextView mTitleContentRightTv;
    @BindView(R.id.tv_personname)
    TextView mTvPersonname;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.iv_time)
    ImageView mIvTime;
    @BindView(R.id.tv_xiaoji)
    TextView mTvXiaoji;
    @BindView(R.id.shop_recycler)
    RecyclerView shopRecycler;
    @BindView(R.id.tv_zongprice1)
    TextView mTvZongprice1;
    @BindView(R.id.tv_quan_price)
    TextView mTvQuanPrice;
    @BindView(R.id.tv_zongprice2)
    TextView mTvZongprice2;
    @BindView(R.id.cb_ear)
    CheckBox mCbEar;
    @BindView(R.id.tv_ear)
    TextView mTvEar;
    @BindView(R.id.edt_liuyan)
    EditText mEdtLiuyan;
    @BindView(R.id.tv_totalprice)
    TextView mTvTotalprice;
    @BindView(R.id.tv_commit)
    TextView mTvCommit;
    @BindView(R.id.iv_address)
    LinearLayout mIvAddress;
    private BaseDialog mDialog;
    private BaseDialog.Builder mBuilder;
    private CommonPopupWindow popupWindow;
    private int mAdressId;
    private boolean hasAddress;
    private int mVoucher_id;
    private boolean hasVoucher;
    private int mDaijinquanprice;
    private String mOrder_good_total;
    private int mEselsohr_total;
    private String payType = "1";
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
                        Toast.makeText(GoodConfirmOrderActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        setResult(1, new Intent());
                        finish();
                        Intent intent = new Intent(GoodConfirmOrderActivity.this, MyOrderActivity.class);
                        intent.putExtra("type", "0");
                        startActivity(intent);
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        /*
                        "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，
                        最终交易是否成功以服务端异步通知为准（小概率状态）
                         */
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(GoodConfirmOrderActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();
                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(GoodConfirmOrderActivity.this, "支付宝支付取消", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };
    private String is_cart;
    private String buy_type;
    private String good_quantity;
    private DecimalFormat df = new DecimalFormat("########0.00");//转换成小数点后2位的格式

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_confirm_order);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        mTitleBackIv.setVisibility(View.VISIBLE);
        mTitleBackIv.setOnClickListener(this);
        mTitleContentTv.setText("确认订单");
        mTvCommit.setOnClickListener(this);
        mTvQuanPrice.setOnClickListener(this);
        mTvTime.setOnClickListener(this);
        mIvTime.setOnClickListener(this);
        mIvAddress.setOnClickListener(this);
        shopRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        shopRecycler.setNestedScrollingEnabled(false);
        initData();
        initListener();
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, WX_APPID, false);
        // 将该app注册到微信
        api.registerApp(WX_APPID);
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
            Intent intent = new Intent(GoodConfirmOrderActivity.this, MyOrderActivity.class);
            intent.putExtra("type", "0");
            startActivity(intent);
        }
    }

    private void initListener() {
        mCbEar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    double v = Double.parseDouble(mOrder_good_total) - mDaijinquanprice - mEselsohr_total;
                    mTvTotalprice.setText("¥" + df.format(v));
                } else {
                    double v = Double.parseDouble(mOrder_good_total) - mDaijinquanprice;
                    mTvTotalprice.setText("¥" + df.format(v));
                }
            }
        });
    }

    private void initData() {
        good_quantity = getIntent().getStringExtra("good_quantity");
        String cart_id = getIntent().getStringExtra("cart_id");
        is_cart = getIntent().getStringExtra("is_cart");
        buy_type = getIntent().getStringExtra("buy_type");

        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(this, "token", ""));

        HttpParams params = new HttpParams();
        params.put("buy_type", buy_type);
        if (!TextUtils.isEmpty(is_cart) && is_cart.equals("1")) {//购物车
            params.put("is_cart", "1");
            params.put("cart_id", cart_id);
        } else {//直接购买
            is_cart = "0";
            params.put("is_cart", "0");
            params.put("good_quantity", good_quantity);
        }

        OkGo.<String>post(Urls.BASEURL + "api/v2/buy/buy-step1")
                .headers(headers)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        try {
                            JSONObject jsonObject = new JSONObject(body);
                            int code = jsonObject.optInt("code");
                            if (code == 1) {
                                JSONObject dataObj = jsonObject.optJSONObject("data");
                                JSONObject addressObj = dataObj.optJSONObject("address");
                                if (addressObj != null) {//为空的时候也会走进来，不知道为啥
                                    String true_name = addressObj.optString("true_name");
                                    String area_info = addressObj.optString("area_info");
                                    String address = addressObj.optString("address");
                                    String mobile = addressObj.optString("mobile");
                                    mAdressId = addressObj.optInt("id");
                                    hasAddress = true;
                                    if (!TextUtils.isEmpty(true_name)) {
                                        mTvPersonname.setText("姓名：" + true_name);
                                    } else {
                                        hasAddress = false;
                                    }
                                    if (!TextUtils.isEmpty(mobile)) {
                                        mTvPhone.setText("电话：" + mobile);
                                    } else {
                                        hasAddress = false;
                                    }
                                    if (!TextUtils.isEmpty(area_info)) {
                                        mTvAddress.setText("地址：" + area_info + address);
                                    } else {
                                        hasAddress = false;
                                    }
                                }
                                Gson gson = new Gson();
                                JSONArray voucher = dataObj.optJSONArray("voucher");
                                if (voucher != null) {
                                    List<QuickBuyBean.DataEntity.VoucherEntity> voucherEntityList = gson.fromJson(voucher.toString(),
                                            new TypeToken<List<QuickBuyBean.DataEntity.VoucherEntity>>() {
                                            }.getType());
                                    if (voucherEntityList != null && voucherEntityList.size() > 0) {
                                        mDaijinquanprice = voucherEntityList.get(0).getPrice();
                                        mVoucher_id = voucherEntityList.get(0).getId();
                                        hasVoucher = true;
                                    }
                                }
                                JSONArray good_list = dataObj.optJSONArray("good_list");
                                //用泛型就会出异常java.lang.ClassCastException: com.google.gson.internal.LinkedTreeMap cannot be cast to xxx
                                //                            List<QuickBuyBean.DataEntity.GoodListEntity> goodListEntities
                                //                                    = parseJsonArrayWithGson(good_list.toString(), QuickBuyBean.DataEntity.GoodListEntity.class);
                                List<QuickBuyBean.DataEntity.GoodListEntity> goodListEntities = gson.fromJson(good_list.toString(),
                                        new TypeToken<List<QuickBuyBean.DataEntity.GoodListEntity>>() {
                                        }.getType());
                                if (goodListEntities != null && goodListEntities.size() > 0) {

                                    ShopAdapter adapter = new ShopAdapter(R.layout.item_confirm_order_shop, goodListEntities);
                                    shopRecycler.setAdapter(adapter);

                                    mOrder_good_total = dataObj.optString("order_good_total");
                                    mTvXiaoji.setText("¥" + mOrder_good_total);
                                    mTvZongprice1.setText("¥" + mOrder_good_total);
                                    if (mDaijinquanprice != 0) {
                                        mTvQuanPrice.setText("¥" + mDaijinquanprice + "");
                                    }
                                    double v = Double.parseDouble(mOrder_good_total) - mDaijinquanprice;
                                    mTvZongprice2.setText("¥" + df.format(v));
                                    mEselsohr_total = dataObj.optInt("eselsohr_total");
                                    mTvEar.setText("可用" + mEselsohr_total + "驴耳朵抵用" + mEselsohr_total + "元");
                                    if (mCbEar.isChecked()) {
                                        double v1 = Double.parseDouble(mOrder_good_total) - mDaijinquanprice - mEselsohr_total;
                                        mTvTotalprice.setText("¥" + df.format(v1));
                                    } else {
                                        mTvTotalprice.setText("¥" + df.format(v));
                                    }
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            //                            Logger.e(e.toString());
                            //                            Logger.e(e.getMessage());
                        }
                    }
                });
    }

    // 将Json数组解析成相应的映射对象列表
    public <T> List<T> parseJsonArrayWithGson(String jsonData, Class<T> type) {
        Gson gson = new Gson();
        List<T> result = gson.fromJson(jsonData, new TypeToken<List<T>>() {
        }.getType());
        return result;
    }

    private class ShopAdapter extends BaseQuickAdapter<QuickBuyBean.DataEntity.GoodListEntity, BaseViewHolder> {

        public ShopAdapter(int layoutResId, @Nullable List<QuickBuyBean.DataEntity.GoodListEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, QuickBuyBean.DataEntity.GoodListEntity item) {
            Glide.with(mContext).load(item.getGood_image()).into((ImageView) helper.getView(R.id.iv_good));
            helper.setText(R.id.tv_shopname, item.getGood_name())
                    .setText(R.id.tv_oneprice, "¥" + item.getGood_price())
                    .setText(R.id.tv_count, "x" + item.getGood_num());
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
        TextView tv_price = mDialog.getView(R.id.tv_price);
        tv_price.setText(mTvTotalprice.getText().toString());
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
                    Toast.makeText(GoodConfirmOrderActivity.this, "至少选择一种支付方式~", Toast.LENGTH_SHORT).show();
                    return;
                }
                mDialog.dismiss();
                pushOrder();
                //                Intent intent = new Intent(GoodConfirmOrderActivity.this, MyOrderActivity.class);
                //                intent.putExtra("type", "0");
                //                startActivity(intent);
            }
        });
    }

    //向下弹出
    public void showDownPop(final TextView textView, final List<String> list) {
        if (popupWindow != null && popupWindow.isShowing())
            return;
        popupWindow = new CommonPopupWindow.Builder(this)
                .setView(R.layout.popup_down)
                .setWidthAndHeight(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setAnimationStyle(R.style.AnimDown)
                .setViewOnclickListener(new CommonPopupWindow.ViewInterface() {
                    @Override
                    public void getChildView(View view, int layoutResId) {
                        RecyclerView recycle_view = (RecyclerView) view.findViewById(R.id.recycle_view);
                        recycle_view.setLayoutManager(new LinearLayoutManager(GoodConfirmOrderActivity.this));
                        PopupAdapter mAdapter = new PopupAdapter(R.layout.item_pop_textview, list);
                        recycle_view.setAdapter(mAdapter);
                        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                textView.setText(list.get(position));
                                if (popupWindow != null) {
                                    popupWindow.dismiss();
                                }
                            }
                        });
                    }
                })
                .setOutsideTouchable(true)
                .create();
        popupWindow.showAsDropDown(textView);
        //得到button的左上角坐标
        //        int[] positions = new int[2];
        //        view.getLocationOnScreen(positions);
        //        popupWindow.showAtLocation(findViewById(android.R.id.content), Gravity.NO_GRAVITY, 0, positions[1] + view.getHeight());
    }

    private class PopupAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public PopupAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.tv_pop, item);
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
                if (!hasAddress) {
                    Toast.makeText(this, "请确保姓名、电话、地址都填写完整", Toast.LENGTH_SHORT).show();
                    return;
                }
                showPayStyleDialog();
                break;
            case R.id.tv_quan_price:
                String userid2 = SpUtils.getString(mContext, "userid", "");
                if (TextUtils.isEmpty(userid2)) {
                    startActivity(new Intent(mContext, LoginActivity.class));
                    overridePendingTransition(R.anim.push_bottom_in, R.anim.push_bottom_out);
                    return;
                }
                Intent intent = new Intent(this, MyVoucherActivity.class);
                intent.putExtra("type", "query");
                startActivityForResult(intent, 0);
                break;
            case R.id.tv_time:
            case R.id.iv_time:
                List<String> list = new ArrayList<String>();
                list.add("工作日");
                list.add("休息日");
                showDownPop(mTvTime, list);
                break;
            case R.id.iv_address:
                Intent intent1 = new Intent(this, AddressManagerActivity.class);
                intent1.putExtra("type", "order");
                startActivityForResult(intent1, 0);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) {//领券返回
            MyVoucherBean.DataBean result = (MyVoucherBean.DataBean) data.getSerializableExtra("result");
            mDaijinquanprice = result.getPrice();
            mVoucher_id = result.getId();
            hasVoucher = true;
            if (mDaijinquanprice != 0) {
                mTvQuanPrice.setText("¥" + mDaijinquanprice + "");
            }
            double v = Double.parseDouble(mOrder_good_total) - mDaijinquanprice;
            mTvZongprice2.setText("¥" + df.format(v));
            if (mCbEar.isChecked()) {
                double v1 = Double.parseDouble(mOrder_good_total) - mDaijinquanprice - mEselsohr_total;
                mTvTotalprice.setText("¥" + df.format(v1));
            } else {
                mTvTotalprice.setText("¥" + df.format(v));
            }
        } else if (resultCode == 2) {//修改地址返回
            AddressManageBean.DataBean result = (AddressManageBean.DataBean) data.getSerializableExtra("result");
            if (result != null) {
                hasAddress = true;
                String true_name = result.getTrue_name();
                String area_info = result.getArea_info();
                String address = area_info + result.getAddress();
                String mobile = result.getMobile();
                mAdressId = result.getId();
                hasAddress = true;
                mTvPersonname.setText("姓名：" + true_name);
                mTvPhone.setText("电话：" + mobile);
                mTvAddress.setText("地址：" + address);
            } else {
                mTvPersonname.setText("姓名：暂无");
                mTvPhone.setText("电话：暂无");
                mTvAddress.setText("地址：暂无");
                hasAddress = false;
            }
        }
    }

    private void pushOrder() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(this, "token", ""));
        HttpParams params = new HttpParams();
        params.put("is_cart", is_cart);
        params.put("buy_type", buy_type);
        params.put("good_quantity", good_quantity);
        if (hasAddress) {
            params.put("address_id", mAdressId + "");
        }
        if (hasVoucher) {
            params.put("voucher_id", mVoucher_id + "");
        }
        params.put("is_eselsohr", mCbEar.isChecked() ? "1" : "0");
        params.put("shipping_time", mTvTime.getText().toString());
        String liuyan = mEdtLiuyan.getText().toString();
        if (!TextUtils.isEmpty(liuyan)) {
            params.put("order_message", liuyan);
        }
        OkGo.<PushOrderBean>post(Urls.BASEURL + "api/v2/buy/buy-step2")
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<PushOrderBean>(GoodConfirmOrderActivity.this, PushOrderBean.class) {
                    @Override
                    public void onSuccess(Response<PushOrderBean> response) {
                        PushOrderBean bean = response.body();
                        int code = bean.getCode();
                        if (code == 0) {
                            Toast.makeText(mContext, bean.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (code == 1) {
                            Toast.makeText(mContext, bean.getMessage(), Toast.LENGTH_SHORT).show();
                            String order_sn = bean.getData().getOrder_sn();
                            goPay(payType, order_sn);
                        }
                    }
                });
    }

    private void goPay(String payType, String order_sn) {
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
                        .execute(new DialogCallback<AliPayBean>(GoodConfirmOrderActivity.this, AliPayBean.class) {
                                     @Override
                                     public void onSuccess(Response<AliPayBean> response) {
                                         int code = response.code();
                                         final AliPayBean aliPayBean = response.body();
                                         Runnable payRunnable = new Runnable() {

                                             @Override
                                             public void run() {
                                                 PayTask alipay = new PayTask(GoodConfirmOrderActivity.this);
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
                        .execute(new DialogCallback<WXPayBean>(GoodConfirmOrderActivity.this, WXPayBean.class) {
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
                        .execute(new DialogCallback<BaseBean>(GoodConfirmOrderActivity.this, BaseBean.class) {
                                     @Override
                                     public void onSuccess(Response<BaseBean> response) {

                                     }
                                 }
                        );
                break;
        }
    }
}
