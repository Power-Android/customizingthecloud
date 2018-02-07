package com.power.customizingthecloud.activity.mine;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.MyMoneyRecordBean;
import com.power.customizingthecloud.bean.MyOderBean;
import com.power.customizingthecloud.utils.TUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyMoneyRecordActivity extends BaseActivity implements View.OnClickListener, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.quanbu_tv) TextView quanbuTv;
    @BindView(R.id.indicator_quanbu) View indicatorQuanbu;
    @BindView(R.id.quanbu_ll) LinearLayout quanbuLl;
    @BindView(R.id.shouyi_tv) TextView shouyiTv;
    @BindView(R.id.indicator_shouyi) View indicatorShouyi;
    @BindView(R.id.shouyi_ll) LinearLayout shouyiLl;
    @BindView(R.id.tixian_tv) TextView tixianTv;
    @BindView(R.id.indicator_tixian) View indicatorTixian;
    @BindView(R.id.tixian_ll) LinearLayout tixianLl;
    @BindView(R.id.yongjin_tv) TextView yongjinTv;
    @BindView(R.id.indicator_yongjin) View indicatorYongjin;
    @BindView(R.id.yongjin_ll) LinearLayout yongjinLl;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    private List<MyMoneyRecordBean> list;
    private MyMoneyRecordBean bean1;
    private MyMoneyRecordBean bean2;
    private MyMoneyRecordBean bean3;
    private final int ALL = 1, SHOUYI = 2, TIXIAN = 3, YONGJIN = 4;
    private MyMoneyRecordAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_money_record);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("我的订单");
        titleBackIv.setOnClickListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setNestedScrollingEnabled(false);

        list = new ArrayList<>();
        initData();
    }

    private void initData() {
        bean1 = new MyMoneyRecordBean();
        bean1.setType("shouyi");
        bean1.setDate("2018.02.07");
        bean1.setContent("您认养的第32期驴宝宝-------------------------驴宝获得收益到账800.00元");

        bean2 = new MyMoneyRecordBean();
        bean2.setType("tixian");
        bean2.setDate("2018.02.07");
        bean2.setContent("您向招商银行提现100.00元");

        bean3 = new MyMoneyRecordBean();
        bean3.setType("yongjin");
        bean3.setDate("2018.02.07");
        bean3.setContent("您分享的驴奶粉获得一级销售收益100.00元");

        list.add(bean1);
        list.add(bean2);
        list.add(bean3);
        adapter = new MyMoneyRecordAdapter(R.layout.item_money_record,list,ALL);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @OnClick(R.id.quanbu_ll)
    public void quanbu() {
        initQuanbuColor();
        list.clear();
        list.add(bean1);
        list.add(bean2);
        list.add(bean3);
        adapter = new MyMoneyRecordAdapter(R.layout.item_money_record,list,ALL);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    private void initQuanbuColor() {
        quanbuTv.setTextColor(getResources().getColor(R.color.green));
        indicatorQuanbu.setBackgroundColor(getResources().getColor(R.color.green));
        shouyiTv.setTextColor(getResources().getColor(R.color.gray));
        indicatorShouyi.setBackgroundColor(getResources().getColor(R.color.white));
        tixianTv.setTextColor(getResources().getColor(R.color.gray));
        indicatorTixian.setBackgroundColor(getResources().getColor(R.color.white));
        yongjinTv.setTextColor(getResources().getColor(R.color.gray));
        indicatorYongjin.setBackgroundColor(getResources().getColor(R.color.white));
    }

    @OnClick(R.id.shouyi_ll)
    public void shouyi(){
        initShouyiColor();
        list.clear();
        list.add(bean1);
        adapter = new MyMoneyRecordAdapter(R.layout.item_money_record,list,SHOUYI);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    private void initShouyiColor() {
        quanbuTv.setTextColor(getResources().getColor(R.color.gray));
        indicatorQuanbu.setBackgroundColor(getResources().getColor(R.color.white));
        shouyiTv.setTextColor(getResources().getColor(R.color.green));
        indicatorShouyi.setBackgroundColor(getResources().getColor(R.color.green));
        tixianTv.setTextColor(getResources().getColor(R.color.gray));
        indicatorTixian.setBackgroundColor(getResources().getColor(R.color.white));
        yongjinTv.setTextColor(getResources().getColor(R.color.gray));
        indicatorYongjin.setBackgroundColor(getResources().getColor(R.color.white));
    }

    @OnClick(R.id.tixian_ll)
    public void tixian(){
        initTixianColor();
        list.clear();
        list.add(bean2);
        adapter = new MyMoneyRecordAdapter(R.layout.item_money_record,list,TIXIAN);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    private void initTixianColor() {
        quanbuTv.setTextColor(getResources().getColor(R.color.gray));
        indicatorQuanbu.setBackgroundColor(getResources().getColor(R.color.white));
        shouyiTv.setTextColor(getResources().getColor(R.color.gray));
        indicatorShouyi.setBackgroundColor(getResources().getColor(R.color.white));
        tixianTv.setTextColor(getResources().getColor(R.color.green));
        indicatorTixian.setBackgroundColor(getResources().getColor(R.color.green));
        yongjinTv.setTextColor(getResources().getColor(R.color.gray));
        indicatorYongjin.setBackgroundColor(getResources().getColor(R.color.white));
    }

    @OnClick(R.id.yongjin_ll)
    public void yongjin(){
        initYongjinColor();
        list.clear();
        list.add(bean3);
        adapter = new MyMoneyRecordAdapter(R.layout.item_money_record,list,YONGJIN);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    private void initYongjinColor() {
        quanbuTv.setTextColor(getResources().getColor(R.color.gray));
        indicatorQuanbu.setBackgroundColor(getResources().getColor(R.color.white));
        shouyiTv.setTextColor(getResources().getColor(R.color.gray));
        indicatorShouyi.setBackgroundColor(getResources().getColor(R.color.white));
        tixianTv.setTextColor(getResources().getColor(R.color.gray));
        indicatorTixian.setBackgroundColor(getResources().getColor(R.color.white));
        yongjinTv.setTextColor(getResources().getColor(R.color.green));
        indicatorYongjin.setBackgroundColor(getResources().getColor(R.color.green));
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        TUtils.showShort(mContext,"点击了---item" + position);
    }

    private class MyMoneyRecordAdapter extends BaseQuickAdapter<MyMoneyRecordBean,BaseViewHolder>{
        private int mPosition;

        public MyMoneyRecordAdapter(@LayoutRes int layoutResId, @Nullable List<MyMoneyRecordBean> data, int position) {
            super(layoutResId, data);
            this.mPosition = position;
        }

        @Override
        protected void convert(BaseViewHolder helper, MyMoneyRecordBean item) {
            ImageView tixianIv = helper.getView(R.id.item_tixian_iv);
            TextView dateTv = helper.getView(R.id.item_date_tv);
            TextView contentTv = helper.getView(R.id.item_content_tv);
            dateTv.setText(item.getDate());
            contentTv.setText(item.getContent());
            String type = item.getType();
            switch (mPosition){
                case ALL:
                    if (TextUtils.equals(type,"tixian")){
                        tixianIv.setVisibility(View.VISIBLE);
                    }
                    break;
                case SHOUYI:
                    break;
                case TIXIAN:
                    tixianIv.setVisibility(View.VISIBLE);
                    break;
                case YONGJIN:
                    break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_back_iv:
                finish();
                break;
        }
    }
}
