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
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.utils.SoftKeyboardTool;
import com.power.customizingthecloud.view.BaseSelectPopupWindow;
import com.power.customizingthecloud.view.NineGridTestLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    @BindView(R.id.nine_gridlayout)
    NineGridTestLayout mNineGridlayout;
    @BindView(R.id.iv_pinglun)
    ImageView mIvPinglun;
    @BindView(R.id.iv_like)
    ImageView mIvLike;
    @BindView(R.id.ll_like)
    LinearLayout mLlLike;
    @BindView(R.id.ll_pinglun)
    LinearLayout mLlPinglun;
    @BindView(R.id.recycler_chat)
    RecyclerView mRecyclerChat;
    private String[] mUrls = new String[]{"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=202447557,2967022603&fm=27&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=104961686,3757525983&fm=27&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=569334953,1638673400&fm=27&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2043305675,3979419376&fm=200&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=266745161,658804068&fm=27&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=222615259,2947254622&fm=27&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=950771854,530317119&fm=27&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=2557022909,3736713361&fm=27&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=1830359176,654163576&fm=200&gp=0.jpg",
    };

    private List<String> urls = new ArrayList<>();
    private BaseSelectPopupWindow popWiw;// 昵称 编辑框

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dong_tai_detail);
        ButterKnife.bind(this);
        mTitleMessageIv.setVisibility(View.VISIBLE);
        mTitleMessageIv.setOnClickListener(this);
        mTitleContentTv.setText("详情");
        initData();
    }

    private void initData() {
        for (int i = 0; i < mUrls.length; i++) {
            urls.add(mUrls[i]);
        }
        mNineGridlayout.setIsShowAll(true);
        mNineGridlayout.setUrlList(urls);
        mRecyclerChat.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerChat.setNestedScrollingEnabled(false);
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        ChatAdapter chatAdapter = new ChatAdapter(R.layout.item_market_chat, list);
        mRecyclerChat.setAdapter(chatAdapter);
        chatAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                showCommentPopWindow(view);
            }
        });
        mLlPinglun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCommentPopWindow(v);
            }
        });
    }

    private class ChatAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public ChatAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, String item) {
            TextView tv_content = helper.getView(R.id.tv_content);
            SpannableString spannableString = new SpannableString("小豆：的撒娇的卡萨京东设计费华科税费低卡拉萨街坊邻居");
            StyleSpan styleSpan_B = new StyleSpan(Typeface.BOLD);
            spannableString.setSpan(styleSpan_B, 0, 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            tv_content.setText(spannableString);
        }
    }

    private void showCommentPopWindow(View view) {
        if (popWiw == null) {
            popWiw = new BaseSelectPopupWindow(mContext, R.layout.edit_data2);
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
        final EditText edt = (EditText) popWiw.getContentView().findViewById(R.id.edt_content);
        final TextView tv_send = (TextView) popWiw.getContentView().findViewById(R.id.tv_send);
        edt.setInputType(EditorInfo.TYPE_CLASS_TEXT);
        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (TextUtils.isEmpty(edt.getText())) {

                } else {

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
                    popWiw.dismiss();
                }
            }
        });
        popWiw.showAtLocation(view, Gravity.BOTTOM
                | Gravity.CENTER_HORIZONTAL, 0, 0);
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