package com.power.customizingthecloud.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2018/1/29.
 */

public class CustomViewPager extends ViewPager {
    /*
    * 解决scrollView嵌套viewpager导致的viewpager不显示的问题
    * 遍历ViewPager所有的子View,得到高度，赋值给ViewPager。这样写，是解决了view为null的问题，并且也给ViewPager设置了高度，
      但是，切换页面时，子View的高度与最大的子View的高度不匹配，就会导致，页面下一大片空白部分。
    * */
    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int height = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int h = child.getMeasuredHeight();
            if (h > height)
                height = h;
        }

        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
