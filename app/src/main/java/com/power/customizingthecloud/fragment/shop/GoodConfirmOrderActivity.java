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
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.activity.mine.AddressManagerActivity;
import com.power.customizingthecloud.activity.mine.MyOrderActivity;
import com.power.customizingthecloud.activity.mine.MyVoucherActivity;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.login.LoginActivity;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.view.BaseDialog;
import com.power.customizingthecloud.view.CommonPopupWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoodConfirmOrderActivity extends BaseActivity implements View.OnClickListener {

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
    @BindView(R.id.tv_personname)
    TextView mTvPersonname;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.iv_good)
    ImageView mIvGood;
    @BindView(R.id.tv_shopname)
    TextView mTvShopname;
    @BindView(R.id.tv_oneprice)
    TextView mTvOneprice;
    @BindView(R.id.tv_count)
    TextView mTvCount;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.iv_time)
    ImageView mIvTime;
    @BindView(R.id.tv_xiaoji)
    TextView mTvXiaoji;
    @BindView(R.id.tv_zongprice1)
    TextView mTvZongprice1;
    @BindView(R.id.tv_quan_price)
    RelativeLayout mTvQuanPrice;
    @BindView(R.id.tv_zongprice2)
    TextView mTvZongprice2;
    @BindView(R.id.cb_ear)
    CheckBox mCbEar;
    @BindView(R.id.tv_ear)
    TextView mTvEar;
    @BindView(R.id.edt_liuyan)
    EditText mEdtLiuyan;
    @BindView(R.id.tv_totalprice)
    TextView mTvTotalprice;
    @BindView(R.id.tv_commit)
    TextView mTvCommit;
    @BindView(R.id.iv_address)
    LinearLayout mIvAddress;
    private BaseDialog mDialog;
    private BaseDialog.Builder mBuilder;
    private CommonPopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_confirm_order);
        ButterKnife.bind(this);
        mTitleBackIv.setVisibility(View.VISIBLE);
        mTitleBackIv.setOnClickListener(this);
        mTitleContentTv.setText("确认订单");
        mTvCommit.setOnClickListener(this);
        mTvQuanPrice.setOnClickListener(this);
        mTvTime.setOnClickListener(this);
        mIvTime.setOnClickListener(this);
        mIvAddress.setOnClickListener(this);
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
        mDialog.getView(R.id.tv_pay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!cb_alipay.isChecked() && !cb_weixin.isChecked() && !cb_yinlian.isChecked()) {
                    Toast.makeText(GoodConfirmOrderActivity.this, "至少选择一种支付方式~", Toast.LENGTH_SHORT).show();
                    return;
                }
                mDialog.dismiss();
                Intent intent = new Intent(GoodConfirmOrderActivity.this, MyOrderActivity.class);
                intent.putExtra("type","0");
                startActivity(intent);
            }
        });
    }

    //向下弹出
    public void showDownPop(final TextView textView, final List<String> list) {
        if (popupWindow != null && popupWindow.isShowing())
            return;
        popupWindow = new CommonPopupWindow.Builder(this)
                .setView(R.layout.popup_down)
                .setWidthAndHeight(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setAnimationStyle(R.style.AnimDown)
                .setViewOnclickListener(new CommonPopupWindow.ViewInterface() {
                    @Override
                    public void getChildView(View view, int layoutResId) {
                        RecyclerView recycle_view = (RecyclerView) view.findViewById(R.id.recycle_view);
                        recycle_view.setLayoutManager(new LinearLayoutManager(GoodConfirmOrderActivity.this));
                        PopupAdapter mAdapter = new PopupAdapter(R.layout.item_pop_textview, list);
                        recycle_view.setAdapter(mAdapter);
                        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                textView.setText(list.get(position));
                                if (popupWindow != null) {
                                    popupWindow.dismiss();
                                }
                            }
                        });
                    }
                })
                .setOutsideTouchable(true)
                .create();
        popupWindow.showAsDropDown(textView);
        //得到button的左上角坐标
        //        int[] positions = new int[2];
        //        view.getLocationOnScreen(positions);
        //        popupWindow.showAtLocation(findViewById(android.R.id.content), Gravity.NO_GRAVITY, 0, positions[1] + view.getHeight());
    }

    private class PopupAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public PopupAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.tv_pop, item);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.tv_commit:
                String userid = SpUtils.getString(mContext, "userid", "");
                if (TextUtils.isEmpty(userid)) {
                    startActivity(new Intent(mContext, LoginActivity.class));
                    overridePendingTransition(R.anim.push_bottom_in, R.anim.push_bottom_out);
                    return;
                }
                showPayStyleDialog();
                break;
            case R.id.tv_quan_price:
                String userid2 = SpUtils.getString(mContext, "userid", "");
                if (TextUtils.isEmpty(userid2)) {
                    startActivity(new Intent(mContext, LoginActivity.class));
                    overridePendingTransition(R.anim.push_bottom_in, R.anim.push_bottom_out);
                    return;
                }
                Intent intent = new Intent(this, MyVoucherActivity.class);
                intent.putExtra("type", "query");
                startActivity(intent);
                break;
            case R.id.tv_time:
            case R.id.iv_time:
                List<String> list = new ArrayList<String>();
                list.add("工作日");
                list.add("休息日");
                showDownPop(mTvTime, list);
                break;
            case R.id.iv_address:
                Intent intent1 = new Intent(this, AddressManagerActivity.class);
                intent1.putExtra("type", "order");
                startActivity(intent1);
                break;
        }
    }
}
