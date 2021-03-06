package com.power.customizingthecloud.fragment.shop;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.MyApplication;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.base.UMShareActivity;
import com.power.customizingthecloud.bean.VideoDetailBean;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.utils.Urls;
import com.power.customizingthecloud.view.BaseDialog;
import com.umeng.socialize.bean.SHARE_MEDIA;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

public class VideoDetailActivity extends BaseActivity implements View.OnClickListener {

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
    @BindView(R.id.tv_content)
    TextView tv_content;
    @BindView(R.id.videoplayer)
    JZVideoPlayerStandard mVideoplayer;
    private BaseDialog mDialog;
    private BaseDialog.Builder mBuilder;
    @BindView(R.id.webview)
    WebView mWebview;
    private String url;
    private VideoDetailBean.DataEntity data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);
        ButterKnife.bind(this);
        mTitleBackIv.setVisibility(View.VISIBLE);
        mTitleBackIv.setOnClickListener(this);
        mTitleContentTv.setText("视频详情");
        mTitleShareIv.setVisibility(View.VISIBLE);
        mTitleShareIv.setOnClickListener(this);
        initData();
    }

    private void initData() {
        String kc_id = getIntent().getStringExtra("id");
        HttpParams params = new HttpParams();
        params.put("kc_id", kc_id);
        OkGo.<VideoDetailBean>get(Urls.BASEURL + "api/v2/kitchen/show")
                .params(params)
                .execute(new DialogCallback<VideoDetailBean>(VideoDetailActivity.this, VideoDetailBean.class) {
                    @Override
                    public void onSuccess(Response<VideoDetailBean> response) {
                        VideoDetailBean bean = response.body();
                        int code = bean.getCode();
                        if (code == 0) {
                            Toast.makeText(VideoDetailActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (code == 1) {
                            data = bean.getData();
                            mVideoplayer.setUp(data.getVideo_url(), JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "");
                            mVideoplayer.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                            Glide.with(MyApplication.getGloableContext())
                                    .load(data.getImgurl())
                                    .into(mVideoplayer.thumbImageView);
                            //模拟点击事件，在调用performClick之前必须设置了点击事件，不然无效
                            mVideoplayer.startButton.performClick();
                            tv_content.setText(data.getBody());
                        }
                    }
                });
        initWeb();
        url=Urls.BASEURL+"wap/videoDetails.html?id="+kc_id;
        mWebview.loadUrl(url);
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
            case R.id.title_share_iv:
                showShareDialog();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

    private void showShareDialog() {
        mBuilder = new BaseDialog.Builder(this);
        mDialog = mBuilder.setViewId(R.layout.dialog_share)
                //设置dialogpadding
                .setPaddingdp(0, 0, 0, 0)
                //设置显示位置
                .setGravity(Gravity.BOTTOM)
                //设置动画
                .setAnimation(R.style.Bottom_Top_aniamtion)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(true)
                //设置监听事件
                .builder();
        mDialog.show();
        mDialog.getView(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        mDialog.getView(R.id.tv_weixin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                UMShareActivity.shareWebUrl(url, data.getTitle(), data.getImgurl(), data.getBody(), VideoDetailActivity.this, SHARE_MEDIA.WEIXIN);
                //                startActivity(new Intent(GoodDetailActivity.this, ShareSuccessActivity.class));
            }
        });
        mDialog.getView(R.id.tv_pengyouquan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                UMShareActivity.shareWebUrl(url, data.getTitle(), data.getImgurl(), data.getBody(), VideoDetailActivity.this, SHARE_MEDIA.WEIXIN_CIRCLE);
                //                startActivity(new Intent(GoodDetailActivity.this, ShareSuccessActivity.class));
            }
        });
        mDialog.getView(R.id.tv_zone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                UMShareActivity.shareWebUrl(url, data.getTitle(), data.getImgurl(), data.getBody(), VideoDetailActivity.this, SHARE_MEDIA.QZONE);
                //                startActivity(new Intent(GoodDetailActivity.this, ShareSuccessActivity.class));
            }
        });
        mDialog.getView(R.id.tv_qq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                UMShareActivity.shareWebUrl(url, data.getTitle(), data.getImgurl(), data.getBody(), VideoDetailActivity.this, SHARE_MEDIA.QQ);
                //                startActivity(new Intent(GoodDetailActivity.this, ShareSuccessActivity.class));
            }
        });
        mDialog.getView(R.id.tv_sina).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                UMShareActivity.shareWebUrl(url, data.getTitle(), data.getImgurl(), data.getBody(), VideoDetailActivity.this, SHARE_MEDIA.SINA);
                //                startActivity(new Intent(GoodDetailActivity.this, ShareSuccessActivity.class));
            }
        });
    }
}
