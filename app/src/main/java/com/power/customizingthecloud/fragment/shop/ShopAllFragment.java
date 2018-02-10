package com.power.customizingthecloud.fragment.shop;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.activity.mine.LatestActivity;
import com.power.customizingthecloud.base.BaseFragment;
import com.power.customizingthecloud.bean.EventBean;
import com.power.customizingthecloud.fragment.home.GoodDetailActivity;
import com.power.customizingthecloud.fragment.home.GoodListActivity;
import com.power.customizingthecloud.fragment.home.MiaoShaDetailActivity;
import com.power.customizingthecloud.fragment.home.top.KaiDianActivity;
import com.power.customizingthecloud.fragment.home.top.MiaoShaActivity;
import com.power.customizingthecloud.utils.BannerUtils;
import com.power.customizingthecloud.view.BaseDialog;
import com.youth.banner.Banner;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.iwgang.countdownview.CountdownView;

/**
 * Created by Administrator on 2018/2/2.
 */

public class ShopAllFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.cv_countdownView)
    CountdownView mCvCountdownView;
    @BindView(R.id.tv_xianlianggou)
    TextView mTvXianlianggou;
    @BindView(R.id.tv_saishangjishi)
    TextView mTvSaishangjishi;
    @BindView(R.id.tv_kaidian)
    TextView mTvKaidian;
    @BindView(R.id.tv_meichu)
    TextView mTvMeichu;
    @BindView(R.id.tv_newactivity)
    TextView mTvNewactivity;
    @BindView(R.id.iv_newproduct_more)
    ImageView mIvNewproductMore;
    @BindView(R.id.recycler_new)
    RecyclerView mRecyclerNew;
    @BindView(R.id.iv_hotproduct_more)
    ImageView mIvHotproductMore;
    @BindView(R.id.recycler_hot)
    RecyclerView mRecyclerHot;
    @BindView(R.id.iv_miaosha_more)
    ImageView mIvMiaoshaMore;
    @BindView(R.id.recycler_miaosha)
    RecyclerView mRecyclerMiaosha;
    @BindView(R.id.iv_quan_more)
    ImageView mIvQuanMore;
    @BindView(R.id.recycler_quan)
    RecyclerView mRecyclerQuan;
    @BindView(R.id.ll_miaosha)
    LinearLayout ll_miaosha;
    Unbinder unbinder;
    private HotProductAdapter mHotProductAdapter;
    private NewProductAdapter mNewProductAdapter;
    private MiaoshaAdapter mMiaoshaAdapter;
    private QuanAdapter mQuanAdapter;
    private BaseDialog mDialog;
    private BaseDialog.Builder mBuilder;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopall, null);
        unbinder = ButterKnife.bind(this, view);
        mIvNewproductMore.setOnClickListener(this);
        mIvHotproductMore.setOnClickListener(this);
        mIvMiaoshaMore.setOnClickListener(this);
        ll_miaosha.setOnClickListener(this);
        mIvQuanMore.setOnClickListener(this);
        mTvSaishangjishi.setOnClickListener(this);
        mTvKaidian.setOnClickListener(this);
        mTvMeichu.setOnClickListener(this);
        mTvNewactivity.setOnClickListener(this);
        return view;
    }

    @Override
    protected void initLazyData() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        BannerUtils.startBanner(mBanner, new ArrayList<String>());
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        mRecyclerNew.setNestedScrollingEnabled(false);
        mRecyclerNew.setLayoutManager(new GridLayoutManager(mContext, 2));
        mNewProductAdapter = new NewProductAdapter(R.layout.item_product, list);
        mRecyclerNew.setAdapter(mNewProductAdapter);
        mNewProductAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(mContext, GoodDetailActivity.class));
            }
        });
        mRecyclerHot.setNestedScrollingEnabled(false);
        mRecyclerHot.setLayoutManager(new GridLayoutManager(mContext, 2));
        mHotProductAdapter = new HotProductAdapter(R.layout.item_product, list);
        mRecyclerHot.setAdapter(mHotProductAdapter);
        mHotProductAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(mContext, GoodDetailActivity.class));
            }
        });
        mRecyclerMiaosha.setNestedScrollingEnabled(false);
        mRecyclerMiaosha.setLayoutManager(new LinearLayoutManager(mContext));
        mMiaoshaAdapter = new MiaoshaAdapter(R.layout.item_home_miaosha, list);
        mRecyclerMiaosha.setAdapter(mMiaoshaAdapter);
        mMiaoshaAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(mContext, MiaoShaDetailActivity.class));
            }
        });
        mRecyclerQuan.setNestedScrollingEnabled(false);
        mRecyclerQuan.setLayoutManager(new LinearLayoutManager(mContext));
        mQuanAdapter = new QuanAdapter(R.layout.item_daijinquan, list);
        mRecyclerQuan.setAdapter(mQuanAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_newproduct_more:
                startActivity(new Intent(mContext, GoodListActivity.class));
                break;
            case R.id.iv_hotproduct_more:
                startActivity(new Intent(mContext, GoodListActivity.class));
                break;
            case R.id.iv_miaosha_more:
                startActivity(new Intent(mContext, MiaoShaActivity.class));
                break;
            case R.id.ll_miaosha:
                startActivity(new Intent(mContext, MiaoShaDetailActivity.class));
                break;
            case R.id.iv_quan_more:
                startActivity(new Intent(mContext, QuanListActivity.class));
                break;
            case R.id.tv_saishangjishi:
                EventBus.getDefault().postSticky(new EventBean("checkganji"));
                break;
            case R.id.tv_kaidian:
                startActivity(new Intent(mContext, KaiDianActivity.class));
                break;
            case R.id.tv_meichu:
                startActivity(new Intent(mContext, VideoListActivity.class));
                break;
            case R.id.tv_newactivity:
                startActivity(new Intent(mContext, LatestActivity.class));
                break;
        }
    }

    private class NewProductAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public NewProductAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            ImageView iv_insertcar=helper.getView(R.id.iv_insertcar);
            iv_insertcar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "加入购物车成功，请去购物车结算~", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private class HotProductAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public HotProductAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            ImageView iv_insertcar=helper.getView(R.id.iv_insertcar);
            iv_insertcar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "加入购物车成功，请去购物车结算~", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private class MiaoshaAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public MiaoshaAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            TextView tv_yuanjia = helper.getView(R.id.tv_yuanjia);
            //添加删除线
            tv_yuanjia.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }

    private class QuanAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public QuanAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            TextView tv_lingqu = helper.getView(R.id.tv_lingqu);
            tv_lingqu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showLingquDialog();
                }
            });
        }
    }

    private void showLingquDialog() {
        mBuilder = new BaseDialog.Builder(mContext);
        mDialog = mBuilder.setViewId(R.layout.dialog_getquan)
                //设置dialogpadding
                .setPaddingdp(0, 0, 0, 0)
                //设置显示位置
                .setGravity(Gravity.CENTER)
                //设置动画
                .setAnimation(R.style.Alpah_aniamtion)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(true)
                //设置监听事件
                .builder();
        mDialog.show();
        mDialog.getView(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        mDialog.getView(R.id.tv_yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
