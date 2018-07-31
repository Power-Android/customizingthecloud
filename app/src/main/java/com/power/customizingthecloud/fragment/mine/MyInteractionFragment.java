package com.power.customizingthecloud.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseFragment;
import com.power.customizingthecloud.bean.InteractionBean;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.fragment.market.DongTaiDetailActivity;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.Urls;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.power.customizingthecloud.utils.Urls.BASEURL;

/**
 * Created by Administrator on 2018/1/26.
 */

public class MyInteractionFragment extends BaseFragment implements BaseQuickAdapter.OnItemChildClickListener {
    @BindView(R.id.pinglun_tv)
    TextView pinglunTv;
    @BindView(R.id.huifu_tv)
    TextView huifuTv;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;
    private List<InteractionBean.DataBean> list;
    @BindView(R.id.springview)
    SpringView mSpringview;
    private String after;
    private boolean isLoadMore;
    private int currPosition = 1;
    private InteractionAdapter adapter;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(mContext, R.layout.fragment_interraction, null);
        unbinder = ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setNestedScrollingEnabled(false);
        initData();
        initListener();
        return view;
    }

    private void initListener() {
        mSpringview.setHeader(new DefaultHeader(mContext));
        mSpringview.setFooter(new DefaultFooter(mContext));
        mSpringview.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                after = "";
                isLoadMore = false;
                initData();
                mSpringview.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                isLoadMore = true;
                initData();
                mSpringview.onFinishFreshAndLoad();
            }
        });
    }

    private void initData() {
        String url = "";
        if (currPosition == 1) {
            url = Urls.BASEURL + "api/v2/user/my-comment";
        } else if (currPosition == 2) {
            url = BASEURL + "api/v2/user/my-reply";
        }
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));
        HttpParams params = new HttpParams();
        params.put("after", after);
        OkGo.<InteractionBean>get(url)
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<InteractionBean>(mActivity, InteractionBean.class) {
                    @Override
                    public void onSuccess(Response<InteractionBean> response) {
                        InteractionBean body = response.body();
                        if (body.getCode() == 1) {
                            if (!isLoadMore) {
                                list = body.getData();
                                adapter = new InteractionAdapter(R.layout.item_interacation, list);
                                recyclerView.setAdapter(adapter);
                            } else {
                                if (body.getData() != null && body.getData().size() > 0) {
                                    list.addAll(body.getData());
                                    adapter.notifyDataSetChanged();
                                } else {
                                    Toast.makeText(mContext, "没有更多了~", Toast.LENGTH_SHORT).show();
                                }
                            }
                            adapter.setOnItemChildClickListener(MyInteractionFragment.this);
                        }
                    }
                });
    }


    @Override
    protected void initLazyData() {

    }

    @OnClick(R.id.pinglun_tv)
    public void pinglun() {
        pinglunTv.setTextColor(mContext.getResources().getColor(R.color.green));
        huifuTv.setTextColor(mContext.getResources().getColor(R.color.text_black));
        currPosition = 1;
        after = "";
        isLoadMore = false;
        list = new ArrayList<>();
        initData();
    }

    @OnClick(R.id.huifu_tv)
    public void huifu() {
        huifuTv.setTextColor(mContext.getResources().getColor(R.color.green));
        pinglunTv.setTextColor(mContext.getResources().getColor(R.color.text_black));
        currPosition = 2;
        isLoadMore = false;
        after = "";
        list = new ArrayList<>();
        initData();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(mContext, DongTaiDetailActivity.class);
        intent.putExtra("id", list.get(position).getFeed_id() + "");
        startActivity(intent);
    }

    private class InteractionAdapter extends BaseQuickAdapter<InteractionBean.DataBean, BaseViewHolder> {

        public InteractionAdapter(@LayoutRes int layoutResId, @Nullable List<InteractionBean.DataBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, InteractionBean.DataBean item) {
            after = item.getId() + "";
            if (!TextUtils.isEmpty(item.getUser_name()) && item.getUser_name().length() > 14) {
                String user_name = item.getUser_name();
                String substring = user_name.substring(0, 14);
                helper.setText(R.id.item_name_tv, substring + "...");
            } else {
                helper.setText(R.id.item_name_tv, item.getUser_name());
            }
            helper.setText(R.id.item_content_tv, item.getBody())
                    .addOnClickListener(R.id.ll_root);
            ImageView imageView = helper.getView(R.id.item_pic_iv);
            ImageView faceiV = helper.getView(R.id.item_face_iv);
            Glide.with(mActivity).load(item.getUser_avatar()).into(faceiV);
            if (item.getImage() != null || item.getImage() != "") {
                imageView.setVisibility(View.VISIBLE);
                Glide.with(mActivity).load(item.getImage()).into(imageView);
            } else {
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
