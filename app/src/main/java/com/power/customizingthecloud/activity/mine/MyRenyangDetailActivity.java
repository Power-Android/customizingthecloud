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
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.ImgBean;
import com.power.customizingthecloud.bean.RengyangDetailBean;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.fragment.home.jiankong.JianKongActivity;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.Urls;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyRenyangDetailActivity extends BaseActivity {

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
        setContentView(R.layout.activity_myrenyang_detail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("认养详情");

        String id = getIntent().getStringExtra("id");

        recyclerView.setLayoutManager(new GridLayoutManager(mContext,3));
        recyclerView.setNestedScrollingEnabled(false);

        initData(id);


    }

    private void initData(String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));
        HttpParams params = new HttpParams();
        params.put("id",id);
        OkGo.<RengyangDetailBean>get(Urls.BASEURL + "api/v2/user/donkey_show")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<RengyangDetailBean>(this,RengyangDetailBean.class) {
                    @Override
                    public void onSuccess(Response<RengyangDetailBean> response) {
                        RengyangDetailBean body = response.body();
                        if (body.getCode() == 1){
                            RengyangDetailBean.DataBean data = body.getData();
                            Glide.with(mContext).load(data.getImage()).into(picIv);
                            titleTv.setText(data.getTitle());
                            chengbenTv.setText("养殖成本：￥" + data.getPrice());
                            nameTv.setText("名称：" + data.getTitle());
                            chandiTv.setText("产地：" + data.getPlace());
                            chushengDateTv.setText("出生日期：" + data.getBirth_date());
                            renyangDateTv.setText("认养时间：" + data.getPayment_time());
                            numTv.setText("X "+data.getAmount());
                            List<String> list = data.getDonkey_images();
                            List<ImgBean> imgBeanList = new ArrayList<>();
                            for (int i = 0; i < list.size(); i++) {
                                ImgBean bean = new ImgBean();
                                bean.setPath(list.get(i));
                                imgBeanList.add(bean);
                            }
                            RengyangDetailAdapter adapter = new RengyangDetailAdapter(R.layout.item_renyang_detail,imgBeanList);
                            recyclerView.setAdapter(adapter);
                        }
                    }
                });
    }

    private class RengyangDetailAdapter extends BaseQuickAdapter<ImgBean,BaseViewHolder>{

        public RengyangDetailAdapter(@LayoutRes int layoutResId, @Nullable List<ImgBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ImgBean item) {
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
