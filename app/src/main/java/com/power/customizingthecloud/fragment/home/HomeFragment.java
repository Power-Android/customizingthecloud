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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.activity.mine.FortuneCenterAcitivity;
import com.power.customizingthecloud.activity.mine.LatestActivity;
import com.power.customizingthecloud.activity.mine.MyDonkeyEarsActivity;
import com.power.customizingthecloud.activity.mine.MyMessageActivity;
import com.power.customizingthecloud.base.BaseFragment;
import com.power.customizingthecloud.bean.EventBean;
import com.power.customizingthecloud.fragment.home.jiankong.JianKongActivity;
import com.power.customizingthecloud.fragment.home.renyang.RenYangListActivity;
import com.power.customizingthecloud.fragment.home.renyang.detail.RenYangDetailActivity;
import com.power.customizingthecloud.fragment.home.top.CanWeiYuDingAcitivity;
import com.power.customizingthecloud.fragment.home.top.KaiDianActivity;
import com.power.customizingthecloud.fragment.home.top.MiaoShaActivity;
import com.power.customizingthecloud.fragment.home.top.ShengXianHuiActivity;
import com.power.customizingthecloud.fragment.home.top.XinShouZhiYinActivity;
import com.power.customizingthecloud.fragment.home.top.ZiXunActivity;
import com.power.customizingthecloud.login.LoginActivity;
import com.power.customizingthecloud.utils.BannerUtils;
import com.power.customizingthecloud.utils.MyUtils;
import com.power.customizingthecloud.utils.SpUtils;
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

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerTop.setNestedScrollingEnabled(false);
        mRecyclerTop.setLayoutManager(new GridLayoutManager(mContext, 5));
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
        BannerUtils.startBanner(mBanner, new ArrayList<String>());
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                startActivity(new Intent(mContext,RenYangDetailActivity.class));
            }
        });
        if (mTopAdapter == null) {
            mTopAdapter = new TopAdapter(R.layout.item_hometop, topStringList);
            mRecyclerTop.setAdapter(mTopAdapter);
            mTopAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    switch (position) {
                        case 0:
                            startActivity(new Intent(mContext, MiaoShaActivity.class));
                            break;
                        case 1:
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
                            startActivity(new Intent(mContext, KaiDianActivity.class));
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
                            startActivity(new Intent(mContext, MyDonkeyEarsActivity.class));
                            break;
                    }
                }
            });
        }
        mTvToutiao.requestFocus();//有趣，在布局文件中也设置了获取了焦点，但是没有，在代码中加上就好使了
        mTitleContentTv.setText("养驴啦");
        mTitleMessageIv.setVisibility(View.VISIBLE);
        mTitleMessageIv.setOnClickListener(this);
        mTitleJiaiv.setVisibility(View.VISIBLE);
        mTitleJiaiv.setOnClickListener(this);
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        mRecyclerMiddle.setNestedScrollingEnabled(false);
        mRecyclerMiddle.setLayoutManager(new LinearLayoutManager(mContext));
        mMiddleAdapter = new MiddleAdapter(R.layout.home_middle, list);
        mRecyclerMiddle.setAdapter(mMiddleAdapter);
        mMiddleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mContext, RenYangDetailActivity.class);
                if (position == 0) {
                    intent.putExtra("type", "jijiang");
                } else if (position == 1) {
                    intent.putExtra("type", "ing");
                }
                startActivity(intent);
            }
        });
        List<String> list2 = new ArrayList<>();
        list2.add("运动一区");
        list2.add("运动二区");
        list2.add("饲养区");
        mJianKongAdapter = new JianKongAdapter(R.layout.item_home_jiankong, list2);
        mRecyclerJiankong.setLayoutManager(new GridLayoutManager(mContext, 3));
        mRecyclerJiankong.setNestedScrollingEnabled(false);
        mRecyclerJiankong.setAdapter(mJianKongAdapter);
        mJianKongAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mContext, JianKongActivity.class);
                if (position == 2) {
                    intent.putExtra("position", 3);
                } else {
                    intent.putExtra("position", position);
                }
                startActivity(intent);
            }
        });
        mGoodAdapter = new GoodAdapter(R.layout.item_home_comment, list);
        mRecyclerGood.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerGood.setNestedScrollingEnabled(false);
        mRecyclerGood.setAdapter(mGoodAdapter);
        mGoodAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(mContext, GoodDetailActivity.class));
            }
        });
        mRecyclerMiaosha.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerMiaosha.setNestedScrollingEnabled(false);
        mMiaoshaAdapter = new MiaoshaAdapter(R.layout.item_home_miaosha, list);
        mRecyclerMiaosha.setAdapter(mMiaoshaAdapter);
        mMiaoshaAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(mContext, MiaoShaDetailActivity.class));
            }
        });
        mTvQiang.setOnClickListener(this);
        mIvJiankongMore.setOnClickListener(this);
        mIvMiaoshaMore.setOnClickListener(this);
        mIvGoodMore.setOnClickListener(this);
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
                startActivity(new Intent(mContext, GoodListActivity.class));
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
                    Toast.makeText(mContext, "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(mContext, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
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
        mDialog.getView(R.id.tv_yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
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

    private class JianKongAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public JianKongAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.tv_jiankong, item);
            ImageView iv_top = helper.getView(R.id.iv_jiankong);
            int width = MyUtils.getScreenWidth(mContext) - MyUtils.dip2px(mContext, 60);
            ViewGroup.LayoutParams layoutParams = iv_top.getLayoutParams();
            layoutParams.height = width / 3;
            iv_top.setLayoutParams(layoutParams);
        }
    }

    private class MiaoshaAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public MiaoshaAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            TextView tv_yuanjia = helper.getView(R.id.tv_yuanjia);
            //添加删除线
            tv_yuanjia.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            CountdownView cv_countdownView = helper.getView(R.id.cv_countdownView);
            cv_countdownView.start(10000000); // Millisecond
        }
    }

    private class GoodAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public GoodAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }
    }

    private class MiddleAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public MiddleAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            ProgressBar progressBar = helper.getView(R.id.progressBar);
            TextView tv_shengyu = helper.getView(R.id.tv_shengyu);
            TextView tv_state = helper.getView(R.id.tv_state);
            if (helper.getAdapterPosition() == 1) {
                helper.setVisible(R.id.view_line, false);
                progressBar.setProgress(80);
                tv_shengyu.setText("剩余数量：6头");
                tv_shengyu.setTextColor(getResources().getColor(R.color.red1));
                tv_state.setText("进行中");
                tv_state.setBackgroundColor(getResources().getColor(R.color.red1));
            } else {
                helper.setVisible(R.id.view_line, true);
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
