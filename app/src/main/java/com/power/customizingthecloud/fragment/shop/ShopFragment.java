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
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseFragment;
import com.power.customizingthecloud.callback.JsonCallback;
import com.power.customizingthecloud.fragment.shop.bean.ShopTypeBean;
import com.power.customizingthecloud.utils.Urls;

import java.io.Serializable;
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
    private List<String> nameList = new ArrayList<>();
    private List<ShopTypeBean.DataEntity.SeriesClassEntity> mSeries_class;

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
        OkGo.<ShopTypeBean>get(Urls.BASEURL + "api/v2/good/class")
                .tag(this)
                .execute(new JsonCallback<ShopTypeBean>(ShopTypeBean.class) {
                    @Override
                    public void onSuccess(Response<ShopTypeBean> response) {
                        ShopTypeBean bean = response.body();
                        int code = bean.getCode();
                        if (code == 0) {
                            Toast.makeText(mContext, bean.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (code == 1) {
                            ShopTypeBean.DataEntity data = bean.getData();
                            List<ShopTypeBean.DataEntity.GoodClassEntity> good_class = data.getGood_class();
                            if (mFragmentList.size() == 0) {
                                mFragmentList.add(new ShopAllFragment());
                                nameList.add("全部");
                                MeatFragment meatFragment = new MeatFragment();
                                Bundle bundle = new Bundle();
                                bundle.putString("id", good_class.get(0).getId() + "");
                                meatFragment.setArguments(bundle);
                                mFragmentList.add(meatFragment);
                                nameList.add(good_class.get(0).getName());
                                mFragmentList.add(new ShopMiaoFragment());
                                nameList.add("秒杀");
                                for (int i = 1; i < 4; i++) {
                                    PinPaiFragment pinPaiFragment = new PinPaiFragment();
                                    Bundle bundle2 = new Bundle();
                                    bundle2.putString("id", good_class.get(i).getId() + "");
                                    pinPaiFragment.setArguments(bundle2);
                                    mFragmentList.add(pinPaiFragment);
                                    nameList.add(good_class.get(i).getName());
                                }
                            }
                            mTablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
                            mAdapter = new MyTabAdapter(getChildFragmentManager(), mFragmentList);
                            mViewpager.setAdapter(mAdapter);
                            mTablayout.setupWithViewPager(mViewpager);
                            mViewpager.setOffscreenPageLimit(3);//缓存3个界面
                            mSeries_class = data.getSeries_class();
                        }
                    }
                });
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
            if (nameList != null) {
                return nameList.get(position);
            }
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
                if (mSeries_class != null && mSeries_class.size() > 0) {
                    Intent intent = new Intent(mContext, GoodTypeActivity.class);
                    intent.putExtra("data", (Serializable) mSeries_class);
                    startActivity(intent);
                }
                break;
            case R.id.title_search_iv:
                startActivity(new Intent(mContext, SearchActivity.class));
                break;
        }
    }
}
