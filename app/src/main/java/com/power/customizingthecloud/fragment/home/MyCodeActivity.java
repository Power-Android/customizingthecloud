package com.power.customizingthecloud.fragment.home;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.MyApplication;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.base.UMShareActivity;
import com.power.customizingthecloud.bean.MyCodeBean;
import com.power.customizingthecloud.callback.JsonCallback;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.Urls;
import com.power.customizingthecloud.view.BaseDialog;
import com.umeng.socialize.bean.SHARE_MEDIA;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyCodeActivity extends BaseActivity implements View.OnClickListener {

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
    @BindView(R.id.iv_code)
    ImageView iv_code;
    @BindView(R.id.title_content_right_tv)
    TextView mTitleContentRightTv;
    @BindView(R.id.tv_code)
    TextView tv_code;
    private BaseDialog mDialog;
    private BaseDialog.Builder mBuilder;
    private MyCodeBean.DataEntity data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_code);
        ButterKnife.bind(this);
        mTitleBackIv.setVisibility(View.VISIBLE);
        mTitleBackIv.setOnClickListener(this);
        mTitleContentTv.setText("我的二维码");
        mTitleShareIv.setVisibility(View.VISIBLE);
        mTitleShareIv.setOnClickListener(this);
        initData();
    }

    private void initData() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));
        OkGo.<MyCodeBean>get(Urls.BASEURL + "api/v2/qrcode")
                .tag(this)
                .headers(headers)
                .execute(new JsonCallback<MyCodeBean>(MyCodeBean.class) {
                    @Override
                    public void onSuccess(Response<MyCodeBean> response) {
                        MyCodeBean myCodeBean = response.body();
                        if (myCodeBean.getCode() == 1) {
                            data = myCodeBean.getData();
                            tv_code.setText(data.getInviter_code());
                            Glide.with(MyApplication.getGloableContext()).load(data.getImage()).into(iv_code);
                        }else {
                            Toast.makeText(mContext, myCodeBean.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
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

    private void showShareDialog() {
        final String url="http://www.ssydfarm.com/wap/myQRcode.html?share=0&token="+"Bearer " + SpUtils.getString(mContext, "token", "");
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
                UMShareActivity.shareWebUrl(url, "我的二维码", data.getImage(), "好东西要和朋友共分享", MyCodeActivity.this, SHARE_MEDIA.WEIXIN);
                //                startActivity(new Intent(GoodDetailActivity.this, ShareSuccessActivity.class));
            }
        });
        mDialog.getView(R.id.tv_pengyouquan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                UMShareActivity.shareWebUrl(url, "我的二维码", data.getImage(), "好东西要和朋友共分享", MyCodeActivity.this, SHARE_MEDIA.WEIXIN_CIRCLE);
                //                startActivity(new Intent(GoodDetailActivity.this, ShareSuccessActivity.class));
            }
        });
        mDialog.getView(R.id.tv_zone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                UMShareActivity.shareWebUrl(url,"我的二维码", data.getImage(), "好东西要和朋友共分享", MyCodeActivity.this, SHARE_MEDIA.QZONE);
                //                startActivity(new Intent(GoodDetailActivity.this, ShareSuccessActivity.class));
            }
        });
        mDialog.getView(R.id.tv_qq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                UMShareActivity.shareWebUrl(url, "我的二维码", data.getImage(), "好东西要和朋友共分享", MyCodeActivity.this, SHARE_MEDIA.QQ);
                //                startActivity(new Intent(GoodDetailActivity.this, ShareSuccessActivity.class));
            }
        });
        mDialog.getView(R.id.tv_sina).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                UMShareActivity.shareWebUrl(url, "我的二维码", data.getImage(), "好东西要和朋友共分享", MyCodeActivity.this, SHARE_MEDIA.SINA);
                //                startActivity(new Intent(GoodDetailActivity.this, ShareSuccessActivity.class));
            }
        });
    }
}
