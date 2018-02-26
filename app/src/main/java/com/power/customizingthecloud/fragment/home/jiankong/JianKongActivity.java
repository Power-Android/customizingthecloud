package com.power.customizingthecloud.fragment.home.jiankong;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.power.customizingthecloud.MyApplication;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.EventBean;
import com.power.customizingthecloud.fragment.home.renyang.BaseTabAdapter;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jian_kong);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        mTitleBackIv.setVisibility(View.VISIBLE);
        mTitleBackIv.setOnClickListener(this);
        mTitleContentTv.setText("监控列表");
        if (tab_list.size() == 0) {
            //            tab_list.add("全部");
            tab_list.add("毛驴运动场A区");
            tab_list.add("毛驴运动场B区");
            tab_list.add("粗饲料仓储区");
            tab_list.add("驴妈妈饲养区");
            tab_list.add("驴妈妈繁殖区");
            tab_list.add("驴宝宝饲养区");
            tab_list.add("精料加工厂");
            tab_list.add("隔离检疫区");
            tab_list.add("屠宰车间");
            tab_list.add("驴肉食品及阿胶深加工车间");
        }
        if (fragmentList.size() == 0) {
            for (int i = 0; i < tab_list.size(); i++) {
                fragmentList.add(new JiankongFragment());
            }
        }
        mTablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        for (int i = 0; i < tab_list.size(); i++) {
            mTablayout.addTab(mTablayout.newTab().setText(tab_list.get(i)));
        }
        BaseTabAdapter adapter = new BaseTabAdapter(getSupportFragmentManager(), fragmentList, tab_list);
        mViewpager.setAdapter(adapter);
        mTablayout.setupWithViewPager(mViewpager);
        int position = getIntent().getIntExtra("position", 0);
        mViewpager.setCurrentItem(position);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void myEvent(EventBean eventBean) {
        if (eventBean.getMsg().equals("player")) {//播放视频
            mVideoplayer.setVisibility(View.VISIBLE);
            mVideoplayer.setUp("http://www.170mv.com/tool/jiexi/ajax/pid/13053/vid/3155386.mp4"
                    , JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "");
            mVideoplayer.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(MyApplication.getGloableContext())
                    .load("http://jzvd-pic.nathen.cn/jzvd-pic/00b026e7-b830-4994-bc87-38f4033806a6.jpg")
                    .into(mVideoplayer.thumbImageView);
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
