package com.power.customizingthecloud.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.activity.mine.ShopCartActivity;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoodListActivity extends BaseActivity implements View.OnClickListener {

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
    @BindView(R.id.title_shopcar_iv)
    ImageView mTitleShopcarIv;
    @BindView(R.id.title_content_right_tv)
    TextView mTitleContentRightTv;
    @BindView(R.id.recycler_shop)
    RecyclerView mRecyclerShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);
        ButterKnife.bind(this);
        mTitleBackIv.setVisibility(View.VISIBLE);
        mTitleBackIv.setOnClickListener(this);
        mTitleContentTv.setText("商品列表");
        mTitleShopcarIv.setVisibility(View.VISIBLE);
        mTitleShopcarIv.setOnClickListener(this);
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        mRecyclerShop.setLayoutManager(new GridLayoutManager(this,2));
        ShopAdapter shopAdapter=new ShopAdapter(R.layout.item_shengxian,list);
        mRecyclerShop.setAdapter(shopAdapter);
        shopAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(GoodListActivity.this,GoodDetailActivity.class));
            }
        });
    }

    private class ShopAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public ShopAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            ImageView iv_insertcar=helper.getView(R.id.iv_insertcar);
            iv_insertcar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(GoodListActivity.this, "加入购物车成功，请去购物车结算~", Toast.LENGTH_SHORT).show();
                }
            });
            ImageView iv_top=helper.getView(R.id.iv_top);
            int width = MyUtils.getScreenWidth(mContext) - MyUtils.dip2px(mContext, 50);
            ViewGroup.LayoutParams layoutParams = iv_top.getLayoutParams();
            layoutParams.height=width/2;
            iv_top.setLayoutParams(layoutParams);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.title_shopcar_iv:
                startActivity(new Intent(this, ShopCartActivity.class));
                break;
        }
    }
}
