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
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.AllMoneyRecordDetailBean;
import com.power.customizingthecloud.callback.JsonCallback;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.Urls;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyMoneyRecordActivity extends BaseActivity implements View.OnClickListener, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.quanbu_tv)
    TextView quanbuTv;
    @BindView(R.id.indicator_quanbu)
    View indicatorQuanbu;
    @BindView(R.id.quanbu_ll)
    LinearLayout quanbuLl;
    @BindView(R.id.shouyi_tv)
    TextView shouyiTv;
    @BindView(R.id.indicator_shouyi)
    View indicatorShouyi;
    @BindView(R.id.shouyi_ll)
    LinearLayout shouyiLl;
    @BindView(R.id.tixian_tv)
    TextView tixianTv;
    @BindView(R.id.indicator_tixian)
    View indicatorTixian;
    @BindView(R.id.tixian_ll)
    LinearLayout tixianLl;
    @BindView(R.id.yongjin_tv)
    TextView yongjinTv;
    @BindView(R.id.indicator_yongjin)
    View indicatorYongjin;
    @BindView(R.id.yongjin_ll)
    LinearLayout yongjinLl;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private final int ALL = 0, SHOUYI = 2, TIXIAN = 3, YONGJIN = 4;
    private MyMoneyRecordAdapter adapter;
    private int type;
    private List<AllMoneyRecordDetailBean.DataEntity> data;
    private String after;
    private boolean isLoadMore;
    @BindView(R.id.springview)
    SpringView mSpringview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_money_record);
        ButterKnife.bind(this);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        mSpringview.setHeader(new DefaultHeader(mContext));
        mSpringview.setFooter(new DefaultFooter(mContext));
        mSpringview.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                after = "";
                isLoadMore = false;
                initData();
                mSpringview.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                isLoadMore = true;
                initData();
                mSpringview.onFinishFreshAndLoad();
            }
        });
    }


    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("全部记录");
        titleBackIv.setOnClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setNestedScrollingEnabled(false);
    }

    private void initData() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));
        HttpParams params = new HttpParams();
        params.put("after", after);
        params.put("type", type);
        OkGo.<AllMoneyRecordDetailBean>get(Urls.BASEURL + "api/v2/user/all-details")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new JsonCallback<AllMoneyRecordDetailBean>(AllMoneyRecordDetailBean.class) {
                    @Override
                    public void onSuccess(Response<AllMoneyRecordDetailBean> response) {
                        AllMoneyRecordDetailBean caiFuBean = response.body();
                        if (caiFuBean.getCode() == 1) {
                            if (!isLoadMore) {
                                data = caiFuBean.getData();
                                adapter = new MyMoneyRecordAdapter(R.layout.item_money_record, data);
                                recyclerView.setAdapter(adapter);
                            } else {
                                if (caiFuBean.getData() != null && caiFuBean.getData().size() > 0) {
                                    data.addAll(caiFuBean.getData());
                                    adapter.notifyDataSetChanged();
                                } else {
                                    Toast.makeText(mContext, "没有更多了~", Toast.LENGTH_SHORT).show();
                                }
                            }
                            adapter.setOnItemClickListener(MyMoneyRecordActivity.this);
                        }
                    }
                });
    }

    @OnClick(R.id.quanbu_ll)
    public void quanbu() {
        initQuanbuColor();
        type = ALL;
        after="";
        initData();
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
    public void shouyi() {
        initShouyiColor();
        type = SHOUYI;
        after="";
        initData();
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
    public void tixian() {
        initTixianColor();
        type = TIXIAN;
        after="";
        initData();
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
    public void yongjin() {
        initYongjinColor();
        type = YONGJIN;
        after="";
        initData();
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
        if (type == TIXIAN) {
            startActivity(new Intent(mContext, TixianMingxiDetailActivity.class));
        }
    }

    private class MyMoneyRecordAdapter extends BaseQuickAdapter<AllMoneyRecordDetailBean.DataEntity, BaseViewHolder> {

        public MyMoneyRecordAdapter(@LayoutRes int layoutResId, @Nullable List<AllMoneyRecordDetailBean.DataEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, AllMoneyRecordDetailBean.DataEntity item) {
            after = item.getId() + "";
            ImageView tixianIv = helper.getView(R.id.item_tixian_iv);
            TextView dateTv = helper.getView(R.id.item_date_tv);
            TextView contentTv = helper.getView(R.id.item_content_tv);
            dateTv.setText(item.getCreated_at());
            contentTv.setText(item.getNote());
            switch (type) {
                case ALL:
                    tixianIv.setVisibility(View.VISIBLE);
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
        switch (v.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
        }
    }
}
