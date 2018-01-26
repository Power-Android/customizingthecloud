package com.power.customizingthecloud.activity.mine;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.fragment.mine.MyInteractionFragment;
import com.power.customizingthecloud.fragment.mine.MyNotifacationFragment;
import com.power.customizingthecloud.view.NoScrollViewPager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyMessageActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.message_tablayout)
    TabLayout messageTablayout;
    @BindView(R.id.viewpager)
    NoScrollViewPager viewpager;
    private List<Fragment> fragmentList;
    private List<String> tab_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_message);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setOnClickListener(this);
        //初始化fragment
        MyNotifacationFragment notifacationFragment = new MyNotifacationFragment();
        MyInteractionFragment interactionFragment = new MyInteractionFragment();
        //将fragment装进列表中
        fragmentList = new ArrayList<>();
        fragmentList.add(notifacationFragment);
        fragmentList.add(interactionFragment);
        //设置TabLayout
        tab_list = new ArrayList<>();
        tab_list.add("通知");
        tab_list.add("互动");
        //动态设置指示器下划线长度
        messageTablayout.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(messageTablayout, 15, 15);
            }
        });
        messageTablayout.setTabMode(TabLayout.MODE_FIXED);
        messageTablayout.addTab(messageTablayout.newTab().setText(tab_list.get(0)));
        messageTablayout.addTab(messageTablayout.newTab().setText(tab_list.get(1)));

        MessageTabAdapter messageTabAdapter = new MessageTabAdapter(getSupportFragmentManager(),fragmentList,tab_list);
        viewpager.setAdapter(messageTabAdapter);
        messageTablayout.setupWithViewPager(viewpager);
    }

    //动态设置指示器下划线长度
    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }

    public class MessageTabAdapter extends FragmentPagerAdapter {

        private FragmentManager fm;
        private List<Fragment> fragmentList;
        private List<String> tab_list;

        public MessageTabAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> tab_list) {
            super(fm);
            this.fm = fm;
            this.fragmentList = fragmentList;
            this.tab_list = tab_list;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return tab_list.size();
        }

        //此方法用来显示tab上的名字
        @Override
        public CharSequence getPageTitle(int position) {
            return tab_list.get(position % tab_list.size());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_back_iv:
                finish();
                break;
        }
    }
}
