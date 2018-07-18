package com.power.customizingthecloud.fragment.home.top;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.MyApplication;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.fragment.home.MiaoShaDetailActivity;
import com.power.customizingthecloud.fragment.shop.bean.MiaoListBean;
import com.power.customizingthecloud.utils.Urls;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.iwgang.countdownview.CountdownView;

public class MiaoShaActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_message_iv)
    ImageView mTitleMessageIv;
    @BindView(R.id.title_back_iv)
    ImageView mTitleBackIv;
    @BindView(R.id.title_content_tv)
    TextView mTitleContentTv;
    @BindView(R.id.title_sign_in_iv)
    ImageView mTitleSignInIv;
    @BindView(R.id.title_setting_iv)
    ImageView mTitleSettingIv;
    @BindView(R.id.title_content_right_tv)
    TextView mTitleContentRightTv;
    @BindView(R.id.recycler_miaosha)
    RecyclerView mRecyclerMiaosha;
    @BindView(R.id.springview)
    SpringView mSpringview;
    private MiaoshaAdapter mMiaoshaAdapter;
    private int page = 1;
    private boolean isLoadMore;
    private List<MiaoListBean.DataEntity> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miao_sha);
        ButterKnife.bind(this);
        mTitleBackIv.setVisibility(View.VISIBLE);
        mTitleBackIv.setOnClickListener(this);
        mTitleContentTv.setText("限量秒杀");
        mRecyclerMiaosha.setLayoutManager(new LinearLayoutManager(mContext));
        initData();
        initListener();
    }

    private void initData() {
        HttpParams params = new HttpParams();
        params.put("page", page);
        params.put("limit", "10");
        OkGo.<MiaoListBean>get(Urls.BASEURL + "api/v2/good/seckill-good-list")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<MiaoListBean>(MiaoShaActivity.this, MiaoListBean.class) {
                    @Override
                    public void onSuccess(Response<MiaoListBean> response) {
                        MiaoListBean miaoListBean = response.body();
                        int code = miaoListBean.getCode();
                        if (code == 0) {
                            Toast.makeText(mContext, miaoListBean.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (code == 1) {
                            if (!isLoadMore) {
                                mData = miaoListBean.getData();
                                mMiaoshaAdapter = new MiaoshaAdapter(R.layout.item_home_miaosha, mData);
                                mRecyclerMiaosha.setAdapter(mMiaoshaAdapter);
                            } else {
                                if (miaoListBean.getData() != null && miaoListBean.getData().size() > 0) {
                                    mData.addAll(miaoListBean.getData());
                                    mMiaoshaAdapter.notifyDataSetChanged();
                                } else {
                                    Toast.makeText(MiaoShaActivity.this, "没有更多了~", Toast.LENGTH_SHORT).show();
                                    page--;
                                }
                            }
                            mMiaoshaAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent = new Intent(MiaoShaActivity.this, MiaoShaDetailActivity.class);
                                    intent.putExtra("id", mData.get(position).getId() + "");
                                    startActivity(intent);
                                }
                            });
                        }
                    }
                });
    }

    private void initListener() {
        //        mSpringview.setType(SpringView.Type.FOLLOW);
        mSpringview.setHeader(new DefaultHeader(this));
        mSpringview.setFooter(new DefaultFooter(this));
        mSpringview.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                isLoadMore = false;
                page = 1;
                initData();
                mSpringview.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                isLoadMore = true;
                page++;
                initData();
                mSpringview.onFinishFreshAndLoad();
            }
        });
    }

    private class MiaoshaAdapter extends BaseQuickAdapter<MiaoListBean.DataEntity, BaseViewHolder> {

        public MiaoshaAdapter(@LayoutRes int layoutResId, @Nullable List<MiaoListBean.DataEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, MiaoListBean.DataEntity item) {
            TextView tv_yuanjia = helper.getView(R.id.tv_yuanjia);
            tv_yuanjia.setText(item.getPrice());
            //添加删除线
            tv_yuanjia.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            CountdownView cv_countdownView = helper.getView(R.id.cv_countdownView);
            long time = item.getSeckill_end_time() * 1000L - System.currentTimeMillis();
            cv_countdownView.start(time); // Millisecond
            ImageView iv_img = helper.getView(R.id.iv_image);
            Glide.with(MyApplication.getGloableContext()).load(item.getImage()).into(iv_img);
            helper.setText(R.id.tv_title, item.getName())
                    .setText(R.id.tv_curprice, item.getSeckill_price())
                    .setText(R.id.tv_last_count, item.getSeckill_storage() + "");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
        }
    }
}
