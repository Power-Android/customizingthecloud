package com.power.customizingthecloud.activity.mine;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.fragment.home.renyang.BaseTabAdapter;
import com.power.customizingthecloud.fragment.mine.MarketGoodFragment;
import com.power.customizingthecloud.fragment.mine.MarketMaolvFragment;
import com.power.customizingthecloud.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FenxiaoListActivoty extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    NoScrollViewPager viewpager;
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> tab_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list_activoty);
        ButterKnife.bind(this);
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("商品列表");
        if (tab_list.size() == 0) {
            tab_list.add("销售商品");
            tab_list.add("销售毛驴");
        }
        if (fragmentList.size() == 0) {
            fragmentList.add(new MarketGoodFragment());
            fragmentList.add(new MarketMaolvFragment());
        }
        tablayout.setTabMode(TabLayout.MODE_FIXED);
        for (int i = 0; i < tab_list.size(); i++) {
            tablayout.addTab(tablayout.newTab().setText(tab_list.get(i)));
        }
        //设置中间竖线
        LinearLayout linearLayout = (LinearLayout) tablayout.getChildAt(0);
        linearLayout.setDividerPadding(30);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(this,
                R.drawable.layout_divider_vertical));
        BaseTabAdapter adapter = new BaseTabAdapter(getSupportFragmentManager(), fragmentList, tab_list);
        viewpager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewpager);
    }

    @OnClick(R.id.title_back_iv)
    public void onViewClicked() {
        finish();
    }
}
