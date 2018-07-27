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

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.ReturnMoneyListBean;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.Urls;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RefundAfterActivity extends BaseActivity implements View.OnClickListener, BaseQuickAdapter.OnItemChildClickListener {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private List<ReturnMoneyListBean.DataEntity> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refund_after);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));
        HttpParams params = new HttpParams();
        OkGo.<ReturnMoneyListBean>get(Urls.BASEURL + "api/v2/user/order-return-list")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<ReturnMoneyListBean>(this, ReturnMoneyListBean.class) {
                    @Override
                    public void onSuccess(Response<ReturnMoneyListBean> response) {
                        ReturnMoneyListBean body = response.body();
                        if (body.getCode() == 1) {
                            data = body.getData();
                            RefundAfterAdapter adapter = new RefundAfterAdapter(R.layout.item_refund_after, data);
                            recyclerView.setAdapter(adapter);
                            adapter.setOnItemChildClickListener(RefundAfterActivity.this);
                        }
                    }
                });
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("退款/售后");
        titleBackIv.setOnClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(mContext, RefundGoodActivity.class);
        intent.putExtra("order_id",data.get(position).getId()+"");
        startActivity(intent);
    }

    private class RefundAfterAdapter extends BaseQuickAdapter<ReturnMoneyListBean.DataEntity,BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {

        public RefundAfterAdapter(@LayoutRes int layoutResId, @Nullable List<ReturnMoneyListBean.DataEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ReturnMoneyListBean.DataEntity item) {
            RecyclerView itemRecycler = helper.getView(R.id.item_recycler);
            itemRecycler.setNestedScrollingEnabled(false);
            itemRecycler.setLayoutManager(new LinearLayoutManager(mContext));
            ItemAdapter adapter = new ItemAdapter(R.layout.item_recycler_refund,item.getGoods());
            helper.addOnClickListener(R.id.item_use_tv);
            itemRecycler.setAdapter(adapter);
            adapter.setOnItemClickListener(this);
        }

        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            Intent intent = new Intent(mContext, RefundGoodActivity.class);
            intent.putExtra("order_id",data.get(position).getId()+"");
            startActivity(intent);
        }
    }

    private class ItemAdapter extends BaseQuickAdapter<ReturnMoneyListBean.DataEntity.GoodsEntity,BaseViewHolder>{

        public ItemAdapter(int layoutResId, @Nullable List<ReturnMoneyListBean.DataEntity.GoodsEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ReturnMoneyListBean.DataEntity.GoodsEntity item) {
            Glide.with(mContext).load(item.getGoods_image()).into((ImageView) helper.getView(R.id.item_img_iv));
            helper.setText(R.id.item_name_tv,item.getGoods_name())
                    .setText(R.id.item_fenlei_tv,"商品分类："+item.getGoods_class())
                    .setText(R.id.item_money_tv,"¥"+item.getGoods_price())
                    .setText(R.id.item_num_tv,"x"+item.getGoods_num());
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
