package com.power.customizingthecloud.fragment.shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.fragment.home.GoodListActivity;
import com.power.customizingthecloud.fragment.shop.bean.QuanListBean;
import com.power.customizingthecloud.login.LoginActivity;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.Urls;
import com.power.customizingthecloud.view.BaseDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuanListActivity extends BaseActivity implements View.OnClickListener {

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
    @BindView(R.id.title_content_right_tv)
    TextView mTitleContentRightTv;
    @BindView(R.id.recycler_quan)
    RecyclerView mRecyclerQuan;
    private QuanAdapter mQuanAdapter;
    private BaseDialog mDialog;
    private BaseDialog.Builder mBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_list);
        ButterKnife.bind(this);
        mTitleBackIv.setVisibility(View.VISIBLE);
        mTitleBackIv.setOnClickListener(this);
        mTitleContentTv.setText("代金券");
        mRecyclerQuan.setLayoutManager(new LinearLayoutManager(this));
        HttpParams params = new HttpParams();
        params.put("after", "");
        params.put("limit", "10");
        OkGo.<QuanListBean>get(Urls.BASEURL + "api/v2/voucher-template-list")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<QuanListBean>(QuanListActivity.this, QuanListBean.class) {
                    @Override
                    public void onSuccess(Response<QuanListBean> response) {
                        QuanListBean listBean = response.body();
                        int code = listBean.getCode();
                        if (code == 0) {
                            Toast.makeText(mContext, listBean.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (code == 1) {
                            List<QuanListBean.DataEntity> data = listBean.getData();
                            mQuanAdapter = new QuanAdapter(R.layout.item_daijinquan, data);
                            mRecyclerQuan.setAdapter(mQuanAdapter);
                        }
                    }
                });
    }

    private class QuanAdapter extends BaseQuickAdapter<QuanListBean.DataEntity, BaseViewHolder> {

        public QuanAdapter(@LayoutRes int layoutResId, @Nullable List<QuanListBean.DataEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, QuanListBean.DataEntity item) {
            TextView tv_lingqu = helper.getView(R.id.tv_lingqu);
            tv_lingqu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String userid = SpUtils.getString(mContext, "userid", "");
                    if (TextUtils.isEmpty(userid)) {
                        startActivity(new Intent(mContext, LoginActivity.class));
                        overridePendingTransition(R.anim.push_bottom_in, R.anim.push_bottom_out);
                        return;
                    }
                    showLingquDialog();
                }
            });
            helper.setText(R.id.item_money_tv,item.getPrice()+"")
                    .setText(R.id.item_man_jian_tv,"满￥"+item.getOrder_limit()+"使用")
                    .setText(R.id.item_name_tv,item.getTitle())
                    .setText(R.id.item_date_tv,"使用期限："+item.getStart_date()+"-"+item.getEnd_date());
        }
    }

    private void showLingquDialog() {
        mBuilder = new BaseDialog.Builder(this);
        mDialog = mBuilder.setViewId(R.layout.dialog_getquan)
                //设置dialogpadding
                .setPaddingdp(0, 0, 0, 0)
                //设置显示位置
                .setGravity(Gravity.CENTER)
                //设置动画
                .setAnimation(R.style.Alpah_aniamtion)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
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
        mDialog.getView(R.id.tv_yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                startActivity(new Intent(QuanListActivity.this,GoodListActivity.class));
            }
        });
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
