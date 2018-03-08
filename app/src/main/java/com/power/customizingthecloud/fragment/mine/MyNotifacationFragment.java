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
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.activity.mine.NotifaDetailActivity;
import com.power.customizingthecloud.base.BaseFragment;
import com.power.customizingthecloud.bean.NotifacationBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/1/26.
 */

public class MyNotifacationFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;

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
        List<NotifacationBean> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            NotifacationBean bean = new NotifacationBean();
            bean.setDate("2020.11.11");
            bean.setContent("此处是消息内容------------------此处是消息内容------------------此处是消息内容------------------此处是消息内容------------------");
            bean.setIsRead(i+"");
            list.add(bean);
        }
        NotifacationAdapter adapter = new NotifacationAdapter(R.layout.item_notifacation,list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        startActivity(new Intent(mContext,NotifaDetailActivity.class));
    }

    private class NotifacationAdapter extends BaseQuickAdapter<NotifacationBean,BaseViewHolder>{

        public NotifacationAdapter(@LayoutRes int layoutResId, @Nullable List<NotifacationBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, NotifacationBean item) {
            helper.setText(R.id.item_date_tv,item.getDate())
                    .setText(R.id.item_content_tv,item.getContent());
            if (item.getIsRead().equals("1")){
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
