package com.power.customizingthecloud.fragment.home;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.MyApplication;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.activity.mine.FortuneCenterAcitivity;
import com.power.customizingthecloud.activity.mine.LatestActivity;
import com.power.customizingthecloud.activity.mine.LatestDetailActivity;
import com.power.customizingthecloud.activity.mine.MyDonkeyEarsActivity;
import com.power.customizingthecloud.activity.mine.MyMessageActivity;
import com.power.customizingthecloud.activity.mine.WebDetailActivity;
import com.power.customizingthecloud.base.BaseFragment;
import com.power.customizingthecloud.bean.BaseBean;
import com.power.customizingthecloud.bean.EventBean;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.callback.JsonCallback;
import com.power.customizingthecloud.fragment.home.bean.HomeBean;
import com.power.customizingthecloud.fragment.home.jiankong.JianKongActivity;
import com.power.customizingthecloud.fragment.home.renyang.RenYangDetailActivity;
import com.power.customizingthecloud.fragment.home.renyang.RenYangListActivity;
import com.power.customizingthecloud.fragment.home.top.CanWeiYuDingAcitivity;
import com.power.customizingthecloud.fragment.home.top.KaiDianActivity;
import com.power.customizingthecloud.fragment.home.top.MiaoShaActivity;
import com.power.customizingthecloud.fragment.home.top.ShengXianHuiActivity;
import com.power.customizingthecloud.fragment.home.top.XinShouZhiYinActivity;
import com.power.customizingthecloud.fragment.home.top.ZiXunActivity;
import com.power.customizingthecloud.login.LoginActivity;
import com.power.customizingthecloud.utils.BannerUtils;
import com.power.customizingthecloud.utils.MyUtils;
import com.power.customizingthecloud.utils.SoftKeyboardTool;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.Urls;
import com.power.customizingthecloud.view.BaseDialog;
import com.power.customizingthecloud.view.CommonPopupWindow;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.iwgang.countdownview.CountdownView;

import static com.power.customizingthecloud.R.id.tv_toutiao;

/**
 * Created by Administrator on 2018/1/19.
 * 首页
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener {

    private static final int REQUEST_CODE_ZXING = 5;
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
    Banner mBanner;
    @BindView(R.id.recycler_top)
    RecyclerView mRecyclerTop;
    @BindView(tv_toutiao)
    TextView mTvToutiao;
    @BindView(R.id.tv_nianshouyi)
    TextView mTvNianshouyi;
    @BindView(R.id.tv_yangzhichengben)
    TextView mTvYangzhichengben;
    @BindView(R.id.tv_touzizhouqi)
    TextView mTvTouzizhouqi;
    @BindView(R.id.tv_shengyu)
    TextView mTvShengyu;
    @BindView(R.id.tv_qiang)
    TextView mTvQiang;
    @BindView(R.id.recycler_middle)
    RecyclerView mRecyclerMiddle;
    @BindView(R.id.iv_jiankong_more)
    ImageView mIvJiankongMore;
    @BindView(R.id.recycler_jiankong)
    RecyclerView mRecyclerJiankong;
    @BindView(R.id.iv_good_more)
    ImageView mIvGoodMore;
    @BindView(R.id.recycler_good)
    RecyclerView mRecyclerGood;
    @BindView(R.id.iv_miaosha_more)
    ImageView mIvMiaoshaMore;
    @BindView(R.id.title_jia_iv)
    ImageView mTitleJiaiv;
    @BindView(R.id.recycler_miaosha)
    RecyclerView mRecyclerMiaosha;
    Unbinder unbinder;
    private List<String> topStringList = new ArrayList<>();
    private List<Integer> topPicList = new ArrayList<>();
    private TopAdapter mTopAdapter;
    private MiddleAdapter mMiddleAdapter;
    private JianKongAdapter mJianKongAdapter;
    private GoodAdapter mGoodAdapter;
    private MiaoshaAdapter mMiaoshaAdapter;
    private CommonPopupWindow popupWindow;
    private BaseDialog mDialog;
    private BaseDialog.Builder mBuilder;
    private List<String> bannerList = new ArrayList<>();
    private List<HomeBean.DataEntity.DonkeyEntity> mDonkey;
    private List<HomeBean.DataEntity.MuchangEntity> mMuchang;
    private List<HomeBean.DataEntity.HotGoodsEntity> mHot_goods;
    private List<HomeBean.DataEntity.SeckillGoodEntity> mSeckill_good;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mTitleContentTv.setText("养驴啦");
        mTitleMessageIv.setVisibility(View.VISIBLE);
        mTitleMessageIv.setOnClickListener(this);
        mTitleJiaiv.setVisibility(View.VISIBLE);
        mTitleJiaiv.setOnClickListener(this);
        mTvToutiao.requestFocus();//有趣，在布局文件中也设置了获取了焦点，但是没有，在代码中加上就好使了
        mRecyclerTop.setNestedScrollingEnabled(false);
        mRecyclerTop.setLayoutManager(new GridLayoutManager(mContext, 5));
        initTop();
        mRecyclerMiddle.setNestedScrollingEnabled(false);
        mRecyclerMiddle.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerJiankong.setLayoutManager(new GridLayoutManager(mContext, 3));
        mRecyclerJiankong.setNestedScrollingEnabled(false);
        mRecyclerGood.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerGood.setNestedScrollingEnabled(false);
        mRecyclerMiaosha.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerMiaosha.setNestedScrollingEnabled(false);
        mTvQiang.setOnClickListener(this);
        mIvJiankongMore.setOnClickListener(this);
        mIvMiaoshaMore.setOnClickListener(this);
        mIvGoodMore.setOnClickListener(this);
        OkGo.<HomeBean>get(Urls.BASEURL + "api/v2/index")
                .tag(this)
                .execute(new DialogCallback<HomeBean>(mActivity, HomeBean.class) {
                    @Override
                    public void onSuccess(Response<HomeBean> response) {
                        HomeBean homeBean = response.body();
                        int code = homeBean.getCode();
                        if (code == 0) {
                            Toast.makeText(mContext, homeBean.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (code == 1) {
                            bannerList.clear();
                            HomeBean.DataEntity data = homeBean.getData();
                            final List<HomeBean.DataEntity.HomeslidEntity> homeslid = data.getHomeslid();
                            for (int i = 0; i < homeslid.size(); i++) {
                                String image_url = homeslid.get(i).getImage_url();
                                bannerList.add(image_url);
                            }
                            BannerUtils.startBanner(mBanner, bannerList);
                            mBanner.setOnBannerListener(new OnBannerListener() {
                                @Override
                                public void OnBannerClick(int position) {
                                    switch (homeslid.get(position).getTarge_type()) {
                                        case 1:
                                            Intent intent = new Intent(mContext, GoodDetailActivity.class);
                                            intent.putExtra("id", homeslid.get(position).getTarge_url());
                                            startActivity(intent);
                                            break;
                                        case 2:
                                            Intent intent2 = new Intent(mContext, MiaoShaDetailActivity.class);
                                            intent2.putExtra("id", homeslid.get(position).getTarge_url());
                                            startActivity(intent2);
                                            break;
                                        case 3:
                                            Intent intent3 = new Intent(mContext, JianKongActivity.class);
                                            try {
                                                int i = Integer.parseInt(homeslid.get(position).getTarge_url());
                                                intent3.putExtra("class_id", i);
                                                startActivity(intent3);
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            break;
                                        case 4:
                                            int state = homeslid.get(position).getState();
                                            Intent intent4 = new Intent(mContext, RenYangDetailActivity.class);
                                            intent4.putExtra("state", state);
                                            intent4.putExtra("id", homeslid.get(position).getTarge_url());
                                            startActivity(intent4);
                                            break;
                                        case 5:
                                            Intent intent5 = new Intent(mContext, LatestDetailActivity.class);
                                            intent5.putExtra("id", homeslid.get(position).getTarge_url());
                                            startActivity(intent5);
                                            break;
                                        default:
                                            Intent intent6=new Intent(mContext,WebDetailActivity.class);
                                            intent6.putExtra("url", homeslid.get(position).getTarge_url());
                                            startActivity(intent6);
                                            break;
                                    }
                                }
                            });
                            mTvToutiao.setText(data.getToutiao().getContent());
                            mTvNianshouyi.setText(data.getGrab_donkey().getProfit());
                            mTvYangzhichengben.setText(data.getGrab_donkey().getPrice());
                            mTvTouzizhouqi.setText(data.getGrab_donkey().getPeriod());
                            mTvShengyu.setText(data.getGrab_donkey().getLast_amount());
                            mDonkey = data.getDonkey();
                            mMiddleAdapter = new MiddleAdapter(R.layout.home_middle, mDonkey);
                            mRecyclerMiddle.setAdapter(mMiddleAdapter);
                            mMiddleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent = new Intent(mContext, RenYangDetailActivity.class);
                                    intent.putExtra("state", mDonkey.get(position).getState());
                                    intent.putExtra("id", mDonkey.get(position).getId() + "");
                                    startActivity(intent);
                                }
                            });
                            mMuchang = data.getMuchang();
                            mJianKongAdapter = new JianKongAdapter(R.layout.item_home_jiankong, mMuchang);
                            mRecyclerJiankong.setAdapter(mJianKongAdapter);
                            mJianKongAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent = new Intent(mContext, JianKongActivity.class);
                                    intent.putExtra("class_id", mMuchang.get(position).getId());
                                    startActivity(intent);
                                }
                            });
                            mHot_goods = data.getHot_goods();
                            mGoodAdapter = new GoodAdapter(R.layout.item_home_comment, mHot_goods);
                            mRecyclerGood.setAdapter(mGoodAdapter);
                            mGoodAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent = new Intent(mContext, GoodDetailActivity.class);
                                    intent.putExtra("id", mHot_goods.get(position).getId() + "");
                                    startActivity(intent);
                                }
                            });
                            mSeckill_good = data.getSeckill_good();
                            mMiaoshaAdapter = new MiaoshaAdapter(R.layout.item_home_miaosha, mSeckill_good);
                            mRecyclerMiaosha.setAdapter(mMiaoshaAdapter);
                            mMiaoshaAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent = new Intent(mContext, MiaoShaDetailActivity.class);
                                    intent.putExtra("id", mSeckill_good.get(position).getId() + "");
                                    startActivity(intent);
                                }
                            });
                        }
                    }
                });
        EventBus.getDefault().postSticky(new EventBean("userinfo"));//刷新下我的界面
    }

    private void initTop() {
        if (topStringList.size() == 0) {
            topStringList.add("秒杀");
            topStringList.add("财富中心");
            topStringList.add("牧场");
            topStringList.add("生鲜汇");
            topStringList.add("餐位预定");
            topStringList.add("我要开店");
            topStringList.add("媒体资讯");
            topStringList.add("新手指引");
            topStringList.add("最新活动");
            topStringList.add("每日签到");
        }
        if (topPicList.size() == 0) {
            topPicList.add(R.drawable.miaosha);
            topPicList.add(R.drawable.caifuzhongxin);
            topPicList.add(R.drawable.muchang);
            topPicList.add(R.drawable.shengxianhui);
            topPicList.add(R.drawable.canweiyuding);
            topPicList.add(R.drawable.kaidian);
            topPicList.add(R.drawable.meitizixun);
            topPicList.add(R.drawable.xinshouzhiyin);
            topPicList.add(R.drawable.newactivity);
            topPicList.add(R.drawable.meiriqiandao);
        }
        if (mTopAdapter == null) {
            mTopAdapter = new TopAdapter(R.layout.item_hometop, topStringList);
            mRecyclerTop.setAdapter(mTopAdapter);
            mTopAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    String userid = SpUtils.getString(mContext, "userid", "");
                    switch (position) {
                        case 0:
                            startActivity(new Intent(mContext, MiaoShaActivity.class));
                            break;
                        case 1:
                            if (TextUtils.isEmpty(userid)) {
                                startActivity(new Intent(mContext, LoginActivity.class));
                                mActivity.overridePendingTransition(R.anim.push_bottom_in, R.anim.push_bottom_out);
                                return;
                            }
                            startActivity(new Intent(mContext, FortuneCenterAcitivity.class));
                            break;
                        case 2:
                            EventBus.getDefault().postSticky(new EventBean("checkmuchang"));
                            break;
                        case 3:
                            startActivity(new Intent(mContext, ShengXianHuiActivity.class));
                            break;
                        case 4:
                            startActivity(new Intent(mContext, CanWeiYuDingAcitivity.class));
                            break;
                        case 5:
                            //                            showRenYangDialog();
                            if (TextUtils.isEmpty(userid)) {
                                startActivity(new Intent(mContext, LoginActivity.class));
                                mActivity.overridePendingTransition(R.anim.push_bottom_in, R.anim.push_bottom_out);
                                return;
                            }
                            if (SpUtils.getBoolean(mContext, "inviter_code", false)) {
                                startActivity(new Intent(mContext, KaiDianActivity.class));
                            } else {
                                showRenYangDialog();
                            }
                            break;
                        case 6:
                            startActivity(new Intent(mContext, ZiXunActivity.class));
                            break;
                        case 7:
                            startActivity(new Intent(mContext, XinShouZhiYinActivity.class));
                            break;
                        case 8:
                            startActivity(new Intent(mContext, LatestActivity.class));
                            break;
                        case 9:
                            if (TextUtils.isEmpty(userid)) {
                                startActivity(new Intent(mContext, LoginActivity.class));
                                mActivity.overridePendingTransition(R.anim.push_bottom_in, R.anim.push_bottom_out);
                                return;
                            }
                            startActivity(new Intent(mContext, MyDonkeyEarsActivity.class));
                            break;
                    }
                }
            });
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        mTvToutiao.requestFocus();//解决切换fragment之后回来不滚动的问题
    }

    @Override
    protected void initLazyData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_message_iv://消息
                String userid = SpUtils.getString(mContext, "userid", "");
                if (TextUtils.isEmpty(userid)) {
                    startActivity(new Intent(mContext, LoginActivity.class));
                    mActivity.overridePendingTransition(R.anim.push_bottom_in, R.anim.push_bottom_out);
                    return;
                }
                startActivity(new Intent(mContext, MyMessageActivity.class));
                break;
            case R.id.title_jia_iv://加号
                showDownPop(mTitleJiaiv);
                break;
            case R.id.tv_qiang:
                startActivity(new Intent(mContext, RenYangListActivity.class));
                break;
            case R.id.iv_jiankong_more:
                startActivity(new Intent(mContext, JianKongActivity.class));
                break;
            case R.id.iv_miaosha_more:
                startActivity(new Intent(mContext, MiaoShaActivity.class));
                break;
            case R.id.iv_good_more:
                Intent intent1 = new Intent(mContext, GoodListActivity.class);
                intent1.putExtra("type", "hot");
                startActivity(intent1);
                break;
        }
    }

    //向下弹出
    public void showDownPop(View view) {
        if (popupWindow != null && popupWindow.isShowing()) {
            return;
        }
        popupWindow = new CommonPopupWindow.Builder(mContext)
                .setView(R.layout.popup_home)
                .setWidthAndHeight(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setAnimationStyle(R.style.AnimDown)
                .setViewOnclickListener(new CommonPopupWindow.ViewInterface() {
                    @Override
                    public void getChildView(View view, int layoutResId) {
                        TextView tv_shopma = (TextView) view.findViewById(R.id.tv_shopma);
                        tv_shopma.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showShopMaDialog();
                                popupWindow.dismiss();
                            }
                        });
                        TextView tv_mycode = (TextView) view.findViewById(R.id.tv_mycode);
                        tv_mycode.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(mContext, MyCodeActivity.class));
                                popupWindow.dismiss();
                            }
                        });
                        TextView tv_dian_rule = (TextView) view.findViewById(R.id.tv_dian_rule);
                        tv_dian_rule.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(mContext, DianRuleActivity.class));
                                popupWindow.dismiss();
                            }
                        });
                        TextView tv_scan = (TextView) view.findViewById(R.id.tv_scan);
                        tv_scan.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(mContext, MyZxingActivity.class);
                                startActivityForResult(intent, REQUEST_CODE_ZXING);
                                //这里的REQUEST_CODE是我们定义的int型常量,这里设置为5，为了方便接受onActivityResult分别进行处理
                                popupWindow.dismiss();
                            }
                        });
                    }
                })
                .setOutsideTouchable(true)
                .create();
        popupWindow.showAsDropDown(view);
    }

    /*zxing扫描的回调*/
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ZXING) {
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    //                    Toast.makeText(mContext, "解析结果:" + result, Toast.LENGTH_LONG).show();
                    sao(result);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(mContext, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private void sao(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));
        HttpParams params = new HttpParams();
        params.put("code", code);
        OkGo.<BaseBean>post(Urls.BASEURL + "api/v2/sao")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new JsonCallback<BaseBean>(BaseBean.class) {
                    @Override
                    public void onSuccess(Response<BaseBean> response) {
                        BaseBean baseBean = response.body();
                        Toast.makeText(mContext, baseBean.getMessage(), Toast.LENGTH_SHORT).show();
                        if (baseBean.getCode() == 1) {
                            startActivity(new Intent(mContext, KaiDianActivity.class));
                        }
                    }
                });
    }

    private void showShopMaDialog() {
        mBuilder = new BaseDialog.Builder(mContext);
        mDialog = mBuilder.setViewId(R.layout.dialog_shopma)
                //设置dialogpadding
                .setPaddingdp(50, 0, 50, 0)
                //设置显示位置
                .setGravity(Gravity.CENTER)
                //设置动画
                .setAnimation(R.style.Alpah_aniamtion)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
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
        final EditText editText = mDialog.getView(R.id.edt_content);
        mDialog.getView(R.id.tv_yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(editText.getText().toString())) {
                    Toast.makeText(mContext, "请输入店铺码~", Toast.LENGTH_SHORT).show();
                    return;
                }
                sao(editText.getText().toString());
                mDialog.dismiss();
                SoftKeyboardTool.closeKeyboard(editText);
            }
        });
    }

    private void showRenYangDialog() {
        mBuilder = new BaseDialog.Builder(mContext);
        mDialog = mBuilder.setViewId(R.layout.dialog_home_renyang)
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
                startActivity(new Intent(mContext, RenYangListActivity.class));
                mDialog.dismiss();
            }
        });
    }

    private class TopAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public TopAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            ImageView iv_top = helper.getView(R.id.iv_top);
            iv_top.setImageResource(topPicList.get(helper.getLayoutPosition()));
            helper.setText(R.id.tv_top, item);
        }
    }

    private class JianKongAdapter extends BaseQuickAdapter<HomeBean.DataEntity.MuchangEntity, BaseViewHolder> {

        public JianKongAdapter(@LayoutRes int layoutResId, @Nullable List<HomeBean.DataEntity.MuchangEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, HomeBean.DataEntity.MuchangEntity item) {
            helper.setText(R.id.tv_jiankong, item.getTitle());
            ImageView iv_top = helper.getView(R.id.iv_jiankong);
            int width = MyUtils.getScreenWidth(mContext) - MyUtils.dip2px(mContext, 60);
            ViewGroup.LayoutParams layoutParams = iv_top.getLayoutParams();
            layoutParams.height = width / 3;
            iv_top.setLayoutParams(layoutParams);
            Glide.with(MyApplication.getGloableContext()).load(item.getImage()).into(iv_top);
        }
    }

    private class MiaoshaAdapter extends BaseQuickAdapter<HomeBean.DataEntity.SeckillGoodEntity, BaseViewHolder> {

        public MiaoshaAdapter(@LayoutRes int layoutResId, @Nullable List<HomeBean.DataEntity.SeckillGoodEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, HomeBean.DataEntity.SeckillGoodEntity item) {
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

    private class GoodAdapter extends BaseQuickAdapter<HomeBean.DataEntity.HotGoodsEntity, BaseViewHolder> {

        public GoodAdapter(@LayoutRes int layoutResId, @Nullable List<HomeBean.DataEntity.HotGoodsEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, HomeBean.DataEntity.HotGoodsEntity item) {
            ImageView iv_img = helper.getView(R.id.iv_comment);
            Glide.with(MyApplication.getGloableContext()).load(item.getHot_imgurl()).into(iv_img);
        }
    }

    private class MiddleAdapter extends BaseQuickAdapter<HomeBean.DataEntity.DonkeyEntity, BaseViewHolder> {

        public MiddleAdapter(@LayoutRes int layoutResId, @Nullable List<HomeBean.DataEntity.DonkeyEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, HomeBean.DataEntity.DonkeyEntity item) {
            ProgressBar progressBar = helper.getView(R.id.progressBar);
            TextView tv_shengyu = helper.getView(R.id.tv_shengyu);
            TextView tv_state = helper.getView(R.id.tv_state);
            tv_shengyu.setText("剩余数量：" + item.getLast_amount());
            helper.setText(R.id.tv_totalcount, "总数量：" + item.getAmount())
                    .setText(R.id.tv_title, item.getTitle())
                    .setText(R.id.tv_nianshouyi, item.getProfit())
                    .setText(R.id.tv_yangzhichengben, item.getPrice())
                    .setText(R.id.tv_touzizhouqi, item.getPeriod());
            ImageView iv_shouqing = helper.getView(R.id.iv_shouqing);
            ImageView iv_img = helper.getView(R.id.iv_img);
            Glide.with(MyApplication.getGloableContext()).load(item.getImage()).into(iv_img);
            String lastCount = item.getLast_amount().substring(0, item.getLast_amount().length() - 1);
            String totalCount = item.getAmount().substring(0, item.getAmount().length() - 1);
            float bili = Float.parseFloat(lastCount) / Float.parseFloat(totalCount);
            if (item.getState() == 2) {
                progressBar.setProgress((int) ((1 - bili) * 100));
                tv_shengyu.setTextColor(getResources().getColor(R.color.red1));
                tv_state.setText("进行中");
                tv_state.setBackgroundColor(getResources().getColor(R.color.red1));
            } else if (item.getState() == 1) {
                progressBar.setProgress(0);
                tv_state.setText("即将开始");
            } else if (item.getState() == 3) {
                progressBar.setProgress(0);
                tv_state.setText("已结束");
                tv_state.setBackgroundColor(getResources().getColor(R.color.huise));
                iv_shouqing.setVisibility(View.VISIBLE);
            }
            if (helper.getAdapterPosition() == mDonkey.size() - 1) {
                helper.setVisible(R.id.view_line, false);
            }
            helper.getView(R.id.tv_jiankong).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(mContext, JianKongActivity.class));
                }
            });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
