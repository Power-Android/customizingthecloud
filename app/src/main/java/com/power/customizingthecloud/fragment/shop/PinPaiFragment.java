package com.power.customizingthecloud.fragment.shop;

import android.content.Intent;
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
import com.power.customizingthecloud.fragment.shop.bean.PinPaiBean;
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

public class PinPaiFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.iv_car)
    ImageView mIvCar;
    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;
    Unbinder unbinder;
    private List<String> imgList = new ArrayList<>();

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pinpai, null);
        unbinder = ButterKnife.bind(this, view);
        mIvCar.setOnClickListener(this);
        return view;
    }

    @Override
    protected void initLazyData() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        mRecyclerView.setNestedScrollingEnabled(false);
        Bundle bundle = getArguments();
        String id = bundle.getString("id");
        HttpParams params = new HttpParams();
        params.put("id", id);
        params.put("after", "");
        params.put("limit", "10");
        OkGo.<PinPaiBean>get(Urls.BASEURL + "api/v2/good/outher-good")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<PinPaiBean>(mActivity, PinPaiBean.class) {
                    @Override
                    public void onSuccess(Response<PinPaiBean> response) {
                        PinPaiBean pinPaiBean = response.body();
                        int code = pinPaiBean.getCode();
                        if (code == 0) {
                            Toast.makeText(mContext, pinPaiBean.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (code == 1) {
                            PinPaiBean.DataEntity data = pinPaiBean.getData();
                            final List<PinPaiBean.DataEntity.GoodSlidEntity> good_slid = data.getGood_slid();
                            imgList.clear();
                            for (int i = 0; i < good_slid.size(); i++) {
                                imgList.add(good_slid.get(i).getImage_url());
                            }
                            BannerUtils.startBanner(mBanner, imgList);
                            mBanner.setOnBannerListener(new OnBannerListener() {
                                @Override
                                public void OnBannerClick(int position) {
                                    if (good_slid.get(position).getType() == 1) {
                                        startActivity(new Intent(mContext, GoodDetailActivity.class));
                                    }
                                }
                            });
                            List<PinPaiBean.DataEntity.GoodsEntity> goods = data.getGoods();
                            RecyclerAdapter recyclerAdapter = new RecyclerAdapter(R.layout.item_shengxian, goods);
                            mRecyclerView.setAdapter(recyclerAdapter);
                            recyclerAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_car:
                String userid = SpUtils.getString(mContext, "userid", "");
                if (TextUtils.isEmpty(userid)){
                    startActivity(new Intent(mContext, LoginActivity.class));
                    mActivity.overridePendingTransition(R.anim.push_bottom_in,R.anim.push_bottom_out);
                    return;
                }
                startActivity(new Intent(mContext, ShopCartActivity.class));
                break;
        }
    }

    private class RecyclerAdapter extends BaseQuickAdapter<PinPaiBean.DataEntity.GoodsEntity, BaseViewHolder> {

        public RecyclerAdapter(@LayoutRes int layoutResId, @Nullable List<PinPaiBean.DataEntity.GoodsEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, PinPaiBean.DataEntity.GoodsEntity item) {
            ImageView iv_insertcar=helper.getView(R.id.iv_insertcar);
            iv_insertcar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String userid = SpUtils.getString(mContext, "userid", "");
                    if (TextUtils.isEmpty(userid)){
                        startActivity(new Intent(mContext, LoginActivity.class));
                        mActivity.overridePendingTransition(R.anim.push_bottom_in,R.anim.push_bottom_out);
                        return;
                    }
                    Toast.makeText(mContext, "加入购物车成功，请去购物车结算~", Toast.LENGTH_SHORT).show();
                }
            });
            ImageView iv_top=helper.getView(R.id.iv_top);
            int width = MyUtils.getScreenWidth(mContext) - MyUtils.dip2px(mContext, 50);
            ViewGroup.LayoutParams layoutParams = iv_top.getLayoutParams();
            layoutParams.height=width/2;
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
    }
}
