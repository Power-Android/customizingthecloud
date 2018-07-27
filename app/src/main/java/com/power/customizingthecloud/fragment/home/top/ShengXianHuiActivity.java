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

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.MyApplication;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.fragment.home.GoodDetailActivity;
import com.power.customizingthecloud.fragment.home.LvBuWeiActivity;
import com.power.customizingthecloud.fragment.home.bean.ShengXianBean;
import com.power.customizingthecloud.login.LoginActivity;
import com.power.customizingthecloud.utils.CommonUtils;
import com.power.customizingthecloud.utils.MyUtils;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.Urls;

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
    private int mlvbuweiId;
    private Intent mIntent;
    @BindView(R.id.springview)
    SpringView mSpringview;
    private String after = "";
    private boolean isLoadMore;
    private List<ShengXianBean.DataEntity.GoodsEntity> mGoods;
    private List<ShengXianBean.DataEntity.PositionClassEntity> mPosition_class;

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
        //开启在布局文件中设置的帧动画
        mAnimationDrawable = (AnimationDrawable) mIvEye.getDrawable();
        mAnimationDrawable.start();
        mViewBeibu.setOnClickListener(this);
        mViewLeibu.setOnClickListener(this);
        mViewJianbu.setOnClickListener(this);
        mViewFubu.setOnClickListener(this);
        mViewQiantuibu.setOnClickListener(this);
        mViewHoutuibu.setOnClickListener(this);
        initData();
        initListener();
    }

    private void initListener() {
        mSpringview.setHeader(new DefaultHeader(mContext));
        mSpringview.setFooter(new DefaultFooter(mContext));
        mSpringview.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                isLoadMore = false;
                initData();
                mSpringview.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                isLoadMore = true;
                initData();
                mSpringview.onFinishFreshAndLoad();
            }
        });
    }

    private void initData() {
        HttpParams params = new HttpParams();
        if (isLoadMore) {
            params.put("after", after);
        }
        params.put("limit", "10");
        OkGo.<ShengXianBean>get(Urls.BASEURL + "api/v2/good/fresh-remittance")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<ShengXianBean>(ShengXianHuiActivity.this, ShengXianBean.class) {
                    @Override
                    public void onSuccess(Response<ShengXianBean> response) {
                        ShengXianBean meatBean = response.body();
                        int code = meatBean.getCode();
                        if (code == 0) {
                            Toast.makeText(ShengXianHuiActivity.this, meatBean.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (code == 1) {
                            ShengXianBean.DataEntity data = meatBean.getData();
                            mGoods = data.getGoods();
                            if (!isLoadMore) {
                                mShopAdapter = new XianAdapter(R.layout.item_shengxian, mGoods);
                                mRecyclerXian.setAdapter(mShopAdapter);
                            } else {
                                if (data.getGoods() != null && data.getGoods().size() > 0) {
                                    mGoods.addAll(data.getGoods());
                                    mShopAdapter.notifyDataSetChanged();
                                } else {
                                    Toast.makeText(mContext, "没有更多了~", Toast.LENGTH_SHORT).show();
                                }
                            }
                            mShopAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent = new Intent(ShengXianHuiActivity.this, GoodDetailActivity.class);
                                    intent.putExtra("id",mGoods.get(position).getId()+"");
                                    startActivity(intent);
                                }
                            });
                            mPosition_class = data.getPosition_class();
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAnimationDrawable.stop();
        mAnimationDrawable = null;
    }

    private class XianAdapter extends BaseQuickAdapter<ShengXianBean.DataEntity.GoodsEntity, BaseViewHolder> {

        public XianAdapter(@LayoutRes int layoutResId, @Nullable List<ShengXianBean.DataEntity.GoodsEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final ShengXianBean.DataEntity.GoodsEntity item) {
            after = item.getId() + "";
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
                    //                    Toast.makeText(mContext, "加入购物车成功，请去购物车结算~", Toast.LENGTH_SHORT).show();
                    CommonUtils.insertCar(ShengXianHuiActivity.this, item.getId() + "", "good");
                }
            });
            ImageView iv_top = helper.getView(R.id.iv_top);
            int width = MyUtils.getScreenWidth(mContext) - MyUtils.dip2px(mContext, 50);
            ViewGroup.LayoutParams layoutParams = iv_top.getLayoutParams();
            layoutParams.height = width / 2;
            iv_top.setLayoutParams(layoutParams);
            Glide.with(MyApplication.getGloableContext()).load(item.getImage()).into(iv_top);
            helper.setText(R.id.tv_name, item.getName())
                    .setText(R.id.tv_price, "¥"+item.getPrice());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.view_beibu:
                mlvbuweiId = mPosition_class.get(1).getId();
                mIntent = new Intent(mContext, LvBuWeiActivity.class);
                mIntent.putExtra("id", mlvbuweiId + "");
                startActivity(mIntent);
                break;
            case R.id.view_leibu:
                mlvbuweiId = mPosition_class.get(2).getId();
                mIntent = new Intent(mContext, LvBuWeiActivity.class);
                mIntent.putExtra("id", mlvbuweiId + "");
                startActivity(mIntent);
                break;
            case R.id.view_jianbu:
                mlvbuweiId = mPosition_class.get(0).getId();
                mIntent = new Intent(mContext, LvBuWeiActivity.class);
                mIntent.putExtra("id", mlvbuweiId + "");
                startActivity(mIntent);
                break;
            case R.id.view_fubu:
                mlvbuweiId = mPosition_class.get(3).getId();
                mIntent = new Intent(mContext, LvBuWeiActivity.class);
                mIntent.putExtra("id", mlvbuweiId + "");
                startActivity(mIntent);
                break;
            case R.id.view_qiantuibu:
                mlvbuweiId = mPosition_class.get(4).getId();
                mIntent = new Intent(mContext, LvBuWeiActivity.class);
                mIntent.putExtra("id", mlvbuweiId + "");
                startActivity(mIntent);
                break;
            case R.id.view_houtuibu:
                mlvbuweiId = mPosition_class.get(5).getId();
                mIntent = new Intent(mContext, LvBuWeiActivity.class);
                mIntent.putExtra("id", mlvbuweiId + "");
                startActivity(mIntent);
                break;
        }
    }
}
