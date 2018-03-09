package com.power.customizingthecloud.activity.mine;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
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
                finish();
                break;
        }
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
                //                mDialog.dismiss();
            }
        });
        final CheckBox cb_yinlian = mDialog.getView(R.id.cb_yinlian);
        final CheckBox cb_alipay = mDialog.getView(R.id.cb_alipay);
        final CheckBox cb_guitai = mDialog.getView(R.id.cb_guitai);
        cb_yinlian.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cb_alipay.setChecked(false);
                    cb_guitai.setChecked(false);
                }
            }
        });
        cb_alipay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cb_yinlian.setChecked(false);
                    cb_guitai.setChecked(false);
                }
            }
        });
        cb_guitai.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cb_yinlian.setChecked(false);
                    cb_alipay.setChecked(false);
                }
            }
        });
    }
}
