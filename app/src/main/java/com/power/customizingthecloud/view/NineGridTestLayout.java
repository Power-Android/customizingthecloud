package com.power.customizingthecloud.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.bean.EventBean;
import com.power.customizingthecloud.fragment.market.ViewPagerActivity;
import com.power.customizingthecloud.utils.ImageLoaderUtil;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.List;

/**
 * 描述：
 * 作者：HMY
 * 时间：2016/5/12
 */
public class NineGridTestLayout extends NineGridLayout {

    protected static final int MAX_W_H_RATIO = 3;
    private RequestOptions options = new RequestOptions();

    public NineGridTestLayout(Context context) {
        super(context);
    }

    public NineGridTestLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected boolean displayOneImage(final RatioImageView imageView, String url, final int parentWidth) {
        ImageLoaderUtil.displayImage(mContext, imageView, url, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {

            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap bitmap) {
                int w = bitmap.getWidth();
                int h = bitmap.getHeight();

                int newW;
                int newH;
                if (h > w * MAX_W_H_RATIO) {//h:w = 5:3
                    newW = parentWidth / 2;
                    newH = newW * 5 / 3;
                } else if (h < w) {//h:w = 2:3
                    newW = parentWidth * 2 / 3;
                    newH = newW * 2 / 3;
                } else {//newH:h = newW :w
                    newW = parentWidth / 2;
                    newH = h * newW / w;
                }
                setOneImageLayoutParams(imageView, newW, newH);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {

            }
        });
        return false;
    }

    @Override
    protected void displayImage(RatioImageView imageView, String url, int singleWidth, int singleHeight) {
        //        ImageLoaderUtil.getImageLoader(mContext).displayImage(url, imageView);
        //用imageLoader会出现oom，真是个垃圾框架
        options.placeholder(R.drawable.face);
        options.override(singleWidth, singleHeight);
        Glide.with(mContext).load(url).apply(options).into(imageView);
    }

    @Override
    protected void onClickImage(RatioImageView imageView, int i, String url, List<String> urlList) {
        //        Toast.makeText(mContext, "点击了图片" + url, Toast.LENGTH_SHORT).show();
        EventBus.getDefault().postSticky(new EventBean("closeSoftKeyboard"));
        Intent intent = new Intent(mContext, ViewPagerActivity.class);
        intent.putExtra("pics", (Serializable) urlList);
        intent.putExtra("position", i);
        mContext.startActivity(intent);
    }
}
