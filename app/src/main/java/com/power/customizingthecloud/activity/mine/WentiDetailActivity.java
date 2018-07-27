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
import com.power.customizingthecloud.bean.KefuDetailBean;
import com.power.customizingthecloud.callback.JsonCallback;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.Urls;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

public class WentiDetailActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.title_kefu_iv)
    ImageView titleKefuIv;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_content)
    TextView tv_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wenti_detail);
        ButterKnife.bind(this);
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("问题详情");
        titleKefuIv.setVisibility(View.VISIBLE);
        initData();
    }

    private void initData() {
        String id = getIntent().getStringExtra("id");
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(this, "token", ""));
        HttpParams params = new HttpParams();
        params.put("id", id);
        OkGo.<KefuDetailBean>get(Urls.BASEURL + "api/v2/kefu/after-show")
                .headers(headers)
                .params(params)
                .execute(new JsonCallback<KefuDetailBean>(KefuDetailBean.class) {
                    @Override
                    public void onSuccess(Response<KefuDetailBean> response) {
                        KefuDetailBean bean = response.body();
                        int code = bean.getCode();
                        if (code == 1) {
                            KefuDetailBean.DataEntity data = bean.getData();
                            tv_content.setText(data.getContent());
                            tv_title.setText(data.getTitle());
                        }
                    }
                });
    }

    @OnClick({R.id.title_back_iv, R.id.title_kefu_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.title_kefu_iv:
                //                TUtils.showShort(mContext,"点击了---客服");
                RongIM.getInstance().startConversation(this, Conversation.ConversationType.PRIVATE, "admin_1", "客服牧小童");
                break;
        }
    }
}
