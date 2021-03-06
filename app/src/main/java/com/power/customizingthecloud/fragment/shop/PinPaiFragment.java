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
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.MyApplication;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.activity.mine.LatestDetailActivity;
import com.power.customizingthecloud.activity.mine.ShopCartActivity;
import com.power.customizingthecloud.activity.mine.WebDetailActivity;
import com.power.customizingthecloud.base.BaseFragment;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.fragment.home.GoodDetailActivity;
import com.power.customizingthecloud.fragment.home.MiaoShaDetailActivity;
import com.power.customizingthecloud.fragment.home.jiankong.JianKongActivity;
import com.power.customizingthecloud.fragment.home.renyang.RenYangDetailActivity;
import com.power.customizingthecloud.fragment.shop.bean.PinPaiBean;
import com.power.customizingthecloud.login.LoginActivity;
import com.power.customizingthecloud.utils.BannerUtils;
import com.power.customizingthecloud.utils.CommonUtils;
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
    @BindView(R.id.springview)
    SpringView mSpringview;
    private String after = "";
    private boolean isLoadMore;
    private List<PinPaiBean.DataEntity.GoodsEntity> mGoods;
    private RecyclerAdapter mRecyclerAdapter;

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
                initData();
                mSpringview.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                isLoadMore = true;
                initData();
                mSpringview.onFinishFreshAndLoad();
            }
        });
    }

    private void initData() {
        Bundle bundle = getArguments();
        String id = bundle.getString("id");
        HttpParams params = new HttpParams();
        params.put("id", id);
        if (isLoadMore) {
            params.put("after", after);
        }
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
                            if (good_slid != null && good_slid.size() > 0) {
                                imgList.clear();
                                for (int i = 0; i < good_slid.size(); i++) {
                                    imgList.add(good_slid.get(i).getImage_url());
                                }
                                BannerUtils.startBanner(mBanner, imgList);
                                mBanner.setOnBannerListener(new OnBannerListener() {
                                    @Override
                                    public void OnBannerClick(int position) {
//                                        String targe_url = good_slid.get(position).getTarge_url();
//                                        try {
//                                            Integer.parseInt(targe_url);
//                                            Intent intent = new Intent(mContext, GoodDetailActivity.class);
//                                            intent.putExtra("id", good_slid.get(position).getTarge_url());
//                                            startActivity(intent);
//                                        } catch (Exception e) {
//                                            e.printStackTrace();
//                                            Intent intent = new Intent(mContext, WebDetailActivity.class);
//                                            intent.putExtra("url", good_slid.get(position).getTarge_url());
//                                            startActivity(intent);
//                                        }
                                        switch (good_slid.get(position).getTarge_type()) {
                                            case 1:
                                                Intent intent = new Intent(mContext, GoodDetailActivity.class);
                                                intent.putExtra("id", good_slid.get(position).getTarge_url());
                                                startActivity(intent);
                                                break;
                                            case 2:
                                                Intent intent2 = new Intent(mContext, MiaoShaDetailActivity.class);
                                                intent2.putExtra("id", good_slid.get(position).getTarge_url());
                                                startActivity(intent2);
                                                break;
                                            case 3:
                                                Intent intent3 = new Intent(mContext, JianKongActivity.class);
                                                try {
                                                    int i = Integer.parseInt(good_slid.get(position).getTarge_url());
                                                    intent3.putExtra("class_id", i);
                                                    startActivity(intent3);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                                break;
                                            case 4:
                                                int state = good_slid.get(position).getState();
                                                Intent intent4 = new Intent(mContext, RenYangDetailActivity.class);
                                                intent4.putExtra("state", state);
                                                intent4.putExtra("id", good_slid.get(position).getTarge_url());
                                                startActivity(intent4);
                                                break;
                                            case 5:
                                                Intent intent5 = new Intent(mContext, LatestDetailActivity.class);
                                                intent5.putExtra("id", good_slid.get(position).getTarge_url());
                                                startActivity(intent5);
                                                break;
                                            default:
                                                Intent intent6=new Intent(mContext,WebDetailActivity.class);
                                                intent6.putExtra("url", good_slid.get(position).getTarge_url());
                                                startActivity(intent6);
                                                break;
                                        }
                                    }
                                });
                            }
                            if (!isLoadMore) {
                                mGoods = data.getGoods();
                                mRecyclerAdapter = new RecyclerAdapter(R.layout.item_shengxian, mGoods);
                                mRecyclerView.setAdapter(mRecyclerAdapter);
                            } else {
                                if (data.getGoods() != null && data.getGoods().size() > 0) {
                                    mGoods.addAll(data.getGoods());
                                    mRecyclerAdapter.notifyDataSetChanged();
                                } else {
                                    Toast.makeText(mContext, "没有更多了~", Toast.LENGTH_SHORT).show();
                                }
                            }
                            mRecyclerAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent = new Intent(mContext, GoodDetailActivity.class);
                                    intent.putExtra("id", mGoods.get(position).getId() + "");
                                    startActivity(intent);
                                }
                            });

                        }
                    }
                });
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
        }
    }

    private class RecyclerAdapter extends BaseQuickAdapter<PinPaiBean.DataEntity.GoodsEntity, BaseViewHolder> {

        public RecyclerAdapter(@LayoutRes int layoutResId, @Nullable List<PinPaiBean.DataEntity.GoodsEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final PinPaiBean.DataEntity.GoodsEntity item) {
            after = item.getId() + "";
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
                    //                    Toast.makeText(mContext, "加入购物车成功，请去购物车结算~", Toast.LENGTH_SHORT).show();
                    CommonUtils.insertCar(mActivity, item.getId() + "", item.getGood_type());
                }
            });
            ImageView iv_top = helper.getView(R.id.iv_top);
            int width = MyUtils.getScreenWidth(mContext) - MyUtils.dip2px(mContext, 50);
            ViewGroup.LayoutParams layoutParams = iv_top.getLayoutParams();
            layoutParams.height = width / 2;
            iv_top.setLayoutParams(layoutParams);
            Glide.with(MyApplication.getGloableContext()).load(item.getImage()).into(iv_top);
            helper.setText(R.id.tv_name, item.getName())
                    .setText(R.id.tv_price, "¥" + item.getPrice());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
