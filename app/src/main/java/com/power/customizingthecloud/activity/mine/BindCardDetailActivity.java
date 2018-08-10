package com.power.customizingthecloud.activity.mine;

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
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.BaseBean;
import com.power.customizingthecloud.bean.MyBankDetailBean;
import com.power.customizingthecloud.callback.JsonCallback;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.Urls;
import com.power.customizingthecloud.view.BaseDialog;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BindCardDetailActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.pic_iv)
    ImageView picIv;
    @BindView(R.id.name_tv)
    TextView nameTv;
    @BindView(R.id.num_tv)
    TextView numTv;
    @BindView(R.id.dbxe_tv)
    TextView dbxeTv;
    @BindView(R.id.mrxe_tv)
    TextView mrxeTv;
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
    @BindView(R.id.title_point_iv)
    ImageView titlePointIv;
    @BindView(R.id.title_content_right_tv)
    TextView titleContentRightTv;
    private BaseDialog mDialog;
    private BaseDialog.Builder mBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_card_detail);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        String bank_id = getIntent().getStringExtra("bank_id");
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(this, "token", ""));
        HttpParams params = new HttpParams();
        params.put("bank_id", bank_id);
        OkGo.<MyBankDetailBean>get(Urls.BASEURL + "api/v2/user/bank-show")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new JsonCallback<MyBankDetailBean>(MyBankDetailBean.class) {
                    @Override
                    public void onSuccess(Response<MyBankDetailBean> response) {
                        MyBankDetailBean body = response.body();
                        int code = body.getCode();
                        if (code == 0) {
                            Toast.makeText(mContext, body.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (code == 1) {
                            MyBankDetailBean.DataEntity data = body.getData();
                            nameTv.setText(data.getBank_name());
                            Glide.with(mContext).load(data.getImage()).into(picIv);
                            numTv.setText(data.getBank_card());
                            dbxeTv.setText("¥" + data.getDanbi());
                            mrxeTv.setText("¥" + data.getDay());
                        }
                    }
                });
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleBackIv.setOnClickListener(this);
        titlePointIv.setVisibility(View.VISIBLE);
        titlePointIv.setOnClickListener(this);
        titleContentTv.setText("银行卡详情");
    }

    private void showBindDialog() {
        mBuilder = new BaseDialog.Builder(this);
        mDialog = mBuilder.setViewId(R.layout.dialog_bind)
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
                showisBindDialog();
            }
        });
    }

    private void showisBindDialog() {
        mBuilder = new BaseDialog.Builder(this);
        mDialog = mBuilder.setViewId(R.layout.dialog_cancelbind)
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
                unbindCard();
            }

        });
    }

    private void unbindCard() {
        String bank_id = getIntent().getStringExtra("bank_id");
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(this, "token", ""));
        HttpParams params = new HttpParams();
        params.put("bank_id", bank_id);
        OkGo.<BaseBean>get(Urls.BASEURL + "api/v2/user/unbind-bank")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new JsonCallback<BaseBean>(BaseBean.class) {
                    @Override
                    public void onSuccess(Response<BaseBean> response) {
                        BaseBean body = response.body();
                        int code = body.getCode();
                        if (code == 0) {
                            Toast.makeText(mContext, body.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (code == 1) {
                            Toast.makeText(mContext, body.getMessage(), Toast.LENGTH_SHORT).show();
                            finish();
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
            case R.id.title_point_iv:
                showBindDialog();
                break;
        }
    }
}
