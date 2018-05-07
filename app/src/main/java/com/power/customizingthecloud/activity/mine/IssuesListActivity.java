package com.power.customizingthecloud.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.IssuesListBean;
import com.power.customizingthecloud.utils.TUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IssuesListActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.title_kefu_iv)
    ImageView titleKefuIv;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issues_list);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("问题列表");
        titleKefuIv.setVisibility(View.VISIBLE);

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setNestedScrollingEnabled(false);
        List<IssuesListBean> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            IssuesListBean bean = new IssuesListBean();
            bean.setTitle("没有收到货怎么办？"+i);
            list.add(bean);
        }
        IssuesListAdapter adapter = new IssuesListAdapter(R.layout.item_issues_list,list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        TUtils.showShort(mContext,"点击了---问题列表"+position);
    }

    private class IssuesListAdapter extends BaseQuickAdapter<IssuesListBean,BaseViewHolder>{

        public IssuesListAdapter(@LayoutRes int layoutResId, @Nullable List<IssuesListBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, IssuesListBean item) {
            helper.setText(R.id.item_title_tv,item.getTitle());
        }
    }

    @OnClick({R.id.title_back_iv, R.id.title_kefu_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.title_kefu_iv:
//                TUtils.showShort(mContext,"点击了---客服");
                startActivity(new Intent(mContext,ChatActivity.class));
                break;
        }
    }
}
