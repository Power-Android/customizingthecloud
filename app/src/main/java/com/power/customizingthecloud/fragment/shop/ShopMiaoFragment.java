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
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.MyApplication;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.activity.mine.WebDetailActivity;
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
    private int page = 1;
    private boolean isLoadMore;
    @BindView(R.id.springview)
    SpringView mSpringview;
    private List<ShopMiaoBean.DataEntity.GoodsEntity> mGoods;
    private MiaoShaAdapter mMiaoShaAdapter;

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
        initData();
        initListener();
    }

    private void initListener() {
        mSpringview.setHeader(new DefaultHeader(mContext));
        mSpringview.setFooter(new DefaultFooter(mContext));
        mSpringview.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                isLoadMore = false;
                page = 1;
                initData();
                mSpringview.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                isLoadMore = true;
                page++;
                initData();
                mSpringview.onFinishFreshAndLoad();
            }
        });
    }

    private void initData() {
        HttpParams params = new HttpParams();
        params.put("page", page);
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
                            if (good_slid != null && good_slid.size() > 0) {
                                imgList.clear();
                                for (int i = 0; i < good_slid.size(); i++) {
                                    imgList.add(good_slid.get(i).getImage_url());
                                }
                                BannerUtils.startBanner(mBanner, imgList);
                                mBanner.setOnBannerListener(new OnBannerListener() {
                                    @Override
                                    public void OnBannerClick(int position) {
                                        String targe_url = good_slid.get(position).getTarge_url();
                                        try {
                                            Integer.parseInt(targe_url);
                                            Intent intent = new Intent(mContext, GoodDetailActivity.class);
                                            intent.putExtra("id", good_slid.get(position).getTarge_url());
                                            startActivity(intent);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                            Intent intent = new Intent(mContext, WebDetailActivity.class);
                                            intent.putExtra("url", good_slid.get(position).getTarge_url());
                                            startActivity(intent);
                                        }
                                    }
                                });
                            }
                            if (!isLoadMore) {
                                mGoods = data.getGoods();
                                mMiaoShaAdapter = new MiaoShaAdapter(R.layout.item_home_miaosha, mGoods);
                                mRecyclerMiao.setAdapter(mMiaoShaAdapter);
                            } else {
                                if (data.getGoods() != null && data.getGoods().size() > 0) {
                                    mGoods.addAll(data.getGoods());
                                    mMiaoShaAdapter.notifyDataSetChanged();
                                } else {
                                    Toast.makeText(mContext, "没有更多了~", Toast.LENGTH_SHORT).show();
                                    page--;
                                }
                            }
                            mMiaoShaAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent = new Intent(mContext, MiaoShaDetailActivity.class);
                                    intent.putExtra("id", mGoods.get(position).getId() + "");
                                    startActivity(intent);
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
            long time = item.getSeckill_end_time() * 1000L - System.currentTimeMillis();
            cv_countdownView.start(time); // Millisecond
            ImageView iv_img = helper.getView(R.id.iv_image);
            Glide.with(MyApplication.getGloableContext()).load(item.getImage()).into(iv_img);
            helper.setText(R.id.tv_title, item.getName())
                    .setText(R.id.tv_unit, item.getUnit())
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
