package com.power.customizingthecloud.fragment.home.jiankong;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.MyApplication;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.PlayerBean;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.fragment.home.bean.MuChangTypeBean;
import com.power.customizingthecloud.fragment.home.renyang.BaseTabAdapter;
import com.power.customizingthecloud.utils.Urls;
import com.power.customizingthecloud.view.NoScrollViewPager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

public class JianKongActivity extends BaseActivity implements View.OnClickListener {

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
    @BindView(R.id.tablayout)
    TabLayout mTablayout;
    @BindView(R.id.viewpager)
    NoScrollViewPager mViewpager;
    @BindView(R.id.videoplayer)
    JZVideoPlayerStandard mVideoplayer;
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> tab_list = new ArrayList<>();
    private List<MuChangTypeBean.DataEntity> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jian_kong);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        mTitleBackIv.setVisibility(View.VISIBLE);
        mTitleBackIv.setOnClickListener(this);
        mTitleContentTv.setText("监控列表");
        OkGo.<MuChangTypeBean>get(Urls.BASEURL + "api/v2/muchang")
                .tag(this)
                .execute(new DialogCallback<MuChangTypeBean>(this, MuChangTypeBean.class) {
                    @Override
                    public void onSuccess(Response<MuChangTypeBean> response) {
                        MuChangTypeBean typeBean = response.body();
                        int code = typeBean.getCode();
                        if (code == 0) {
                            Toast.makeText(mContext, typeBean.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (code == 1) {
                            mData = typeBean.getData();
                            MuChangTypeBean.DataEntity dataEntity = new MuChangTypeBean.DataEntity();
                            dataEntity.setName("全部");//加个全部
                            mData.add(0,dataEntity);
                            tab_list.clear();
                            for (int i = 0; i < mData.size(); i++) {
                                tab_list.add(mData.get(i).getName());
                            }
                            if (fragmentList.size() == 0) {
                                for (int i = 0; i < tab_list.size(); i++) {
                                    JiankongFragment jiankongFragment = new JiankongFragment();
                                    Bundle bundle = new Bundle();
                                    bundle.putString("class_id", mData.get(i).getId() + "");
                                    jiankongFragment.setArguments(bundle);
                                    fragmentList.add(jiankongFragment);
                                }
                            }
                            mTablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
                            for (int i = 0; i < tab_list.size(); i++) {
                                mTablayout.addTab(mTablayout.newTab().setText(tab_list.get(i)));
                            }
                            BaseTabAdapter adapter = new BaseTabAdapter(getSupportFragmentManager(), fragmentList, tab_list);
                            mViewpager.setAdapter(adapter);
                            mTablayout.setupWithViewPager(mViewpager);
                            int class_id = getIntent().getIntExtra("class_id", 0);
                            int position = getClassIdPosition(class_id);
                            mViewpager.setCurrentItem(position);
                        }
                    }
                });
    }

    private int getClassIdPosition(int classid) {
        for (int i = 0; i < mData.size(); i++) {
            if (mData.get(i).getId() == classid) {
                return i;
            }
        }
        return 0;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void myEvent(PlayerBean playerBean) {
        //播放视频
        mVideoplayer.setVisibility(View.VISIBLE);
        mVideoplayer.setUp(playerBean.getVideo_url()
                , JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "");
        mVideoplayer.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(MyApplication.getGloableContext())
                .load(playerBean.getImage())
                .into(mVideoplayer.thumbImageView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }
}
