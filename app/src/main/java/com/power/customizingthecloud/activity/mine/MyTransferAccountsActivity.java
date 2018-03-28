package com.power.customizingthecloud.activity.mine;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.BaseBean;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.TUtils;
import com.power.customizingthecloud.utils.Urls;
import com.power.customizingthecloud.view.BaseDialog;
import com.power.customizingthecloud.view.CommonPopupWindow;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyTransferAccountsActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_back_iv) ImageView titleBackIv;
    @BindView(R.id.title_content_tv) TextView titleContentTv;
    @BindView(R.id.money_et) EditText moneyEt;
    @BindView(R.id.danhao_et) EditText danhaoEt;
    @BindView(R.id.name_et) EditText nameEt;
    @BindView(R.id.paystyle_tv) TextView paystyleTv;
    @BindView(R.id.paystyle_rl) RelativeLayout paystyleRl;
    @BindView(R.id.tijiao_tv) TextView tijiaoTv;
    private BaseDialog mDialog;
    private BaseDialog.Builder mBuilder;
    private int payType = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_transfer_accounts);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleBackIv.setOnClickListener(this);
        titleContentTv.setText("我的转账");
        paystyleRl.setOnClickListener(this);
        tijiaoTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.paystyle_rl:
                showPayStyleDialog();
                break;
            case R.id.tijiao_tv:
                commit();
                finish();
                break;
        }
    }

    private void commit() {
        if (TextUtils.isEmpty(moneyEt.getText().toString().trim())){
            TUtils.showShort(mContext,"请输入转账金额");
            return;
        }
        if (TextUtils.isEmpty(danhaoEt.getText().toString().trim())){
            TUtils.showShort(mContext,"请输入转账单号");
            return;
        }
        if (TextUtils.isEmpty(nameEt.getText().toString().trim())){
            TUtils.showShort(mContext,"请输入用户姓名");
            return;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));
        HttpParams params = new HttpParams();
        params.put("money", moneyEt.getText().toString());
        params.put("transfer_sn",danhaoEt.getText().toString());
        params.put("name",nameEt.getText().toString());
        params.put("transfer_type",payType+"");

        OkGo.<BaseBean>post(Urls.BASEURL + "api/v2/user/transfer_accounts")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<BaseBean>(this,BaseBean.class) {
                    @Override
                    public void onSuccess(Response<BaseBean> response) {
                        BaseBean body = response.body();
                        if (body.getCode() == 1){
                            TUtils.showShort(mContext,body.getMessage());
                        }else {
                            TUtils.showShort(mContext,body.getMessage());
                        }
                    }
                });

    }

    private void showPayStyleDialog() {
        mBuilder = new BaseDialog.Builder(this);
        mDialog = mBuilder.setViewId(R.layout.dialog_paystyle1)
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
        mDialog.getView(R.id.iv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        mDialog.getView(R.id.tv_pay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        final CheckBox cb_yinlian = mDialog.getView(R.id.cb_yinlian);
        final CheckBox cb_alipay = mDialog.getView(R.id.cb_alipay);
        final CheckBox cb_guitai = mDialog.getView(R.id.cb_guitai);
        cb_yinlian.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    payType = 1;
                    cb_alipay.setChecked(false);
                    cb_guitai.setChecked(false);
                }
            }
        });
        cb_alipay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    payType = 2;
                    cb_yinlian.setChecked(false);
                    cb_guitai.setChecked(false);
                }
            }
        });
        cb_guitai.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    payType = 3;
                    cb_yinlian.setChecked(false);
                    cb_alipay.setChecked(false);
                }
            }
        });
    }
}
