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

public class OverFragment extends BaseFragment{
    @BindView(R.id.recycler)
    RecyclerView mRecyclerRenyang;
    Unbinder unbinder;

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
        HttpParams params=new HttpParams();
        params.put("page","1");
        params.put("limit","10");
        params.put("state","3");
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
                            final List<RenYangListBean.DataEntity> data = renYangListBean.getData();
                            RenYangAdapter renYangAdapter = new RenYangAdapter(R.layout.home_middle, data, mContext, 1);
                            mRecyclerRenyang.setAdapter(renYangAdapter);
                            renYangAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent = new Intent(mContext, RenYangDetailActivity.class);
                                    RenYangListBean.DataEntity dataEntity = data.get(position);
                                    int state = dataEntity.getState();
                                    intent.putExtra("state",state);
                                    intent.putExtra("id",dataEntity.getId()+"");
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
