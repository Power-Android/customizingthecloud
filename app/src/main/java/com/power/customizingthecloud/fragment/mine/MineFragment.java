package com.power.customizingthecloud.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.activity.mine.CustomerCenterActivity;
import com.power.customizingthecloud.activity.mine.EditInfoActivity;
import com.power.customizingthecloud.activity.mine.FortuneCenterAcitivity;
import com.power.customizingthecloud.activity.mine.LatestActivity;
import com.power.customizingthecloud.activity.mine.MyDonkeyEarsActivity;
import com.power.customizingthecloud.activity.mine.MyFootprintActivity;
import com.power.customizingthecloud.activity.mine.MyMessageActivity;
import com.power.customizingthecloud.activity.mine.MyMoneyRecordActivity;
import com.power.customizingthecloud.activity.mine.MyOrderActivity;
import com.power.customizingthecloud.activity.mine.MyRedPacketActivity;
import com.power.customizingthecloud.activity.mine.MyRenyangCenterActivity;
import com.power.customizingthecloud.activity.mine.MyReserveActivity;
import com.power.customizingthecloud.activity.mine.MyTransferAccountsActivity;
import com.power.customizingthecloud.activity.mine.MyVoucherActivity;
import com.power.customizingthecloud.activity.mine.RefundAfterActivity;
import com.power.customizingthecloud.activity.mine.SettingActivity;
import com.power.customizingthecloud.activity.mine.ShopCartActivity;
import com.power.customizingthecloud.base.BaseFragment;
import com.power.customizingthecloud.bean.EventBean;
import com.power.customizingthecloud.bean.UserBean;
import com.power.customizingthecloud.callback.JsonCallback;
import com.power.customizingthecloud.fragment.home.top.KaiDianActivity;
import com.power.customizingthecloud.fragment.home.top.MyShareActivity;
import com.power.customizingthecloud.login.LoginActivity;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.Urls;
import com.power.customizingthecloud.view.CircleImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/1/19.
 * 我的
 */

public class MineFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.title_message_iv)
    ImageView titleMessageIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.title_setting_iv)
    ImageView titleSettingIv;
    @BindView(R.id.mine_face_iv)
    CircleImageView mineFaceIv;
    @BindView(R.id.mine_name_tv)
    TextView mineNameTv;
    @BindView(R.id.mine_sign_tv)
    TextView mineSignTv;
    @BindView(R.id.mine_shop_cart_ll)
    LinearLayout mineShopCartLl;
    @BindView(R.id.mine_lv_quan_ll)
    LinearLayout mineLvQuanLl;
    @BindView(R.id.mine_zu_ji_ll)
    LinearLayout mineZuJiLl;
    @BindView(R.id.mine_lingxianjin_tv)
    TextView mineLingXianJinTv;
    @BindView(R.id.mine_lverduo_tv)
    TextView mineLverduoTv;
    @BindView(R.id.mine_lverduo_ll)
    LinearLayout mineLverduoLl;
    @BindView(R.id.mine_daijinquan_tv)
    TextView mineDaijinquanTv;
    @BindView(R.id.mine_daijinquan_ll)
    LinearLayout mineDaijinquanLl;
    @BindView(R.id.mine_hongbao_tv)
    TextView mineHongbaoTv;
    @BindView(R.id.mine_hongbao_ll)
    LinearLayout mineHongbaoLl;
    @BindView(R.id.mine_yu_e_tv)
    TextView mineYuETv;
    @BindView(R.id.mine_yu_e_ll)
    LinearLayout mineYuELl;
    @BindView(R.id.mine_chakandingdan_tv)
    TextView mineChakandingdanTv;
    @BindView(R.id.mine_daifukuan_ll)
    LinearLayout mineDaifukuanLl;
    @BindView(R.id.mine_daifahuo_ll)
    LinearLayout mineDaifahuoLl;
    @BindView(R.id.mine_daishouhuo_ll)
    LinearLayout mineDaishouhuoLl;
    @BindView(R.id.mine_daipingjia_ll)
    LinearLayout mineDaipingjiaLl;
    @BindView(R.id.mine_tuikuan_ll)
    LinearLayout mineTuikuanLl;
    @BindView(R.id.mine_renyang_rl)
    RelativeLayout mineRenyangRl;
    @BindView(R.id.mine_yuding_rl)
    RelativeLayout mineYudingRl;
    @BindView(R.id.mine_dianpu_rl)
    RelativeLayout mineDianpuRl;
    @BindView(R.id.mine_zhuanzhang_rl)
    RelativeLayout mineZhuanzhangRl;
    @BindView(R.id.mine_huodong_rl)
    RelativeLayout mineHuodongRl;
    @BindView(R.id.mine_fenxiang_rl)
    RelativeLayout mineFenxiangRl;
    @BindView(R.id.mine_zijin_rl)
    RelativeLayout mineZijinRl;
    @BindView(R.id.mine_kefu_rl)
    RelativeLayout mineKefuRl;
    Unbinder unbinder;
    private Intent intent;
    private String user_avatar;
    private String user_name;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(mContext, R.layout.fragment_mine, null);
        unbinder = ButterKnife.bind(this, view);
        initView();
        EventBus.getDefault().register(this);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void initLazyData() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void myEvent(EventBean eventBean) {
        if (eventBean.getMsg().equals("userinfo")) {
            initData();
        }
    }

    private void initView() {
        titleMessageIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("个人中心");
        titleSettingIv.setVisibility(View.VISIBLE);
        titleMessageIv.setOnClickListener(this);
        titleSettingIv.setOnClickListener(this);
        mineFaceIv.setOnClickListener(this);
        mineSignTv.setOnClickListener(this);
        mineShopCartLl.setOnClickListener(this);
        mineLvQuanLl.setOnClickListener(this);
        mineZuJiLl.setOnClickListener(this);
        mineLingXianJinTv.setOnClickListener(this);
        mineLverduoLl.setOnClickListener(this);
        mineDaijinquanLl.setOnClickListener(this);
        mineHongbaoLl.setOnClickListener(this);
        mineYuELl.setOnClickListener(this);
        mineChakandingdanTv.setOnClickListener(this);
        mineDaifukuanLl.setOnClickListener(this);
        mineDaifahuoLl.setOnClickListener(this);
        mineDaishouhuoLl.setOnClickListener(this);
        mineDaipingjiaLl.setOnClickListener(this);
        mineTuikuanLl.setOnClickListener(this);
        mineRenyangRl.setOnClickListener(this);
        mineYudingRl.setOnClickListener(this);
        mineDianpuRl.setOnClickListener(this);
        mineZhuanzhangRl.setOnClickListener(this);
        mineHuodongRl.setOnClickListener(this);
        mineFenxiangRl.setOnClickListener(this);
        mineZijinRl.setOnClickListener(this);
        mineKefuRl.setOnClickListener(this);
        initData();
    }

    private void initData() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));
        OkGo.<UserBean>get(Urls.BASEURL + "api/v2/user")
                .tag(this)
                .headers(headers)
                .execute(new JsonCallback<UserBean>(UserBean.class) {
                    @Override
                    public void onSuccess(Response<UserBean> response) {
                        UserBean userBean = response.body();
                        if (userBean.getCode() == 1) {
                            mineNameTv.setText(userBean.getData().getUser_name());
                            SpUtils.putString(mContext, "userName", userBean.getData().getUser_name());
                            mineLverduoTv.setText(userBean.getData().getUser_eselsohr() + "只");
                            mineDaijinquanTv.setText(userBean.getData().getVoucher_count() + "张");
                            mineHongbaoTv.setText(userBean.getData().getPackage_count() + "个");
                            mineYuETv.setText(userBean.getData().getUser_balance() + "元");
                            user_name = userBean.getData().getUser_name();
                            user_avatar = userBean.getData().getUser_avatar();
                            if (!TextUtils.isEmpty(userBean.getData().getUser_avatar())) {
                                Glide.with(mContext).load(userBean.getData().getUser_avatar()).into(mineFaceIv);
                                SpUtils.putString(mContext, "userHead", userBean.getData().getUser_avatar());
                            } else {
                                mineFaceIv.setImageResource(R.drawable.face);
                            }
                            //判断用户有没有邀请码
                            if (TextUtils.isEmpty(userBean.getData().getInviter_code())){
                                SpUtils.putBoolean(mContext,"inviter_code",false);
                            }else {
                                SpUtils.putBoolean(mContext,"inviter_code",true);
                            }
                        }
                    }
                });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            String userid = SpUtils.getString(mContext, "userid", "");
            if (TextUtils.isEmpty(userid)) {
                startActivity(new Intent(mContext, LoginActivity.class));
                mActivity.overridePendingTransition(R.anim.push_bottom_in, R.anim.push_bottom_out);
            } else {
                //这是当用户在别的界面点击领取代金券的时候或者获得驴耳朵的时候刷新一下
                initData();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_message_iv://消息
                startActivity(new Intent(mContext, MyMessageActivity.class));
                break;
            case R.id.title_setting_iv://设置
                startActivity(new Intent(mContext, SettingActivity.class));
                break;
            case R.id.mine_face_iv://头像
                intent = new Intent(mContext, EditInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.mine_sign_tv://签到有礼
                startActivity(new Intent(mContext, MyDonkeyEarsActivity.class));
                break;
            case R.id.mine_shop_cart_ll://购物车
                startActivity(new Intent(mContext, ShopCartActivity.class));
                break;
            case R.id.mine_lv_quan_ll://驴圈---财富中心
                startActivity(new Intent(mContext, FortuneCenterAcitivity.class));
                break;
            case R.id.mine_zu_ji_ll://足迹
                startActivity(new Intent(mContext, MyFootprintActivity.class));
                break;
            case R.id.mine_lingxianjin_tv://领现金
                startActivity(new Intent(mContext, LatestActivity.class));
                break;
            case R.id.mine_lverduo_ll://驴耳朵
                startActivity(new Intent(mContext, MyDonkeyEarsActivity.class));
                break;
            case R.id.mine_daijinquan_ll://代金券
                startActivity(new Intent(mContext, MyVoucherActivity.class));
                break;
            case R.id.mine_hongbao_ll://红包
                startActivity(new Intent(mContext, MyRedPacketActivity.class));
                break;
            case R.id.mine_yu_e_ll://余额
                startActivity(new Intent(mContext, FortuneCenterAcitivity.class));
                break;
            case R.id.mine_chakandingdan_tv://查看全部订单
                intent = new Intent(mContext, MyOrderActivity.class);
                intent.putExtra("type", "0");
                startActivity(intent);
                break;
            case R.id.mine_daifukuan_ll://待付款
                intent = new Intent(mContext, MyOrderActivity.class);
                intent.putExtra("type", "1");
                startActivity(intent);
                break;
            case R.id.mine_daifahuo_ll://待发货
                intent = new Intent(mContext, MyOrderActivity.class);
                intent.putExtra("type", "2");
                startActivity(intent);
                break;
            case R.id.mine_daishouhuo_ll://待收货
                intent = new Intent(mContext, MyOrderActivity.class);
                intent.putExtra("type", "3");
                startActivity(intent);
                break;
            case R.id.mine_daipingjia_ll://待评价
                intent = new Intent(mContext, MyOrderActivity.class);
                intent.putExtra("type", "4");
                startActivity(intent);
                break;
            case R.id.mine_tuikuan_ll://退款、售后
                startActivity(new Intent(mContext, RefundAfterActivity.class));
                break;
            case R.id.mine_renyang_rl://我的认养
                startActivity(new Intent(mContext, MyRenyangCenterActivity.class));
                break;
            case R.id.mine_yuding_rl://我的预订
                startActivity(new Intent(mContext, MyReserveActivity.class));
                break;
            case R.id.mine_dianpu_rl://我的店铺
                if (SpUtils.getBoolean(mContext, "inviter_code", false)) {
                    startActivity(new Intent(mContext, KaiDianActivity.class));
                }else {
                    Toast.makeText(mContext, "您还无法开店，先去认养毛驴吧~", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.mine_zhuanzhang_rl://我的转账
                startActivity(new Intent(mContext, MyTransferAccountsActivity.class));
                break;
            case R.id.mine_huodong_rl://我的活动
                startActivity(new Intent(mContext, LatestActivity.class));
                break;
            case R.id.mine_fenxiang_rl://我的分享
                startActivity(new Intent(mContext, MyShareActivity.class));
                break;
            case R.id.mine_zijin_rl://资金记录
                startActivity(new Intent(mContext, MyMoneyRecordActivity.class));
                break;
            case R.id.mine_kefu_rl://联系客服
                startActivity(new Intent(mContext, CustomerCenterActivity.class));
                break;
        }
    }
}
