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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.PlayerBean;

import java.util.ArrayList;
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
    private List<PlayerBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);
        ButterKnife.bind(this);
        mTitleBackIv.setVisibility(View.VISIBLE);
        mTitleBackIv.setOnClickListener(this);
        mTitleContentTv.setText("视频列表");
        mRecylcerVideo.setLayoutManager(new LinearLayoutManager(this));
        mList = new ArrayList<>();
        mList.add(new PlayerBean("视频标题1", "视频内容1"));
        mList.add(new PlayerBean("视频标题2", "视频内容2"));
        mList.add(new PlayerBean("视频标题3", "视频内容3"));
        mVideoAdapter = new VideoAdapter(R.layout.item_video, mList);
        mRecylcerVideo.setAdapter(mVideoAdapter);
    }

    private class VideoAdapter extends BaseQuickAdapter<PlayerBean, BaseViewHolder> {

        public VideoAdapter(@LayoutRes int layoutResId, @Nullable List<PlayerBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final PlayerBean item) {
            final ImageView iv_status = helper.getView(R.id.iv_status);
            if (item.isPlaying()) {
                iv_status.setImageResource(R.drawable.icon_playing);
            } else {
                iv_status.setImageResource(R.drawable.icon_pause);
            }
            iv_status.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.setPlaying(!item.isPlaying());
                    iv_status.setImageResource(R.drawable.icon_playing);
                    startActivity(new Intent(VideoListActivity.this, VideoDetailActivity.class));
                }
            });
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        for (int i = 0; i < mList.size(); i++) {
            mList.get(i).setPlaying(false);
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
