package com.power.customizingthecloud.fragment.shop;

import android.content.Intent;
import android.os.Bundle;
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
import com.orhanobut.logger.Logger;
import com.power.customizingthecloud.MyApplication;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.activity.mine.AddressManagerActivity;
import com.power.customizingthecloud.activity.mine.MyOrderActivity;
import com.power.customizingthecloud.activity.mine.MyVoucherActivity;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.AddressManageBean;
import com.power.customizingthecloud.bean.MyVoucherBean;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.fragment.home.bean.PushOrderBean;
import com.power.customizingthecloud.fragment.home.bean.QuickBuyBean;
import com.power.customizingthecloud.login.LoginActivity;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.Urls;
import com.power.customizingthecloud.view.BaseDialog;
import com.power.customizingthecloud.view.CommonPopupWindow;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    @BindView(R.id.iv_good)
    ImageView mIvGood;
    @BindView(R.id.tv_shopname)
    TextView mTvShopname;
    @BindView(R.id.tv_oneprice)
    TextView mTvOneprice;
    @BindView(R.id.tv_count)
    TextView mTvCount;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.iv_time)
    ImageView mIvTime;
    @BindView(R.id.tv_xiaoji)
    TextView mTvXiaoji;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_confirm_order);
        ButterKnife.bind(this);
        mTitleBackIv.setVisibility(View.VISIBLE);
        mTitleBackIv.setOnClickListener(this);
        mTitleContentTv.setText("确认订单");
        mTvCommit.setOnClickListener(this);
        mTvQuanPrice.setOnClickListener(this);
        mTvTime.setOnClickListener(this);
        mIvTime.setOnClickListener(this);
        mIvAddress.setOnClickListener(this);
        initData();
    }

    private void initData() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(this, "token", ""));
        HttpParams params = new HttpParams();
        params.put("is_cart", "0");
        params.put("buy_type", "2");
        params.put("good_quantity", getIntent().getStringExtra("good_quantity"));
        OkGo.<String>post(Urls.BASEURL + "api/v2/buy/buy-step1")
                .headers(headers)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        Logger.e(body);
                        try {
                            JSONObject jsonObject = new JSONObject(body);
                            JSONObject dataObj = jsonObject.optJSONObject("data");
                            JSONObject addressObj = dataObj.optJSONObject("address");
                            if (addressObj != null) {
                                String true_name = addressObj.optString("true_name");
                                String area_info = addressObj.optString("area_info");
                                String address = addressObj.optString("address");
                                String mobile = addressObj.optString("mobile");
                                mAdressId = addressObj.optInt("id");
                                hasAddress = true;
                                mTvPersonname.setText("姓名：" + true_name);
                                mTvPhone.setText("电话：" + mobile);
                                mTvAddress.setText("地址：" + address);
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
                                QuickBuyBean.DataEntity.GoodListEntity entity = goodListEntities.get(0);
                                mTvShopname.setText(entity.getGood_name());
                                Glide.with(MyApplication.getGloableContext()).load(entity.getGood_image()).into(mIvGood);
                                mTvOneprice.setText("¥" + entity.getGood_price());
                                mTvCount.setText("x" + entity.getGood_num());
                                mOrder_good_total = dataObj.optString("order_good_total");
                                mTvXiaoji.setText("¥" + mOrder_good_total);
                                mTvZongprice1.setText("¥" + mOrder_good_total);
                                if (mDaijinquanprice != 0) {
                                    mTvQuanPrice.setText("¥" + mDaijinquanprice + "");
                                }
                                mTvZongprice2.setText("¥" + (Float.parseFloat(mOrder_good_total) - mDaijinquanprice));
                                mEselsohr_total = dataObj.optInt("eselsohr_total");
                                mTvEar.setText("可用" + mEselsohr_total + "驴耳朵抵用" + mEselsohr_total + "元");
                                if (mCbEar.isChecked()) {
                                    mTvTotalprice.setText("¥" + (Float.parseFloat(mOrder_good_total) - mDaijinquanprice - mEselsohr_total));
                                } else {
                                    mTvTotalprice.setText("¥" + (Float.parseFloat(mOrder_good_total) - mDaijinquanprice));
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
        final CheckBox cb_alipay = mDialog.getView(R.id.cb_alipay);
        final CheckBox cb_weixin = mDialog.getView(R.id.cb_weixin);
        final CheckBox cb_yinlian = mDialog.getView(R.id.cb_yinlian);
        cb_alipay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cb_weixin.setChecked(false);
                    cb_yinlian.setChecked(false);
                }
            }
        });
        cb_weixin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cb_alipay.setChecked(false);
                    cb_yinlian.setChecked(false);
                }
            }
        });
        cb_yinlian.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cb_weixin.setChecked(false);
                    cb_alipay.setChecked(false);
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
                Intent intent = new Intent(GoodConfirmOrderActivity.this, MyOrderActivity.class);
                intent.putExtra("type", "0");
                startActivity(intent);
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
                pushOrder();
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
                startActivityForResult(intent,0);
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
                startActivityForResult(intent1,0);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==1){
            MyVoucherBean.DataBean result= (MyVoucherBean.DataBean) data.getSerializableExtra("result");
            mDaijinquanprice = result.getPrice();
            mVoucher_id=result.getId();
            hasVoucher = true;
            if (mDaijinquanprice != 0) {
                mTvQuanPrice.setText("¥" + mDaijinquanprice + "");
            }
            mTvZongprice2.setText("¥" + (Float.parseFloat(mOrder_good_total) - mDaijinquanprice));
            if (mCbEar.isChecked()) {
                mTvTotalprice.setText("¥" + (Float.parseFloat(mOrder_good_total) - mDaijinquanprice - mEselsohr_total));
            } else {
                mTvTotalprice.setText("¥" + (Float.parseFloat(mOrder_good_total) - mDaijinquanprice));
            }
        }else if (resultCode==2){
            AddressManageBean.DataBean result= (AddressManageBean.DataBean) data.getSerializableExtra("result");
            String true_name = result.getTrue_name();
            String area_info = result.getArea_info();
            String address = result.getAddress();
            String mobile = result.getMobile();
            mAdressId = result.getId();
            hasAddress = true;
            mTvPersonname.setText("姓名：" + true_name);
            mTvPhone.setText("电话：" + mobile);
            mTvAddress.setText("地址：" + address);
        }
    }

    private void pushOrder() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(this, "token", ""));
        HttpParams params = new HttpParams();
        params.put("is_cart", "0");
        params.put("buy_type", "2");
        params.put("good_quantity", getIntent().getStringExtra("good_quantity"));
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
            params.put("order_message", "");
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
                            String pay_sn = bean.getData().getPay_sn();
                            showPayStyleDialog();
                        }
                    }
                });
    }
}
