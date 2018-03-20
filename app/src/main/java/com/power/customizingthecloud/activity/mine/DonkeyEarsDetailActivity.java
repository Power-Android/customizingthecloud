package com.power.customizingthecloud.activity.mine;

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
import com.power.customizingthecloud.bean.DonkeyEarsDetailBaen;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.Urls;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DonkeyEarsDetailActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private List<DonkeyEarsDetailBaen.DataBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donkey_ears_detail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("驴耳朵记录");

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setNestedScrollingEnabled(false);

        initData();
    }

    private void initData() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));

        OkGo.<DonkeyEarsDetailBaen>get(Urls.BASEURL + "api/v2/eselsohr")
                .tag(this)
                .headers(headers)
                .execute(new DialogCallback<DonkeyEarsDetailBaen>(this,DonkeyEarsDetailBaen.class) {
                    @Override
                    public void onSuccess(Response<DonkeyEarsDetailBaen> response) {
                        DonkeyEarsDetailBaen body = response.body();
                        if (body.getCode() == 1){
                            list = body.getData();
                            DonkeyEarsAdapter adapter = new DonkeyEarsAdapter(R.layout.item_donkeyears_detail, list);
                            recyclerView.setAdapter(adapter);
                        }
                    }
                });
    }

    private class DonkeyEarsAdapter extends BaseQuickAdapter<DonkeyEarsDetailBaen.DataBean,BaseViewHolder>{

        public DonkeyEarsAdapter(@LayoutRes int layoutResId, @Nullable List<DonkeyEarsDetailBaen.DataBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, DonkeyEarsDetailBaen.DataBean item) {
            helper.setText(R.id.item_date_tv,item.getTime())
                    .setText(R.id.item_content_tv,item.getNote());
        }
    }

    @OnClick(R.id.title_back_iv)
    public void onViewClicked() {
        finish();
    }
}
