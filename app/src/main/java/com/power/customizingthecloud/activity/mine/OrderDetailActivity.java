package com.power.customizingthecloud.activity.mine;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.utils.TUtils;
import com.power.customizingthecloud.view.BaseDialog;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.NormalAlertDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderDetailActivity extends BaseActivity {

    @BindView(R.id.title_back_iv) ImageView mTitleBackIv;
    @BindView(R.id.title_content_tv) TextView mTitleContentTv;
    @BindView(R.id.type_content_tv) TextView mTypeContentTv;
    @BindView(R.id.name_tv) TextView mNameTv;
    @BindView(R.id.phone_tv) TextView mPhoneTv;
    @BindView(R.id.address_tv) TextView mAddressTv;
    @BindView(R.id.peisong_tv) TextView mPeisongTv;
    @BindView(R.id.liuyan_tv) TextView mLiuyanTv;
    @BindView(R.id.item_img_iv) ImageView mItemImgIv;
    @BindView(R.id.item_name_tv) TextView mItemNameTv;
    @BindView(R.id.item_fenlei_tv) TextView mItemFenleiTv;
    @BindView(R.id.item_money_tv) TextView mItemMoneyTv;
    @BindView(R.id.item_num_tv) TextView mItemNumTv;
    @BindView(R.id.item_shifukuan_tv) TextView mItemShifukuanTv;
    @BindView(R.id.item_yunfei_tv) TextView mItemYunfeiTv;
    @BindView(R.id.order_bianhao_tv) TextView mOrderBianhaoTv;
    @BindView(R.id.date_tv) TextView mDateTv;
    @BindView(R.id.wuliu_bianhao_tv) TextView mWuliuBianhaoTv;
    @BindView(R.id.item_use_tv) TextView mItemUseTv;
    @BindView(R.id.item_cancle_order_tv) TextView mItemCancleOrderTv;
    private String type;
    private BaseDialog mDialog;
    private BaseDialog.Builder mBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mTitleBackIv.setVisibility(View.VISIBLE);
        mTitleContentTv.setText("订单详情");
        initIntentData();
    }

    private void initIntentData() {
        type = getIntent().getStringExtra("type");
        switch (type){
            case "1"://待付款
                mTypeContentTv.setText("等待买家付款");
                mItemCancleOrderTv.setVisibility(View.VISIBLE);
                mItemCancleOrderTv.setText("取消订单");
                mItemUseTv.setText("付款");
                break;
            case "2"://待发货
                mTypeContentTv.setText("等待卖家发货");
                mItemUseTv.setText("提醒发货");
                break;
            case "3"://待收货
                mTypeContentTv.setText("卖家已发货");
                mItemCancleOrderTv.setVisibility(View.VISIBLE);
                mItemCancleOrderTv.setText("退款");
                mItemUseTv.setText("确认收货");
                break;
            case "4"://待评价
                mTypeContentTv.setText("等待买家评价");
                mItemUseTv.setText("评价");
                break;
        }
    }

    @OnClick({R.id.title_back_iv, R.id.item_use_tv, R.id.item_cancle_order_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.item_use_tv:
                switch (type){
                    case "1"://待付款
                        showPayStyleDialog();
                        break;
                    case "2"://待发货
                        TUtils.showShort(mContext,"点击了---提醒发货");
                        break;
                    case "3"://待收货
                        showPayStyleDialog();
                        break;
                    case "4"://待评价
                        TUtils.showShort(mContext,"点击了---评价");
                        break;
                }
                break;
            case R.id.item_cancle_order_tv:
                switch (type){
                    case "1"://待付款
                        showTip();
                        break;
                    case "2"://待发货

                        break;
                    case "3"://待收货
                        TUtils.showShort(mContext,"点击了---退款");
                        break;
                    case "4"://待评价
                        break;
                }
                break;
        }
    }

    private void showPayStyleDialog() {
        mBuilder = new BaseDialog.Builder(this);
        mDialog = mBuilder.setViewId(R.layout.dialog_paystyle)
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
        final CheckBox cb_alipay = mDialog.getView(R.id.cb_alipay);
        final CheckBox cb_weixin = mDialog.getView(R.id.cb_weixin);
        final CheckBox cb_yinlian = mDialog.getView(R.id.cb_yinlian);
        cb_alipay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cb_weixin.setChecked(false);
                    cb_yinlian.setChecked(false);
                }
            }
        });
        cb_weixin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cb_alipay.setChecked(false);
                    cb_yinlian.setChecked(false);
                }
            }
        });
        cb_yinlian.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cb_weixin.setChecked(false);
                    cb_alipay.setChecked(false);
                }
            }
        });
    }

    private void showTip() {
        new NormalAlertDialog.Builder(this)
                .setTitleVisible(false).setTitleText("提示")
                .setTitleTextColor(R.color.text_black)
                .setContentText("您确定要取消此订单吗？")
                .setContentTextColor(R.color.text_black)
                .setLeftButtonText("确定")
                .setLeftButtonTextColor(R.color.text_black)
                .setRightButtonText("取消")
                .setRightButtonTextColor(R.color.text_black)
                .setCanceledOnTouchOutside(false)
                .setOnclickListener(new DialogInterface.OnLeftAndRightClickListener<NormalAlertDialog>() {
                    @Override
                    public void clickLeftButton(NormalAlertDialog dialog, View view) {
                        dialog.dismiss();
                    }

                    @Override
                    public void clickRightButton(NormalAlertDialog dialog, View view) {
                        dialog.dismiss();
                    }
                })
                .build()
                .show();
    }

}
