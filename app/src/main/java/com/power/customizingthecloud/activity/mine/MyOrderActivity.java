package com.power.customizingthecloud.activity.mine;

import android.content.Intent;
import android.os.Bundle;
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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.MyOderBean;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.fragment.home.bean.OrderBean;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.TUtils;
import com.power.customizingthecloud.utils.Urls;
import com.power.customizingthecloud.view.BaseDialog;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.NormalAlertDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyOrderActivity extends BaseActivity implements View.OnClickListener, BaseQuickAdapter.OnItemChildClickListener{

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
    private List<MyOderBean.DataBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        ButterKnife.bind(this);
        initView();
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
        initData(type);
    }

    private void initData(final String type) {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));
        HttpParams params = new HttpParams();
        params.put("type",type);
        params.put("after","");

        OkGo.<MyOderBean>get(Urls.BASEURL + "api/v2/user/order-list")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<MyOderBean>(this,MyOderBean.class) {
                    @Override
                    public void onSuccess(Response<MyOderBean> response) {
                        MyOderBean body = response.body();
                        if (body.getCode() == 1){
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
                adapter = new MyOrderAdapter(R.layout.item_my_order, list);
                recyclerView.setAdapter(adapter);
                adapter.setOnItemChildClickListener(this);
                break;
            case "1":
                initDaifukuanColor();
                adapter = new MyOrderAdapter(R.layout.item_my_order, list);
                recyclerView.setAdapter(adapter);
                adapter.setOnItemChildClickListener(this);
                break;
            case "2":
                initDaifahuoColor();
                adapter = new MyOrderAdapter(R.layout.item_my_order, list);
                recyclerView.setAdapter(adapter);
                adapter.setOnItemChildClickListener(this);
                break;
            case "3":
                initDaishouhuoColor();
                adapter = new MyOrderAdapter(R.layout.item_my_order, list);
                recyclerView.setAdapter(adapter);
                adapter.setOnItemChildClickListener(this);
                break;
            case "4":
                initDaipingjiaColor();
                adapter = new MyOrderAdapter(R.layout.item_my_order, list);
                recyclerView.setAdapter(adapter);
                adapter.setOnItemChildClickListener(this);
                break;
        }
    }

    @OnClick(R.id.quanbu_ll)
    public void quanbu() {
        list.clear();
        initQuanbuColor();
        initData("0");
        adapter.notifyDataSetChanged();
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
        initData("1");
        adapter.notifyDataSetChanged();
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
        initData("2");
        adapter.notifyDataSetChanged();
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
        initData("3");
        adapter.notifyDataSetChanged();
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
        initData("4");
        adapter.notifyDataSetChanged();
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

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.item_use_tv:
                switch (type) {
                    case "1":
                        showPayStyleDialog();
                        break;
                    case "2":
                        TUtils.showShort(mContext, "点击了---提醒发货" + position);
                        break;
                    case "3":
                        TUtils.showShort(mContext, "点击了---确认收货" + position);
                        break;
                    case "4":
                        startActivity(new Intent(mContext,PingJiaActivity.class));
                        break;
                }
                break;
            case R.id.item_cancle_order_tv:
                switch (type){
                    case "1":
                        showTip(position);
                        break;
                    case "3":
                        startActivity(new Intent(mContext,RequestRefundActiviy.class));
                        break;
                }
                break;
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
                        list.remove(position);
                        adapter.notifyDataSetChanged();
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
        mDialog.getView(R.id.tv_pay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                mDialog.dismiss();
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
    }

    private class MyOrderAdapter extends BaseQuickAdapter<MyOderBean.DataBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {

        public MyOrderAdapter(@LayoutRes int layoutResId, @Nullable List<MyOderBean.DataBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, MyOderBean.DataBean item) {
            helper.addOnClickListener(R.id.item_use_tv)
                    .addOnClickListener(R.id.item_cancle_order_tv);
            TextView useTv = helper.getView(R.id.item_use_tv);
            TextView cancleTv = helper.getView(R.id.item_cancle_order_tv);
            switch (type) {
                case "1":
                    cancleTv.setVisibility(View.VISIBLE);
                    cancleTv.setText("取消订单");
                    useTv.setText("付款");
                    break;
                case "2":
                    cancleTv.setVisibility(View.GONE);
                    useTv.setText("提醒发货");
                    break;
                case "3":
                    cancleTv.setVisibility(View.VISIBLE);
                    cancleTv.setText("退款");
                    useTv.setText("确认收货");
                    break;
                case "4":
                    cancleTv.setVisibility(View.GONE);
                    useTv.setText("评价");
                    break;
            }
            RecyclerView itemRecycler = helper.getView(R.id.item_recycler);
            itemRecycler.setLayoutManager(new LinearLayoutManager(mContext));
            itemRecycler.setNestedScrollingEnabled(false);
            String order_state = item.getOrder_state();
            List<MyOderBean.DataBean.GoodsBean> itemList = item.getGoods();
            ItmeOrderAdapter adapter = new ItmeOrderAdapter(R.layout.item_itemorder_layout, itemList, order_state);
            itemRecycler.setAdapter(adapter);
            adapter.setOnItemClickListener(this);
        }

        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            switch (type) {
                case "1":
                    intent = new Intent(mContext, OrderDetailActivity.class);
                    intent.putExtra("type", type);
                    startActivity(intent);
                    break;
                case "2":
                    intent = new Intent(mContext, OrderDetailActivity.class);
                    intent.putExtra("type", type);
                    startActivity(intent);
                    break;
                case "3":
                    intent = new Intent(mContext, OrderDetailActivity.class);
                    intent.putExtra("type", type);
                    startActivity(intent);
                    break;
                case "4":
                    intent = new Intent(mContext, OrderDetailActivity.class);
                    intent.putExtra("type", type);
                    startActivity(intent);
                    break;
            }
        }
    }

    private class ItmeOrderAdapter extends BaseQuickAdapter<MyOderBean.DataBean.GoodsBean,BaseViewHolder>{
        private String type;
        public ItmeOrderAdapter(int layoutResId, @Nullable List<MyOderBean.DataBean.GoodsBean> data,String type) {
            super(layoutResId, data);
            this.type = type;
        }

        @Override
        protected void convert(BaseViewHolder helper, MyOderBean.DataBean.GoodsBean item) {
            helper.setText(R.id.item_name_tv, item.getGoods_name())
                    .setText(R.id.item_fenlei_tv, item.getGoods_name())
                    .setText(R.id.item_money_tv, item.getGoods_price())
                    .setText(R.id.item_num_tv, item.getGoods_num())
                    .setText(R.id.item_des_tv, item.getGoods_price());
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
}
