package com.power.customizingthecloud.fragment.shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/1/19.
 * 商城
 */

public class ShopFragment extends BaseFragment implements View.OnClickListener {
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
    @BindView(R.id.title_shopcar_iv)
    ImageView mTitleShopcarIv;
    @BindView(R.id.title_share_iv)
    ImageView mTitleShareIv;
    @BindView(R.id.title_search_iv)
    ImageView mTitleSearchIv;
    @BindView(R.id.title_content_right_tv)
    TextView mTitleContentRightTv;
    @BindView(R.id.tablayout)
    TabLayout mTablayout;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    Unbinder unbinder;
    private List<Fragment> mFragmentList = new ArrayList<>();
    private MyTabAdapter mAdapter;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initLazyData() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mTitleContentTv.setText("商城");
        mTitleListIv.setVisibility(View.VISIBLE);
        mTitleListIv.setOnClickListener(this);
        mTitleSearchIv.setVisibility(View.VISIBLE);
        mTitleSearchIv.setOnClickListener(this);
        if (mFragmentList.size() == 0) {
            mFragmentList.add(new ShopAllFragment());
            mFragmentList.add(new MeatFragment());
            mFragmentList.add(new ShopMiaoFragment());
            mFragmentList.add(new PinPaiFragment());
            mFragmentList.add(new TeChanFragment());
            mFragmentList.add(new BaoJianFragment());
        }
        mTablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mAdapter = new MyTabAdapter(getChildFragmentManager(), mFragmentList);
        mViewpager.setAdapter(mAdapter);
        mTablayout.setupWithViewPager(mViewpager);
        //        mViewpager.setOffscreenPageLimit(3);//缓存3个界面
    }

    public class MyTabAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList;
        private String[] title = {"全部", "生鲜肉类", "秒杀", "品牌精选", "地方特产", "养生保健"};

        public MyTabAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.mFragmentList = fragmentList;
        }

        //别忘了这个方法一定要有
        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }

        @Override//返回position位置的Fragment对象
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return title.length;
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
            case R.id.title_list_iv:
                startActivity(new Intent(mContext,GoodTypeActivity.class));
                break;
            case R.id.title_search_iv:
                break;
        }
    }
}
