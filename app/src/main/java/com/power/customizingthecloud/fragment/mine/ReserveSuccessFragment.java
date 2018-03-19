package com.power.customizingthecloud.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.activity.mine.ReserveDetailActivity;
import com.power.customizingthecloud.adapter.MyReserveAdapter;
import com.power.customizingthecloud.base.BaseFragment;
import com.power.customizingthecloud.bean.ReserveBean;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.fragment.home.GoodDetailActivity;
import com.power.customizingthecloud.fragment.home.ShopDetailActivity;
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

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_renyang, null);
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));
        HttpParams params = new HttpParams();
        params.put("after","");
        params.put("limit","");
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
                            list = body.getData().getData();
                            mRecyclerRenyang.setLayoutManager(new LinearLayoutManager(mContext));
                            MyReserveAdapter adapter = new MyReserveAdapter(R.layout.item_my_reserve, list,mContext,2);
                            mRecyclerRenyang.setAdapter(adapter);
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

    @Override
    protected void initLazyData() {

    }
}
