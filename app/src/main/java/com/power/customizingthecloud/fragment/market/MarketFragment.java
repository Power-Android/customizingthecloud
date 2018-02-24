package com.power.customizingthecloud.fragment.market;

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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseFragment;
import com.power.customizingthecloud.bean.NineGridTestModel;
import com.power.customizingthecloud.view.CircleImageView;
import com.power.customizingthecloud.view.NineGridTestLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/1/19.
 * 赶集啦
 */

public class MarketFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.title_message_iv)
    ImageView mTitleMessageIv;
    @BindView(R.id.title_back_iv)
    ImageView mTitleBackIv;
    @BindView(R.id.title_list_iv)
    ImageView mTitleListIv;
    @BindView(R.id.title_content_tv)
    TextView mTitleContentTv;
    @BindView(R.id.title_sign_in_iv)
    ImageView mTitleSignInIv;
    @BindView(R.id.title_setting_iv)
    ImageView mTitleSettingIv;
    @BindView(R.id.title_qrcode_iv)
    ImageView mTitleQrcodeIv;
    @BindView(R.id.title_shopcar_iv)
    ImageView mTitleShopcarIv;
    @BindView(R.id.title_share_iv)
    ImageView mTitleShareIv;
    @BindView(R.id.title_search_iv)
    ImageView mTitleSearchIv;
    @BindView(R.id.title_jia_iv)
    ImageView mTitleJiaIv;
    @BindView(R.id.title_content_right_tv)
    TextView mTitleContentRightTv;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.iv_head)
    CircleImageView mIvHead;
    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    Unbinder unbinder;
    private List<NineGridTestModel> mList = new ArrayList<>();
    private String[] mUrls = new String[]{"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=202447557,2967022603&fm=27&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=104961686,3757525983&fm=27&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=569334953,1638673400&fm=27&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2043305675,3979419376&fm=200&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=266745161,658804068&fm=27&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=222615259,2947254622&fm=27&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=950771854,530317119&fm=27&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=2557022909,3736713361&fm=27&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=1830359176,654163576&fm=200&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=4193964417,1586871857&fm=27&gp=0.jpg",
            };

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ganji, null);
        unbinder = ButterKnife.bind(this, view);
        mTitleMessageIv.setVisibility(View.VISIBLE);
        mTitleMessageIv.setOnClickListener(this);
        mTitleContentTv.setText("我的圈子");
        mTitleContentRightTv.setText("发动态");
        mTitleContentRightTv.setOnClickListener(this);
        return view;
    }

    @Override
    protected void initLazyData() {

    }

    private void initData() {
        NineGridTestModel model1 = new NineGridTestModel();
        model1.urlList.add(mUrls[0]);
        mList.add(model1);

        NineGridTestModel model2 = new NineGridTestModel();
        model2.urlList.add(mUrls[0]);
        model2.urlList.add(mUrls[1]);
        mList.add(model2);

//        NineGridTestModel model4 = new NineGridTestModel();
//        for (int i = 0; i < mUrls.length; i++) {
//            model4.urlList.add(mUrls[i]);
//        }
//        model4.isShowAll = false;
//        mList.add(model4);
//
//        NineGridTestModel model5 = new NineGridTestModel();
//        for (int i = 0; i < mUrls.length; i++) {
//            model5.urlList.add(mUrls[i]);
//        }
//        model5.isShowAll = true;//显示全部图片
//        mList.add(model5);

        NineGridTestModel model6 = new NineGridTestModel();
        for (int i = 0; i < 9; i++) {
            model6.urlList.add(mUrls[i]);
        }
        mList.add(model6);

        NineGridTestModel model7 = new NineGridTestModel();
        for (int i = 3; i < 7; i++) {
            model7.urlList.add(mUrls[i]);
        }
        mList.add(model7);

        NineGridTestModel model8 = new NineGridTestModel();
        for (int i = 3; i < 6; i++) {
            model8.urlList.add(mUrls[i]);
        }
        mList.add(model8);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        mRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        mRecycler.setNestedScrollingEnabled(false);
        MyAdapter myAdapter = new MyAdapter(R.layout.item_circle_friend, mList);
        mRecycler.setAdapter(myAdapter);
    }

    private class MyAdapter extends BaseQuickAdapter<NineGridTestModel, BaseViewHolder> {

        public MyAdapter(@LayoutRes int layoutResId, @Nullable List<NineGridTestModel> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, NineGridTestModel item) {
            NineGridTestLayout nineGridlayout = helper.getView(R.id.nine_gridlayout);
            nineGridlayout.setIsShowAll(mList.get(helper.getAdapterPosition()).isShowAll);
            nineGridlayout.setUrlList(mList.get(helper.getAdapterPosition()).urlList);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_message_iv:
                break;
            case R.id.title_content_right_tv:
                break;
        }
    }
}
