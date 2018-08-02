package com.power.customizingthecloud.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.MainActivity;
import com.power.customizingthecloud.MyApplication;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.callback.JsonCallback;
import com.power.customizingthecloud.login.bean.GuidePageBean;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.Urls;
import com.power.customizingthecloud.utils.ViewPagerIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/24.
 */

public class GuidePageActivity extends BaseActivity {
    private ViewPager vp;
    private GuidePagerAdapter mGuidePagerAdapter;
    private int[] imgurls = {R.drawable.guide1, R.drawable.guide2, R.drawable.guide3};
    private List<String> imgUrlList = new ArrayList<>();
    private boolean isNetImg = true;
    private LinearLayout liner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guidepage);
        vp = (ViewPager) findViewById(R.id.vp);
        liner = (LinearLayout) findViewById(R.id.liner);
        OkGo.<GuidePageBean>get(Urls.BASEURL + "api/v2/bootstrapper")
                .tag(this)
                .execute(new JsonCallback<GuidePageBean>(GuidePageBean.class) {
                    @Override
                    public void onSuccess(Response<GuidePageBean> response) {
                        GuidePageBean guidePageBean = response.body();
                        int code = guidePageBean.getCode();
                        //                        Toast.makeText(GuidePageActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        if (code == 0) {
                            isNetImg = false;
                            if (mGuidePagerAdapter == null) {
                                mGuidePagerAdapter = new GuidePagerAdapter();
                            }
                            vp.setAdapter(mGuidePagerAdapter);
                        } else if (code == 1) {
                            List<GuidePageBean.DataEntity.GuideEntity> guide =
                                    guidePageBean.getData().getGuide();
                            if (guide != null && guide.size() > 0) {
                                for (int i = 0; i < guide.size(); i++) {
                                    imgUrlList.add(guide.get(i).getImgurl());
                                }
                            } else {
                                isNetImg = false;
                            }
                            if (mGuidePagerAdapter == null) {
                                mGuidePagerAdapter = new GuidePagerAdapter();
                            }
                            vp.setOnPageChangeListener(new ViewPagerIndicator(GuidePageActivity.this, vp, liner, imgUrlList.size()));
                            vp.setAdapter(mGuidePagerAdapter);
                        }
                    }

                    @Override
                    public void onError(Response<GuidePageBean> response) {
                        super.onError(response);
                        isNetImg = false;
                        if (mGuidePagerAdapter == null) {
                            mGuidePagerAdapter = new GuidePagerAdapter();
                        }
                        vp.setAdapter(mGuidePagerAdapter);
                    }
                });
    }

    private class GuidePagerAdapter extends PagerAdapter {
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = View.inflate(GuidePageActivity.this, R.layout.item_guide, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.iv_guide);
            ImageView tv_into = (ImageView) view.findViewById(R.id.tv_into);
            tv_into.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SpUtils.putBoolean(GuidePageActivity.this, "guide", true);
                    Intent intent = new Intent(GuidePageActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            if (isNetImg) {
                Glide.with(MyApplication.getGloableContext()).load(imgUrlList.get(position)).into(imageView);
                if (position == imgUrlList.size() - 1) {
                    tv_into.setVisibility(View.VISIBLE);
                } else {
                    tv_into.setVisibility(View.GONE);
                }
            } else {
                if (position == 2) {
                    tv_into.setVisibility(View.VISIBLE);
                } else {
                    tv_into.setVisibility(View.GONE);
                }
                imageView.setImageResource(imgurls[position]);
            }
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            if (imgUrlList != null && imgUrlList.size() > 0) {
                return imgUrlList.size();
            } else {
                return 3;
            }
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
