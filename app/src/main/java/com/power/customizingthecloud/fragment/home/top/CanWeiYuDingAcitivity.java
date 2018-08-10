package com.power.customizingthecloud.fragment.home.top;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.fragment.home.ShopDetailActivity;
import com.power.customizingthecloud.fragment.home.bean.CanWeiListBean;
import com.power.customizingthecloud.utils.BannerUtils;
import com.power.customizingthecloud.utils.MyUtils;
import com.power.customizingthecloud.utils.Urls;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CanWeiYuDingAcitivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_message_iv)
    ImageView mTitleMessageIv;
    @BindView(R.id.title_back_iv)
    ImageView mTitleBackIv;
    @BindView(R.id.title_content_tv)
    TextView mTitleContentTv;
    @BindView(R.id.title_sign_in_iv)
    ImageView mTitleSignInIv;
    @BindView(R.id.title_setting_iv)
    ImageView mTitleSettingIv;
    @BindView(R.id.title_content_right_tv)
    TextView mTitleContentRightTv;
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.recycler_shop)
    RecyclerView mRecyclerShop;
    private ShopAdapter mShopAdapter;
    private List<String> imgList = new ArrayList<>();
    private List<CanWeiListBean.DataEntity.BusinessEntity> mBusiness;
    private int page = 1;
    private boolean isLoadMore;
    @BindView(R.id.springview)
    SpringView mSpringview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_wei_yu_ding_acitivity);
        ButterKnife.bind(this);
        mTitleBackIv.setVisibility(View.VISIBLE);
        mTitleBackIv.setOnClickListener(this);
        mTitleContentTv.setText("推荐商家");
        mRecyclerShop.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerShop.setNestedScrollingEnabled(false);
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
        OkGo.<CanWeiListBean>get(Urls.BASEURL + "api/v2/restaurant")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<CanWeiListBean>(this, CanWeiListBean.class) {
                    @Override
                    public void onSuccess(Response<CanWeiListBean> response) {
                        CanWeiListBean canWeiListBean = response.body();
                        int code = canWeiListBean.getCode();
                        if (code == 0) {
                            Toast.makeText(mContext, canWeiListBean.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (code == 1) {
                            CanWeiListBean.DataEntity data = canWeiListBean.getData();
                            final List<CanWeiListBean.DataEntity.SlidEntity> slid = data.getSlid();
                            if (slid != null && slid.size() > 0) {
                                imgList.clear();
                                for (int i = 0; i < slid.size(); i++) {
                                    String image_url = slid.get(i).getImage_url();
                                    imgList.add(image_url);
                                }
                                BannerUtils.startBanner(mBanner, imgList);
                                mBanner.setOnBannerListener(new OnBannerListener() {
                                    @Override
                                    public void OnBannerClick(int position) {
                                        if (slid.get(position).getType() == 1) {
                                            Intent intent = new Intent(CanWeiYuDingAcitivity.this, ShopDetailActivity.class);
                                            intent.putExtra("id", slid.get(position).getTarge_url());
                                            startActivity(intent);
                                        } else {

                                        }
                                    }
                                });
                            }
                            if (!isLoadMore) {
                                mBusiness = data.getBusiness();
                                mShopAdapter = new ShopAdapter(R.layout.item_canweiyuding, mBusiness);
                                mRecyclerShop.setAdapter(mShopAdapter);
                            } else {
                                if (data.getBusiness() != null && data.getBusiness().size() > 0) {
                                    mBusiness.addAll(data.getBusiness());
                                    mShopAdapter.notifyDataSetChanged();
                                } else {
                                    Toast.makeText(mContext, "没有更多了~", Toast.LENGTH_SHORT).show();
                                    page--;
                                }
                            }
                            mShopAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent = new Intent(CanWeiYuDingAcitivity.this, ShopDetailActivity.class);
                                    intent.putExtra("id", mBusiness.get(position).getId() + "");
                                    startActivity(intent);
                                }
                            });
                        }
                    }
                });
    }

    private class ShopAdapter extends BaseQuickAdapter<CanWeiListBean.DataEntity.BusinessEntity, BaseViewHolder> {

        public ShopAdapter(@LayoutRes int layoutResId, @Nullable List<CanWeiListBean.DataEntity.BusinessEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, CanWeiListBean.DataEntity.BusinessEntity item) {
            ImageView iv_top = helper.getView(R.id.iv_top);
            int width = MyUtils.getScreenWidth(mContext) - MyUtils.dip2px(mContext, 50);
            ViewGroup.LayoutParams layoutParams = iv_top.getLayoutParams();
            layoutParams.height = width / 2;
            iv_top.setLayoutParams(layoutParams);
            Glide.with(MyApplication.getGloableContext()).load(item.getImgurl()).into(iv_top);
            helper.setText(R.id.tv_name, item.getName())
                    .setText(R.id.tv_price, item.getEveryone())
                    .setText(R.id.tv_zuowei, item.getSeat_number());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
        }
    }
}
