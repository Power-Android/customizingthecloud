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
import com.power.customizingthecloud.MyApplication;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.activity.mine.MyRenyangDetailActivity;
import com.power.customizingthecloud.base.BaseFragment;
import com.power.customizingthecloud.bean.MyRenyangBean;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.fragment.home.jiankong.JianKongActivity;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.Urls;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/2/1.
 */

public class AllRenyangFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.recycler)
    RecyclerView mRecyclerRenyang;
    Unbinder unbinder;
    private List<MyRenyangBean.DataBean> list = new ArrayList<>();
    private MyRenYangAdapter renYangAdapter;
    private String after;
    private boolean isLoadMore;
    @BindView(R.id.springview)
    SpringView mSpringview;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_renyang, null);
        unbinder = ButterKnife.bind(this, view);
        mRecyclerRenyang.setLayoutManager(new LinearLayoutManager(mContext));
        after="";
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
        params.put("after", after);
        params.put("state", "0");
        OkGo.<MyRenyangBean>get(Urls.BASEURL + "api/v2/user/donkey")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<MyRenyangBean>(mActivity, MyRenyangBean.class) {
                    @Override
                    public void onSuccess(Response<MyRenyangBean> response) {
                        MyRenyangBean body = response.body();
                        if (body.getCode() == 1) {
                            if (!isLoadMore) {
                                list = body.getData();
                                renYangAdapter = new MyRenYangAdapter(R.layout.item_my_renyang, list);
                                mRecyclerRenyang.setAdapter(renYangAdapter);
                            } else {
                                if (body.getData() != null && body.getData().size() > 0) {
                                    list.addAll(body.getData());
                                    renYangAdapter.notifyDataSetChanged();
                                } else {
                                    Toast.makeText(mContext, "没有更多了~", Toast.LENGTH_SHORT).show();
                                }
                            }
                            renYangAdapter.setOnItemClickListener(AllRenyangFragment.this);
                        }
                    }
                });
    }

    @Override
    protected void initLazyData() {

    }

    public class MyRenYangAdapter extends BaseQuickAdapter<MyRenyangBean.DataBean, BaseViewHolder> {

        public MyRenYangAdapter(@LayoutRes int layoutResId, @Nullable List<MyRenyangBean.DataBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, MyRenyangBean.DataBean item) {
            after = item.getId() + "";
            helper.setText(R.id.tv_state, item.getTitle())
                    .setText(R.id.start_tv, "开始养殖：" + item.getPayment_time())
                    .setText(R.id.end_tv, "结束养殖：" + item.getEnd_time())
                    .setText(R.id.nianshouyi_tv, item.getProfit())
                    .setText(R.id.yangzhichengben_tv, item.getPrice())
                    .setText(R.id.yangzhizhouqi_tv, item.getPeriod())
                    .setText(R.id.yangzhishouyi_tv, item.getIncome());
            Glide.with(MyApplication.getGloableContext()).load(item.getImage()).into((ImageView) helper.getView(R.id.iv_tupian));
            helper.getView(R.id.tv_jiankong).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext, JianKongActivity.class));
                }
            });
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(mContext, MyRenyangDetailActivity.class);
        intent.putExtra("id", list.get(position).getId() + "");
        startActivity(intent);
    }
}
