package com.power.customizingthecloud.fragment.market;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.power.customizingthecloud.MyApplication;
import com.power.customizingthecloud.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoView;

public class ViewPagerActivity extends AppCompatActivity implements View.OnClickListener {

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
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    private List<String> mPics;
    private int mPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        ButterKnife.bind(this);
        mTitleBackIv.setVisibility(View.VISIBLE);
        mTitleBackIv.setOnClickListener(this);
        mTitleContentTv.setText("");
        mPics = (List<String>) getIntent().getSerializableExtra("pics");
        mPosition = getIntent().getIntExtra("position", 0);
        mViewPager.setAdapter(new SamplePagerAdapter());
        mViewPager.setCurrentItem(mPosition);
        mViewPager.setOffscreenPageLimit(mPics.size());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
        }
    }

    private class SamplePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mPics.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(container.getContext());
            Glide.with(MyApplication.getGloableContext()).load(mPics.get(position)).into(photoView);
            // Now just add PhotoView to ViewPager and return it
            container.addView(photoView, ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.WRAP_CONTENT);

            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }
}
