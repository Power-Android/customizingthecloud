package com.power.customizingthecloud.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
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

public class TixianDetailActivity extends BaseActivity implements View.OnClickListener, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tixian_detail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleBackIv.setOnClickListener(this);
        titleContentTv.setText("提现明细");
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setNestedScrollingEnabled(false);
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));
        HttpParams params = new HttpParams();
        params.put("after", "");
        params.put("type", 3);
        OkGo.<AllMoneyRecordDetailBean>get(Urls.BASEURL + "api/v2/user/all-details")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new JsonCallback<AllMoneyRecordDetailBean>(AllMoneyRecordDetailBean.class) {
                    @Override
                    public void onSuccess(Response<AllMoneyRecordDetailBean> response) {
                        AllMoneyRecordDetailBean caiFuBean = response.body();
                        if (caiFuBean.getCode() == 1) {
                            List<AllMoneyRecordDetailBean.DataEntity> data = caiFuBean.getData();
                            TixianDetailAdapter adapter = new TixianDetailAdapter(R.layout.item_tixian_detail,data);
                            recyclerView.setAdapter(adapter);
                            adapter.setOnItemClickListener(TixianDetailActivity.this);
                        }
                    }
                });
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//        TUtils.showShort(mContext,"点击了---item"+position);
        startActivity(new Intent(mContext,TixianMingxiDetailActivity.class));
    }

    private class TixianDetailAdapter extends BaseQuickAdapter<AllMoneyRecordDetailBean.DataEntity,BaseViewHolder>{

        public TixianDetailAdapter(@LayoutRes int layoutResId, @Nullable List<AllMoneyRecordDetailBean.DataEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, AllMoneyRecordDetailBean.DataEntity item) {
            helper.setText(R.id.item_date_tv,item.getCreated_at())
                    .setText(R.id.item_content_tv,item.getNote());
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
