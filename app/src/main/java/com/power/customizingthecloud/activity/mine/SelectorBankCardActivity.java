package com.power.customizingthecloud.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

public class SelectorBankCardActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    private List<MyBankListBean.DataEntity> data;
    private SelectCardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector_bank_card);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
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
                            data = body.getData();
                            adapter = new SelectCardAdapter(R.layout.item_select_card, data);
                            recyclerView.setAdapter(adapter);
                        }
                    }
                });
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleBackIv.setOnClickListener(this);
        titleContentTv.setText("选择银行卡");
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setNestedScrollingEnabled(false);
    }

    private class SelectCardAdapter extends BaseQuickAdapter<MyBankListBean.DataEntity, BaseViewHolder> {

        public SelectCardAdapter(@LayoutRes int layoutResId, @Nullable List<MyBankListBean.DataEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, MyBankListBean.DataEntity item) {
            helper.setText(R.id.item_name_tv, item.getBank_name());
            CheckBox checkBox = helper.getView(R.id.item_checkbox);
            if (item.isChecked()) {
                checkBox.setChecked(true);
            } else {
                checkBox.setChecked(false);
            }
            helper.getView(R.id.rl_item).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < data.size(); i++) {
                        if (i == helper.getAdapterPosition()) {
                            data.get(i).setChecked(true);
                            Intent intent = new Intent();
                            intent.putExtra("bank_id", data.get(i).getId() + "");
                            intent.putExtra("bank_name", data.get(i).getBank_name());
                            setResult(1, intent);
                            finish();
                        } else {
                            data.get(i).setChecked(false);
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            });
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
