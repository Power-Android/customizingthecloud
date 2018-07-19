package com.power.customizingthecloud.activity.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.BaseBean;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.Urls;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BindAliPayActivity extends BaseActivity {

    @BindView(R.id.view_01)
    ImageView view01;
    @BindView(R.id.bind_show_tv)
    TextView bindShowTv;
    @BindView(R.id.content_tv)
    TextView contentTv;
    @BindView(R.id.is_bind_tv)
    TextView isBindTv;
    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_ali_pay);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("绑定支付宝");
    }

    @OnClick({R.id.title_back_iv, R.id.is_bind_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.is_bind_tv:
                bindShowTv.setVisibility(View.VISIBLE);
                contentTv.setText("支付宝帐号：139****6666");
                isBindTv.setText("解绑");

//                bindAlipayCount();
                break;
        }
    }

    private void bindAlipayCount() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(this, "token", ""));
        HttpParams params = new HttpParams();
        params.put("bind_alipay", "");
        OkGo.<BaseBean>post(Urls.BASEURL + "api/v2/user/bind_alipay")
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<BaseBean>(BindAliPayActivity.this, BaseBean.class) {
                    @Override
                    public void onSuccess(Response<BaseBean> response) {
                        BaseBean bean = response.body();
                        int code = bean.getCode();
                        if (code == 0) {
                            Toast.makeText(BindAliPayActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (code == 1) {
                            Toast.makeText(BindAliPayActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });
    }
}
