package com.power.customizingthecloud.fragment.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.MyApplication;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.activity.mine.MyReserveActivity;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.fragment.home.bean.CanWeiDetailBean;
import com.power.customizingthecloud.login.LoginActivity;
import com.power.customizingthecloud.login.bean.RegisterBean;
import com.power.customizingthecloud.utils.BannerUtils;
import com.power.customizingthecloud.utils.MyUtils;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.Urls;
import com.power.customizingthecloud.view.BaseDialog;
import com.power.customizingthecloud.view.CommonPopupWindow;
import com.youth.banner.Banner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.power.customizingthecloud.R.id.tv_shengyu;

public class ShopDetailActivity extends BaseActivity implements View.OnClickListener {

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
    @BindView(R.id.title_shopcar_iv)
    ImageView mTitleShopcarIv;
    @BindView(R.id.title_share_iv)
    ImageView mTitleShareIv;
    @BindView(R.id.title_content_right_tv)
    TextView mTitleContentRightTv;
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(tv_shengyu)
    TextView mTvShengyu;
    @BindView(R.id.tv_intro)
    TextView mTvIntro;
    @BindView(R.id.tv_location)
    TextView mTvLocation;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.tv_order)
    TextView mTvOrder;
    private BaseDialog mDialog;
    private BaseDialog.Builder mBuilder;
    private CommonPopupWindow popupWindow;
    private TextView mTv_zuowei;
    private TextView mTv_person_count;
    private String mId;
    private List<String> personCountList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);
        ButterKnife.bind(this);
        mTitleBackIv.setVisibility(View.VISIBLE);
        mTitleBackIv.setOnClickListener(this);
        mTitleContentTv.setText("商家详情");
        mTvPhone.setOnClickListener(this);
        mTvOrder.setOnClickListener(this);
        mRecycler.setLayoutManager(new GridLayoutManager(this, 4));
        mRecycler.setNestedScrollingEnabled(false);
        mId = getIntent().getStringExtra("id");
        if (personCountList.size() == 0) {
            for (int i = 1; i < 21; i++) {
                personCountList.add(i + "人");
            }
        }
        HttpParams params = new HttpParams();
        params.put("id", mId);
        OkGo.<CanWeiDetailBean>get(Urls.BASEURL + "api/v2/restaurant/show")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<CanWeiDetailBean>(this, CanWeiDetailBean.class) {
                    @Override
                    public void onSuccess(Response<CanWeiDetailBean> response) {
                        CanWeiDetailBean canWeiDetailBean = response.body();
                        int code = canWeiDetailBean.getCode();
                        if (code == 0) {
                            Toast.makeText(mContext, canWeiDetailBean.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (code == 1) {
                            CanWeiDetailBean.DataEntity data = canWeiDetailBean.getData();
                            mTvName.setText(data.getName());
                            mTvShengyu.setText(data.getSeat_number());
                            mTvIntro.setText(data.getInformation());
                            mTvLocation.setText(data.getPosition());
                            mTvPhone.setText(data.getTelephone());
                            List<String> slids_img = data.getSlids_img();
                            BannerUtils.startBanner(mBanner, slids_img);
                            List<String> recommend_img = data.getRecommend_img();
                            FoodAdapter foodAdapter = new FoodAdapter(R.layout.item_food, recommend_img);
                            mRecycler.setAdapter(foodAdapter);
                        }
                    }
                });
    }

    private class FoodAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public FoodAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            Glide.with(MyApplication.getGloableContext()).load(item).into((ImageView) helper.getView(R.id.iv_food));
        }
    }

    private void showOrderDialog() {
        mBuilder = new BaseDialog.Builder(this);
        mDialog = mBuilder.setViewId(R.layout.dialog_order)
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
        final LinearLayout ll_person_count = mDialog.getView(R.id.ll_person_count);
        mTv_person_count = mDialog.getView(R.id.tv_person_count);
        ll_person_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDownPop(mTv_person_count, personCountList);
            }
        });
        final LinearLayout ll_zuowei = mDialog.getView(R.id.ll_zuowei);
        mTv_zuowei = mDialog.getView(R.id.tv_zuowei);
        ll_zuowei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> list = new ArrayList<String>();
                list.add("散桌");
                list.add("包厢");
                showDownPop(mTv_zuowei, list);
            }
        });
        final EditText edt_name = mDialog.getView(R.id.edt_name);
        final EditText edt_phone = mDialog.getView(R.id.edt_phone);
        final EditText edt_remarks = mDialog.getView(R.id.edt_remarks);
        final TextView tv_select_time = mDialog.getView(R.id.tv_select_time);
        tv_select_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSelectTimeDialog(tv_select_time);
            }
        });
        mDialog.getView(R.id.tv_yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edt_name.getText().toString())) {
                    Toast.makeText(ShopDetailActivity.this, "请输入姓名", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(edt_phone.getText().toString())) {
                    Toast.makeText(ShopDetailActivity.this, "请输入电话号码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!MyUtils.isMobileNO(edt_phone.getText().toString())) {
                    Toast.makeText(ShopDetailActivity.this, "请输入正确格式的手机号~", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (tv_select_time.getText().toString().equals("选择预定时间")) {
                    Toast.makeText(ShopDetailActivity.this, "请选择预定时间~", Toast.LENGTH_SHORT).show();
                    return;
                }
                HttpHeaders headers = new HttpHeaders();
                headers.put("Authorization", "Bearer " + SpUtils.getString(ShopDetailActivity.this, "token", ""));
                HttpParams params = new HttpParams();
                params.put("id", mId);
                params.put("name", edt_name.getText().toString());
                params.put("mobile", edt_phone.getText().toString());
                String s = mTv_person_count.getText().toString();
                String substring = s.substring(0, s.length() - 1);
                params.put("number", substring);
                params.put("seat", mTv_zuowei.getText().toString());
                params.put("restaurant_time", tv_select_time.getText().toString());
                if (!edt_remarks.getText().toString().equals("")) {
                    params.put("remarks", edt_remarks.getText().toString());
                }
                OkGo.<RegisterBean>post(Urls.BASEURL + "api/v2/restaurant/store")
                        .tag(this)
                        .headers(headers)
                        .params(params)
                        .execute(new DialogCallback<RegisterBean>(ShopDetailActivity.this, RegisterBean.class) {
                            @Override
                            public void onSuccess(Response<RegisterBean> response) {
                                RegisterBean bean = response.body();
                                int code = bean.getCode();
                                if (code == 0) {
                                    Toast.makeText(mContext, bean.getMessage(), Toast.LENGTH_SHORT).show();
                                } else if (code == 1) {
                                    Toast.makeText(mContext, bean.getMessage(), Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(ShopDetailActivity.this, MyReserveActivity.class));
                                    mDialog.dismiss();
                                }
                            }

                            @Override
                            public void onError(Response<RegisterBean> response) {
                                super.onError(response);
                            }
                        });
            }
        });
    }

    private void showSelectTimeDialog(final TextView tv_select_time) {
        //时间选择器
        TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String format = simpleDateFormat.format(date);
                tv_select_time.setText(format);
            }
        })
                //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, true, true, true, false})
                .isDialog(true)
                .build();
        pvTime.setDate(Calendar.getInstance());
        if (!pvTime.isShowing()) {
            pvTime.show();
        }
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
                        recycle_view.setLayoutManager(new LinearLayoutManager(ShopDetailActivity.this));
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
            case R.id.tv_phone:
                Intent dialIntent = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:" + mTvPhone.getText().toString()));//跳转到拨号界面，同时传递电话号码
                startActivity(dialIntent);
                break;
            case R.id.tv_order:
                String userid = SpUtils.getString(mContext, "userid", "");
                if (TextUtils.isEmpty(userid)) {
                    startActivity(new Intent(mContext, LoginActivity.class));
                    overridePendingTransition(R.anim.push_bottom_in, R.anim.push_bottom_out);
                    return;
                }
                showOrderDialog();
                break;
        }
    }
}
