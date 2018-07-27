package com.power.customizingthecloud.fragment.shop;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.MyApplication;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.activity.mine.LatestActivity;
import com.power.customizingthecloud.base.BaseFragment;
import com.power.customizingthecloud.bean.EventBean;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.callback.JsonCallback;
import com.power.customizingthecloud.fragment.home.GoodDetailActivity;
import com.power.customizingthecloud.fragment.home.GoodListActivity;
import com.power.customizingthecloud.fragment.home.MiaoShaDetailActivity;
import com.power.customizingthecloud.fragment.home.top.KaiDianActivity;
import com.power.customizingthecloud.fragment.home.top.MiaoShaActivity;
import com.power.customizingthecloud.fragment.shop.bean.ShopAllBean;
import com.power.customizingthecloud.login.LoginActivity;
import com.power.customizingthecloud.login.bean.RegisterBean;
import com.power.customizingthecloud.utils.BannerUtils;
import com.power.customizingthecloud.utils.CommonUtils;
import com.power.customizingthecloud.utils.MyUtils;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.Urls;
import com.power.customizingthecloud.view.BaseDialog;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.iwgang.countdownview.CountdownView;

/**
 * Created by Administrator on 2018/2/2.
 */

public class ShopAllFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.cv_countdownView)
    CountdownView mCvCountdownView;
    @BindView(R.id.tv_xianlianggou)
    TextView mTvXianlianggou;
    @BindView(R.id.tv_saishangjishi)
    TextView mTvSaishangjishi;
    @BindView(R.id.tv_kaidian)
    TextView mTvKaidian;
    @BindView(R.id.tv_meichu)
    TextView mTvMeichu;
    @BindView(R.id.tv_newactivity)
    TextView mTvNewactivity;
    @BindView(R.id.iv_newproduct_more)
    ImageView mIvNewproductMore;
    @BindView(R.id.recycler_new)
    RecyclerView mRecyclerNew;
    @BindView(R.id.iv_hotproduct_more)
    ImageView mIvHotproductMore;
    @BindView(R.id.recycler_hot)
    RecyclerView mRecyclerHot;
    @BindView(R.id.iv_miaosha_more)
    ImageView mIvMiaoshaMore;
    @BindView(R.id.recycler_miaosha)
    RecyclerView mRecyclerMiaosha;
    @BindView(R.id.iv_quan_more)
    ImageView mIvQuanMore;
    @BindView(R.id.iv_miaosha)
    ImageView iv_miaosha;
    @BindView(R.id.recycler_quan)
    RecyclerView mRecyclerQuan;
    @BindView(R.id.ll_miaosha)
    LinearLayout ll_miaosha;
    Unbinder unbinder;
    private HotProductAdapter mHotProductAdapter;
    private NewProductAdapter mNewProductAdapter;
    private MiaoshaAdapter mMiaoshaAdapter;
    private QuanAdapter mQuanAdapter;
    private BaseDialog mDialog;
    private BaseDialog.Builder mBuilder;
    private List<String> imgList = new ArrayList<>();
    private ShopAllBean.DataEntity.HotSeckillEntity mHot_seckill;
    @BindView(R.id.springview)
    SpringView mSpringview;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopall, null);
        unbinder = ButterKnife.bind(this, view);
        mIvNewproductMore.setOnClickListener(this);
        mIvHotproductMore.setOnClickListener(this);
        mIvMiaoshaMore.setOnClickListener(this);
        ll_miaosha.setOnClickListener(this);
        mIvQuanMore.setOnClickListener(this);
        mTvSaishangjishi.setOnClickListener(this);
        mTvKaidian.setOnClickListener(this);
        mTvMeichu.setOnClickListener(this);
        mTvNewactivity.setOnClickListener(this);
        return view;
    }

    @Override
    protected void initLazyData() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerNew.setNestedScrollingEnabled(false);
        mRecyclerNew.setLayoutManager(new GridLayoutManager(mContext, 2));
        mRecyclerHot.setNestedScrollingEnabled(false);
        mRecyclerHot.setLayoutManager(new GridLayoutManager(mContext, 2));
        mRecyclerMiaosha.setNestedScrollingEnabled(false);
        mRecyclerMiaosha.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerQuan.setNestedScrollingEnabled(false);
        mRecyclerQuan.setLayoutManager(new LinearLayoutManager(mContext));
        initData();
        initListener();
    }

    private void initData() {
        OkGo.<ShopAllBean>get(Urls.BASEURL + "api/v2/good")
                .tag(this)
                .execute(new JsonCallback<ShopAllBean>(ShopAllBean.class) {
                    @Override
                    public void onSuccess(Response<ShopAllBean> response) {
                        ShopAllBean bean = response.body();
                        int code = bean.getCode();
                        if (code == 0) {
                            Toast.makeText(mContext, bean.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (code == 1) {
                            ShopAllBean.DataEntity data = bean.getData();
                            final List<ShopAllBean.DataEntity.GoodSlidEntity> good_slid = data.getGood_slid();
                            imgList.clear();
                            for (int i = 0; i < good_slid.size(); i++) {
                                imgList.add(good_slid.get(i).getImage_url());
                            }
                            BannerUtils.startBanner(mBanner, imgList);
                            mBanner.setOnBannerListener(new OnBannerListener() {
                                @Override
                                public void OnBannerClick(int position) {
                                    if (good_slid.get(position).getType() == 1) {
                                        Intent intent = new Intent(mContext, GoodDetailActivity.class);
                                        intent.putExtra("id",good_slid.get(position).getId()+"");
                                        startActivity(intent);
                                    }
                                }
                            });
                            final List<ShopAllBean.DataEntity.NewGoodEntity> new_good = data.getNew_good();
                            mNewProductAdapter = new NewProductAdapter(R.layout.item_product, new_good);
                            mRecyclerNew.setAdapter(mNewProductAdapter);
                            mNewProductAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent = new Intent(mContext, GoodDetailActivity.class);
                                    intent.putExtra("id",new_good.get(position).getId()+"");
                                    startActivity(intent);
                                }
                            });
                            final List<ShopAllBean.DataEntity.HotGoodEntity> hot_good = data.getHot_good();
                            mHotProductAdapter = new HotProductAdapter(R.layout.item_product, hot_good);
                            mRecyclerHot.setAdapter(mHotProductAdapter);
                            mHotProductAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent = new Intent(mContext, GoodDetailActivity.class);
                                    intent.putExtra("id",hot_good.get(position).getId()+"");
                                    startActivity(intent);
                                }
                            });
                            final List<ShopAllBean.DataEntity.SeckillGoodEntity> seckill_good = data.getSeckill_good();
                            mMiaoshaAdapter = new MiaoshaAdapter(R.layout.item_home_miaosha, seckill_good);
                            mRecyclerMiaosha.setAdapter(mMiaoshaAdapter);
                            mMiaoshaAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent = new Intent(mContext, MiaoShaDetailActivity.class);
                                    intent.putExtra("id",seckill_good.get(position).getId()+"");
                                    startActivity(intent);
                                }
                            });
                            List<ShopAllBean.DataEntity.VoucherTemplateEntity> voucher_template = data.getVoucher_template();
                            mQuanAdapter = new QuanAdapter(R.layout.item_daijinquan, voucher_template);
                            mRecyclerQuan.setAdapter(mQuanAdapter);
                            mQuanAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    startActivity(new Intent(mContext,QuanListActivity.class));
                                }
                            });

                            mHot_seckill = data.getHot_seckill();
                            Glide.with(MyApplication.getGloableContext()).load(mHot_seckill.getImage()).into(iv_miaosha);
                            mTvXianlianggou.setText(mHot_seckill.getPrice()+"元");
                            long time = mHot_seckill.getSeckill_end_time()*1000L - System.currentTimeMillis();
                            mCvCountdownView.start(time);
                        }
                    }
                });
    }

    private void initListener() {
        mSpringview.setHeader(new DefaultHeader(mContext));
        mSpringview.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                initData();
                mSpringview.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_newproduct_more:
                Intent intent = new Intent(mContext, GoodListActivity.class);
                intent.putExtra("type","new");
                startActivity(intent);
                break;
            case R.id.iv_hotproduct_more:
                Intent intent1 = new Intent(mContext, GoodListActivity.class);
                intent1.putExtra("type","hot");
                startActivity(intent1);
                break;
            case R.id.iv_miaosha_more:
                startActivity(new Intent(mContext, MiaoShaActivity.class));
                break;
            case R.id.ll_miaosha:
                Intent intent2 = new Intent(mContext, MiaoShaDetailActivity.class);
                intent2.putExtra("id",mHot_seckill.getId()+"");
                startActivity(intent2);
                break;
            case R.id.iv_quan_more:
                startActivity(new Intent(mContext, QuanListActivity.class));
                break;
            case R.id.tv_saishangjishi:
                EventBus.getDefault().postSticky(new EventBean("checkganji"));
                break;
            case R.id.tv_kaidian:
                String userid = SpUtils.getString(mContext, "userid", "");
                if (TextUtils.isEmpty(userid)){
                    startActivity(new Intent(mContext, LoginActivity.class));
                    return;
                }
                startActivity(new Intent(mContext, KaiDianActivity.class));
                break;
            case R.id.tv_meichu:
                startActivity(new Intent(mContext, VideoListActivity.class));
                break;
            case R.id.tv_newactivity:
                startActivity(new Intent(mContext, LatestActivity.class));
                break;
        }
    }

    private class NewProductAdapter extends BaseQuickAdapter<ShopAllBean.DataEntity.NewGoodEntity, BaseViewHolder> {

        public NewProductAdapter(@LayoutRes int layoutResId, @Nullable List<ShopAllBean.DataEntity.NewGoodEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final ShopAllBean.DataEntity.NewGoodEntity item) {
            ImageView iv_insertcar = helper.getView(R.id.iv_insertcar);
            iv_insertcar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String userid = SpUtils.getString(mContext, "userid", "");
                    if (TextUtils.isEmpty(userid)) {
                        startActivity(new Intent(mContext, LoginActivity.class));
                        mActivity.overridePendingTransition(R.anim.push_bottom_in, R.anim.push_bottom_out);
                        return;
                    }
                    CommonUtils.insertCar(mActivity,item.getId()+"",item.getGood_type());
                }
            });
            ImageView iv_top = helper.getView(R.id.iv_top);
            int width = MyUtils.getScreenWidth(mContext) - MyUtils.dip2px(mContext, 50);
            ViewGroup.LayoutParams layoutParams = iv_top.getLayoutParams();
            layoutParams.height = width / 2;
            iv_top.setLayoutParams(layoutParams);
            Glide.with(MyApplication.getGloableContext()).load(item.getImage()).into(iv_top);
            helper.setText(R.id.tv_name,item.getName())
                    .setText(R.id.tv_price,"¥"+item.getPrice());
        }
    }

    private class HotProductAdapter extends BaseQuickAdapter<ShopAllBean.DataEntity.HotGoodEntity, BaseViewHolder> {

        public HotProductAdapter(@LayoutRes int layoutResId, @Nullable List<ShopAllBean.DataEntity.HotGoodEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final ShopAllBean.DataEntity.HotGoodEntity item) {
            ImageView iv_insertcar = helper.getView(R.id.iv_insertcar);
            iv_insertcar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String userid = SpUtils.getString(mContext, "userid", "");
                    if (TextUtils.isEmpty(userid)) {
                        startActivity(new Intent(mContext, LoginActivity.class));
                        mActivity.overridePendingTransition(R.anim.push_bottom_in, R.anim.push_bottom_out);
                        return;
                    }
                    CommonUtils.insertCar(mActivity,item.getId()+"",item.getGood_type());
                }
            });
            ImageView iv_top = helper.getView(R.id.iv_top);
            int width = MyUtils.getScreenWidth(mContext) - MyUtils.dip2px(mContext, 50);
            ViewGroup.LayoutParams layoutParams = iv_top.getLayoutParams();
            layoutParams.height = width / 2;
            iv_top.setLayoutParams(layoutParams);
            Glide.with(MyApplication.getGloableContext()).load(item.getImage()).into(iv_top);
            helper.setText(R.id.tv_name,item.getName())
                    .setText(R.id.tv_price,"¥"+item.getPrice());
        }
    }

    private class MiaoshaAdapter extends BaseQuickAdapter<ShopAllBean.DataEntity.SeckillGoodEntity, BaseViewHolder> {

        public MiaoshaAdapter(@LayoutRes int layoutResId, @Nullable List<ShopAllBean.DataEntity.SeckillGoodEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ShopAllBean.DataEntity.SeckillGoodEntity item) {
            TextView tv_yuanjia = helper.getView(R.id.tv_yuanjia);
            tv_yuanjia.setText(item.getPrice());
            //添加删除线
            tv_yuanjia.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            CountdownView cv_countdownView = helper.getView(R.id.cv_countdownView);
            long time = item.getSeckill_end_time() * 1000L - System.currentTimeMillis();
            cv_countdownView.start(time); // Millisecond
            ImageView iv_img = helper.getView(R.id.iv_image);
            Glide.with(MyApplication.getGloableContext()).load(item.getImage()).into(iv_img);
            helper.setText(R.id.tv_title, item.getName())
                    .setText(R.id.tv_curprice, item.getSeckill_price())
                    .setText(R.id.tv_last_count, item.getSeckill_storage() + "");
        }
    }

    private class QuanAdapter extends BaseQuickAdapter<ShopAllBean.DataEntity.VoucherTemplateEntity, BaseViewHolder> {

        public QuanAdapter(@LayoutRes int layoutResId, @Nullable List<ShopAllBean.DataEntity.VoucherTemplateEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final ShopAllBean.DataEntity.VoucherTemplateEntity item) {
            TextView tv_lingqu = helper.getView(R.id.tv_lingqu);
            tv_lingqu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String userid = SpUtils.getString(mContext, "userid", "");
                    if (TextUtils.isEmpty(userid)) {
                        startActivity(new Intent(mContext, LoginActivity.class));
                        mActivity.overridePendingTransition(R.anim.push_bottom_in, R.anim.push_bottom_out);
                        return;
                    }
                    getQuan(item.getId()+"");
                }
            });
            helper.setText(R.id.item_money_tv,item.getPrice()+"")
                    .setText(R.id.item_man_jian_tv,"满¥"+item.getOrder_limit()+"使用")
                    .setText(R.id.item_name_tv,item.getTitle())
                    .setText(R.id.item_use_tv,item.getDescribe())
                    .setText(R.id.item_date_tv,"使用期限："+item.getStart_date()+"-"+item.getEnd_date());
        }
    }

    private void getQuan(String quanId) {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));
        HttpParams params = new HttpParams();
        params.put("id", quanId);
        OkGo.<RegisterBean>get(Urls.BASEURL + "api/v2/get-voucher")
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<RegisterBean>(mActivity, RegisterBean.class) {
                    @Override
                    public void onSuccess(Response<RegisterBean> response) {
                        RegisterBean bean = response.body();
                        int code = bean.getCode();
                        if (code == 0) {
                            Toast.makeText(mActivity, bean.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (code == 1) {
                            Toast.makeText(mActivity, bean.getMessage(), Toast.LENGTH_SHORT).show();
                            showLingquDialog();
                        }
                    }
                });
    }

    private void showLingquDialog() {
        mBuilder = new BaseDialog.Builder(mContext);
        mDialog = mBuilder.setViewId(R.layout.dialog_getquan)
                //设置dialogpadding
                .setPaddingdp(0, 0, 0, 0)
                //设置显示位置
                .setGravity(Gravity.CENTER)
                //设置动画
                .setAnimation(R.style.Alpah_aniamtion)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(true)
                //设置监听事件
                .builder();
        mDialog.show();
        mDialog.getView(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        mDialog.getView(R.id.tv_yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                Intent intent1 = new Intent(mContext, GoodListActivity.class);
                intent1.putExtra("type","hot");
                startActivity(intent1);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
