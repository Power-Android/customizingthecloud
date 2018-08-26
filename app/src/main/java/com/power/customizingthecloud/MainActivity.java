package com.power.customizingthecloud;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.base.BaseFragment;
import com.power.customizingthecloud.bean.EventBean;
import com.power.customizingthecloud.callback.JsonCallback;
import com.power.customizingthecloud.fragment.home.HomeFragment;
import com.power.customizingthecloud.fragment.market.MarketFragment;
import com.power.customizingthecloud.fragment.mine.MineFragment;
import com.power.customizingthecloud.fragment.pasture.PastureFragment;
import com.power.customizingthecloud.fragment.shop.ShopFragment;
import com.power.customizingthecloud.im.RcConnect;
import com.power.customizingthecloud.im.RongTokenBean;
import com.power.customizingthecloud.login.LoginActivity;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.Urls;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
    private long preTime;
    private String[] permissions = {
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE
            , android.Manifest.permission.READ_PHONE_STATE, android.Manifest.permission.CAMERA};
    private static final int CODE_FOR_WRITE_PERMISSION = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        homeFragment = new HomeFragment();
        addFragments(homeFragment);
        EventBus.getDefault().register(this);
        getRongToken();
        for (int i = 0; i < permissions.length; i++) {
            checkPermission(permissions[i]);
        }
    }

    private void checkPermission(String permission) {
        //使用兼容库就无需判断系统版本
        int hasWriteStoragePermission = ContextCompat.checkSelfPermission(getApplication(),
                permission);
        if (hasWriteStoragePermission == PackageManager.PERMISSION_GRANTED) {
            //拥有权限，执行操作
        } else {
            //没有权限，向用户请求权限
            ActivityCompat.requestPermissions(this, new String[]{permission}, CODE_FOR_WRITE_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        //通过requestCode来识别是否同一个请求
        if (requestCode == CODE_FOR_WRITE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //用户同意，执行操作
            } else {
                //用户不同意，向用户展示该权限作用
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_FINE_LOCATION)) {
                    Toast.makeText(this, "应用需要此权限", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void getRongToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));
        String userid = SpUtils.getString(mContext, "userid", "");
        String userName = SpUtils.getString(mContext, "userName", "");
        String userHead = SpUtils.getString(mContext, "userHead", "");
        HttpParams params = new HttpParams();
        params.put("user_id", "user_"+userid);
        params.put("user_name", userName);
        params.put("pic", userHead);
        OkGo.<RongTokenBean>post(Urls.BASEURL + "api/v2/user/rongcloud")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new JsonCallback<RongTokenBean>(RongTokenBean.class) {
                    @Override
                    public void onSuccess(Response<RongTokenBean> response) {
                        RongTokenBean bean = response.body();
                        String token = bean.getData().getToken();
                        RcConnect.rongCloudConnection(MainActivity.this, token);
                    }
                });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void myEvent(EventBean eventBean) {
        if (eventBean.getMsg().equals("checkmuchang")){//选中牧场
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
        }else if (eventBean.getMsg().equals("checkganji")){//选中赶集啦
            String userid = SpUtils.getString(this, "userid", "");
            if (TextUtils.isEmpty(userid)){
                startActivity(new Intent(this, LoginActivity.class));
                overridePendingTransition(R.anim.push_bottom_in,R.anim.push_bottom_out);
                return;
            }
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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

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
        String userid = SpUtils.getString(this, "userid", "");
        if (TextUtils.isEmpty(userid)){
            startActivity(new Intent(this, LoginActivity.class));
            overridePendingTransition(R.anim.push_bottom_in,R.anim.push_bottom_out);
            return;
        }
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
        String userid = SpUtils.getString(this, "userid", "");
        if (TextUtils.isEmpty(userid)){
            startActivity(new Intent(this, LoginActivity.class));
            overridePendingTransition(R.anim.push_bottom_in,R.anim.push_bottom_out);
            return;
        }
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

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() > preTime + 2000) {
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            preTime = System.currentTimeMillis();
        } else {
            super.onBackPressed();//相当于finish()
            realBack();//删除所有引用
        }
    }
}
