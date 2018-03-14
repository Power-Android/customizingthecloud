package com.power.customizingthecloud.fragment.shop;

import android.content.Intent;
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
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.MyApplication;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.fragment.shop.bean.VideoListBean;
import com.power.customizingthecloud.utils.Urls;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoListActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_message_iv)
    ImageView mTitleMessageIv;
    @BindView(R.id.title_back_iv)
    ImageView mTitleBackIv;
    @BindView(R.id.title_list_iv)
    ImageView mTitleListIv;
    @BindView(R.id.title_content_tv)
    TextView mTitleContentTv;
    @BindView(R.id.title_sign_in_iv)
    ImageView mTitleSignInIv;
    @BindView(R.id.title_setting_iv)
    ImageView mTitleSettingIv;
    @BindView(R.id.title_qrcode_iv)
    ImageView mTitleQrcodeIv;
    @BindView(R.id.title_shopcar_iv)
    ImageView mTitleShopcarIv;
    @BindView(R.id.title_share_iv)
    ImageView mTitleShareIv;
    @BindView(R.id.title_search_iv)
    ImageView mTitleSearchIv;
    @BindView(R.id.title_jia_iv)
    ImageView mTitleJiaIv;
    @BindView(R.id.title_content_right_tv)
    TextView mTitleContentRightTv;
    @BindView(R.id.recylcer_video)
    RecyclerView mRecylcerVideo;
    private VideoAdapter mVideoAdapter;
    private List<VideoListBean.DataEntity> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);
        ButterKnife.bind(this);
        mTitleBackIv.setVisibility(View.VISIBLE);
        mTitleBackIv.setOnClickListener(this);
        mTitleContentTv.setText("视频列表");
        mRecylcerVideo.setLayoutManager(new LinearLayoutManager(this));
        HttpParams params=new HttpParams();
        params.put("after","");
        OkGo.<VideoListBean>get(Urls.BASEURL + "api/v2/kitchen")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<VideoListBean>(this, VideoListBean.class) {
                    @Override
                    public void onSuccess(Response<VideoListBean> response) {
                        VideoListBean videoListBean = response.body();
                        int code = videoListBean.getCode();
                        if (code == 0) {
                            Toast.makeText(mContext, videoListBean.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (code == 1) {
                            mData = videoListBean.getData();
                            mVideoAdapter = new VideoAdapter(R.layout.item_video, mData);
                            mRecylcerVideo.setAdapter(mVideoAdapter);
                        }
                    }
                });
    }

    private class VideoAdapter extends BaseQuickAdapter<VideoListBean.DataEntity, BaseViewHolder> {

        public VideoAdapter(@LayoutRes int layoutResId, @Nullable List<VideoListBean.DataEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final VideoListBean.DataEntity item) {
            final ImageView iv_status = helper.getView(R.id.iv_status);
            if (item.isPlaying()) {
                iv_status.setImageResource(R.drawable.icon_playing);
            } else {
                iv_status.setImageResource(R.drawable.icon_pause);
            }
            Glide.with(MyApplication.getGloableContext()).load(item.getImgurl())
                    .into((ImageView) helper.getView(R.id.iv_tupian));
            helper.setText(R.id.tv_title,item.getTitle());
            iv_status.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.setPlaying(!item.isPlaying());
                    iv_status.setImageResource(R.drawable.icon_playing);
                    Intent intent = new Intent(VideoListActivity.this, VideoDetailActivity.class);
                    intent.putExtra("videourl",item.getVideo_url());
                    intent.putExtra("imgurl",item.getImgurl());
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        for (int i = 0; i < mData.size(); i++) {
            mData.get(i).setPlaying(false);
        }
        mVideoAdapter.notifyDataSetChanged();
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
