package com.power.customizingthecloud.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.MaolvTopBean;
import com.power.customizingthecloud.fragment.home.renyang.detail.RenYangDetailActivity;
import com.power.customizingthecloud.login.LoginActivity;
import com.power.customizingthecloud.utils.SpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MaolvDetailActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_back_iv) ImageView titleBackIv;
    @BindView(R.id.title_content_tv) TextView titleContentTv;
    @BindView(R.id.iv_top) ImageView ivTop;
    @BindView(R.id.recycler_top) RecyclerView recyclerTop;
    @BindView(R.id.xiangmu_tv) TextView mXiangmuTv;
    @BindView(R.id.indicator_xiangmu) View mIndicatorXiangmu;
    @BindView(R.id.xiangmu_ll) LinearLayout mXiangmuLl;
    @BindView(R.id.record_tv) TextView mRecordTv;
    @BindView(R.id.indicator_record) View mIndicatorRecord;
    @BindView(R.id.record_ll) LinearLayout mRecordLl;
    @BindView(R.id.paihang_tv) TextView mPaihangTv;
    @BindView(R.id.indicator_paihang) View mIndicatorPaihang;
    @BindView(R.id.paihang_ll) LinearLayout mPaihangLl;
    @BindView(R.id.des_tv) TextView mDesTv;
    @BindView(R.id.indicator_des) View mIndicatorDes;
    @BindView(R.id.des_ll) LinearLayout mDesLl;
    @BindView(R.id.webview) WebView mWebview;
    @BindView(R.id.recycler) RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maolv_detail);
        ButterKnife.bind(this);
        titleBackIv.setVisibility(View.VISIBLE);
        titleBackIv.setOnClickListener(this);
        titleContentTv.setText("认养详情");

        List<MaolvTopBean> list = new ArrayList<>();
        MaolvTopBean bean = new MaolvTopBean();
        bean.setTitle("第41期：【11.15日驴妈妈】");
        list.add(bean);
        MaolvTopBean bean1 = new MaolvTopBean();
        bean1.setTitle("产地：宁夏 - 青铜峡");
        list.add(bean1);
        MaolvTopBean bean2 = new MaolvTopBean();
        bean2.setTitle("年利润率：15%");
        list.add(bean2);
        MaolvTopBean bean3 = new MaolvTopBean();
        bean3.setTitle("养殖周期：360天");
        list.add(bean3);
        MaolvTopBean bean4 = new MaolvTopBean();
        bean4.setTitle("养殖利润：20000.00元");
        list.add(bean4);
        MaolvTopBean bean5 = new MaolvTopBean();
        bean5.setTitle("利润获取：到期一次性返本分红");
        list.add(bean5);

        recyclerTop.setLayoutManager(new LinearLayoutManager(this));
        recyclerTop.setNestedScrollingEnabled(false);
        TopAdapter topAdapter = new TopAdapter(R.layout.item_renyang_detail_top, list);
        recyclerTop.setAdapter(topAdapter);
        recycler.setLayoutManager(new LinearLayoutManager(mContext));
        recycler.setNestedScrollingEnabled(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
        }
    }

    private class TopAdapter extends BaseQuickAdapter<MaolvTopBean, BaseViewHolder> {

        public TopAdapter(@LayoutRes int layoutResId, @Nullable List<MaolvTopBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, MaolvTopBean item) {
            helper.setText(R.id.tv_title, item.getTitle());
        }
    }

    @OnClick(R.id.xiangmu_ll)
    public void xiangmu() {
        initXiangMuColor();
        mWebview.setVisibility(View.VISIBLE);
        recycler.setVisibility(View.GONE);
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
        recycler.setVisibility(View.VISIBLE);
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        RecordAdapter recordAdapter = new RecordAdapter(R.layout.item_record, list);
        recycler.setAdapter(recordAdapter);
    }

    private class RecordAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public RecordAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
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
        recycler.setVisibility(View.VISIBLE);
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        PaiHangAdapter paiHangAdapter = new PaiHangAdapter(R.layout.item_paihangbang, list);
        recycler.setAdapter(paiHangAdapter);
    }

    private class PaiHangAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public PaiHangAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            int adapterPosition = helper.getAdapterPosition();
            ImageView iv_pai=helper.getView(R.id.iv_pai);
            TextView tv_paixu=helper.getView(R.id.tv_paixu);
            if (adapterPosition==0){
                iv_pai.setImageResource(R.drawable.jinpai);
            }else if (adapterPosition==1){
                iv_pai.setImageResource(R.drawable.yinpai);
            }else if (adapterPosition==2){
                iv_pai.setImageResource(R.drawable.tongpai);
            }else {
                iv_pai.setVisibility(View.GONE);
                tv_paixu.setVisibility(View.VISIBLE);
                tv_paixu.setText(adapterPosition+1+"");
            }
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
        mWebview.setVisibility(View.VISIBLE);
        recycler.setVisibility(View.GONE);
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
