package com.power.customizingthecloud.fragment.mine;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseFragment;
import com.power.customizingthecloud.bean.InteractionBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/1/26.
 */

public class MyInteractionFragment extends BaseFragment {
    @BindView(R.id.pinglun_tv)
    TextView pinglunTv;
    @BindView(R.id.huifu_tv)
    TextView huifuTv;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(mContext, R.layout.fragment_interraction, null);
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setNestedScrollingEnabled(false);
        List<InteractionBean> list = new ArrayList<>();
        InteractionBean bean1 = new InteractionBean();
        bean1.setName("评论A");
        bean1.setContent("评论：小伙子，快过年了哈！！！");
        bean1.setIsPic("1");
        list.add(bean1);
        InteractionBean bean2 = new InteractionBean();
        bean2.setName("评论B");
        bean2.setContent("评论：小伙子，快过年了哈！！！");
        bean2.setIsPic("0");
        list.add(bean2);
        InteractionBean bean3 = new InteractionBean();
        bean3.setName("评论C");
        bean3.setContent("评论：小伙子，快过年了哈！！！");
        bean3.setIsPic("1");
        list.add(bean3);
        InteractionAdapter adapter = new InteractionAdapter(R.layout.item_interacation,list);
        recyclerView.setAdapter(adapter);
    }

    private void initData2() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setNestedScrollingEnabled(false);
        List<InteractionBean> list1 = new ArrayList<>();
        InteractionBean bean1 = new InteractionBean();
        bean1.setName("回复A");
        bean1.setContent("回复A：小伙子，快过年了哈！！！");
        bean1.setIsPic("1");
        list1.add(bean1);
        InteractionBean bean2 = new InteractionBean();
        bean2.setName("回复B");
        bean2.setContent("回复B：小伙子，快过年了哈！！！");
        bean2.setIsPic("0");
        list1.add(bean2);
        InteractionBean bean3 = new InteractionBean();
        bean3.setName("回复C");
        bean3.setContent("回复C：小伙子，快过年了哈！！！");
        bean3.setIsPic("1");
        list1.add(bean3);
        InteractionAdapter adapter = new InteractionAdapter(R.layout.item_interacation,list1);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initLazyData() {

    }

    @OnClick(R.id.pinglun_tv)
    public void pinglun(){
        pinglunTv.setTextColor(mContext.getResources().getColor(R.color.green));
        huifuTv.setTextColor(mContext.getResources().getColor(R.color.text_black));
        initData();
    }

    @OnClick(R.id.huifu_tv)
    public void huifu(){
        huifuTv.setTextColor(mContext.getResources().getColor(R.color.green));
        pinglunTv.setTextColor(mContext.getResources().getColor(R.color.text_black));
        initData2();
    }

    private class InteractionAdapter extends BaseQuickAdapter<InteractionBean,BaseViewHolder>{

        public InteractionAdapter(@LayoutRes int layoutResId, @Nullable List<InteractionBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, InteractionBean item) {
            helper.setText(R.id.item_name_tv,item.getName())
                    .setText(R.id.item_content_tv,item.getContent());
            ImageView imageView = helper.getView(R.id.item_pic_iv);
            if (item.getIsPic().equals("1")){
                imageView.setVisibility(View.VISIBLE);
            }else {
                imageView.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
