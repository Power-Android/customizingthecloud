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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RequestRefundActiviy extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.face_iv)
    ImageView faceIv;
    @BindView(R.id.name_tv)
    TextView nameTv;
    @BindView(R.id.fenlei_tv)
    TextView fenleiTv;
    @BindView(R.id.hwzt_tv)
    TextView hwztTv;
    @BindView(R.id.hwzt_ll)
    RelativeLayout hwztLl;
    @BindView(R.id.reason_tv)
    TextView reasonTv;
    @BindView(R.id.tkyy_ll)
    RelativeLayout tkyyLl;
    @BindView(R.id.money_tv)
    TextView moneyTv;
    @BindView(R.id.instructions_et)
    EditText instructionsEt;
    @BindView(R.id.commit_tv)
    TextView commitTv;
    private BaseDialog mDialog;
    private BaseDialog.Builder mBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_refund);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("申请退款");
    }

    private void showHwztDialog() {
        mBuilder = new BaseDialog.Builder(this);
        mDialog = mBuilder.setViewId(R.layout.dialog_hwzt)
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
        mDialog.getView(R.id.tv_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        final CheckBox cb_not = mDialog.getView(R.id.cb_not);
        final CheckBox cb_already = mDialog.getView(R.id.cb_already);
        cb_not.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cb_already.setChecked(false);

                }
            }
        });
        cb_already.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cb_not.setChecked(false);
                }
            }
        });
    }

    private void showTkyyDialog() {
        mBuilder = new BaseDialog.Builder(this);
        mDialog = mBuilder.setViewId(R.layout.dialog_tkyy)
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
        mDialog.getView(R.id.tv_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        final CheckBox cb_paymistake = mDialog.getView(R.id.cb_paymistake);//买错了
        final CheckBox cb_notpay = mDialog.getView(R.id.cb_notpay);//不想买了
        final CheckBox cb_infoerror = mDialog.getView(R.id.cb_infoerror);//信息填写错误
        final CheckBox cb_payagain = mDialog.getView(R.id.cb_payagain);//重新下单
        final CheckBox cb_payquestion = mDialog.getView(R.id.cb_payquestion);//支付遇到问题

        cb_paymistake.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cb_notpay.setChecked(false);
                    cb_infoerror.setChecked(false);
                    cb_payagain.setChecked(false);
                    cb_payquestion.setChecked(false);
                }
            }
        });
        cb_notpay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cb_paymistake.setChecked(false);
                    cb_infoerror.setChecked(false);
                    cb_payagain.setChecked(false);
                    cb_payquestion.setChecked(false);
                }
            }
        });
        cb_infoerror.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    cb_paymistake.setChecked(false);
                    cb_notpay.setChecked(false);
                    cb_payagain.setChecked(false);
                    cb_payquestion.setChecked(false);
                }
            }
        });
        cb_payagain.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    cb_paymistake.setChecked(false);
                    cb_notpay.setChecked(false);
                    cb_infoerror.setChecked(false);
                    cb_payquestion.setChecked(false);
                }
            }
        });
        cb_payquestion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    cb_paymistake.setChecked(false);
                    cb_notpay.setChecked(false);
                    cb_infoerror.setChecked(false);
                    cb_payagain.setChecked(false);
                }
            }
        });
    }

    @OnClick({R.id.title_back_iv, R.id.hwzt_ll, R.id.tkyy_ll, R.id.commit_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.hwzt_ll:
                showHwztDialog();
                break;
            case R.id.tkyy_ll:
                showTkyyDialog();
                break;
            case R.id.commit_tv:
                finish();
                break;
        }
    }
}
