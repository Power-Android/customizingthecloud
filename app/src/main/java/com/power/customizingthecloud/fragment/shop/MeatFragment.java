package com.power.customizingthecloud.fragment.shop;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.activity.mine.ShopCartActivity;
import com.power.customizingthecloud.base.BaseFragment;
import com.power.customizingthecloud.fragment.home.GoodDetailActivity;
import com.power.customizingthecloud.fragment.home.GoodListActivity;
import com.power.customizingthecloud.login.LoginActivity;
import com.power.customizingthecloud.utils.BannerUtils;
import com.power.customizingthecloud.utils.MyUtils;
import com.power.customizingthecloud.utils.SpUtils;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/2/2.
 */

public class MeatFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.iv_buwei)
    ImageView mIvBuwei;
    @BindView(R.id.iv_eye)
    ImageView mIvEye;
    @BindView(R.id.iv_car)
    ImageView mIvCar;
    @BindView(R.id.recycler_meat)
    RecyclerView mRecyclerMeat;
    Unbinder unbinder;
    @BindView(R.id.view_beibu)
    View mViewBeibu;
    @BindView(R.id.view_leibu)
    View mViewLeibu;
    @BindView(R.id.view_jianbu)
    View mViewJianbu;
    @BindView(R.id.view_fubu)
    View mViewFubu;
    @BindView(R.id.view_qiantuibu)
    View mViewQiantuibu;
    @BindView(R.id.view_houtuibu)
    View mViewHoutuibu;
    private AnimationDrawable mAnimationDrawable;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopmeat, null);
        unbinder = ButterKnife.bind(this, view);
        mIvCar.setOnClickListener(this);
        mViewBeibu.setOnClickListener(this);
        mViewLeibu.setOnClickListener(this);
        mViewJianbu.setOnClickListener(this);
        mViewFubu.setOnClickListener(this);
        mViewQiantuibu.setOnClickListener(this);
        mViewHoutuibu.setOnClickListener(this);
        return view;
    }

    @Override
    protected void initLazyData() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        BannerUtils.startBanner(mBanner, new ArrayList<String>());
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                startActivity(new Intent(mContext,GoodDetailActivity.class));
            }
        });
        //开启在布局文件中设置的帧动画
        mAnimationDrawable = (AnimationDrawable) mIvEye.getDrawable();
        mAnimationDrawable.start();
        mRecyclerMeat.setLayoutManager(new GridLayoutManager(mContext, 2));
        mRecyclerMeat.setNestedScrollingEnabled(false);
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        XianAdapter xianAdapter = new XianAdapter(R.layout.item_shengxian, list);
        mRecyclerMeat.setAdapter(xianAdapter);
        xianAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(mContext, GoodDetailActivity.class));
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        mAnimationDrawable.stop();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_car:
                String userid = SpUtils.getString(mContext, "userid", "");
                if (TextUtils.isEmpty(userid)) {
                    startActivity(new Intent(mContext, LoginActivity.class));
                    mActivity.overridePendingTransition(R.anim.push_bottom_in, R.anim.push_bottom_out);
                    return;
                }
                startActivity(new Intent(mContext, ShopCartActivity.class));
                break;
            case R.id.view_beibu:
                startActivity(new Intent(mContext, GoodListActivity.class));
                break;
            case R.id.view_leibu:
                startActivity(new Intent(mContext, GoodListActivity.class));
                break;
            case R.id.view_jianbu:
                startActivity(new Intent(mContext, GoodListActivity.class));
                break;
            case R.id.view_fubu:
                startActivity(new Intent(mContext, GoodListActivity.class));
                break;
            case R.id.view_qiantuibu:
                startActivity(new Intent(mContext, GoodListActivity.class));
                break;
            case R.id.view_houtuibu:
                startActivity(new Intent(mContext, GoodListActivity.class));
                break;
        }
    }

    private class XianAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public XianAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            ImageView iv_insertcar = helper.getView(R.id.iv_insertcar);
            iv_insertcar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String userid = SpUtils.getString(mContext, "userid", "");
                    if (TextUtils.isEmpty(userid)) {
                        startActivity(new Intent(mContext, LoginActivity.class));
                        mActivity.overridePendingTransition(R.anim.push_bottom_in, R.anim.push_bottom_out);
                        return;
                    }
                    Toast.makeText(mContext, "加入购物车成功，请去购物车结算~", Toast.LENGTH_SHORT).show();
                }
            });
            ImageView iv_top = helper.getView(R.id.iv_top);
            int width = MyUtils.getScreenWidth(mContext) - MyUtils.dip2px(mContext, 50);
            ViewGroup.LayoutParams layoutParams = iv_top.getLayoutParams();
            layoutParams.height = width / 2;
            iv_top.setLayoutParams(layoutParams);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        mAnimationDrawable = null;
    }
}
