package com.power.customizingthecloud.fragment.home.top;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.power.customizingthecloud.MyApplication;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.activity.mine.ShareRuleActivity;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.fragment.home.ShareSuccessActivity;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.view.BaseDialog;
import com.power.customizingthecloud.view.CircleImageView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyShareActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.face_iv)
    CircleImageView faceIv;
    @BindView(R.id.tv_invite)
    TextView tv_invite;
    @BindView(R.id.title_message_iv)
    ImageView titleMessageIv;
    @BindView(R.id.title_list_iv)
    ImageView titleListIv;
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
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.iv_haolvyou)
    ImageView ivHaolvyou;
    @BindView(R.id.tv_rule)
    TextView tvRule;
    @BindView(R.id.tv_11)
    TextView tv11;
    @BindView(R.id.activity_my_share)
    LinearLayout activityMyShare;
    private BaseDialog mDialog;
    private BaseDialog.Builder mBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_share);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleBackIv.setOnClickListener(this);
        tv_invite.setOnClickListener(this);
        ivClose.setOnClickListener(this);
        tvRule.setOnClickListener(this);
        titleContentTv.setText("分享");
        tvName.setText(SpUtils.getString(mContext, "userName", ""));
        Glide.with(this).load(SpUtils.getString(mContext, "userHead", "")).into(faceIv);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.iv_close:
                finish();
                break;
            case R.id.tv_rule:
                startActivity(new Intent(mContext, ShareRuleActivity.class));
                break;
            case R.id.tv_invite:
                //                startActivity(new Intent(mContext, ShareSuccessActivity.class));
                showShareDialog();
                break;
        }
    }

    private void showShareDialog() {
        final String url = "http://39.107.91.92:84/wap/share.html?share=0&token=" + "Bearer " + SpUtils.getString(mContext, "token", "");
        final String title = "邀请好驴友，一起发驴财";
        final String des = "他赚，你也赚。他有，你也有哦！";
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
                shareWebUrl(url, title, SpUtils.getString(mContext, "userHead", ""), des, MyShareActivity.this, SHARE_MEDIA.WEIXIN);
                //                startActivity(new Intent(GoodDetailActivity.this, ShareSuccessActivity.class));
            }
        });
        mDialog.getView(R.id.tv_pengyouquan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                shareWebUrl(url, title, SpUtils.getString(mContext, "userHead", ""), des, MyShareActivity.this, SHARE_MEDIA.WEIXIN_CIRCLE);
                //                startActivity(new Intent(GoodDetailActivity.this, ShareSuccessActivity.class));
            }
        });
        mDialog.getView(R.id.tv_zone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                shareWebUrl(url, title, SpUtils.getString(mContext, "userHead", ""), des, MyShareActivity.this, SHARE_MEDIA.QZONE);
                //                startActivity(new Intent(GoodDetailActivity.this, ShareSuccessActivity.class));
            }
        });
        mDialog.getView(R.id.tv_qq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                shareWebUrl(url, title, SpUtils.getString(mContext, "userHead", ""), des, MyShareActivity.this, SHARE_MEDIA.QQ);
                //                startActivity(new Intent(GoodDetailActivity.this, ShareSuccessActivity.class));
            }
        });
        mDialog.getView(R.id.tv_sina).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                shareWebUrl(url, title, SpUtils.getString(mContext, "userHead", ""), des, MyShareActivity.this, SHARE_MEDIA.SINA);
                //                startActivity(new Intent(GoodDetailActivity.this, ShareSuccessActivity.class));
            }
        });
    }

    public void shareWebUrl(String url, String title, String imgUrl, String des,
                                   Activity context, SHARE_MEDIA pingtai) {
        UMWeb web = new UMWeb(url);
        web.setTitle(title);//标题
        UMImage thumb = new UMImage(context, imgUrl);
        web.setThumb(thumb);  //缩略图
        web.setDescription(des);//描述
        //注意在新浪平台，缩略图属于必传参数，否则会报错
        ShareAction shareAction = new ShareAction(context).withMedia(web);
        shareAction.setPlatform(pingtai);//传入平台
        shareAction.setCallback(umShareListener);
        shareAction.share();
    }

    public UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //分享开始的回调
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            //            Log.d("plat", "platform" + platform);
            Toast.makeText(MyApplication.getGloableContext(), "分享成功啦", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(MyShareActivity.this, ShareSuccessActivity.class));
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(MyApplication.getGloableContext(), "分享失败啦", Toast.LENGTH_SHORT).show();
            if (t != null) {
                //                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(MyApplication.getGloableContext(), "分享取消了", Toast.LENGTH_SHORT).show();
        }
    };

    /*
   * 在分享所在的Activity里复写onActivityResult方法,注意不可在fragment中实现，如果在fragment中调用分享，
   * 在fragment依赖的Activity中实现，如果不实现onActivityResult方法，会导致分享或回调无法正常进行
   * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
