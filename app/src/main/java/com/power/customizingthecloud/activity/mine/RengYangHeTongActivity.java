package com.power.customizingthecloud.activity.mine;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RengYangHeTongActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.webView)
    WebView mWebview;
    @BindView(R.id.title_message_iv)
    ImageView titleMessageIv;
    @BindView(R.id.title_list_iv)
    ImageView titleListIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.title_sign_in_iv)
    ImageView titleSignInIv;
    @BindView(R.id.title_setting_iv)
    ImageView titleSettingIv;
    @BindView(R.id.title_qrcode_iv)
    ImageView titleQrcodeIv;
    @BindView(R.id.title_shopcar_iv)
    ImageView titleShopcarIv;
    @BindView(R.id.title_share_iv)
    ImageView titleShareIv;
    @BindView(R.id.title_search_iv)
    ImageView titleSearchIv;
    @BindView(R.id.title_jia_iv)
    ImageView titleJiaIv;
    @BindView(R.id.title_kefu_iv)
    ImageView titleKefuIv;
    @BindView(R.id.title_content_right_tv)
    TextView titleContentRightTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reng_yang_he_tong);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleBackIv.setOnClickListener(this);
        titleContentTv.setText("认养合同");
        initWeb();
        mWebview.loadUrl("https://openapi.bestsign.info/openapi/v2/dist_v3/index.html#/donePage?developerId=1898248050875302504&data=FZiLywyKAh9Xywiiydj6jIf4psHZmcHXmsb4mYvYncj1ncrJkdiIA250DNeICfKLjIPJnsvYnYjXmsf0ncbWncbXncbWjJXJCNKKC1q5Dh" +
                "vJpJiXDNu2Bxu3jJXJywGXBwiKrwrJpJjWmsnYpcb5psrWjJXJygaOjIPJnIrXjJXJD2KMANuZjIPJD3mWD2eVyhiUFe9VBx5MFhKGw3KTAh1bnszYkNmUAtjTjMe1zxWOCgLJpJjWncbJEr%3D%3D");
    }

    private void initWeb() {
        WebSettings webSettings = mWebview.getSettings();
        webSettings.setJavaScriptEnabled(true); // 设置支持javascript脚本
        webSettings.setUseWideViewPort(true);//关键点
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //        webSettings.setBuiltInZoomControls(true); // 设置显示缩放按钮
        //        webSettings.setSupportZoom(true); // 支持缩放
        mWebview.setWebChromeClient(new WebChromeClient());
    }

    @OnClick(R.id.title_back_iv)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onClick(View v) {

    }
}
