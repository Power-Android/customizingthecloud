package com.power.customizingthecloud.fragment.shop;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.MyApplication;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseFragment;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.fragment.home.GoodDetailActivity;
import com.power.customizingthecloud.fragment.home.MiaoShaDetailActivity;
import com.power.customizingthecloud.fragment.shop.bean.ShopMiaoBean;
import com.power.customizingthecloud.utils.BannerUtils;
import com.power.customizingthecloud.utils.Urls;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.iwgang.countdownview.CountdownView;

/**
 * Created by Administrator on 2018/2/2.
 */

public class ShopMiaoFragment extends BaseFragment {
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.recycler_miao)
    RecyclerView mRecyclerMiao;
    Unbinder unbinder;
    private List<String> imgList = new ArrayList<>();

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopmiao, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initLazyData() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerMiao.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerMiao.setNestedScrollingEnabled(false);
        HttpParams params = new HttpParams();
        params.put("page", "1");
        params.put("limit", "10");
        OkGo.<ShopMiaoBean>get(Urls.BASEURL + "api/v2/good/seckill-good")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<ShopMiaoBean>(mActivity, ShopMiaoBean.class) {
                    @Override
                    public void onSuccess(Response<ShopMiaoBean> response) {
                        ShopMiaoBean miaoBean = response.body();
                        int code = miaoBean.getCode();
                        if (code == 0) {
                            Toast.makeText(mContext, miaoBean.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (code == 1) {
                            ShopMiaoBean.DataEntity data = miaoBean.getData();
                            final List<ShopMiaoBean.DataEntity.GoodSlidEntity> good_slid = data.getGood_slid();
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
                            List<ShopMiaoBean.DataEntity.GoodsEntity> goods = data.getGoods();
                            MiaoShaAdapter miaoShaAdapter = new MiaoShaAdapter(R.layout.item_home_miaosha, goods);
                            mRecyclerMiao.setAdapter(miaoShaAdapter);
                            miaoShaAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    startActivity(new Intent(mContext, MiaoShaDetailActivity.class));
                                }
                            });
                        }
                    }
                });
    }

    private class MiaoShaAdapter extends BaseQuickAdapter<ShopMiaoBean.DataEntity.GoodsEntity, BaseViewHolder> {

        public MiaoShaAdapter(@LayoutRes int layoutResId, @Nullable List<ShopMiaoBean.DataEntity.GoodsEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ShopMiaoBean.DataEntity.GoodsEntity item) {
            TextView tv_yuanjia = helper.getView(R.id.tv_yuanjia);
            tv_yuanjia.setText(item.getPrice());
            //添加删除线
            tv_yuanjia.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            CountdownView cv_countdownView = helper.getView(R.id.cv_countdownView);
            int time = item.getSeckill_end_time() - item.getSeckill_start_time();
            cv_countdownView.start(time); // Millisecond
            ImageView iv_img = helper.getView(R.id.iv_image);
            Glide.with(MyApplication.getGloableContext()).load(item.getImage()).into(iv_img);
            helper.setText(R.id.tv_title, item.getName())
                    .setText(R.id.tv_curprice, item.getSeckill_price())
                    .setText(R.id.tv_last_count, item.getSeckill_storage() + "");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
