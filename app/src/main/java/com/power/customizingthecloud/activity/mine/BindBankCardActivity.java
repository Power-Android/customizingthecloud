package com.power.customizingthecloud.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.MyBankListBean;
import com.power.customizingthecloud.callback.JsonCallback;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.Urls;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BindBankCardActivity extends BaseActivity implements View.OnClickListener, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.add_bank_rl)
    RelativeLayout addBankRl;
    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_bank_card);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleBackIv.setOnClickListener(this);
        titleContentTv.setText("银行卡管理");
        addBankRl.setOnClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(this, "token", ""));
        OkGo.<MyBankListBean>get(Urls.BASEURL + "api/v2/user/bankcard-list")
                .tag(this)
                .headers(headers)
                .execute(new JsonCallback<MyBankListBean>(MyBankListBean.class) {
                    @Override
                    public void onSuccess(Response<MyBankListBean> response) {
                        MyBankListBean body = response.body();
                        int code = body.getCode();
                        if (code == 0) {
                            Toast.makeText(mContext, body.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (code == 1) {
                            List<MyBankListBean.DataEntity> data = body.getData();
                            BindCardAdapter adapter = new BindCardAdapter(R.layout.item_bind_cart, data);
                            recyclerView.setAdapter(adapter);
                            adapter.setOnItemClickListener(BindBankCardActivity.this);
                        }
                    }
                });
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        startActivity(new Intent(mContext, BindCardDetailActivity.class));
    }

    private class BindCardAdapter extends BaseQuickAdapter<MyBankListBean.DataEntity, BaseViewHolder> {

        public BindCardAdapter(@LayoutRes int layoutResId, @Nullable List<MyBankListBean.DataEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, MyBankListBean.DataEntity item) {
            ImageView picIv = helper.getView(R.id.item_pic_iv);
            TextView nameTv = helper.getView(R.id.item_name_tv);
            TextView typeTv = helper.getView(R.id.item_type_tv);
            TextView numTv = helper.getView(R.id.item_num_tv);
            Glide.with(mContext).load(item.getImage()).into(picIv);
            nameTv.setText(item.getBank_name());
            numTv.setText(item.getBank_card());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.add_bank_rl:
                Intent intent = new Intent(mContext, TixianSecondActivity.class);
                intent.putExtra("type", "addCard");
                startActivity(intent);
                break;
        }
    }
}
