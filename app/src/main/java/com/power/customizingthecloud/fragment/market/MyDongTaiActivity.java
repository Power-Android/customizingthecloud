package com.power.customizingthecloud.fragment.market;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.MyApplication;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.MultiItemBean;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.fragment.market.bean.MyDongTaiBean;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.Urls;
import com.power.customizingthecloud.view.BaseDialog;
import com.power.customizingthecloud.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyDongTaiActivity extends BaseActivity implements View.OnClickListener {

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
    @BindView(R.id.title_kefu_iv)
    ImageView mTitleKefuIv;
    @BindView(R.id.title_content_right_tv)
    TextView mTitleContentRightTv;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.iv_head)
    CircleImageView mIvHead;
    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.iv_photo)
    ImageView mIvPhoto;
    @BindView(R.id.springview)
    SpringView mSpringview;
    private String after = "";
    private boolean isLoadMore;
    private List<MyDongTaiBean.DataEntity.FeedEntity> mFeed;
    private MyAdapter mMyAdapter;
    private List<MultiItemBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_dong_tai);
        ButterKnife.bind(this);
        mTitleBackIv.setVisibility(View.VISIBLE);
        mTitleBackIv.setOnClickListener(this);
        mTitleContentTv.setText("我的动态");
        mRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        mRecycler.setNestedScrollingEnabled(false);
        mIvPhoto.setOnClickListener(this);
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
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(this, "token", ""));
        HttpParams params = new HttpParams();
        if (isLoadMore) {
            params.put("after", after);
        }
        OkGo.<MyDongTaiBean>get(Urls.BASEURL + "api/v2/feed/home")
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<MyDongTaiBean>(MyDongTaiActivity.this, MyDongTaiBean.class) {
                    @Override
                    public void onSuccess(Response<MyDongTaiBean> response) {
                        MyDongTaiBean dongTaiBean = response.body();
                        int code = dongTaiBean.getCode();
                        if (code == 0) {
                            Toast.makeText(MyDongTaiActivity.this, dongTaiBean.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (code == 1) {
                            MyDongTaiBean.DataEntity data = dongTaiBean.getData();
                            MyDongTaiBean.DataEntity.UserEntity user = data.getUser();
                            if (!TextUtils.isEmpty(user.getUser_name())) {
                                mTvName.setText(user.getUser_name());
                            }
                            if (!TextUtils.isEmpty(user.getUser_avatar())) {
                                Glide.with(MyApplication.getGloableContext()).load(user.getUser_avatar()).into(mIvHead);
                            }
                            if (!isLoadMore) {
                                mFeed = data.getFeed();
                                list.clear();
                                for (int i = 0; i < mFeed.size(); i++) {
                                    MyDongTaiBean.DataEntity.FeedEntity feedEntity = mFeed.get(i);
                                    List<MyDongTaiBean.DataEntity.FeedEntity.ImagesEntity> images = feedEntity.getImages();
                                    if (images == null || images.size() == 0) {
                                        list.add(new MultiItemBean(MultiItemBean.NOIMAGE, feedEntity));
                                    } else if (images.size() == 1) {
                                        list.add(new MultiItemBean(MultiItemBean.ONEIMAGE, feedEntity));
                                    } else if (images.size() == 2) {
                                        list.add(new MultiItemBean(MultiItemBean.TWOIMAGE, feedEntity));
                                    } else if (images.size() == 3) {
                                        list.add(new MultiItemBean(MultiItemBean.THREEIMAGE, feedEntity));
                                    } else if (images.size() >= 4) {
                                        list.add(new MultiItemBean(MultiItemBean.FOURIMAGE, feedEntity));
                                    }
                                }
                                mMyAdapter = new MyAdapter(list);
                                mRecycler.setAdapter(mMyAdapter);
                            } else {
                                if (data.getFeed() != null && data.getFeed().size() > 0) {
                                    mFeed.addAll(data.getFeed());
                                    for (int i = 0; i < mFeed.size(); i++) {
                                        MyDongTaiBean.DataEntity.FeedEntity feedEntity = mFeed.get(i);
                                        List<MyDongTaiBean.DataEntity.FeedEntity.ImagesEntity> images = feedEntity.getImages();
                                        if (images == null || images.size() == 0) {
                                            list.add(new MultiItemBean(MultiItemBean.NOIMAGE, feedEntity));
                                        } else if (images.size() == 1) {
                                            list.add(new MultiItemBean(MultiItemBean.ONEIMAGE, feedEntity));
                                        } else if (images.size() == 2) {
                                            list.add(new MultiItemBean(MultiItemBean.TWOIMAGE, feedEntity));
                                        } else if (images.size() == 3) {
                                            list.add(new MultiItemBean(MultiItemBean.THREEIMAGE, feedEntity));
                                        } else if (images.size() >= 4) {
                                            list.add(new MultiItemBean(MultiItemBean.FOURIMAGE, feedEntity));
                                        }
                                    }
                                    mMyAdapter.notifyDataSetChanged();
                                } else {
                                    Toast.makeText(mContext, "没有更多了~", Toast.LENGTH_SHORT).show();
                                }
                            }
                            mMyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent = new Intent(MyDongTaiActivity.this, DongTaiDetailActivity.class);
                                    intent.putExtra("id",mFeed.get(position).getId()+"");
                                    startActivity(intent);
                                }
                            });
                        }
                    }
                });
    }

    private class MyAdapter extends BaseMultiItemQuickAdapter<MultiItemBean, BaseViewHolder> {

        public MyAdapter(List<MultiItemBean> data) {
            super(data);
            addItemType(MultiItemBean.NOIMAGE, R.layout.item_mydongtai0);
            addItemType(MultiItemBean.ONEIMAGE, R.layout.item_mydongtai1);
            addItemType(MultiItemBean.TWOIMAGE, R.layout.item_mydongtai2);
            addItemType(MultiItemBean.THREEIMAGE, R.layout.item_mydongtai3);
            addItemType(MultiItemBean.FOURIMAGE, R.layout.item_mydongtai4);
        }

        @Override
        protected void convert(BaseViewHolder helper, MultiItemBean item) {
            MyDongTaiBean.DataEntity.FeedEntity feedEntity = item.getFeedEntity();
            after = feedEntity.getId() + "";
            helper.setText(R.id.tv_content, feedEntity.getFeed_content());
            switch (item.getItemType()) {
                case MultiItemBean.NOIMAGE:
                    break;
                case MultiItemBean.ONEIMAGE:
                    Glide.with(MyApplication.getGloableContext())
                            .load(feedEntity.getImages().get(0).getFileurl()).into((ImageView) helper.getView(R.id.iv_img1));
                    break;
                case MultiItemBean.TWOIMAGE:
                    Glide.with(MyApplication.getGloableContext())
                            .load(feedEntity.getImages().get(0).getFileurl()).into((ImageView) helper.getView(R.id.iv_img1));
                    Glide.with(MyApplication.getGloableContext())
                            .load(feedEntity.getImages().get(1).getFileurl()).into((ImageView) helper.getView(R.id.iv_img2));
                    break;
                case MultiItemBean.THREEIMAGE:
                    Glide.with(MyApplication.getGloableContext())
                            .load(feedEntity.getImages().get(0).getFileurl()).into((ImageView) helper.getView(R.id.iv_img1));
                    Glide.with(MyApplication.getGloableContext())
                            .load(feedEntity.getImages().get(1).getFileurl()).into((ImageView) helper.getView(R.id.iv_img2));
                    Glide.with(MyApplication.getGloableContext())
                            .load(feedEntity.getImages().get(2).getFileurl()).into((ImageView) helper.getView(R.id.iv_img3));
                    break;
                case MultiItemBean.FOURIMAGE:
                    Glide.with(MyApplication.getGloableContext())
                            .load(feedEntity.getImages().get(0).getFileurl()).into((ImageView) helper.getView(R.id.iv_img1));
                    Glide.with(MyApplication.getGloableContext())
                            .load(feedEntity.getImages().get(1).getFileurl()).into((ImageView) helper.getView(R.id.iv_img2));
                    Glide.with(MyApplication.getGloableContext())
                            .load(feedEntity.getImages().get(2).getFileurl()).into((ImageView) helper.getView(R.id.iv_img3));
                    Glide.with(MyApplication.getGloableContext())
                            .load(feedEntity.getImages().get(3).getFileurl()).into((ImageView) helper.getView(R.id.iv_img4));
                    break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.iv_photo:
                showPhotoDialog(Gravity.BOTTOM, R.style.Bottom_Top_aniamtion);
                break;
        }
    }

    private void showPhotoDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        final BaseDialog dialog = builder.setViewId(R.layout.dialog_photo)
                //设置dialogpadding
                .setPaddingdp(20, 0, 20, 0)
                //设置显示位置
                .setGravity(grary)
                //设置动画
                .setAnimation(animationStyle)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(true)
                //设置监听事件
                .builder();
        dialog.getView(R.id.tv_xiangce).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(MyDongTaiActivity.this, FaDongTaiActivity.class);
                intent.putExtra("type", "photo");
                startActivity(intent);
            }
        });
        dialog.getView(R.id.tv_takephoto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(MyDongTaiActivity.this, FaDongTaiActivity.class);
                intent.putExtra("type", "camera");
                startActivity(intent);
            }
        });
        dialog.getView(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
