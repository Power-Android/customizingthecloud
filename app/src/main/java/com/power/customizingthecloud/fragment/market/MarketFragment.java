package com.power.customizingthecloud.fragment.market;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.StyleSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
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
import com.power.customizingthecloud.activity.mine.MyMessageActivity;
import com.power.customizingthecloud.base.BaseFragment;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.fragment.market.bean.CircleHomeBean;
import com.power.customizingthecloud.login.bean.RegisterBean;
import com.power.customizingthecloud.utils.SoftKeyboardTool;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.Urls;
import com.power.customizingthecloud.view.BaseDialog;
import com.power.customizingthecloud.view.BaseSelectPopupWindow;
import com.power.customizingthecloud.view.CircleImageView;
import com.power.customizingthecloud.view.NineGridTestLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/1/19.
 * 赶集啦
 */

public class MarketFragment extends BaseFragment implements View.OnClickListener {
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
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.iv_head)
    CircleImageView mIvHead;
    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.iv_photo)
    ImageView iv_photo;
    Unbinder unbinder;
    private BaseSelectPopupWindow popWiw;// 昵称 编辑框
    private boolean mIsLike;
    private MyAdapter mMyAdapter;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ganji, null);
        unbinder = ButterKnife.bind(this, view);
        mTitleMessageIv.setVisibility(View.VISIBLE);
        mTitleMessageIv.setOnClickListener(this);
        mTitleContentTv.setText("我的圈子");
        mTitleContentRightTv.setVisibility(View.VISIBLE);
        mTitleContentRightTv.setText("发动态");
        mTitleContentRightTv.setOnClickListener(this);
        iv_photo.setOnClickListener(this);
        mIvHead.setOnClickListener(this);
        return view;
    }

    @Override
    protected void initLazyData() {

    }

    private void initData() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mActivity, "token", ""));
        HttpParams params = new HttpParams();
        params.put("after", "");
        OkGo.<CircleHomeBean>get(Urls.BASEURL + "api/v2/feed")
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<CircleHomeBean>(mActivity, CircleHomeBean.class) {
                    @Override
                    public void onSuccess(Response<CircleHomeBean> response) {
                        CircleHomeBean circleHomeBean = response.body();
                        int code = circleHomeBean.getCode();
                        if (code == 0) {
                            Toast.makeText(mActivity, circleHomeBean.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (code == 1) {
                            CircleHomeBean.DataEntity data = circleHomeBean.getData();
                            CircleHomeBean.DataEntity.UserEntity user = data.getUser();
                            mTvName.setText(user.getUser_name());
                            Glide.with(MyApplication.getGloableContext()).load(user.getUser_avatar()).into(mIvHead);
                            List<CircleHomeBean.DataEntity.FeedEntity> feed = data.getFeed();
                            if (feed != null && feed.size() > 0) {
                                mMyAdapter = new MyAdapter(R.layout.item_circle_friend, feed);
                                mRecycler.setAdapter(mMyAdapter);
                            }
                        }
                    }
                });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        mRecycler.setNestedScrollingEnabled(false);
        initData();
    }

    private class MyAdapter extends BaseQuickAdapter<CircleHomeBean.DataEntity.FeedEntity, BaseViewHolder> {

        public MyAdapter(@LayoutRes int layoutResId, @Nullable List<CircleHomeBean.DataEntity.FeedEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, final CircleHomeBean.DataEntity.FeedEntity item) {
            NineGridTestLayout nineGridlayout = helper.getView(R.id.nine_gridlayout);
            nineGridlayout.setIsShowAll(true);
            final List<CircleHomeBean.DataEntity.FeedEntity.ImagesEntity> images = item.getImages();
            List<String> imgList = new ArrayList<>();
            for (int i = 0; i < images.size(); i++) {
                imgList.add(images.get(i).getFileurl());
            }
            nineGridlayout.setUrlList(imgList);
            final ImageView iv_like = helper.getView(R.id.iv_like);
            List<CircleHomeBean.DataEntity.FeedEntity.LikesEntity> likes = item.getLikes();
            StringBuilder builder = new StringBuilder();
            String userid = SpUtils.getString(mContext, "userid", "");
            for (int i = 0; i < likes.size(); i++) {
                if (i == likes.size() - 1) {
                    builder.append(likes.get(i).getUser_name());
                } else {
                    builder.append(likes.get(i).getUser_name() + ",");
                }
                if (!TextUtils.isEmpty(userid) && userid.equals(likes.get(i).getId() + "")) {
                    mIsLike = true;
                }
            }
            if (mIsLike) {
                iv_like.setImageResource(R.drawable.ganji_like3);
            } else {
                iv_like.setImageResource(R.drawable.ganji_like2);
            }
            ImageView iv_head = helper.getView(R.id.iv_head);
            Glide.with(MyApplication.getGloableContext()).load(item.getUser_avatar()).into(iv_head);
            helper.setText(R.id.tv_name, item.getUser_name())
                    .setText(R.id.tv_time, item.getUpdated_at())
                    .setText(R.id.tv_content, item.getFeed_content())
                    .setText(R.id.tv_likeperson, builder.toString());

            final RecyclerView recycler_chat = helper.getView(R.id.recycler_chat);
            recycler_chat.setLayoutManager(new LinearLayoutManager(mContext));
            recycler_chat.setNestedScrollingEnabled(false);
            final List<CircleHomeBean.DataEntity.FeedEntity.CommentsEntity> comments = item.getComments();
            ChatAdapter chatAdapter = new ChatAdapter(R.layout.item_market_chat, comments);
            recycler_chat.setAdapter(chatAdapter);
            chatAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    showCommentPopWindow(view, item.getId() + "", comments.get(position).getReply_user()+"");
                }
            });
            helper.getView(R.id.ll_like).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeLike(item.getId() + "");
                }
            });
            helper.getView(R.id.ll_pinglun).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showCommentPopWindow(v,item.getId()+"","");
                }
            });
        }
    }

    private void changeLike(String s) {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));
        HttpParams params = new HttpParams();
        params.put("feed_id", s);
        String url="";
        if (mIsLike){
            url=Urls.BASEURL + "api/v2/feed/unlike";
        }else {
            url=Urls.BASEURL + "api/v2/feed/like";
        }
        OkGo.<RegisterBean>get(url)
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
                            mIsLike = !mIsLike;//手动设置，不用再请求一次网络
                            mMyAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    private void showCommentPopWindow(View view, final String feed_id, final String reply_user) {
        if (popWiw == null) {
            popWiw = new BaseSelectPopupWindow(mContext, R.layout.edit_data);
            // popWiw.setOpenKeyboard(true);
            popWiw.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
            popWiw.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            popWiw.setShowTitle(false);
        }
        popWiw.setFocusable(true);
        //        InputMethodManager im = (InputMethodManager)
        //                mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        //        im.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        SoftKeyboardTool.showSoftKeyboard(view);
        final ImageView send = (ImageView) popWiw.getContentView().findViewById(R.id.query_iv);
        final EditText edt = (EditText) popWiw.getContentView().findViewById(R.id.edt_content);
        final ImageView close = (ImageView) popWiw.getContentView().findViewById(R.id.cancle_iv);
        edt.setInputType(EditorInfo.TYPE_CLASS_TEXT);
        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (TextUtils.isEmpty(edt.getText())) {
                    send.setEnabled(false);
                } else {
                    send.setEnabled(true);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(edt.getText().toString().trim())) {
                    String content = edt.getText().toString().trim();
                    sendComment(feed_id,content,reply_user);
                    popWiw.dismiss();
                }
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popWiw.dismiss();
            }
        });
        popWiw.showAtLocation(view, Gravity.BOTTOM
                | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    private void sendComment(String feed_id, String content, String reply_user) {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));
        HttpParams params = new HttpParams();
        params.put("feed_id", feed_id);
        params.put("body", content);
        if (!TextUtils.isEmpty(reply_user))
        params.put("reply_user", reply_user);
        OkGo.<RegisterBean>post(Urls.BASEURL + "api/v2/feed/comments")
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
                            initData();
                        }
                    }
                });
    }

    private class ChatAdapter extends BaseQuickAdapter<CircleHomeBean.DataEntity.FeedEntity.CommentsEntity, BaseViewHolder> {

        public ChatAdapter(@LayoutRes int layoutResId, @Nullable List<CircleHomeBean.DataEntity.FeedEntity.CommentsEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, CircleHomeBean.DataEntity.FeedEntity.CommentsEntity item) {
            TextView tv_content = helper.getView(R.id.tv_content);
            String user_name = item.getUser_name();
            String reply_name = item.getReply_name();
            String body = item.getBody();
            if (TextUtils.isEmpty(reply_name)) {
                SpannableString spannableString = new SpannableString(user_name + ":" + body);
                StyleSpan styleSpan_B = new StyleSpan(Typeface.BOLD);
                spannableString.setSpan(styleSpan_B, 0, user_name.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                tv_content.setText(spannableString);
            } else {
                SpannableString spannableString = new SpannableString(user_name + "回复" + reply_name + ":" + body);
                StyleSpan styleSpan_B = new StyleSpan(Typeface.BOLD);
                StyleSpan styleSpan_I = new StyleSpan(Typeface.BOLD);
                spannableString.setSpan(styleSpan_I, 0, user_name.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                spannableString.setSpan(styleSpan_B, user_name.length() + 2, user_name.length() + 2 + reply_name.length()
                        , Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                tv_content.setText(spannableString);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_message_iv:
                startActivity(new Intent(mContext, MyMessageActivity.class));
                break;
            case R.id.title_content_right_tv:
                startActivity(new Intent(mContext, FaDongTaiActivity.class));
                break;
            case R.id.iv_head:
                startActivity(new Intent(mContext, MyDongTaiActivity.class));
                break;
            case R.id.iv_photo:
                showPhotoDialog(Gravity.BOTTOM, R.style.Bottom_Top_aniamtion);
                break;
        }
    }

    private void showPhotoDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(mContext);
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
                Intent intent = new Intent(mContext, FaDongTaiActivity.class);
                intent.putExtra("type", "photo");
                startActivity(intent);
            }
        });
        dialog.getView(R.id.tv_takephoto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(mContext, FaDongTaiActivity.class);
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
