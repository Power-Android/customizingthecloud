package com.power.customizingthecloud.fragment.home;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseFragment;
import com.power.customizingthecloud.utils.BannerUtils;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.power.customizingthecloud.R.id.tv_toutiao;

/**
 * Created by Administrator on 2018/1/19.
 * 首页
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener {

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
    @BindView(R.id.recycler_top)
    RecyclerView mRecyclerTop;
    @BindView(tv_toutiao)
    TextView mTvToutiao;
    @BindView(R.id.tv_nianshouyi)
    TextView mTvNianshouyi;
    @BindView(R.id.tv_yangzhichengben)
    TextView mTvYangzhichengben;
    @BindView(R.id.tv_touzizhouqi)
    TextView mTvTouzizhouqi;
    @BindView(R.id.tv_shengyu)
    TextView mTvShengyu;
    @BindView(R.id.tv_qiang)
    TextView mTvQiang;
    @BindView(R.id.recycler_middle)
    RecyclerView mRecyclerMiddle;
    @BindView(R.id.iv_jiankong_more)
    ImageView mIvJiankongMore;
    @BindView(R.id.recycler_jiankong)
    RecyclerView mRecyclerJiankong;
    @BindView(R.id.iv_good_more)
    ImageView mIvGoodMore;
    @BindView(R.id.recycler_good)
    RecyclerView mRecyclerGood;
    @BindView(R.id.iv_miaosha_more)
    ImageView mIvMiaoshaMore;
    @BindView(R.id.recycler_miaosha)
    RecyclerView mRecyclerMiaosha;
    Unbinder unbinder;
    private List<String> topStringList = new ArrayList<>();
    private List<Integer> topPicList = new ArrayList<>();
    private TopAdapter mTopAdapter;
    private MiddleAdapter mMiddleAdapter;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerTop.setNestedScrollingEnabled(false);
        mRecyclerTop.setLayoutManager(new GridLayoutManager(mContext, 5));
        if (topStringList.size() == 0) {
            topStringList.add("秒杀");
            topStringList.add("财富中心");
            topStringList.add("牧场");
            topStringList.add("生鲜汇");
            topStringList.add("餐位预定");
            topStringList.add("我要开店");
            topStringList.add("媒体资讯");
            topStringList.add("新手指引");
            topStringList.add("最新活动");
            topStringList.add("我的分享");
        }
        if (topPicList.size() == 0) {
            topPicList.add(R.drawable.miaosha);
            topPicList.add(R.drawable.caifuzhongxin);
            topPicList.add(R.drawable.muchang);
            topPicList.add(R.drawable.shengxianhui);
            topPicList.add(R.drawable.canweiyuding);
            topPicList.add(R.drawable.kaidian);
            topPicList.add(R.drawable.meitizixun);
            topPicList.add(R.drawable.xinshouzhiyin);
            topPicList.add(R.drawable.newactivity);
            topPicList.add(R.drawable.myshare);
        }
        BannerUtils.startBanner(mBanner, new ArrayList<String>());
        if (mTopAdapter == null) {
            mTopAdapter = new TopAdapter(R.layout.item_hometop, topStringList);
            mRecyclerTop.setAdapter(mTopAdapter);
        }
        mTvToutiao.requestFocus();//有趣，在布局文件中也设置了获取了焦点，但是没有，在代码中加上就好使了
        mTitleContentTv.setText("养驴啦");
        mTitleMessageIv.setVisibility(View.VISIBLE);
        mTitleMessageIv.setOnClickListener(this);
        mTitleSignInIv.setVisibility(View.VISIBLE);
        mTitleSignInIv.setOnClickListener(this);
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        mMiddleAdapter = new MiddleAdapter(R.layout.home_middle, list);
        mRecyclerMiddle.setAdapter(mMiddleAdapter);
    }

    @Override
    protected void initLazyData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_message_iv://消息

                break;
            case R.id.title_sign_in_iv://签到

                break;

        }
    }

    private class TopAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public TopAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            ImageView iv_top = helper.getView(R.id.iv_top);
            iv_top.setImageResource(topPicList.get(helper.getLayoutPosition()));
            helper.setText(R.id.tv_top, item);
        }
    }

    private class MiddleAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public MiddleAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
