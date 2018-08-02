package com.power.customizingthecloud.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.power.customizingthecloud.bean.BaseBean;
import com.power.customizingthecloud.bean.ShopcartBean;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.callback.JsonCallback;
import com.power.customizingthecloud.fragment.home.GoodDetailActivity;
import com.power.customizingthecloud.fragment.shop.GoodConfirmOrderActivity;
import com.power.customizingthecloud.listener.SnappingStepperValueChangeListener;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.TUtils;
import com.power.customizingthecloud.utils.Urls;
import com.power.customizingthecloud.view.SnappingStepper;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopCartActivity extends BaseActivity implements View.OnClickListener, BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.title_content_right_tv)
    TextView titleContentRightTv;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.all_checkBox)
    CheckBox allCheckBox;
    @BindView(R.id.heji_tv)
    TextView hejiTv;
    @BindView(R.id.jiesuan_tv)
    TextView jiesuanTv;
    @BindView(R.id.isedit_tv)
    TextView isEditTv;
    @BindView(R.id.fenxiao_tv)
    TextView fenxiaoTv;
    @BindView(R.id.indicator_fenxiao)
    View indicatorFenxiao;
    @BindView(R.id.shangcheng_tv)
    TextView shangchengTv;
    @BindView(R.id.indicator_shangcheng)
    View indicatorShangcheng;
    @BindView(R.id.all_check_ll)
    LinearLayout allCheckLl;
    @BindView(R.id.fenixiao_ll)
    LinearLayout fenixiaoLl;
    @BindView(R.id.shangcheng_ll)
    LinearLayout shangchengLl;
    @BindView(R.id.rl_bottom)
    RelativeLayout rlBottom;
    private List<ShopcartBean.DataBean> list = new ArrayList<>();
    private double totalMoney;
    private int num;
    private ShopCartAdapter adapter;
    private boolean isEdit;
    private int TYPE = -1;
    private DecimalFormat df = new DecimalFormat("########0.00");//转换成小数点后2位的格式

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_cart);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("购物车");
        titleContentRightTv.setVisibility(View.VISIBLE);
        titleContentRightTv.setText("管理");
        titleBackIv.setOnClickListener(this);
        titleContentRightTv.setOnClickListener(this);
        jiesuanTv.setOnClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setNestedScrollingEnabled(false);
        initFenxiao();
        allCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    if (!isAllChecked()) {
                        quanxuan();
                    }
                } else {
                    cancleQuanxuan();
                }
            }
        });
    }

    private void initFenxiao() {
        TYPE = 2;
        initFenxiaooColor();
        initFxData();
    }

    private void initFxData() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));
        HttpParams params = new HttpParams();
        params.put("good_cart_type", "2");
        OkGo.<ShopcartBean>get(Urls.BASEURL + "api/v2/cart/list")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<ShopcartBean>(this, ShopcartBean.class) {
                    @Override
                    public void onSuccess(Response<ShopcartBean> response) {
                        ShopcartBean body = response.body();
                        if (body.getCode() == 1) {
                            list = body.getData();
                            if (list == null || list.size() == 0) {
                                rlBottom.setVisibility(View.GONE);
                            } else {
                                rlBottom.setVisibility(View.VISIBLE);
                            }
                            adapter = new ShopCartAdapter(R.layout.item_shop_cart, list);
                            recyclerView.setAdapter(adapter);
                            adapter.setOnItemChildClickListener(ShopCartActivity.this);
                            adapter.setOnItemClickListener(ShopCartActivity.this);
                        }
                    }
                });
    }

    private void initShangcheng() {
        TYPE = 1;
        initShangchengColor();
        initShangchengData();
    }

    private void initShangchengData() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));
        HttpParams params = new HttpParams();
        params.put("good_cart_type", "1");
        OkGo.<ShopcartBean>get(Urls.BASEURL + "api/v2/cart/list")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<ShopcartBean>(this, ShopcartBean.class) {
                    @Override
                    public void onSuccess(Response<ShopcartBean> response) {
                        ShopcartBean body = response.body();
                        if (body.getCode() == 1) {
                            list = body.getData();
                            if (list == null || list.size() == 0) {
                                rlBottom.setVisibility(View.GONE);
                            } else {
                                rlBottom.setVisibility(View.VISIBLE);
                            }
                            adapter = new ShopCartAdapter(R.layout.item_shop_cart, list);
                            recyclerView.setAdapter(adapter);
                            adapter.setOnItemChildClickListener(ShopCartActivity.this);
                            adapter.setOnItemClickListener(ShopCartActivity.this);
                        }
                    }
                });
    }

    private void initFenxiaooColor() {
        fenxiaoTv.setTextColor(getResources().getColor(R.color.green));
        indicatorFenxiao.setBackgroundColor(getResources().getColor(R.color.green));
        shangchengTv.setTextColor(getResources().getColor(R.color.gray));
        indicatorShangcheng.setBackgroundColor(getResources().getColor(R.color.white));
    }

    private void initShangchengColor() {
        fenxiaoTv.setTextColor(getResources().getColor(R.color.gray));
        indicatorFenxiao.setBackgroundColor(getResources().getColor(R.color.white));
        shangchengTv.setTextColor(getResources().getColor(R.color.green));
        indicatorShangcheng.setBackgroundColor(getResources().getColor(R.color.green));
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        ShopcartBean.DataBean item = list.get(position);
        if (!isEdit) {//结算状态
            if (!((CheckBox) view).isChecked()) {//未选中状态
                allCheckBox.setChecked(false);
                item.setChecked(false);
                jiesuan();
                adapter.notifyDataSetChanged();
            } else {
                item.setChecked(true);
                if (isAllChecked()) {
                    allCheckBox.setChecked(true);
                }
                jiesuan();
                adapter.notifyDataSetChanged();
            }
        } else {//编辑状态
            if (!((CheckBox) view).isChecked()) {//未选中状态
                allCheckBox.setChecked(false);
                item.setChecked(false);
                shanchu();
                adapter.notifyDataSetChanged();
            } else {
                item.setChecked(true);
                if (isAllChecked()) {
                    allCheckBox.setChecked(true);
                }
                shanchu();
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        int buy_type = list.get(position).getBuy_type();
        if (buy_type == 1) {
            Intent intent = new Intent(mContext, GoodDetailActivity.class);
            intent.putExtra("id", list.get(position).getGood_id() + "");
            startActivity(intent);
        } else {
            Intent intent = new Intent(mContext, FenxiaoDetailActivity.class);
            intent.putExtra("id", list.get(position).getGood_id() + "");
            startActivity(intent);
        }
    }

    @OnClick({R.id.fenixiao_ll, R.id.shangcheng_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fenixiao_ll:
                initFenxiao();
                break;
            case R.id.shangcheng_ll:
                initShangcheng();
                break;
        }
    }

    public String getGood_quantity() {
        String aa = "";
        StringBuilder sb = new StringBuilder();
        if (list == null || list.size() == 0) {
            return aa;
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isChecked()) {
                sb.append(list.get(i).getId() + "=" + list.get(i).getGood_num() + ",");
            }
        }
        if (TextUtils.isEmpty(sb.toString())) {
            return aa;
        }
        //当循环结束后截取最后一个逗号
        aa = sb.substring(0, sb.length() - 1);
        return aa;
    }

    private class ShopCartAdapter extends BaseQuickAdapter<ShopcartBean.DataBean, BaseViewHolder> {

        public ShopCartAdapter(@LayoutRes int layoutResId, @Nullable List<ShopcartBean.DataBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, final ShopcartBean.DataBean item) {
            final SnappingStepper stepper = helper.getView(R.id.item_stepper);
            CheckBox checkBox = helper.getView(R.id.item_checkBox);
            final TextView moneyTv = helper.getView(R.id.item_money_tv);
            stepper.setValue(item.getGood_num());
            checkBox.setChecked(item.isChecked());
            moneyTv.setText("¥" + df.format(item.getGood_price() * stepper.getValue()));
            helper.setText(R.id.item_name_tv, item.getGood_name())
                    .setText(R.id.item_fenlei_tv, "商品分类：" + item.getClass_name())
                    .addOnClickListener(R.id.item_checkBox);
            Glide.with(MyApplication.getGloableContext()).load(item.getGood_image()).into((ImageView) helper.getView(R.id.item_img_iv));
            stepper.setOnValueChangeListener(new SnappingStepperValueChangeListener() {
                @Override
                public void onValueChange(View view, int value) {
                    if (value <= 0) {
                    } else {
                        if (value > item.getGood_num()) {
                            addCar(item.getGood_id() + "");
                        } else if (value < item.getGood_num()) {
                            minusCar(item.getGood_id() + "");
                        }
                    }
                    item.setGood_num(value);
                    moneyTv.setText("¥" + df.format(item.getGood_price() * value));
                    notifyDataSetChanged();
                    jiesuan();
                }
            });
        }
    }

    private void addCar(String good_id) {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));
        HttpParams params = new HttpParams();
        params.put("good_id", good_id);
        OkGo.<BaseBean>get(Urls.BASEURL + "api/v2/cart/plus")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<BaseBean>(ShopCartActivity.this, BaseBean.class) {
                    @Override
                    public void onSuccess(Response<BaseBean> response) {
                        int code = response.body().getCode();
                        String message = response.body().getMessage();
                        if (code == 0) {
                            Toast.makeText(ShopCartActivity.this, message, Toast.LENGTH_SHORT).show();
                        } else if (code == 1) {
                            Toast.makeText(ShopCartActivity.this, message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void minusCar(String good_id) {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));
        HttpParams params = new HttpParams();
        params.put("good_id", good_id);
        OkGo.<BaseBean>get(Urls.BASEURL + "api/v2/cart/minus")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<BaseBean>(ShopCartActivity.this, BaseBean.class) {
                    @Override
                    public void onSuccess(Response<BaseBean> response) {
                        int code = response.body().getCode();
                        String message = response.body().getMessage();
                        if (code == 0) {
                            Toast.makeText(ShopCartActivity.this, message, Toast.LENGTH_SHORT).show();
                        } else if (code == 1) {
                            Toast.makeText(ShopCartActivity.this, message, Toast.LENGTH_SHORT).show();
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
            case R.id.title_content_right_tv:
                if (!isEdit) {//编辑状态
                    titleContentRightTv.setText("完成");
                    isEditTv.setVisibility(View.GONE);
                    hejiTv.setVisibility(View.GONE);
                    shanchu();
                    isEdit = !isEdit;
                } else {//结算状态
                    titleContentRightTv.setText("管理");
                    isEditTv.setVisibility(View.VISIBLE);
                    hejiTv.setVisibility(View.VISIBLE);
                    jiesuan();
                    isEdit = !isEdit;
                }
                break;
            case R.id.all_check_ll:
                break;
            case R.id.jiesuan_tv:
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).isChecked()) {
                        sb.append(list.get(i).getId() + ",");
                    }
                }
                if (TextUtils.isEmpty(sb.toString())) {
                    return;
                }
                //当循环结束后截取最后一个逗号
                String cart_id = sb.substring(0, sb.length() - 1);

                if (isEdit) {
                    initDel(cart_id);
                } else {
                    Intent intent = new Intent(mContext, GoodConfirmOrderActivity.class);
                    intent.putExtra("cart_id", cart_id);
                    if (TYPE == 2) {//分销购物车
                        intent.putExtra("buy_type", "4");
                    } else if (TYPE == 1)//商城购物车
                    {
                        intent.putExtra("buy_type", "5");
                    }
                    intent.putExtra("good_quantity", getGood_quantity());
                    intent.putExtra("is_cart", "1");
                    startActivityForResult(intent, 0);
                }
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) {
            finish();
        }
    }

    private void initDel(String cart_id) {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));
        HttpParams params = new HttpParams();
        params.put("cart_id", cart_id);

        OkGo.<BaseBean>post(Urls.BASEURL + "api/v2/cart/delete")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new JsonCallback<BaseBean>(BaseBean.class) {
                    @Override
                    public void onSuccess(Response<BaseBean> response) {
                        BaseBean body = response.body();
                        if (body.getCode() == 1) {
                            TUtils.showShort(mContext, body.getMessage());
                            if (TYPE == 1) {
                                initShangcheng();
                            } else {
                                initFenxiao();
                            }
                        } else {
                            TUtils.showShort(mContext, body.getMessage());
                        }
                    }
                });

    }

    private void jiesuan() {
        totalMoney = 0.00;
        num = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isChecked()) {
                double money = list.get(i).getGood_price() * list.get(i).getGood_num();
                totalMoney += money;
                num += 1;
            }
        }
        hejiTv.setText("¥" + df.format(totalMoney));
        jiesuanTv.setText("结算（" + num + "）");
    }

    private void shanchu() {
        totalMoney = 0.00;
        num = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isChecked()) {
                double money = list.get(i).getGood_price() * list.get(i).getGood_num();
                totalMoney += money;
                num += 1;
            }
        }
        jiesuanTv.setText("删除（" + num + "）");
    }

    private void quanxuan() {
        totalMoney = 0.00;
        for (int i = 0; i < list.size(); i++) {
            ShopcartBean.DataBean item = list.get(i);
            item.setChecked(true);
            double money = item.getGood_price() * item.getGood_num();
            totalMoney += money;
        }
        if (isEdit) {
            isEditTv.setVisibility(View.GONE);
            hejiTv.setVisibility(View.GONE);
            jiesuanTv.setText("删除");
        } else {
            isEditTv.setVisibility(View.VISIBLE);
            hejiTv.setVisibility(View.VISIBLE);
            hejiTv.setText("¥" + df.format(totalMoney));
            jiesuanTv.setText("结算（" + list.size() + "）");
        }
        adapter.notifyDataSetChanged();
    }

    private void cancleQuanxuan() {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setChecked(false);
        }
        adapter.notifyDataSetChanged();
        totalMoney = 0;
        hejiTv.setText("¥0.00");
        jiesuanTv.setText("结算（" + 0 + "）");
    }

    private boolean isAllChecked() {
        for (int i = 0; i < list.size(); i++) {
            boolean checked = list.get(i).isChecked();
            if (!checked) {
                return false;
            }
        }
        return true;
    }
}
