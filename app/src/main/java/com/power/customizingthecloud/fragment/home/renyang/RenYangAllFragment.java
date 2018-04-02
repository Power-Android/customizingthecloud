package com.power.customizingthecloud.fragment.home.renyang;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.adapter.RenYangAdapter;
import com.power.customizingthecloud.base.BaseFragment;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.fragment.home.bean.RenYangListBean;
import com.power.customizingthecloud.utils.Urls;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/1/26.
 */

public class RenYangAllFragment extends BaseFragment {
    @BindView(R.id.recycler)
    RecyclerView mRecyclerRenyang;
    Unbinder unbinder;
    private int page = 1;
    private boolean isLoadMore;
    @BindView(R.id.springview)
    SpringView mSpringview;
    private List<RenYangListBean.DataEntity> mData;
    private RenYangAdapter mRenYangAdapter;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_renyang, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initLazyData() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerRenyang.setLayoutManager(new LinearLayoutManager(mContext));
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
        params.put("state", "0");
        OkGo.<RenYangListBean>get(Urls.BASEURL + "api/v2/adopt")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<RenYangListBean>(mActivity, RenYangListBean.class) {
                    @Override
                    public void onSuccess(Response<RenYangListBean> response) {
                        final RenYangListBean renYangListBean = response.body();
                        int code = renYangListBean.getCode();
                        if (code == 0) {
                            Toast.makeText(mContext, renYangListBean.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (code == 1) {
                            if (!isLoadMore) {
                                mData = renYangListBean.getData();
                                mRenYangAdapter = new RenYangAdapter(R.layout.home_middle, mData, mContext, 1);
                                mRecyclerRenyang.setAdapter(mRenYangAdapter);
                            } else {
                                if (renYangListBean.getData() != null && renYangListBean.getData().size() > 0) {
                                    mData.addAll(renYangListBean.getData());
                                    mRenYangAdapter.notifyDataSetChanged();
                                } else {
                                    Toast.makeText(mContext, "没有更多了~", Toast.LENGTH_SHORT).show();
                                    page--;
                                }
                            }
                            mRenYangAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent = new Intent(mContext, RenYangDetailActivity.class);
                                    RenYangListBean.DataEntity dataEntity = mData.get(position);
                                    int state = dataEntity.getState();
                                    intent.putExtra("state", state);
                                    intent.putExtra("id", dataEntity.getId() + "");
                                    startActivity(intent);
                                }
                            });
                        }
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
