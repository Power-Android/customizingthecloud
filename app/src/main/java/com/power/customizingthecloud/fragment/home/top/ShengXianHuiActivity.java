package com.power.customizingthecloud.fragment.home.top;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.fragment.home.GoodDetailActivity;
import com.power.customizingthecloud.fragment.home.GoodListActivity;
import com.power.customizingthecloud.login.LoginActivity;
import com.power.customizingthecloud.utils.MyUtils;
import com.power.customizingthecloud.utils.SpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShengXianHuiActivity extends BaseActivity implements View.OnClickListener {

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
    @BindView(R.id.banner)
    ImageView mBanner;
    @BindView(R.id.recycler_xian)
    RecyclerView mRecyclerXian;
    @BindView(R.id.iv_eye)
    ImageView mIvEye;
    @BindView(R.id.title_list_iv)
    ImageView mTitleListIv;
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
    @BindView(R.id.title_kefu_iv)
    ImageView mTitleKefuIv;
    @BindView(R.id.view_beibu)
    View mViewBeibu;
    @BindView(R.id.view_leibu)
    View mViewLeibu;
    @BindView(R.id.view_jianbu)
    View mViewJianbu;
    @BindView(R.id.view_fubu)
    View mViewFubu;
    @BindView(R.id.view_qiantuibu)
    View mViewQiantuibu;
    @BindView(R.id.view_houtuibu)
    View mViewHoutuibu;
    private XianAdapter mShopAdapter;
    private AnimationDrawable mAnimationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheng_xian_hui);
        ButterKnife.bind(this);
        mTitleBackIv.setVisibility(View.VISIBLE);
        mTitleBackIv.setOnClickListener(this);
        mTitleContentTv.setText("生鲜汇");
        mRecyclerXian.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerXian.setNestedScrollingEnabled(false);
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        mShopAdapter = new XianAdapter(R.layout.item_shengxian, list);
        mRecyclerXian.setAdapter(mShopAdapter);
        mShopAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(ShengXianHuiActivity.this, GoodDetailActivity.class));
            }
        });
        //开启在布局文件中设置的帧动画
        mAnimationDrawable = (AnimationDrawable) mIvEye.getDrawable();
        mAnimationDrawable.start();
        mViewBeibu.setOnClickListener(this);
        mViewLeibu.setOnClickListener(this);
        mViewJianbu.setOnClickListener(this);
        mViewFubu.setOnClickListener(this);
        mViewQiantuibu.setOnClickListener(this);
        mViewHoutuibu.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAnimationDrawable.stop();
        mAnimationDrawable = null;
    }

    private class XianAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public XianAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            ImageView iv_insertcar = helper.getView(R.id.iv_insertcar);
            iv_insertcar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String userid = SpUtils.getString(mContext, "userid", "");
                    if (TextUtils.isEmpty(userid)) {
                        startActivity(new Intent(mContext, LoginActivity.class));
                        overridePendingTransition(R.anim.push_bottom_in, R.anim.push_bottom_out);
                        return;
                    }
                    Toast.makeText(ShengXianHuiActivity.this, "加入购物车成功，请去购物车结算~", Toast.LENGTH_SHORT).show();
                }
            });
            ImageView iv_top = helper.getView(R.id.iv_top);
            int width = MyUtils.getScreenWidth(mContext) - MyUtils.dip2px(mContext, 50);
            ViewGroup.LayoutParams layoutParams = iv_top.getLayoutParams();
            layoutParams.height = width / 2;
            iv_top.setLayoutParams(layoutParams);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.view_beibu:
                startActivity(new Intent(ShengXianHuiActivity.this, GoodListActivity.class));
                break;
            case R.id.view_leibu:
                startActivity(new Intent(ShengXianHuiActivity.this, GoodListActivity.class));
                break;
            case R.id.view_jianbu:
                startActivity(new Intent(ShengXianHuiActivity.this, GoodListActivity.class));
                break;
            case R.id.view_fubu:
                startActivity(new Intent(ShengXianHuiActivity.this, GoodListActivity.class));
                break;
            case R.id.view_qiantuibu:
                startActivity(new Intent(ShengXianHuiActivity.this, GoodListActivity.class));
                break;
            case R.id.view_houtuibu:
                startActivity(new Intent(ShengXianHuiActivity.this, GoodListActivity.class));
                break;
        }
    }
}
