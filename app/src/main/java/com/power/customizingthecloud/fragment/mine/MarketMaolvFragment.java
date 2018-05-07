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
import com.power.customizingthecloud.activity.mine.GoodDetail1Activity;
import com.power.customizingthecloud.adapter.ProductListAdapter;
import com.power.customizingthecloud.base.BaseFragment;
import com.power.customizingthecloud.bean.MarketShopBean;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.fragment.home.renyang.RenYangDetailActivity;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.Urls;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/2/23.
 */

public class MarketMaolvFragment extends BaseFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_market_maolv, null);
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));
        HttpParams params = new HttpParams();
        params.put("after","");

        OkGo.<MarketShopBean>get(Urls.BASEURL + "api/v2/user/fx-donkey")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<MarketShopBean>(mActivity,MarketShopBean.class) {
                    @Override
                    public void onSuccess(Response<MarketShopBean> response) {
                        MarketShopBean marketShopBean = response.body();
                        if (marketShopBean.getCode() == 1){
                            List<MarketShopBean.DataBean> list = marketShopBean.getData();
                            recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                            ProductListAdapter adapter=new ProductListAdapter(R.layout.item_market_maolv,list,mContext,2);
                            recyclerView.setAdapter(adapter);
                            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    startActivity(new Intent(mContext,RenYangDetailActivity.class));
                                }
                            });
                        }
                    }
                });
    }

    @Override
    protected void initLazyData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
