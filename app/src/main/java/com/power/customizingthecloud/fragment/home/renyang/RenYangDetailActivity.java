package com.power.customizingthecloud.fragment.home.renyang;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.MyApplication;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.activity.mine.MyOrderActivity;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.fragment.home.ServiceAgreementActivity;
import com.power.customizingthecloud.fragment.home.bean.OrderBean;
import com.power.customizingthecloud.fragment.home.bean.RenYangDetailBean;
import com.power.customizingthecloud.login.LoginActivity;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.Urls;
import com.power.customizingthecloud.view.BaseDialog;
import com.power.customizingthecloud.view.CustomViewPager;
import com.power.customizingthecloud.view.SnappingStepper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RenYangDetailActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_message_iv)
    ImageView mTitleMessageIv;
    @BindView(R.id.title_back_iv)
    ImageView mTitleBackIv;
    @BindView(R.id.title_content_tv)
    TextView mTitleContentTv;
    @BindView(R.id.title_sign_in_iv)
    ImageView mTitleSignInIv;
    @BindView(R.id.title_setting_iv)
    ImageView mTitleSettingIv;
    @BindView(R.id.title_content_right_tv)
    TextView mTitleContentRightTv;
    @BindView(R.id.iv_top)
    ImageView mIvTop;
    @BindView(R.id.recycler_top)
    RecyclerView mRecyclerTop;
    @BindView(R.id.tv_shengyu)
    TextView mTvShengyu;
    @BindView(R.id.tv_total_count)
    TextView mTvTotalCount;
    @BindView(R.id.item_stepper)
    SnappingStepper mItemStepper;
    @BindView(R.id.tv_price)
    TextView mTvPrice;
    @BindView(R.id.tv_lirun)
    TextView mTvLirun;
    @BindView(R.id.tv_xieyi)
    TextView mTvXieyi;
    @BindView(R.id.tablayout)
    TabLayout mTablayout;
    @BindView(R.id.viewpager)
    CustomViewPager mViewpager;
    @BindView(R.id.xiangmu_tv)
    TextView mXiangmuTv;
    @BindView(R.id.indicator_xiangmu)
    View mIndicatorXiangmu;
    @BindView(R.id.xiangmu_ll)
    LinearLayout mXiangmuLl;
    @BindView(R.id.record_tv)
    TextView mRecordTv;
    @BindView(R.id.indicator_record)
    View mIndicatorRecord;
    @BindView(R.id.record_ll)
    LinearLayout mRecordLl;
    @BindView(R.id.paihang_tv)
    TextView mPaihangTv;
    @BindView(R.id.indicator_paihang)
    View mIndicatorPaihang;
    @BindView(R.id.paihang_ll)
    LinearLayout mPaihangLl;
    @BindView(R.id.des_tv)
    TextView mDesTv;
    @BindView(R.id.indicator_des)
    View mIndicatorDes;
    @BindView(R.id.des_ll)
    LinearLayout mDesLl;
    @BindView(R.id.webview)
    WebView mWebview;
    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.tv_commit)
    TextView mTvCommit;
    @BindView(R.id.iv_shouqing)
    ImageView iv_shouqing;
    @BindView(R.id.iv_check)
    ImageView iv_check;
    @BindView(R.id.title_shopcar_iv)
    ImageView mTitleShopcarIv;
    @BindView(R.id.title_share_iv)
    ImageView mTitleShareIv;
    @BindView(R.id.lv_xiangqing)
    ImageView mLvXiangqing;
    @BindView(R.id.tv_intro)
    TextView mTvIntro;
    private BaseDialog mDialog;
    private BaseDialog.Builder mBuilder;
    private boolean isChecked = true;
    private RenYangDetailBean.DataEntity datas;
    private int payStyle = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ren_yang_detail);
        ButterKnife.bind(this);
        mTitleBackIv.setVisibility(View.VISIBLE);
        mTitleBackIv.setOnClickListener(this);
        mTitleContentTv.setText("认养详情");
        mTvCommit.setOnClickListener(this);
        mTvXieyi.setOnClickListener(this);
        iv_check.setOnClickListener(this);
        mRecyclerTop.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerTop.setNestedScrollingEnabled(false);
        mRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        mRecycler.setNestedScrollingEnabled(false);
        mItemStepper.setContentBackground(R.drawable.bg_stepper_green);
        mItemStepper.setButtonBackGround(R.color.green);
        mItemStepper.setContentTextColor(R.color.green);
        mItemStepper.setLeftButtonResources(R.drawable.jianhao_white);
        mItemStepper.setRightButtonResources(R.drawable.jiahao_white);
        int state = getIntent().getIntExtra("state", 0);
        if (state == 1) {
            iv_shouqing.setVisibility(View.GONE);
            mTvCommit.setBackgroundResource(R.drawable.bg_yuanjiao_huise);
            mTvCommit.setClickable(false);
        } else if (state == 2) {
            iv_shouqing.setVisibility(View.GONE);
            mTvCommit.setBackgroundResource(R.drawable.bg_yuanjiao_green2);
        } else {
            iv_shouqing.setVisibility(View.VISIBLE);
            mTvCommit.setBackgroundResource(R.drawable.bg_yuanjiao_huise);
            mTvCommit.setClickable(false);
        }
        String id = getIntent().getStringExtra("id");
        HttpParams params = new HttpParams();
        params.put("id", id);
        OkGo.<RenYangDetailBean>get(Urls.BASEURL + "api/v2/adopt/show")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<RenYangDetailBean>(RenYangDetailActivity.this, RenYangDetailBean.class) {
                    @Override
                    public void onSuccess(Response<RenYangDetailBean> response) {
                        RenYangDetailBean bean = response.body();
                        int code = bean.getCode();
                        if (code == 0) {
                            Toast.makeText(mContext, bean.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (code == 1) {
                            datas = bean.getData();
                            List<String> list = new ArrayList<String>();
                            list.add(datas.getTitle());
                            list.add(datas.getPlace());
                            list.add(datas.getProfit() + "%");
                            list.add(datas.getPeriod() + "天");
                            list.add(datas.getPrice() + "元");
                            list.add(datas.getProfit_type());
                            TopAdapter topAdapter = new TopAdapter(R.layout.item_renyang_detail_top, list);
                            mRecyclerTop.setAdapter(topAdapter);
                            mTvShengyu.setText(datas.getLast_amount() + "");
                            mTvTotalCount.setText(datas.getAmount() + "");
                            mTvPrice.setText(datas.getAmount() + "");
                            Glide.with(MyApplication.getGloableContext()).load(datas.getImage()).into(mIvTop);
                            mTvIntro.setText(datas.getIntroduce());
                            mTvPrice.setText(Float.parseFloat(datas.getPrice()) * mItemStepper.getValue() + "元");
                            mTvLirun.setText(Float.parseFloat(datas.getPrice())
                                    * mItemStepper.getValue() * datas.getProfit() * datas.getPeriod() / 365 + "元");
                        }
                    }
                });
    }

    private class TopAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public TopAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            switch (helper.getAdapterPosition()) {
                case 0:
                    helper.setText(R.id.tv_title, "第  " + datas.getId() + " 期：")
                            .setText(R.id.tv_content, "【" + item + "】");
                    break;
                case 1:
                    helper.setText(R.id.tv_title, "产        地：")
                            .setText(R.id.tv_content, item);
                    break;
                case 2:
                    helper.setText(R.id.tv_title, "年利润率：")
                            .setText(R.id.tv_content, item);
                    break;
                case 3:
                    helper.setText(R.id.tv_title, "养殖周期：")
                            .setText(R.id.tv_content, item);
                    break;
                case 4:
                    helper.setText(R.id.tv_title, "养殖利润：")
                            .setText(R.id.tv_content, item);
                    break;
                case 5:
                    helper.setText(R.id.tv_title, "利润获取：")
                            .setText(R.id.tv_content, item);
                    break;
            }
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
                if (!isChecked) {
                    Toast.makeText(this, "请同意养驴啦服务协议~", Toast.LENGTH_SHORT).show();
                    return;
                }
                showPayStyleDialog();
                break;
            case R.id.tv_xieyi:
                startActivity(new Intent(this, ServiceAgreementActivity.class));
                break;
            case R.id.iv_check:
                if (isChecked) {
                    iv_check.setImageResource(R.drawable.weixuanzhong);
                } else {
                    iv_check.setImageResource(R.drawable.xuanzhong);
                }
                isChecked = !isChecked;
                break;
        }
    }

    private void commitOrder() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(RenYangDetailActivity.this, "token", ""));
        HttpParams params = new HttpParams();
        params.put("id", datas.getId() + "");
        params.put("pay_type", payStyle + "");
        params.put("order_type", "1");
        params.put("number", mItemStepper.getValue() + "");
        OkGo.<OrderBean>post(Urls.BASEURL + "api/v2/adopt/order")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<OrderBean>(RenYangDetailActivity.this, OrderBean.class) {
                    @Override
                    public void onSuccess(Response<OrderBean> response) {
                        OrderBean bean = response.body();
                        int code = bean.getCode();
                        if (code == 0) {
                            Toast.makeText(mContext, bean.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (code == 1) {
                            Toast.makeText(mContext, bean.getMessage(), Toast.LENGTH_SHORT).show();
                            String pay_sn = bean.getData().getPay_sn();//订单号
                            Intent intent = new Intent(RenYangDetailActivity.this, MyOrderActivity.class);
                            intent.putExtra("type", "0");
                            startActivity(intent);
                        }
                    }
                });
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
        mDialog.getView(R.id.view_lastline).setVisibility(View.VISIBLE);
        mDialog.getView(R.id.ll_zhuanzhang).setVisibility(View.VISIBLE);
        mDialog.getView(R.id.tv_zhuanzhang).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RenYangDetailActivity.this, ZhuanZhangActivity.class));
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
                    payStyle = 1;
                }
            }
        });
        cb_weixin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cb_alipay.setChecked(false);
                    cb_yinlian.setChecked(false);
                    payStyle = 2;
                }
            }
        });
        cb_yinlian.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cb_weixin.setChecked(false);
                    cb_alipay.setChecked(false);
                    payStyle = 3;
                }
            }
        });
        mDialog.getView(R.id.tv_pay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!cb_alipay.isChecked() && !cb_weixin.isChecked() && !cb_yinlian.isChecked()) {
                    Toast.makeText(RenYangDetailActivity.this, "至少选择一种支付方式~", Toast.LENGTH_SHORT).show();
                    return;
                }
                mDialog.dismiss();
                commitOrder();
            }
        });
    }

    @OnClick(R.id.xiangmu_ll)
    public void xiangmu() {
        initXiangMuColor();
        //        mWebview.setVisibility(View.VISIBLE);
        mWebview.setVisibility(View.GONE);
        mRecycler.setVisibility(View.GONE);


        mLvXiangqing.setVisibility(View.VISIBLE);
        mTvIntro.setVisibility(View.GONE);
    }

    private void initXiangMuColor() {
        mXiangmuTv.setTextColor(getResources().getColor(R.color.green));
        mIndicatorXiangmu.setBackgroundColor(getResources().getColor(R.color.green));
        mRecordTv.setTextColor(getResources().getColor(R.color.gray));
        mIndicatorRecord.setBackgroundColor(getResources().getColor(R.color.white));
        mPaihangTv.setTextColor(getResources().getColor(R.color.gray));
        mIndicatorPaihang.setBackgroundColor(getResources().getColor(R.color.white));
        mDesTv.setTextColor(getResources().getColor(R.color.gray));
        mIndicatorDes.setBackgroundColor(getResources().getColor(R.color.white));
    }

    @OnClick(R.id.record_ll)
    public void record() {
        initRecordColor();
        mWebview.setVisibility(View.GONE);
        mRecycler.setVisibility(View.VISIBLE);
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        RecordAdapter recordAdapter = new RecordAdapter(R.layout.item_record, list);
        mRecycler.setAdapter(recordAdapter);


        mTvIntro.setVisibility(View.GONE);
        mLvXiangqing.setVisibility(View.GONE);
    }

    private class RecordAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public RecordAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
        }
    }

    private void initRecordColor() {
        mXiangmuTv.setTextColor(getResources().getColor(R.color.gray));
        mIndicatorXiangmu.setBackgroundColor(getResources().getColor(R.color.white));
        mRecordTv.setTextColor(getResources().getColor(R.color.green));
        mIndicatorRecord.setBackgroundColor(getResources().getColor(R.color.green));
        mPaihangTv.setTextColor(getResources().getColor(R.color.gray));
        mIndicatorPaihang.setBackgroundColor(getResources().getColor(R.color.white));
        mDesTv.setTextColor(getResources().getColor(R.color.gray));
        mIndicatorDes.setBackgroundColor(getResources().getColor(R.color.white));
    }

    @OnClick(R.id.paihang_ll)
    public void paihang() {
        initPaiHangColor();
        mWebview.setVisibility(View.GONE);
        mRecycler.setVisibility(View.VISIBLE);
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        PaiHangAdapter paiHangAdapter = new PaiHangAdapter(R.layout.item_paihangbang, list);
        mRecycler.setAdapter(paiHangAdapter);


        mTvIntro.setVisibility(View.GONE);
        mLvXiangqing.setVisibility(View.GONE);
    }

    private class PaiHangAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public PaiHangAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            int adapterPosition = helper.getAdapterPosition();
            ImageView iv_pai = helper.getView(R.id.iv_pai);
            TextView tv_paixu = helper.getView(R.id.tv_paixu);
            if (adapterPosition == 0) {
                iv_pai.setImageResource(R.drawable.jinpai);
            } else if (adapterPosition == 1) {
                iv_pai.setImageResource(R.drawable.yinpai);
            } else if (adapterPosition == 2) {
                iv_pai.setImageResource(R.drawable.tongpai);
            } else {
                iv_pai.setVisibility(View.GONE);
                tv_paixu.setVisibility(View.VISIBLE);
                tv_paixu.setText(adapterPosition + 1 + "");
            }
        }
    }

    private void initPaiHangColor() {
        mXiangmuTv.setTextColor(getResources().getColor(R.color.gray));
        mIndicatorXiangmu.setBackgroundColor(getResources().getColor(R.color.white));
        mRecordTv.setTextColor(getResources().getColor(R.color.gray));
        mIndicatorRecord.setBackgroundColor(getResources().getColor(R.color.white));
        mPaihangTv.setTextColor(getResources().getColor(R.color.green));
        mIndicatorPaihang.setBackgroundColor(getResources().getColor(R.color.green));
        mDesTv.setTextColor(getResources().getColor(R.color.gray));
        mIndicatorDes.setBackgroundColor(getResources().getColor(R.color.white));
    }

    @OnClick(R.id.des_ll)
    public void des() {
        initDesColor();
        //        mWebview.setVisibility(View.VISIBLE);
        mWebview.setVisibility(View.GONE);
        mRecycler.setVisibility(View.GONE);


        mTvIntro.setVisibility(View.VISIBLE);
        mLvXiangqing.setVisibility(View.GONE);
    }

    private void initDesColor() {
        mXiangmuTv.setTextColor(getResources().getColor(R.color.gray));
        mIndicatorXiangmu.setBackgroundColor(getResources().getColor(R.color.white));
        mRecordTv.setTextColor(getResources().getColor(R.color.gray));
        mIndicatorRecord.setBackgroundColor(getResources().getColor(R.color.white));
        mPaihangTv.setTextColor(getResources().getColor(R.color.gray));
        mIndicatorPaihang.setBackgroundColor(getResources().getColor(R.color.white));
        mDesTv.setTextColor(getResources().getColor(R.color.green));
        mIndicatorDes.setBackgroundColor(getResources().getColor(R.color.green));
    }
}
