package com.power.customizingthecloud.activity.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.LatestBean;
import com.power.customizingthecloud.bean.LatestDetialBean;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.TUtils;
import com.power.customizingthecloud.utils.Urls;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LatestDetailActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.content_tv)
    TextView contentTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latest_detail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("活动详情");
        String id = getIntent().getStringExtra("id");

        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));
        HttpParams params = new HttpParams();
        params.put("type","2");
        params.put("id",id);

        OkGo.<LatestDetialBean>get(Urls.BASEURL + "api/v2/article/show")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<LatestDetialBean>(this,LatestDetialBean.class) {
                    @Override
                    public void onSuccess(Response<LatestDetialBean> response) {
                        LatestDetialBean latestBean = response.body();
                        if (latestBean.getCode() == 1){
                            contentTv.setText(latestBean.getData().getBody());
                        }else {
                            TUtils.showShort(mContext,latestBean.getMessage());
                        }
                    }
                });
    }

    @OnClick(R.id.title_back_iv)
    public void onViewClicked() {
        finish();
    }
}
