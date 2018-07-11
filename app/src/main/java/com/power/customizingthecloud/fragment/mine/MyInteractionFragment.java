package com.power.customizingthecloud.fragment.mine;

import android.content.Intent;
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

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseFragment;
import com.power.customizingthecloud.bean.InteractionBean;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.fragment.market.MyDongTaiActivity;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.Urls;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/1/26.
 */

public class MyInteractionFragment extends BaseFragment implements BaseQuickAdapter.OnItemChildClickListener {
    @BindView(R.id.pinglun_tv)
    TextView pinglunTv;
    @BindView(R.id.huifu_tv)
    TextView huifuTv;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;
    private List<InteractionBean.DataBean> list = new ArrayList<>();

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(mContext, R.layout.fragment_interraction, null);
        unbinder = ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setNestedScrollingEnabled(false);
        initData();
        return view;
    }

    private void initData() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));
        HttpParams params = new HttpParams();
        params.put("after", "");
        OkGo.<InteractionBean>get(Urls.BASEURL + "api/v2/user/my-comment")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<InteractionBean>(mActivity, InteractionBean.class) {
                    @Override
                    public void onSuccess(Response<InteractionBean> response) {
                        InteractionBean body = response.body();
                        if (body.getCode() == 1) {
                            list = body.getData();
                            InteractionAdapter adapter = new InteractionAdapter(R.layout.item_interacation, list);
                            recyclerView.setAdapter(adapter);
                            adapter.setOnItemChildClickListener(MyInteractionFragment.this);
                        }
                    }
                });

    }

    private void initData2() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));
        HttpParams params = new HttpParams();
        params.put("after", "");
        OkGo.<InteractionBean>get(Urls.BASEURL + "api/v2/user/my-reply")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<InteractionBean>(mActivity, InteractionBean.class) {
                    @Override
                    public void onSuccess(Response<InteractionBean> response) {
                        InteractionBean body = response.body();
                        if (body.getCode() == 1) {
                            list = body.getData();
                            InteractionAdapter adapter = new InteractionAdapter(R.layout.item_interacation, list);
                            recyclerView.setAdapter(adapter);
                            adapter.setOnItemChildClickListener(MyInteractionFragment.this);
                        }
                    }
                });

    }

    @Override
    protected void initLazyData() {

    }

    @OnClick(R.id.pinglun_tv)
    public void pinglun() {
        pinglunTv.setTextColor(mContext.getResources().getColor(R.color.green));
        huifuTv.setTextColor(mContext.getResources().getColor(R.color.text_black));
        initData();
    }

    @OnClick(R.id.huifu_tv)
    public void huifu() {
        huifuTv.setTextColor(mContext.getResources().getColor(R.color.green));
        pinglunTv.setTextColor(mContext.getResources().getColor(R.color.text_black));
        initData2();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        startActivity(new Intent(mContext, MyDongTaiActivity.class));
    }

    private class InteractionAdapter extends BaseQuickAdapter<InteractionBean.DataBean, BaseViewHolder> {

        public InteractionAdapter(@LayoutRes int layoutResId, @Nullable List<InteractionBean.DataBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, InteractionBean.DataBean item) {
            helper.setText(R.id.item_name_tv, item.getUser_name())
                    .setText(R.id.item_content_tv, item.getBody())
                    .addOnClickListener(R.id.item_content_tv);
            ImageView imageView = helper.getView(R.id.item_pic_iv);
            ImageView faceiV = helper.getView(R.id.item_face_iv);
            Glide.with(mActivity).load(item.getUser_avatar()).into(faceiV);
            if (item.getImage() != null || item.getImage() != "") {
                imageView.setVisibility(View.VISIBLE);
                Glide.with(mActivity).load(item.getImage()).into(imageView);
            } else {
                imageView.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
