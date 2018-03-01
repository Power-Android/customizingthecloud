package com.power.customizingthecloud.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.RengyangDetailBean;
import com.power.customizingthecloud.fragment.home.jiankong.JianKongActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RengyangDetail1Activity extends BaseActivity {

    @BindView(R.id.title_back_iv) ImageView titleBackIv;
    @BindView(R.id.title_content_tv) TextView titleContentTv;
    @BindView(R.id.tv_jiankong) TextView tvJiankong;
    @BindView(R.id.pic_iv) ImageView picIv;
    @BindView(R.id.title_tv) TextView titleTv;
    @BindView(R.id.chengben_tv) TextView chengbenTv;
    @BindView(R.id.num_tv) TextView numTv;
    @BindView(R.id.check_hetong_tv) TextView checkHetongTv;
    @BindView(R.id.name_tv) TextView nameTv;
    @BindView(R.id.chandi_tv) TextView chandiTv;
    @BindView(R.id.chusheng_date_tv) TextView chushengDateTv;
    @BindView(R.id.renyang_date_tv) TextView renyangDateTv;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rengyang_detail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("认养详情");

        recyclerView.setLayoutManager(new GridLayoutManager(mContext,3));
        recyclerView.setNestedScrollingEnabled(false);

        List<RengyangDetailBean> list = new ArrayList<>();
        RengyangDetailBean bean = new RengyangDetailBean();
        bean.setPath("http://img1.imgtn.bdimg.com/it/u=950771854,530317119&fm=27&gp=0.jpg");
        list.add(bean);
        RengyangDetailBean bean1 = new RengyangDetailBean();
        bean1.setPath("http://img2.imgtn.bdimg.com/it/u=2557022909,3736713361&fm=27&gp=0.jpg");
        list.add(bean1);
        RengyangDetailBean bean2 = new RengyangDetailBean();
        bean2.setPath("http://img2.imgtn.bdimg.com/it/u=1830359176,654163576&fm=200&gp=0.jpg");
        list.add(bean2);
        RengyangDetailAdapter adapter = new RengyangDetailAdapter(R.layout.item_renyang_detail,list);
        recyclerView.setAdapter(adapter);
    }

    private class RengyangDetailAdapter extends BaseQuickAdapter<RengyangDetailBean,BaseViewHolder>{

        public RengyangDetailAdapter(@LayoutRes int layoutResId, @Nullable List<RengyangDetailBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, RengyangDetailBean item) {
            ImageView faceIv = helper.getView(R.id.item_face_iv);
            Glide.with(mContext).load(item.getPath()).into(faceIv);
        }
    }

    @OnClick({R.id.title_back_iv, R.id.tv_jiankong, R.id.check_hetong_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.tv_jiankong:
                startActivity(new Intent(mContext, JianKongActivity.class));
                break;
            case R.id.check_hetong_tv:
                startActivity(new Intent(mContext,RengYangHeTongActivity.class));
                break;
        }
    }
}
