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

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.MyApplication;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.activity.mine.ShopCartActivity;
import com.power.customizingthecloud.base.BaseFragment;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.fragment.home.GoodDetailActivity;
import com.power.customizingthecloud.fragment.home.GoodListActivity;
import com.power.customizingthecloud.fragment.shop.bean.MeatBean;
import com.power.customizingthecloud.login.LoginActivity;
import com.power.customizingthecloud.utils.BannerUtils;
import com.power.customizingthecloud.utils.MyUtils;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.Urls;
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
    private List<String> imgList = new ArrayList<>();

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
        //开启在布局文件中设置的帧动画
        mAnimationDrawable = (AnimationDrawable) mIvEye.getDrawable();
        mAnimationDrawable.start();
        mRecyclerMeat.setLayoutManager(new GridLayoutManager(mContext, 2));
        mRecyclerMeat.setNestedScrollingEnabled(false);
        Bundle bundle = getArguments();
        String id = bundle.getString("id");
        HttpParams params = new HttpParams();
        params.put("id", id);
        params.put("after", "");
        params.put("limit", "10");
        OkGo.<MeatBean>get(Urls.BASEURL + "api/v2/good/fresh-good")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<MeatBean>(mActivity, MeatBean.class) {
                    @Override
                    public void onSuccess(Response<MeatBean> response) {
                        MeatBean meatBean = response.body();
                        int code = meatBean.getCode();
                        if (code == 0) {
                            Toast.makeText(mContext, meatBean.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (code == 1) {
                            MeatBean.DataEntity data = meatBean.getData();
                            final List<MeatBean.DataEntity.GoodSildEntity> good_sild = data.getGood_sild();
                            imgList.clear();
                            for (int i = 0; i < good_sild.size(); i++) {
                                imgList.add(good_sild.get(i).getImage_url());
                            }
                            BannerUtils.startBanner(mBanner, imgList);
                            mBanner.setOnBannerListener(new OnBannerListener() {
                                @Override
                                public void OnBannerClick(int position) {
                                    if (good_sild.get(position).getType() == 1) {
                                        startActivity(new Intent(mContext, GoodDetailActivity.class));
                                    }
                                }
                            });
                            List<MeatBean.DataEntity.GoodsEntity> goods = data.getGoods();
                            XianAdapter xianAdapter = new XianAdapter(R.layout.item_shengxian, goods);
                            mRecyclerMeat.setAdapter(xianAdapter);
                            xianAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    startActivity(new Intent(mContext, GoodDetailActivity.class));
                                }
                            });
                        }
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

    private class XianAdapter extends BaseQuickAdapter<MeatBean.DataEntity.GoodsEntity, BaseViewHolder> {

        public XianAdapter(@LayoutRes int layoutResId, @Nullable List<MeatBean.DataEntity.GoodsEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, MeatBean.DataEntity.GoodsEntity item) {
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
            Glide.with(MyApplication.getGloableContext()).load(item.getImage()).into(iv_top);
            helper.setText(R.id.tv_name,item.getName())
                    .setText(R.id.tv_price,item.getPrice());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        mAnimationDrawable = null;
    }
}
