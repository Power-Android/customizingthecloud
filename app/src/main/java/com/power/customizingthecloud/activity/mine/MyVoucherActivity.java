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
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.MyVoucherBean;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.fragment.home.GoodListActivity;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.Urls;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyVoucherActivity extends BaseActivity implements View.OnClickListener, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private String type;
    private List<MyVoucherBean.DataBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_voucher);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("我的代金券");
        titleBackIv.setOnClickListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setNestedScrollingEnabled(false);

        type = getIntent().getStringExtra("type");

        initData();
    }

    private void initData() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));

        OkGo.<MyVoucherBean>get(Urls.BASEURL + "api/v2/user/voucher")
                .tag(this)
                .headers(headers)
                .execute(new DialogCallback<MyVoucherBean>(this,MyVoucherBean.class) {
                    @Override
                    public void onSuccess(Response<MyVoucherBean> response) {
                        MyVoucherBean body = response.body();
                        if (body.getCode() == 1){
                            list = body.getData();
                            MyVoucherAdapter adapter = new MyVoucherAdapter(R.layout.item_my_voucher, list);
                            recyclerView.setAdapter(adapter);
                            adapter.setOnItemClickListener(MyVoucherActivity.this);
                        }
                    }
                });
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (list.get(position).getState() != 1){
            if (type != null && type.equals("query")){
                //数据是使用Intent返回
                Intent intent = new Intent();
                //把返回数据存入Intent
                intent.putExtra("result", list.get(position));
                setResult(1,intent);
                finish();
            }else {
                startActivity(new Intent(mContext, GoodListActivity.class));
            }
        }
    }

    private class MyVoucherAdapter extends BaseQuickAdapter<MyVoucherBean.DataBean,BaseViewHolder>{

        public MyVoucherAdapter(@LayoutRes int layoutResId, @Nullable List<MyVoucherBean.DataBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, MyVoucherBean.DataBean item) {
            helper.setText(R.id.item_money_tv,item.getPrice()+"")
                    .setText(R.id.item_man_jian_tv,"满￥"+item.getOrder_limit()+"使用")
                    .setText(R.id.item_name_tv,item.getTitle())
//                    .setText(R.id.item_use_tv,item.getUse())
                    .setText(R.id.item_date_tv,item.getStart_date()+"-"+item.getEnd_date());
            if (item.getState() == 3){
                helper.getView(R.id.yi_guo_qi_iv).setVisibility(View.VISIBLE);
            }else if (item.getState()==2){//已用

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
