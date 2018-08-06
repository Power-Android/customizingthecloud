package com.power.customizingthecloud.activity.mine;

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
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.KefuListBean;
import com.power.customizingthecloud.callback.JsonCallback;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.Urls;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

public class CustomerCenterActivity extends BaseActivity implements View.OnClickListener, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.shsq_ll)
    LinearLayout shsqLl;
    @BindView(R.id.xgdz_ll)
    LinearLayout xgdzLl;
    @BindView(R.id.zhaq_ll)
    LinearLayout zhaqLl;
    @BindView(R.id.changjian_tv)
    TextView changjianTv;
    @BindView(R.id.indicator_changjian)
    View indicatorChangjian;
    @BindView(R.id.changjian_ll)
    LinearLayout changjianLl;
    @BindView(R.id.shouhou_tv)
    TextView shouhouTv;
    @BindView(R.id.indicator_shouhou)
    View indicatorShouhou;
    @BindView(R.id.shouhou_ll)
    LinearLayout shouhouLl;
    @BindView(R.id.renyang_tv)
    TextView renyangTv;
    @BindView(R.id.indicator_renyang)
    View indicatorRenyang;
    @BindView(R.id.renyang_ll)
    LinearLayout renyangLl;
    @BindView(R.id.wuliu_tv)
    TextView wuliuTv;
    @BindView(R.id.indicator_wuliu)
    View indicatorWuliu;
    @BindView(R.id.wuliu_ll)
    LinearLayout wuliuLl;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.cnxw_ll)
    LinearLayout cnxwLl;
    @BindView(R.id.zxkf_ll)
    LinearLayout zxkfLl;
    @BindView(R.id.pjfk_ll)
    LinearLayout pjfkLl;
    private List<KefuListBean.DataEntity> data;
    private CustomerCenterAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_center);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleBackIv.setOnClickListener(this);
        titleContentTv.setText("客服中心");
        shsqLl.setOnClickListener(this);
        xgdzLl.setOnClickListener(this);
        zhaqLl.setOnClickListener(this);
        cnxwLl.setOnClickListener(this);
        zxkfLl.setOnClickListener(this);
        pjfkLl.setOnClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setNestedScrollingEnabled(false);
        initData(4);
    }

    private void initData(int position) {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(this, "token", ""));
        HttpParams params = new HttpParams();
        params.put("class_id", position);
        OkGo.<KefuListBean>get(Urls.BASEURL + "api/v2/kefu/after-sale")
                .headers(headers)
                .params(params)
                .execute(new JsonCallback<KefuListBean>(KefuListBean.class) {
                    @Override
                    public void onSuccess(Response<KefuListBean> response) {
                        KefuListBean bean = response.body();
                        int code = bean.getCode();
                        if (code == 1) {
                            data = bean.getData();
                            adapter = new CustomerCenterAdapter(R.layout.item_customer_center, data);
                            recyclerView.setAdapter(adapter);
                            adapter.setOnItemClickListener(CustomerCenterActivity.this);
                        }
                    }
                });
    }

    @OnClick(R.id.changjian_ll)
    public void setChangjianLl() {
        initChangjianColor();
    }

    private void initChangjianColor() {
        changjianTv.setTextColor(getResources().getColor(R.color.green));
        indicatorChangjian.setBackgroundColor(getResources().getColor(R.color.green));
        shouhouTv.setTextColor(getResources().getColor(R.color.text_black));
        indicatorShouhou.setBackgroundColor(getResources().getColor(R.color.white));
        renyangTv.setTextColor(getResources().getColor(R.color.text_black));
        indicatorRenyang.setBackgroundColor(getResources().getColor(R.color.white));
        wuliuTv.setTextColor(getResources().getColor(R.color.text_black));
        indicatorWuliu.setBackgroundColor(getResources().getColor(R.color.white));
        initData(4);
    }

    @OnClick(R.id.shouhou_ll)
    public void setShouhouLl() {
        initShouhouColro();
    }

    private void initShouhouColro() {
        changjianTv.setTextColor(getResources().getColor(R.color.text_black));
        indicatorChangjian.setBackgroundColor(getResources().getColor(R.color.white));
        shouhouTv.setTextColor(getResources().getColor(R.color.green));
        indicatorShouhou.setBackgroundColor(getResources().getColor(R.color.green));
        renyangTv.setTextColor(getResources().getColor(R.color.text_black));
        indicatorRenyang.setBackgroundColor(getResources().getColor(R.color.white));
        wuliuTv.setTextColor(getResources().getColor(R.color.text_black));
        indicatorWuliu.setBackgroundColor(getResources().getColor(R.color.white));
        initData(5);
    }

    @OnClick(R.id.renyang_ll)
    public void setRenyangLl() {
        initRengyangColor();
    }

    private void initRengyangColor() {
        changjianTv.setTextColor(getResources().getColor(R.color.text_black));
        indicatorChangjian.setBackgroundColor(getResources().getColor(R.color.white));
        shouhouTv.setTextColor(getResources().getColor(R.color.text_black));
        indicatorShouhou.setBackgroundColor(getResources().getColor(R.color.white));
        renyangTv.setTextColor(getResources().getColor(R.color.green));
        indicatorRenyang.setBackgroundColor(getResources().getColor(R.color.green));
        wuliuTv.setTextColor(getResources().getColor(R.color.text_black));
        indicatorWuliu.setBackgroundColor(getResources().getColor(R.color.white));
        initData(6);
    }

    @OnClick(R.id.wuliu_ll)

    public void setWuliuLl() {
        initWuliuColor();
    }

    private void initWuliuColor() {
        changjianTv.setTextColor(getResources().getColor(R.color.text_black));
        indicatorChangjian.setBackgroundColor(getResources().getColor(R.color.white));
        shouhouTv.setTextColor(getResources().getColor(R.color.text_black));
        indicatorShouhou.setBackgroundColor(getResources().getColor(R.color.white));
        renyangTv.setTextColor(getResources().getColor(R.color.text_black));
        indicatorRenyang.setBackgroundColor(getResources().getColor(R.color.white));
        wuliuTv.setTextColor(getResources().getColor(R.color.green));
        indicatorWuliu.setBackgroundColor(getResources().getColor(R.color.green));
        initData(7);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.shsq_ll://售后申请
//                Intent intent = new Intent(mContext, IssuesListActivity.class);
//                intent.putExtra("class_id",1);
//                startActivity(intent);
                startActivity(new Intent(mContext, RefundAfterActivity.class));
                break;
            case R.id.xgdz_ll://修改地址
//                Intent intent1 = new Intent(mContext, IssuesListActivity.class);
//                intent1.putExtra("class_id",2);
//                startActivity(intent1);
                startActivity(new Intent(mContext, AddressManagerActivity.class));
                break;
            case R.id.zhaq_ll://帐号安全
//                Intent intent2 = new Intent(mContext, IssuesListActivity.class);
//                intent2.putExtra("class_id",3);
//                startActivity(intent2);
                startActivity(new Intent(mContext, AccountSafeActivity.class));
                break;
            case R.id.cnxw_ll://猜你想问
                Intent intent = new Intent(mContext, IssuesListActivity.class);
                intent.putExtra("class_id",8);
                startActivity(intent);
                break;
            case R.id.zxkf_ll://咨询客服
                RongIM.getInstance().startConversation(this, Conversation.ConversationType.PRIVATE, "admin_1", "客服牧小童");
                break;
            case R.id.pjfk_ll://评价反馈
                startActivity(new Intent(mContext, FeedbackActivity.class));
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        //        TUtils.showShort(mContext,"点击了---item" + position);
        Intent intent = new Intent(mContext, WentiDetailActivity.class);
        intent.putExtra("id",data.get(position).getId()+"");
        startActivity(intent);
    }

    private class CustomerCenterAdapter extends BaseQuickAdapter<KefuListBean.DataEntity, BaseViewHolder> {

        public CustomerCenterAdapter(@LayoutRes int layoutResId, @Nullable List<KefuListBean.DataEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, KefuListBean.DataEntity item) {
            helper.setText(R.id.item_content_tv,item.getTitle());
        }
    }
}
