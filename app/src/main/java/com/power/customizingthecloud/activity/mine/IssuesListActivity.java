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
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.KefuListBean;
import com.power.customizingthecloud.callback.JsonCallback;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.Urls;

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
    private List<KefuListBean.DataEntity> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issues_list);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        int class_id = getIntent().getIntExtra("class_id", 1);
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(this, "token", ""));
        HttpParams params = new HttpParams();
        params.put("class_id", class_id);
        OkGo.<KefuListBean>get(Urls.BASEURL + "api/v2/kefu/after-sale")
                .headers(headers)
                .params(params)
                .execute(new JsonCallback<KefuListBean>(KefuListBean.class) {
                    @Override
                    public void onSuccess(Response<KefuListBean> response) {
                        KefuListBean bean = response.body();
                        int code = bean.getCode();
                        if (code == 1) {
                            data = bean.getData();
                            IssuesListAdapter adapter = new IssuesListAdapter(R.layout.item_issues_list,data);
                            recyclerView.setAdapter(adapter);
                            adapter.setOnItemClickListener(IssuesListActivity.this);
                        }
                    }
                });
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("问题列表");
        titleKefuIv.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//        TUtils.showShort(mContext,"点击了---问题列表"+position);
        Intent intent = new Intent(mContext, WentiDetailActivity.class);
        intent.putExtra("id",data.get(position).getId()+"");
        startActivity(intent);
    }

    private class IssuesListAdapter extends BaseQuickAdapter<KefuListBean.DataEntity,BaseViewHolder>{

        public IssuesListAdapter(@LayoutRes int layoutResId, @Nullable List<KefuListBean.DataEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, KefuListBean.DataEntity item) {
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
