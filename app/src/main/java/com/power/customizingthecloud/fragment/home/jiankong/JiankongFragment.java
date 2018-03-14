package com.power.customizingthecloud.fragment.home.jiankong;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseFragment;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.fragment.home.bean.MuChangListBean;
import com.power.customizingthecloud.utils.Urls;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/1/26.
 */

public class JiankongFragment extends BaseFragment {
    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    Unbinder unbinder;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jiankong, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initLazyData() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecycler.setLayoutManager(new GridLayoutManager(mContext,3));
        String class_id = getArguments().getString("class_id");
        HttpParams params=new HttpParams();
        params.put("page","1");
        params.put("limit","10");
        params.put("class_id",class_id);
        OkGo.<MuChangListBean>post(Urls.BASEURL + "api/v2/muchang/show")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<MuChangListBean>(mActivity, MuChangListBean.class) {
                    @Override
                    public void onSuccess(Response<MuChangListBean> response) {
                        MuChangListBean listBean = response.body();
                        int code = listBean.getCode();
                        if (code == 0) {
                            Toast.makeText(mContext, listBean.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (code == 1) {
                            List<MuChangListBean.DataEntity> data = listBean.getData();
                            JianKongAdapter jianKongAdapter=new JianKongAdapter(R.layout.item_jiankong,data,mContext);
                            mRecycler.setAdapter(jianKongAdapter);
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
