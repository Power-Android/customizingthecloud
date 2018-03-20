package com.power.customizingthecloud.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.activity.mine.RengyangDetail1Activity;
import com.power.customizingthecloud.adapter.MyRenYangAdapter;
import com.power.customizingthecloud.base.BaseFragment;
import com.power.customizingthecloud.bean.MyRenyangBean;
import com.power.customizingthecloud.callback.DialogCallback;
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

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_renyang, null);
        unbinder = ButterKnife.bind(this, view);
        mRecyclerRenyang.setLayoutManager(new LinearLayoutManager(mContext));

        initData();
        return view;
    }

    private void initData() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));
        HttpParams params = new HttpParams();
        params.put("after","");
        params.put("state","0");

        OkGo.<MyRenyangBean>get(Urls.BASEURL + "api/v2/user/donkey")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<MyRenyangBean>(mActivity,MyRenyangBean.class) {
                    @Override
                    public void onSuccess(Response<MyRenyangBean> response) {
                        MyRenyangBean body = response.body();
                        if (body.getCode() == 1){
                            list = body.getData();
                            renYangAdapter = new MyRenYangAdapter(R.layout.item_my_renyang,list,mContext,1);
                            mRecyclerRenyang.setAdapter(renYangAdapter);
                            renYangAdapter.setOnItemClickListener(AllRenyangFragment.this);
                        }
                    }
                });
    }

    @Override
    protected void initLazyData() {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        startActivity(new Intent(mContext,RengyangDetail1Activity.class));
    }
}
