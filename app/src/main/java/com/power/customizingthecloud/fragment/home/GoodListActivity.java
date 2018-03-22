package com.power.customizingthecloud.fragment.home;

import android.content.Intent;
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
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.activity.mine.ShopCartActivity;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.fragment.shop.bean.GoodListBean;
import com.power.customizingthecloud.login.LoginActivity;
import com.power.customizingthecloud.utils.MyUtils;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.Urls;

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
        mRecyclerShop.setLayoutManager(new GridLayoutManager(this,2));
        String type = getIntent().getStringExtra("type");
        String url="";
        if (type!=null && type.equals("new")){
            url=Urls.BASEURL + "api/v2/good/new-good-list";
        }else if (type!=null && type.equals("hot")){
            url=Urls.BASEURL + "api/v2/good/hot-good-list";
        }
        HttpParams params = new HttpParams();
        params.put("page", "1");
        params.put("limit", "10");
        OkGo.<GoodListBean>get(url)
                .tag(this)
                .params(params)
                .execute(new DialogCallback<GoodListBean>(GoodListActivity.this, GoodListBean.class) {
                    @Override
                    public void onSuccess(Response<GoodListBean> response) {
                        GoodListBean goodListBean = response.body();
                        int code = goodListBean.getCode();
                        if (code == 0) {
                            Toast.makeText(mContext, goodListBean.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (code == 1) {
                            final List<GoodListBean.DataEntity> data = goodListBean.getData();
                            ShopAdapter shopAdapter=new ShopAdapter(R.layout.item_shengxian,data);
                            mRecyclerShop.setAdapter(shopAdapter);
                            shopAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent = new Intent(GoodListActivity.this, GoodDetailActivity.class);
                                    intent.putExtra("id",data.get(position).getId()+"");
                                    startActivity(intent);
                                }
                            });
                        }
                    }
                });
    }

    private class ShopAdapter extends BaseQuickAdapter<GoodListBean.DataEntity, BaseViewHolder> {

        public ShopAdapter(@LayoutRes int layoutResId, @Nullable List<GoodListBean.DataEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, GoodListBean.DataEntity item) {
            ImageView iv_insertcar=helper.getView(R.id.iv_insertcar);
            iv_insertcar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String userid = SpUtils.getString(mContext, "userid", "");
                    if (TextUtils.isEmpty(userid)){
                        startActivity(new Intent(mContext, LoginActivity.class));
                        overridePendingTransition(R.anim.push_bottom_in,R.anim.push_bottom_out);
                        return;
                    }
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
                String userid = SpUtils.getString(mContext, "userid", "");
                if (TextUtils.isEmpty(userid)){
                    startActivity(new Intent(mContext, LoginActivity.class));
                    overridePendingTransition(R.anim.push_bottom_in,R.anim.push_bottom_out);
                    return;
                }
                startActivity(new Intent(this, ShopCartActivity.class));
                break;
        }
    }
}
