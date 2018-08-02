package com.power.customizingthecloud.fragment.home;

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

public class ZiXunDetailActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_message_iv)
    ImageView mTitleMessageIv;
    @BindView(R.id.title_back_iv)
    ImageView mTitleBackIv;
    @BindView(R.id.title_list_iv)
    ImageView mTitleListIv;
    @BindView(R.id.title_content_tv)
    TextView mTitleContentTv;
    @BindView(R.id.title_sign_in_iv)
    ImageView mTitleSignInIv;
    @BindView(R.id.title_setting_iv)
    ImageView mTitleSettingIv;
    @BindView(R.id.title_qrcode_iv)
    ImageView mTitleQrcodeIv;
    @BindView(R.id.title_shopcar_iv)
    ImageView mTitleShopcarIv;
    @BindView(R.id.title_share_iv)
    ImageView mTitleShareIv;
    @BindView(R.id.title_search_iv)
    ImageView mTitleSearchIv;
    @BindView(R.id.title_jia_iv)
    ImageView mTitleJiaIv;
    @BindView(R.id.title_kefu_iv)
    ImageView mTitleKefuIv;
    @BindView(R.id.title_content_right_tv)
    TextView mTitleContentRightTv;
    @BindView(R.id.tv_intro)
    TextView mTvIntro;
    @BindView(R.id.webview)
    WebView mWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zi_xun_detail);
        ButterKnife.bind(this);
        mTitleBackIv.setVisibility(View.VISIBLE);
        mTitleBackIv.setOnClickListener(this);
        mTitleContentTv.setText("资讯");
        initView();
    }

    private void initView() {
        String id = getIntent().getStringExtra("id");
        /*HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));
        HttpParams params = new HttpParams();
        params.put("type","1");
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
                            mWebview.setWebChromeClient(new WebChromeClient());
                            mWebview.loadData(latestBean.getData().getBody(), "text/html;charset=UTF-8", null);
//                            mTvIntro.setText(latestBean.getData().getBody());
                        }else {
                            TUtils.showShort(mContext,latestBean.getMessage());
                        }
                    }
                });*/

        initWeb();
        mWebview.loadUrl("http://39.107.91.92:84/wap/informationDetails.html?type=1&id="+id);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
        }
    }
}
