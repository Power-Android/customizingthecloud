package com.power.customizingthecloud.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.power.customizingthecloud.R;
import com.power.customizingthecloud.activity.mine.EditInfoActivity;
import com.power.customizingthecloud.activity.mine.FortuneCenterAcitivity;
import com.power.customizingthecloud.activity.mine.MyDonkeyEarsActivity;
import com.power.customizingthecloud.activity.mine.MyFootprintActivity;
import com.power.customizingthecloud.activity.mine.ShopCartActivity;
import com.power.customizingthecloud.base.BaseFragment;
import com.power.customizingthecloud.view.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/1/19.
 * 我的
 */

public class MineFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.title_message_iv) ImageView titleMessageIv;@BindView(R.id.title_content_tv) TextView titleContentTv;
    @BindView(R.id.title_setting_iv) ImageView titleSettingIv;@BindView(R.id.mine_face_iv) CircleImageView mineFaceIv;
    @BindView(R.id.mine_name_tv) TextView mineNameTv;@BindView(R.id.mine_sign_tv) TextView mineSignTv;
    @BindView(R.id.mine_shop_cart_ll) LinearLayout mineShopCartLl;@BindView(R.id.mine_lv_quan_ll) LinearLayout mineLvQuanLl;
    @BindView(R.id.mine_zu_ji_ll) LinearLayout mineZuJiLl;@BindView(R.id.mine_lingxianjin_tv) TextView mineLingXianJinTv;
    @BindView(R.id.mine_lverduo_tv) TextView mineLverduoTv;@BindView(R.id.mine_lverduo_ll) LinearLayout mineLverduoLl;
    @BindView(R.id.mine_daijinquan_tv) TextView mineDaijinquanTv;@BindView(R.id.mine_daijinquan_ll) LinearLayout mineDaijinquanLl;
    @BindView(R.id.mine_hongbao_tv) TextView mineHongbaoTv;@BindView(R.id.mine_hongbao_ll) LinearLayout mineHongbaoLl;
    @BindView(R.id.mine_yu_e_tv) TextView mineYuETv;@BindView(R.id.mine_yu_e_ll) LinearLayout mineYuELl;
    @BindView(R.id.mine_chakandingdan_tv) TextView mineChakandingdanTv;@BindView(R.id.mine_daifukuan_ll) LinearLayout mineDaifukuanLl;
    @BindView(R.id.mine_daifahuo_ll) LinearLayout mineDaifahuoLl;@BindView(R.id.mine_daishouhuo_ll) LinearLayout mineDaishouhuoLl;
    @BindView(R.id.mine_daipingjia_ll) LinearLayout mineDaipingjiaLl;@BindView(R.id.mine_tuikuan_ll) LinearLayout mineTuikuanLl;
    @BindView(R.id.mine_renyang_rl) RelativeLayout mineRenyangRl;@BindView(R.id.mine_yuding_rl) RelativeLayout mineYudingRl;
    @BindView(R.id.mine_dianpu_rl) RelativeLayout mineDianpuRl;@BindView(R.id.mine_zhuanzhang_rl) RelativeLayout mineZhuanzhangRl;
    @BindView(R.id.mine_huodong_rl) RelativeLayout mineHuodongRl;@BindView(R.id.mine_fenxiang_rl) RelativeLayout mineFenxiangRl;
    @BindView(R.id.mine_zijin_rl) RelativeLayout mineZijinRl;@BindView(R.id.mine_kefu_rl) RelativeLayout mineKefuRl;
    Unbinder unbinder;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(mContext, R.layout.fragment_mine, null);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    protected void initLazyData() {

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
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_message_iv://消息
                break;
            case R.id.title_setting_iv://设置
                break;
            case R.id.mine_face_iv://头像
                startActivity(new Intent(mContext,EditInfoActivity.class));
                break;
            case R.id.mine_sign_tv://签到有礼
                startActivity(new Intent(mContext, MyDonkeyEarsActivity.class));
                break;
            case R.id.mine_shop_cart_ll://购物车
                startActivity(new Intent(mContext, ShopCartActivity.class));
                break;
            case R.id.mine_lv_quan_ll://驴圈---财富中心
                startActivity(new Intent(mContext,FortuneCenterAcitivity.class));
                break;
            case R.id.mine_zu_ji_ll://足迹
                startActivity(new Intent(mContext, MyFootprintActivity.class));
                break;
            case R.id.mine_lingxianjin_tv://领现金
                break;
            case R.id.mine_lverduo_ll://驴耳朵
                startActivity(new Intent(mContext, MyDonkeyEarsActivity.class));
                break;
            case R.id.mine_daijinquan_ll://代金券
                break;
            case R.id.mine_hongbao_ll://红包
                break;
            case R.id.mine_yu_e_ll://余额
                break;
            case R.id.mine_chakandingdan_tv://查看全部订单
                break;
            case R.id.mine_daifukuan_ll://待付款
                break;
            case R.id.mine_daifahuo_ll://待发货
                break;
            case R.id.mine_daishouhuo_ll://待收货
                break;
            case R.id.mine_daipingjia_ll://待评价
                break;
            case R.id.mine_tuikuan_ll://退款、售后
                break;
            case R.id.mine_renyang_rl://我的认养
                break;
            case R.id.mine_yuding_rl://我的预订
                break;
            case R.id.mine_dianpu_rl://我的店铺
                break;
            case R.id.mine_zhuanzhang_rl://我的转账
                break;
            case R.id.mine_huodong_rl://我的活动
                break;
            case R.id.mine_fenxiang_rl://我的分享
                break;
            case R.id.mine_zijin_rl://资金记录
                break;
            case R.id.mine_kefu_rl://联系客服
                break;
        }
    }
}
