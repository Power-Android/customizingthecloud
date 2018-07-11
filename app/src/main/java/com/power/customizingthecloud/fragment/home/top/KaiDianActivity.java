package com.power.customizingthecloud.fragment.home.top;

import android.content.Intent;
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

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.MyApplication;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.activity.mine.FenxiaoListActivoty;
import com.power.customizingthecloud.activity.mine.TixianFirstActivity;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.DainOrderBean;
import com.power.customizingthecloud.bean.KaidianBean;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.fragment.home.MyCodeActivity;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.Urls;
import com.power.customizingthecloud.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class KaiDianActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.title_qrcode_iv)
    ImageView titleQrcodeIv;
    @BindView(R.id.dianpu_face_iv)
    CircleImageView dianpuFaceIv;
    @BindView(R.id.dianpu_name_tv)
    TextView dianpuNameTv;
    @BindView(R.id.money_tv)
    TextView moneyTv;
    @BindView(R.id.view_01)
    ImageView view01;
    @BindView(R.id.ljsy_tv)
    TextView ljsyTv;
    @BindView(R.id.tixian_tv)
    TextView tixianTv;
    @BindView(R.id.team_tv)
    TextView teamTv;
    @BindView(R.id.indicator_team)
    View indicatorTeam;
    @BindView(R.id.team_ll)
    LinearLayout teamLl;
    @BindView(R.id.order_tv)
    TextView orderTv;
    @BindView(R.id.indicator_order)
    View indicatorOrder;
    @BindView(R.id.order_ll)
    LinearLayout orderLl;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.activity_kai_dian)
    LinearLayout activityKaiDian;
    @BindView(R.id.springview)
    SpringView mSpringview;
    private final int TEAM = 1, ORDER = 2;
    private List<KaidianBean.DataBean.TeamBean> teamList = new ArrayList<>();
    private HttpHeaders headers;
    private List<DainOrderBean.DataBean> orderList = new ArrayList<>();

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

        headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));

        initTeamColor();
        initData(headers);
        initListener();
    }

    private void initListener() {
        mSpringview.setHeader(new DefaultHeader(mContext));
        mSpringview.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                initData(headers);
                mSpringview.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {

            }
        });
    }

    private void initData(HttpHeaders headers) {
        OkGo.<KaidianBean>get(Urls.BASEURL + "api/v2/user/shop")
                .tag(this)
                .headers(headers)
                .execute(new DialogCallback<KaidianBean>(this, KaidianBean.class) {
                    @Override
                    public void onSuccess(Response<KaidianBean> response) {
                        KaidianBean body = response.body();
                        if (body.getCode() == 1) {
                            KaidianBean.DataBean data = body.getData();
                            if (!TextUtils.isEmpty(data.getUser_avatar()))
                                Glide.with(MyApplication.getGloableContext()).load(data.getUser_avatar()).into(dianpuFaceIv);
                            if (!TextUtils.isEmpty(data.getUser_name()) && data.getUser_name().length() > 15) {
                                String user_name = data.getUser_name();
                                String substring = user_name.substring(0, 15);
                                dianpuNameTv.setText(substring);
                            } else {
                                dianpuNameTv.setText(data.getUser_name());
                            }
                            ljsyTv.setText(data.getUser_distribution());

                            teamList = data.getTeam();
                            KaidianAdapter adapter = new KaidianAdapter(R.layout.item_kai_dian, teamList);
                            recyclerView.setAdapter(adapter);
                        }
                    }
                });
    }

    @OnClick(R.id.team_ll)
    public void setTeamLl() {
        initTeamColor();
        initData(headers);
    }

    private void initTeamColor() {
        teamTv.setTextColor(getResources().getColor(R.color.green));
        indicatorTeam.setBackgroundColor(getResources().getColor(R.color.green));
        orderTv.setTextColor(getResources().getColor(R.color.gray));
        indicatorOrder.setBackgroundColor(getResources().getColor(R.color.white));
    }

    @OnClick(R.id.order_ll)
    public void setOrderLl() {
        initOrderColor();
    }

    private void initOrderColor() {
        orderTv.setTextColor(getResources().getColor(R.color.green));
        indicatorOrder.setBackgroundColor(getResources().getColor(R.color.green));
        teamTv.setTextColor(getResources().getColor(R.color.gray));
        indicatorTeam.setBackgroundColor(getResources().getColor(R.color.white));

        OkGo.<DainOrderBean>get(Urls.BASEURL + "api/v2/user/shop-order")
                .tag(this)
                .headers(headers)
                .execute(new DialogCallback<DainOrderBean>(this, DainOrderBean.class) {
                    @Override
                    public void onSuccess(Response<DainOrderBean> response) {
                        DainOrderBean body = response.body();
                        if (body.getCode() == 1) {
                            orderList = body.getData();
                            OrderAdapter adapter = new OrderAdapter(R.layout.item_order, orderList);
                            recyclerView.setAdapter(adapter);
                        }
                    }
                });
    }

    private class KaidianAdapter extends BaseQuickAdapter<KaidianBean.DataBean.TeamBean, BaseViewHolder> {

        public KaidianAdapter(@LayoutRes int layoutResId, @Nullable List<KaidianBean.DataBean.TeamBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, KaidianBean.DataBean.TeamBean item) {

            Glide.with(mContext).load(item.getUser_avatar()).into((ImageView) helper.getView(R.id.item_face_iv));
            helper.setText(R.id.item_name_tv, item.getUser_name())
                    .setText(R.id.item_gongxian_tv, item.getTotal_price());
        }
    }

    private class OrderAdapter extends BaseQuickAdapter<DainOrderBean.DataBean, BaseViewHolder> {

        public OrderAdapter(@LayoutRes int layoutResId, @Nullable List<DainOrderBean.DataBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, DainOrderBean.DataBean item) {

            Glide.with(mContext).load(item.getGoods_image()).into((ImageView) helper.getView(R.id.item_face_iv));
            helper.setText(R.id.item_name_tv, item.getGoods_name())
                    .setText(R.id.item_date_tv, item.getCreated_at())
                    .setText(R.id.item_yongjin_tv, "所获佣金：￥" + item.getDistribution_price());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.title_qrcode_iv:
                startActivity(new Intent(mContext, MyCodeActivity.class));
                break;
            case R.id.money_tv:
                startActivity(new Intent(mContext, FenxiaoListActivoty.class));
                break;
            case R.id.tixian_tv:
                startActivity(new Intent(mContext, TixianFirstActivity.class));
                break;
        }
    }
}
