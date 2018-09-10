package com.power.customizingthecloud.fragment.market;

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
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.MyDongTaiDetailBean;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.login.bean.RegisterBean;
import com.power.customizingthecloud.utils.SoftKeyboardTool;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.Urls;
import com.power.customizingthecloud.view.BaseSelectPopupWindow;
import com.power.customizingthecloud.view.NineGridTestLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.power.customizingthecloud.R.id.iv_like;

public class DongTaiDetailActivity extends BaseActivity implements View.OnClickListener {

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
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.tv_content)
    TextView mTvContent;
    @BindView(R.id.tv_likeperson)
    TextView tv_likeperson;
    @BindView(R.id.nine_gridlayout)
    NineGridTestLayout mNineGridlayout;
    @BindView(R.id.iv_pinglun)
    ImageView mIvPinglun;
    @BindView(iv_like)
    ImageView mIvLike;
    @BindView(R.id.iv_head)
    ImageView mIvHead;
    @BindView(R.id.ll_like)
    LinearLayout mLlLike;
    @BindView(R.id.ll_pinglun)
    LinearLayout mLlPinglun;
    @BindView(R.id.fl_chat)
    FrameLayout fl_chat;
    @BindView(R.id.recycler_chat)
    RecyclerView mRecyclerChat;
    @BindView(R.id.view_line)
    View view_line;
    private List<String> urls = new ArrayList<>();
    private BaseSelectPopupWindow popWiw;// 昵称 编辑框
    private MyDongTaiDetailBean.DataEntity.FeedEntity feed;
    private boolean mIsLike = false;
    private MyDongTaiDetailBean.DataEntity.UserEntity user;
    private StringBuilder builder;
    private List<MyDongTaiDetailBean.DataEntity.FeedEntity.CommentsEntity> comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dong_tai_detail);
        ButterKnife.bind(this);
        mTitleBackIv.setVisibility(View.VISIBLE);
        mTitleBackIv.setOnClickListener(this);
        mTitleContentTv.setText("详情");
        mRecyclerChat.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerChat.setNestedScrollingEnabled(false);
        mLlPinglun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCommentPopWindow(v, "");
            }
        });
        mLlLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLike();
            }
        });
        initData();
    }

    private void changeLike() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));
        HttpParams params = new HttpParams();
        params.put("feed_id", feed.getId() + "");
        String url = "";
        if (mIsLike) {
            url = Urls.BASEURL + "api/v2/feed/unlike";
        } else {
            url = Urls.BASEURL + "api/v2/feed/like";
        }
        OkGo.<RegisterBean>get(url)
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<RegisterBean>(DongTaiDetailActivity.this, RegisterBean.class) {
                    @Override
                    public void onSuccess(Response<RegisterBean> response) {
                        RegisterBean bean = response.body();
                        int code = bean.getCode();
                        if (code == 0) {
                            Toast.makeText(DongTaiDetailActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (code == 1) {
                            Toast.makeText(DongTaiDetailActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                            //手动设置，不用再请求一次网络
                            if (mIsLike) {
                                mIvLike.setImageResource(R.drawable.ganji_like2);
                                tv_likeperson.setVisibility(View.GONE);
                                builder=new StringBuilder();
                                if ((comments == null || comments.size() == 0) && TextUtils.isEmpty(builder.toString())) {
                                    fl_chat.setVisibility(View.GONE);
                                }
                            } else {
                                fl_chat.setVisibility(View.VISIBLE);
                                mIvLike.setImageResource(R.drawable.ganji_like3);
                                tv_likeperson.setVisibility(View.VISIBLE);
                                tv_likeperson.setText(user.getUser_name());
                                builder.append(user.getUser_name());
                            }
                            mIsLike = !mIsLike;
                        }
                    }
                });
    }

    private void initData() {
        String id = getIntent().getStringExtra("id");
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(this, "token", ""));
        HttpParams params = new HttpParams();
        params.put("feed_id", id);
        OkGo.<MyDongTaiDetailBean>get(Urls.BASEURL + "api/v2/feed/show")
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<MyDongTaiDetailBean>(DongTaiDetailActivity.this, MyDongTaiDetailBean.class) {
                    @Override
                    public void onSuccess(Response<MyDongTaiDetailBean> response) {
                        MyDongTaiDetailBean circleHomeBean = response.body();
                        final int code = circleHomeBean.getCode();
                        if (code == 0) {
                            Toast.makeText(DongTaiDetailActivity.this, circleHomeBean.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (code == 1) {
                            MyDongTaiDetailBean.DataEntity data = circleHomeBean.getData();
                            user = data.getUser();
                            if (!TextUtils.isEmpty(user.getUser_name())) {
                                mTvName.setText(user.getUser_name());
                            }
                            if (!TextUtils.isEmpty(user.getUser_avatar())) {
                                Glide.with(mContext).load(user.getUser_avatar()).into(mIvHead);
                            } else {
                                mIvHead.setImageResource(R.drawable.face);
                            }
                            feed = data.getFeed();
                            mTvTime.setText(feed.getCreated_at());
                            mTvContent.setText(feed.getFeed_content());
                            List<MyDongTaiDetailBean.DataEntity.FeedEntity.LikesEntity> likes = feed.getLikes();
                            builder = new StringBuilder();
                            if (likes != null && likes.size() > 0) {
                                String userid = SpUtils.getString(mContext, "userid", "");
                                for (int i = 0; i < likes.size(); i++) {
                                    if (i == likes.size() - 1) {
                                        builder.append(likes.get(i).getUser_name());
                                    } else {
                                        builder.append(likes.get(i).getUser_name() + ",");
                                    }
                                    if (!TextUtils.isEmpty(userid) && userid.equals(likes.get(i).getUser_id() + "")) {
                                        mIsLike = true;
                                    }
                                }
                                if (TextUtils.isEmpty(builder.toString())) {
                                    tv_likeperson.setVisibility(View.GONE);
                                } else {
                                    tv_likeperson.setVisibility(View.VISIBLE);
                                    tv_likeperson.setText(builder.toString());

                                }
                            } else {
                                tv_likeperson.setVisibility(View.GONE);
                            }
                            if (mIsLike) {
                                mIvLike.setImageResource(R.drawable.ganji_like3);
                            } else {
                                mIvLike.setImageResource(R.drawable.ganji_like2);
                            }
                            List<MyDongTaiDetailBean.DataEntity.FeedEntity.ImagesEntity> images = feed.getImages();
                            if (images != null && images.size() > 0) {
                                urls.clear();
                                for (int i = 0; i < images.size(); i++) {
                                    urls.add(images.get(i).getFileurl());
                                }
                            }
                            mNineGridlayout.setIsShowAll(true);
                            mNineGridlayout.setUrlList(urls);
                            comments = feed.getComments();
                            if (comments == null || comments.size() == 0) {
                                mRecyclerChat.setVisibility(View.GONE);
                                view_line.setVisibility(View.GONE);
                            } else {
                                mRecyclerChat.setVisibility(View.VISIBLE);
                                view_line.setVisibility(View.VISIBLE);
                                ChatAdapter chatAdapter = new ChatAdapter(R.layout.item_market_chat, comments);
                                mRecyclerChat.setAdapter(chatAdapter);
                                chatAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        showCommentPopWindow(view, comments.get(position).getUser_id() + "");
                                    }
                                });
                            }
                            if ((comments == null || comments.size() == 0) && TextUtils.isEmpty(builder.toString())) {
                                fl_chat.setVisibility(View.GONE);
                                view_line.setVisibility(View.GONE);
                            }else {
                                view_line.setVisibility(View.VISIBLE);
                                fl_chat.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                });
    }

    private class ChatAdapter extends BaseQuickAdapter<MyDongTaiDetailBean.DataEntity.FeedEntity.CommentsEntity, BaseViewHolder> {

        public ChatAdapter(@LayoutRes int layoutResId, @Nullable List<MyDongTaiDetailBean.DataEntity.FeedEntity.CommentsEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, MyDongTaiDetailBean.DataEntity.FeedEntity.CommentsEntity item) {
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

    private void showCommentPopWindow(View view, final String reply_user) {
        if (popWiw == null) {
            popWiw = new BaseSelectPopupWindow(mContext, R.layout.edit_data2);
        }
        SoftKeyboardTool.showSoftKeyboard(view);
        final EditText edt = (EditText) popWiw.getContentView().findViewById(R.id.edt_content);
        final TextView tv_send = (TextView) popWiw.getContentView().findViewById(R.id.tv_send);
        edt.setInputType(EditorInfo.TYPE_CLASS_TEXT);
        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (TextUtils.isEmpty(edt.getText())) {
                    tv_send.setEnabled(false);
                } else {
                    tv_send.setEnabled(true);
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
        tv_send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(edt.getText().toString().trim())) {
                    String content = edt.getText().toString().trim();
                    sendComment(feed.getId() + "", content, reply_user);
                    popWiw.dismiss();
                }
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
                .execute(new DialogCallback<RegisterBean>(DongTaiDetailActivity.this, RegisterBean.class) {
                    @Override
                    public void onSuccess(Response<RegisterBean> response) {
                        RegisterBean bean = response.body();
                        int code = bean.getCode();
                        if (code == 0) {
                            Toast.makeText(DongTaiDetailActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (code == 1) {
                            Toast.makeText(DongTaiDetailActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                            SoftKeyboardTool.closeKeyboard(DongTaiDetailActivity.this);
                            initData();
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
        }
    }
}
