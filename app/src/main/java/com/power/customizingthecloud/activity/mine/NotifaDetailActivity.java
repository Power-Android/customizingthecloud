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
import com.power.customizingthecloud.bean.NotifacationBean;
import com.power.customizingthecloud.bean.NotificationDetailBean;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.TUtils;
import com.power.customizingthecloud.utils.Urls;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotifaDetailActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView mTitleBackIv;
    @BindView(R.id.title_content_tv)
    TextView mTitleContentTv;
    @BindView(R.id.content_tv)
    TextView contentTv;
    private List<NotifacationBean.DataBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifa_detail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mTitleBackIv.setVisibility(View.VISIBLE);
        mTitleContentTv.setText("消息详情");

        String id = getIntent().getStringExtra("id");
        initData(id);
    }

    private void initData(String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));
        HttpParams params = new HttpParams();
        params.put("id", id);

        OkGo.<NotificationDetailBean>get(Urls.BASEURL + "api/v2/system/read")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<NotificationDetailBean>(this, NotificationDetailBean.class) {
                    @Override
                    public void onSuccess(Response<NotificationDetailBean> response) {
                        NotificationDetailBean body = response.body();
                        if (body.getCode() == 1) {
                            contentTv.setText(body.getData().getContent());
                        }else {
                            TUtils.showShort(mContext,body.getMessage());
                        }
                    }
                });
    }

    @OnClick(R.id.title_back_iv)
    public void onViewClicked() {
        finish();
    }
}
