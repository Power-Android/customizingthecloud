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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.activity.mine.NotifaDetailActivity;
import com.power.customizingthecloud.base.BaseFragment;
import com.power.customizingthecloud.bean.NotifacationBean;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.Urls;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/1/26.
 */

public class MyNotifacationFragment extends BaseFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;
    private List<NotifacationBean.DataBean> list;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(mContext, R.layout.fragment_notifacation, null);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setNestedScrollingEnabled(false);

        initData();
    }

    private void initData() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));
        HttpParams params = new HttpParams();
        params.put("after","");

        OkGo.<NotifacationBean>get(Urls.BASEURL + "api/v2/system")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<NotifacationBean>(mActivity,NotifacationBean.class) {
                    @Override
                    public void onSuccess(Response<NotifacationBean> response) {
                        NotifacationBean body = response.body();
                        if (body.getCode() == 1){
                            list = body.getData();
                            NotifacationAdapter adapter = new NotifacationAdapter(R.layout.item_notifacation,list);
                            recyclerView.setAdapter(adapter);
                            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent = new Intent(mContext,NotifaDetailActivity.class);
                                    intent.putExtra("id",list.get(position).getId()+"");
                                    startActivity(intent);
                                }
                            });
                        }
                    }
                });

    }

    private class NotifacationAdapter extends BaseQuickAdapter<NotifacationBean.DataBean,BaseViewHolder>{

        public NotifacationAdapter(@LayoutRes int layoutResId, @Nullable List<NotifacationBean.DataBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, NotifacationBean.DataBean item) {
            helper.setText(R.id.item_date_tv,item.getCreated_at())
                    .setText(R.id.item_content_tv,item.getContent());
            if (item.isIs_read()){
                helper.getView(R.id.item_is_read_iv).setVisibility(View.GONE);
            }
        }
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
