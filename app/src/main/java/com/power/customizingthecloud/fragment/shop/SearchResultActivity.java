package com.power.customizingthecloud.fragment.shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
import com.power.customizingthecloud.activity.mine.ShopCartActivity;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.fragment.home.GoodDetailActivity;
import com.power.customizingthecloud.fragment.shop.bean.GoodListBean;
import com.power.customizingthecloud.login.LoginActivity;
import com.power.customizingthecloud.utils.CommonUtils;
import com.power.customizingthecloud.utils.MyUtils;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.Urls;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchResultActivity extends BaseActivity implements View.OnClickListener {
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
    @BindView(R.id.title_shopcar_iv)
    ImageView mTitleShopcarIv;
    @BindView(R.id.title_content_right_tv)
    TextView mTitleContentRightTv;
    @BindView(R.id.recycler_shop)
    RecyclerView mRecyclerShop;
    private boolean isLoadMore;
    @BindView(R.id.springview)
    SpringView mSpringview;
    private List<GoodListBean.DataEntity> mData;
    private ShopAdapter mShopAdapter;
    private String after;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);
        ButterKnife.bind(this);
        mTitleBackIv.setVisibility(View.VISIBLE);
        mTitleBackIv.setOnClickListener(this);
        mTitleContentTv.setText("商品列表");
        mTitleShopcarIv.setVisibility(View.VISIBLE);
        mTitleShopcarIv.setOnClickListener(this);
        mRecyclerShop.setLayoutManager(new GridLayoutManager(this, 2));
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
                after = "";
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
        String keyword = getIntent().getStringExtra("keyword");
        HttpParams params = new HttpParams();
        params.put("after", after);
        params.put("limit", "10");
        params.put("keyword", keyword);
        OkGo.<GoodListBean>get(Urls.BASEURL + "api/v2/good/search")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<GoodListBean>(SearchResultActivity.this, GoodListBean.class) {
                    @Override
                    public void onSuccess(Response<GoodListBean> response) {
                        GoodListBean goodListBean = response.body();
                        int code = goodListBean.getCode();
                        if (code == 0) {
                            Toast.makeText(mContext, goodListBean.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (code == 1) {
                            if (!isLoadMore) {
                                mData = goodListBean.getData();
                                mShopAdapter = new ShopAdapter(R.layout.item_shengxian, mData);
                                mRecyclerShop.setAdapter(mShopAdapter);
                            } else {
                                if (goodListBean.getData() != null && goodListBean.getData().size() > 0) {
                                    mData.addAll(goodListBean.getData());
                                    mShopAdapter.notifyDataSetChanged();
                                } else {
                                    Toast.makeText(mContext, "没有更多了~", Toast.LENGTH_SHORT).show();
                                }
                            }
                            mShopAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent = new Intent(SearchResultActivity.this, GoodDetailActivity.class);
                                    intent.putExtra("id", mData.get(position).getId() + "");
                                    startActivity(intent);
                                }
                            });
                        }
                    }
                });
    }

    private class ShopAdapter extends BaseQuickAdapter<GoodListBean.DataEntity, BaseViewHolder> {

        public ShopAdapter(@LayoutRes int layoutResId, @Nullable List<GoodListBean.DataEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final GoodListBean.DataEntity item) {
            after = item.getId() + "";
            ImageView iv_insertcar = helper.getView(R.id.iv_insertcar);
            iv_insertcar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String userid = SpUtils.getString(mContext, "userid", "");
                    if (TextUtils.isEmpty(userid)) {
                        startActivity(new Intent(mContext, LoginActivity.class));
                        overridePendingTransition(R.anim.push_bottom_in, R.anim.push_bottom_out);
                        return;
                    }
                    CommonUtils.insertCar(SearchResultActivity.this, item.getId() + "", item.getGood_type());
                }
            });
            ImageView iv_top = helper.getView(R.id.iv_top);
            int width = MyUtils.getScreenWidth(mContext) - MyUtils.dip2px(mContext, 50);
            ViewGroup.LayoutParams layoutParams = iv_top.getLayoutParams();
            layoutParams.height = width / 2;
            iv_top.setLayoutParams(layoutParams);
            Glide.with(MyApplication.getGloableContext()).load(item.getImage()).into(iv_top);
            helper.setText(R.id.tv_name, item.getClass_name())
                    .setText(R.id.tv_price, item.getPrice());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.title_shopcar_iv:
                String userid = SpUtils.getString(mContext, "userid", "");
                if (TextUtils.isEmpty(userid)) {
                    startActivity(new Intent(mContext, LoginActivity.class));
                    overridePendingTransition(R.anim.push_bottom_in, R.anim.push_bottom_out);
                    return;
                }
                startActivity(new Intent(this, ShopCartActivity.class));
                break;
        }
    }
}
