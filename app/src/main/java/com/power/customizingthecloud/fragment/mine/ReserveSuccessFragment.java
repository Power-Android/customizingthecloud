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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.activity.mine.ReserveDetailActivity;
import com.power.customizingthecloud.base.BaseFragment;
import com.power.customizingthecloud.bean.ReserveBean;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.TUtils;
import com.power.customizingthecloud.utils.Urls;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/2/5.
 */

public class ReserveSuccessFragment extends BaseFragment {
    @BindView(R.id.recycler)
    RecyclerView mRecyclerRenyang;
    Unbinder unbinder;
    private List<ReserveBean.DataBeanX.DataBean> list = new ArrayList<>();
    private String after;
    private boolean isLoadMore;
    @BindView(R.id.springview)
    SpringView mSpringview;
    private MyReserveAdapter adapter;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_renyang, null);
        unbinder = ButterKnife.bind(this, view);
        mRecyclerRenyang.setLayoutManager(new LinearLayoutManager(mContext));
        after = "";
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initListener();
    }

    private void initListener() {
        mSpringview.setHeader(new DefaultHeader(mContext));
        mSpringview.setFooter(new DefaultFooter(mContext));
        mSpringview.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                after = "";
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
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));
        HttpParams params = new HttpParams();
        params.put("after",after);
        params.put("limit","10");
        params.put("state","2");

        OkGo.<ReserveBean>get(Urls.BASEURL + "api/v2/user/restaurant")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<ReserveBean>(mActivity,ReserveBean.class) {
                    @Override
                    public void onSuccess(Response<ReserveBean> response) {
                        ReserveBean body = response.body();
                        if (body.getCode() == 1){
                            if (!isLoadMore) {
                                list = body.getData().getData();
                                adapter = new MyReserveAdapter(R.layout.item_my_reserve, list);
                                mRecyclerRenyang.setAdapter(adapter);
                            } else {
                                if (body.getData() != null && body.getData().getData().size() > 0) {
                                    list.addAll(body.getData().getData());
                                    adapter.notifyDataSetChanged();
                                } else {
                                    Toast.makeText(mContext, "没有更多了~", Toast.LENGTH_SHORT).show();
                                }
                            }
                            adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                                @Override
                                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                    switch (view.getId()){
                                        case R.id.content_rl:
                                            Intent intent = new Intent(mContext, ReserveDetailActivity.class);
                                            intent.putExtra("detail", (Serializable) list.get(position));
                                            startActivity(intent);
                                            break;
                                        case R.id.shachu_tv:
                                            TUtils.showShort(mContext,"点击了---删除"+position);
                                            break;
                                    }
                                }
                            });
                        }
                    }
                });
    }

    public class MyReserveAdapter extends BaseQuickAdapter<ReserveBean.DataBeanX.DataBean, BaseViewHolder> {

        public MyReserveAdapter(@LayoutRes int layoutResId, @Nullable List<ReserveBean.DataBeanX.DataBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ReserveBean.DataBeanX.DataBean item) {
            after=item.getId()+"";
            ImageView waitIv = helper.getView(R.id.item_wait_iv);
            ImageView successIv = helper.getView(R.id.item_success_iv);
            ImageView filedIv = helper.getView(R.id.item_filed_iv);
            ImageView faceIv = helper.getView(R.id.item_face_iv);
            TextView titleTv = helper.getView(R.id.item_name_tv);
            TextView timeTv = helper.getView(R.id.item_time_tv);
            TextView addressTv = helper.getView(R.id.item_address_tv);
            helper.addOnClickListener(R.id.content_rl)
                    .addOnClickListener(R.id.shachu_tv);
            Glide.with(mContext).load(item.getImgurl()).into(faceIv);
            titleTv.setText(item.getName());
            timeTv.setText("预约时间：" + item.getRestaurant_time());
            addressTv.setText(item.getPosition());
            waitIv.setVisibility(View.GONE);
            successIv.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void initLazyData() {

    }
}
