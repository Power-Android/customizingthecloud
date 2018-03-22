package com.power.customizingthecloud.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.DonkeyEarsBean;
import com.power.customizingthecloud.bean.ShopcartBean;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.fragment.home.GoodDetailActivity;
import com.power.customizingthecloud.listener.SnappingStepperValueChangeListener;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.Urls;
import com.power.customizingthecloud.view.SnappingStepper;

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
    private List<ShopcartBean> list;
    private double totalMoney;
    private int num;
    private ShopCartAdapter adapter;
    private boolean isEdit;

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

        initFenxiao();

        list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            ShopcartBean bean = new ShopcartBean();
            bean.setName("驴奶粉");
            bean.setFenlei("商品分类：驴奶粉");
            bean.setMoney(99.00);
            bean.setNum(i + 1);
            list.add(bean);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setNestedScrollingEnabled(false);
        adapter = new ShopCartAdapter(R.layout.item_shop_cart, list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemChildClickListener(this);
        adapter.setOnItemClickListener(this);
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
        initFenxiaooColor();
        initFxData();
    }

    private void initFxData() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));

        OkGo.<DonkeyEarsBean>get(Urls.BASEURL + "api/v2/user/eselsohr")
                .tag(this)
                .headers(headers)
                .execute(new DialogCallback<DonkeyEarsBean>(this,DonkeyEarsBean.class) {
                    @Override
                    public void onSuccess(Response<DonkeyEarsBean> response) {
                        DonkeyEarsBean body = response.body();
                        if (body.getCode() == 1){

                        }
                    }
                });
    }

    private void initShangcheng() {
        initShangchengColor();
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
        ShopcartBean item = list.get(position);
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
        startActivity(new Intent(mContext, GoodDetailActivity.class));
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

    private class ShopCartAdapter extends BaseQuickAdapter<ShopcartBean, BaseViewHolder> {

        public ShopCartAdapter(@LayoutRes int layoutResId, @Nullable List<ShopcartBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, final ShopcartBean item) {
            SnappingStepper stepper = helper.getView(R.id.item_stepper);
            CheckBox checkBox = helper.getView(R.id.item_checkBox);
            final TextView moneyTv = helper.getView(R.id.item_money_tv);
            stepper.setValue(item.getNum());
            checkBox.setChecked(item.isChecked());
            moneyTv.setText("￥" + item.getMoney() * stepper.getValue());
            helper.setText(R.id.item_name_tv, item.getName())
                    .setText(R.id.item_fenlei_tv, item.getFenlei())
                    .addOnClickListener(R.id.item_checkBox);

            stepper.setOnValueChangeListener(new SnappingStepperValueChangeListener() {
                @Override
                public void onValueChange(View view, int value) {
                    item.setNum(value);
                    moneyTv.setText("￥" + item.getMoney() * value);
                    jiesuan();
                    notifyDataSetChanged();
                }
            });

        }
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
                break;
        }
    }

    private void jiesuan() {
        totalMoney = 0.00;
        num = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isChecked()) {
                double money = list.get(i).getMoney() * list.get(i).getNum();
                totalMoney += money;
                num += 1;
            }
        }
        hejiTv.setText("￥" + totalMoney);
        jiesuanTv.setText("结算（" + num + "）");
    }

    private void shanchu() {
        totalMoney = 0.00;
        num = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isChecked()) {
                double money = list.get(i).getMoney() * list.get(i).getNum();
                totalMoney += money;
                num += 1;
            }
        }
        jiesuanTv.setText("删除（" + num + "）");
    }

    private void quanxuan() {
        totalMoney = 0.00;
        for (int i = 0; i < list.size(); i++) {
            ShopcartBean item = list.get(i);
            item.setChecked(true);
            double money = item.getMoney() * item.getNum();
            totalMoney += money;
        }
        if (isEdit) {
            isEditTv.setVisibility(View.GONE);
            hejiTv.setVisibility(View.GONE);
            jiesuanTv.setText("删除");
        } else {
            isEditTv.setVisibility(View.VISIBLE);
            hejiTv.setVisibility(View.VISIBLE);
            hejiTv.setText("￥" + totalMoney);
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
        hejiTv.setText("￥0.00");
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
