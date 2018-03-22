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
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.MyOderBean;
import com.power.customizingthecloud.utils.TUtils;
import com.power.customizingthecloud.view.BaseDialog;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.NormalAlertDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyOrderActivity extends BaseActivity implements View.OnClickListener, BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.OnItemClickListener {

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
    private List<MyOderBean> list;
    private MyOderBean bean1;
    private MyOderBean bean2;
    private MyOderBean bean3;
    private MyOderBean bean4;
    private MyOrderAdapter adapter;
    private BaseDialog mDialog;
    private BaseDialog.Builder mBuilder;
    private String type;
    private Intent intent;

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

        list = new ArrayList<>();
        initData();
        type = getIntent().getStringExtra("type");
        if (!TextUtils.isEmpty(type)) {
            switchType(type);
        }
    }

    private void initData() {
        bean1 = new MyOderBean();
        bean1.setType("1");
        bean1.setName("驴奶粉");
        bean1.setFeilei("商品分类：驴奶粉");
        bean1.setMoney("￥99.00");
        bean1.setNum("x1");
        bean1.setYunfei("共1件商品 合计：￥99.00（含运费￥0.00）");

        bean2 = new MyOderBean();
        bean2.setType("2");
        bean2.setName("驴奶粉");
        bean2.setFeilei("商品分类：驴奶粉");
        bean2.setMoney("￥99.00");
        bean2.setNum("x1");
        bean2.setYunfei("共1件商品 合计：￥99.00（含运费￥0.00）");

        bean3 = new MyOderBean();
        bean3.setType("3");
        bean3.setName("驴奶粉");
        bean3.setFeilei("商品分类：驴奶粉");
        bean3.setMoney("￥99.00");
        bean3.setNum("x1");
        bean3.setYunfei("共1件商品 合计：￥99.00（含运费￥0.00）");

        bean4 = new MyOderBean();
        bean4.setType("4");
        bean4.setName("驴奶粉");
        bean4.setFeilei("商品分类：驴奶粉");
        bean4.setMoney("￥99.00");
        bean4.setNum("x1");
        bean4.setYunfei("共1件商品 合计：￥99.00（含运费￥0.00）");
    }

    private void switchType(String type) {
        switch (type) {
            case "0":
                initQuanbuColor();
                list.add(bean1);
                list.add(bean2);
                list.add(bean3);
                list.add(bean4);
                adapter = new MyOrderAdapter(R.layout.item_my_order, list);
                recyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(this);
                adapter.setOnItemChildClickListener(this);
                break;
            case "1":
                initDaifukuanColor();
                list.add(bean1);
                adapter = new MyOrderAdapter(R.layout.item_my_order, list);
                recyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(this);
                adapter.setOnItemChildClickListener(this);
                break;
            case "2":
                initDaifahuoColor();
                list.add(bean2);
                adapter = new MyOrderAdapter(R.layout.item_my_order, list);
                recyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(this);
                adapter.setOnItemChildClickListener(this);
                break;
            case "3":
                initDaishouhuoColor();
                list.add(bean3);
                adapter = new MyOrderAdapter(R.layout.item_my_order, list);
                recyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(this);
                adapter.setOnItemChildClickListener(this);
                break;
            case "4":
                initDaipingjiaColor();
                list.add(bean4);
                adapter = new MyOrderAdapter(R.layout.item_my_order, list);
                recyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(this);
                adapter.setOnItemChildClickListener(this);
                break;
        }
    }

    @OnClick(R.id.quanbu_ll)
    public void quanbu() {
        list.clear();
        initQuanbuColor();
        list.add(bean1);
        list.add(bean2);
        list.add(bean3);
        list.add(bean4);
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
        initDaifukuanColor();
        list.clear();
        list.add(bean1);
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
        list.add(bean2);
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
        list.add(bean3);
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
        list.add(bean4);
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
                switch (list.get(position).getType()) {
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
                switch (list.get(position).getType()){
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

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        switch (list.get(position).getType()) {
            case "1":
                intent = new Intent(mContext, OrderDetailActivity.class);
                intent.putExtra("type", list.get(position).getType());
                startActivity(intent);
                break;
            case "2":
                intent = new Intent(mContext, OrderDetailActivity.class);
                intent.putExtra("type", list.get(position).getType());
                startActivity(intent);
                break;
            case "3":
                intent = new Intent(mContext, OrderDetailActivity.class);
                intent.putExtra("type", list.get(position).getType());
                startActivity(intent);
                break;
            case "4":
                intent = new Intent(mContext, OrderDetailActivity.class);
                intent.putExtra("type", list.get(position).getType());
                startActivity(intent);
                break;
        }
    }


    private class MyOrderAdapter extends BaseQuickAdapter<MyOderBean, BaseViewHolder> {

        public MyOrderAdapter(@LayoutRes int layoutResId, @Nullable List<MyOderBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, MyOderBean item) {
            helper.setText(R.id.item_name_tv, item.getName())
                    .setText(R.id.item_fenlei_tv, item.getFeilei())
                    .setText(R.id.item_money_tv, item.getMoney())
                    .setText(R.id.item_num_tv, item.getNum())
                    .setText(R.id.item_des_tv, item.getYunfei())
                    .addOnClickListener(R.id.item_use_tv)
                    .addOnClickListener(R.id.item_cancle_order_tv);
            TextView useTv = helper.getView(R.id.item_use_tv);
            TextView cancleTv = helper.getView(R.id.item_cancle_order_tv);
            switch (item.getType()) {
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
