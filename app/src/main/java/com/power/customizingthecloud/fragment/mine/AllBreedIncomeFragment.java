package com.power.customizingthecloud.fragment.mine;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.adapter.MyBreedIncomeAdapter;
import com.power.customizingthecloud.base.BaseFragment;
import com.power.customizingthecloud.bean.ProfitDetailBean;
import com.power.customizingthecloud.callback.JsonCallback;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.Urls;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/2/12.
 */

public class AllBreedIncomeFragment extends BaseFragment {

    @BindView(R.id.recycler)
    RecyclerView mRecyclerRenyang;
    Unbinder unbinder;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_breed_income, null);
        unbinder = ButterKnife.bind(this, view);
        mRecyclerRenyang.setLayoutManager(new LinearLayoutManager(mContext));
        initData();
        return view;
    }

    private void initData() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));
        OkGo.<ProfitDetailBean>get(Urls.BASEURL + "api/v2/user/profit-details")
                .tag(this)
                .headers(headers)
                .execute(new JsonCallback<ProfitDetailBean>(ProfitDetailBean.class) {
                    @Override
                    public void onSuccess(Response<ProfitDetailBean> response) {
                        ProfitDetailBean caiFuBean = response.body();
                        if (caiFuBean.getCode() == 1) {
                            List<ProfitDetailBean.DataEntity> data = caiFuBean.getData();
                            MyBreedIncomeAdapter breedIncomeAdapter = new MyBreedIncomeAdapter(R.layout.item_breed_income, data, mContext, 1);
                            mRecyclerRenyang.setAdapter(breedIncomeAdapter);
                        }
                    }
                });

    }

    @Override
    protected void initLazyData() {

    }
}
