package com.power.customizingthecloud;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.base.BaseFragment;
import com.power.customizingthecloud.fragment.home.HomeFragment;
import com.power.customizingthecloud.fragment.market.MarketFragment;
import com.power.customizingthecloud.fragment.mine.MineFragment;
import com.power.customizingthecloud.fragment.pasture.PastureFragment;
import com.power.customizingthecloud.fragment.shop.ShopFragment;
import com.power.customizingthecloud.utils.TUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.fl_content) FrameLayout flContent;
    @BindView(R.id.iv_home) ImageView ivHome;
    @BindView(R.id.tv_home) TextView tvHome;
    @BindView(R.id.ll_home) LinearLayout llHome;
    @BindView(R.id.iv_shop) ImageView ivShop;
    @BindView(R.id.tv_shop) TextView tvShop;
    @BindView(R.id.ll_shop) LinearLayout llShop;
    @BindView(R.id.iv_pasture) ImageView ivPasture;
    @BindView(R.id.tv_pasture) TextView tvPasture;
    @BindView(R.id.ll_pasture) LinearLayout llPasture;
    @BindView(R.id.iv_market) ImageView ivMarket;
    @BindView(R.id.tv_market) TextView tvMarket;
    @BindView(R.id.ll_market) LinearLayout llMarket;
    @BindView(R.id.iv_mine) ImageView ivMine;
    @BindView(R.id.tv_mine) TextView tvMine;
    @BindView(R.id.ll_mine) LinearLayout llMine;
    @BindView(R.id.activity_main) LinearLayout activityMain;
    private BaseFragment baseFragment;
    private HomeFragment homeFragment;
    private ShopFragment shopFragment;
    private PastureFragment pastureFragment;
    private MarketFragment marketFragment;
    private MineFragment mineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        homeFragment = new HomeFragment();
        addFragments(homeFragment);
    }

    /*private void initView() {
        titleMessageIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("养驴啦");
        titleSignInIv.setVisibility(View.VISIBLE);
    }*/

    /*@OnClick(R.id.title_message_iv)
    public void titleMessage(){
        TUtils.showShort(mContext,"点击了---消息");
    }

    @OnClick(R.id.title_sign_in_iv)
    public void titleSignIn(){
        TUtils.showShort(mContext,"点击了---签到");
    }*/

    private void addFragments(BaseFragment f) {
        // 第一步：得到fragment管理类
        FragmentManager manager = getSupportFragmentManager();
        // 第二步：开启一个事务
        FragmentTransaction transaction = manager.beginTransaction();

        if (baseFragment != null) {
            //每次把前一个fragment给隐藏了
            transaction.hide(baseFragment);
        }
        //isAdded:判断当前的fragment对象是否被加载过
        if (!f.isAdded()) {
            // 第三步：调用添加fragment的方法 第一个参数：容器的id 第二个参数：要放置的fragment的一个实例对象
            transaction.add(R.id.fl_content, f);
        }
        //显示当前的fragment
        transaction.show(f);
        // 第四步：提交
        transaction.commit();
        baseFragment = f;
    }

    @OnClick(R.id.ll_home)
    public void home(){
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
        }
        addFragments(homeFragment);
        ivHome.setImageResource(R.drawable.home_ture);
        ivShop.setImageResource(R.drawable.shop_false);
        ivPasture.setImageResource(R.drawable.pasture_false);
        ivMarket.setImageResource(R.drawable.market_false);
        ivMine.setImageResource(R.drawable.mine_false);
        tvHome.setTextColor(getResources().getColor(R.color.green));
        tvShop.setTextColor(getResources().getColor(R.color.text_gray));
        tvPasture.setTextColor(getResources().getColor(R.color.text_gray));
        tvMarket.setTextColor(getResources().getColor(R.color.text_gray));
        tvMine.setTextColor(getResources().getColor(R.color.text_gray));
    }

    @OnClick(R.id.ll_shop)
    public void shop(){
        if (shopFragment == null) {
            shopFragment = new ShopFragment();
        }
        addFragments(shopFragment);
        ivHome.setImageResource(R.drawable.home_false);
        ivShop.setImageResource(R.drawable.shop_ture);
        ivPasture.setImageResource(R.drawable.pasture_false);
        ivMarket.setImageResource(R.drawable.market_false);
        ivMine.setImageResource(R.drawable.mine_false);
        tvHome.setTextColor(getResources().getColor(R.color.text_gray));
        tvShop.setTextColor(getResources().getColor(R.color.green));
        tvPasture.setTextColor(getResources().getColor(R.color.text_gray));
        tvMarket.setTextColor(getResources().getColor(R.color.text_gray));
        tvMine.setTextColor(getResources().getColor(R.color.text_gray));
    }

    @OnClick(R.id.ll_pasture)
    public void  pasture(){
        if (pastureFragment == null) {
            pastureFragment = new PastureFragment();
        }
        addFragments(pastureFragment);
        ivHome.setImageResource(R.drawable.home_false);
        ivShop.setImageResource(R.drawable.shop_false);
        ivPasture.setImageResource(R.drawable.pasture_ture);
        ivMarket.setImageResource(R.drawable.market_false);
        ivMine.setImageResource(R.drawable.mine_false);
        tvHome.setTextColor(getResources().getColor(R.color.text_gray));
        tvShop.setTextColor(getResources().getColor(R.color.text_gray));
        tvPasture.setTextColor(getResources().getColor(R.color.green));
        tvMarket.setTextColor(getResources().getColor(R.color.text_gray));
        tvMine.setTextColor(getResources().getColor(R.color.text_gray));
    }

    @OnClick(R.id.ll_market)
    public void market(){
        if (marketFragment == null) {
            marketFragment = new MarketFragment();
        }
        addFragments(marketFragment);
        ivHome.setImageResource(R.drawable.home_false);
        ivShop.setImageResource(R.drawable.shop_false);
        ivPasture.setImageResource(R.drawable.pasture_false);
        ivMarket.setImageResource(R.drawable.market_true);
        ivMine.setImageResource(R.drawable.mine_false);
        tvHome.setTextColor(getResources().getColor(R.color.text_gray));
        tvShop.setTextColor(getResources().getColor(R.color.text_gray));
        tvPasture.setTextColor(getResources().getColor(R.color.text_gray));
        tvMarket.setTextColor(getResources().getColor(R.color.green));
        tvMine.setTextColor(getResources().getColor(R.color.text_gray));
    }

    @OnClick(R.id.ll_mine)
    public void mine(){
        if (mineFragment == null) {
            mineFragment = new MineFragment();
        }
        addFragments(mineFragment);
        ivHome.setImageResource(R.drawable.home_false);
        ivShop.setImageResource(R.drawable.shop_false);
        ivPasture.setImageResource(R.drawable.pasture_false);
        ivMarket.setImageResource(R.drawable.market_false);
        ivMine.setImageResource(R.drawable.mine_ture);
        tvHome.setTextColor(getResources().getColor(R.color.text_gray));
        tvShop.setTextColor(getResources().getColor(R.color.text_gray));
        tvPasture.setTextColor(getResources().getColor(R.color.text_gray));
        tvMarket.setTextColor(getResources().getColor(R.color.text_gray));
        tvMine.setTextColor(getResources().getColor(R.color.green));
    }
}
