package com.power.customizingthecloud.fragment.home.top;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.activity.mine.ProductListActivoty;
import com.power.customizingthecloud.activity.mine.TixianFirstActivity;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.TeamBean;
import com.power.customizingthecloud.fragment.home.renyang.detail.BuyRecordFragment;
import com.power.customizingthecloud.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class KaiDianActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_back_iv) ImageView titleBackIv;
    @BindView(R.id.title_content_tv) TextView titleContentTv;
    @BindView(R.id.title_qrcode_iv) ImageView titleQrcodeIv;
    @BindView(R.id.dianpu_face_iv) CircleImageView dianpuFaceIv;
    @BindView(R.id.dianpu_name_tv) TextView dianpuNameTv;
    @BindView(R.id.money_tv) TextView moneyTv;
    @BindView(R.id.view_01) ImageView view01;
    @BindView(R.id.ljsy_tv) TextView ljsyTv;
    @BindView(R.id.tixian_tv) TextView tixianTv;
    @BindView(R.id.team_tv) TextView teamTv;
    @BindView(R.id.indicator_team) View indicatorTeam;
    @BindView(R.id.team_ll) LinearLayout teamLl;
    @BindView(R.id.order_tv) TextView orderTv;
    @BindView(R.id.indicator_order) View indicatorOrder;
    @BindView(R.id.order_ll) LinearLayout orderLl;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.activity_kai_dian) LinearLayout activityKaiDian;
    private final int TEAM = 1, ORDER = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kai_dian);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleBackIv.setOnClickListener(this);
        titleContentTv.setText("我的店铺");
        titleQrcodeIv.setVisibility(View.VISIBLE);
        titleQrcodeIv.setOnClickListener(this);

        dianpuFaceIv.setOnClickListener(this);
        moneyTv.setOnClickListener(this);
        tixianTv.setOnClickListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);
        initTeamColor();
    }

    @OnClick(R.id.team_ll)
    public void setTeamLl(){
        initTeamColor();
    }

    private void initTeamColor() {
        teamTv.setTextColor(getResources().getColor(R.color.green));
        indicatorTeam.setBackgroundColor(getResources().getColor(R.color.green));
        orderTv.setTextColor(getResources().getColor(R.color.gray));
        indicatorOrder.setBackgroundColor(getResources().getColor(R.color.white));
        List<TeamBean> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            TeamBean bean = new TeamBean();
            bean.setName("无敌小豆豆");
            bean.setGongxian("贡献：￥100.00"+i);
            list.add(bean);
        }
        KaidianAdapter adapter = new KaidianAdapter(R.layout.item_kai_dian,list,TEAM);
        recyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.order_ll)
    public void setOrderLl(){
        initOrderColor();
    }

    private void initOrderColor() {
        orderTv.setTextColor(getResources().getColor(R.color.green));
        indicatorOrder.setBackgroundColor(getResources().getColor(R.color.green));
        teamTv.setTextColor(getResources().getColor(R.color.gray));
        indicatorTeam.setBackgroundColor(getResources().getColor(R.color.white));
        List<TeamBean> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            TeamBean bean = new TeamBean();
            bean.setName("销售商品名称");
            bean.setDate("2018.02.06-2018.02.16");
            bean.setYongjin("所获佣金：￥100.00"+i);
            list.add(bean);
        }
        KaidianAdapter adapter = new KaidianAdapter(R.layout.item_order,list,ORDER);
        recyclerView.setAdapter(adapter);
    }

    private class KaidianAdapter extends BaseQuickAdapter<TeamBean,BaseViewHolder>{
        private int mPosition;
        public KaidianAdapter(@LayoutRes int layoutResId, @Nullable List<TeamBean> data, int position) {
            super(layoutResId, data);
            this.mPosition = position;
        }

        @Override
        protected void convert(BaseViewHolder helper, TeamBean item) {
            switch (mPosition){
                case TEAM:
                    helper.setText(R.id.item_name_tv,item.getName())
                            .setText(R.id.item_gongxian_tv,item.getGongxian());
                    break;
                case ORDER:
                    helper.setText(R.id.item_name_tv,item.getName())
                            .setText(R.id.item_date_tv,item.getDate())
                            .setText(R.id.item_yongjin_tv,item.getYongjin());
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
            case R.id.title_qrcode_iv:
                break;
            case R.id.money_tv:
                startActivity(new Intent(mContext,ProductListActivoty.class));
                break;
            case R.id.tixian_tv:
                startActivity(new Intent(mContext,TixianFirstActivity.class));
                break;
        }
    }
}
