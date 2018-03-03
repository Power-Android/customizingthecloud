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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.activity.mine.MyMessageActivity;
import com.power.customizingthecloud.base.BaseFragment;
import com.power.customizingthecloud.bean.NineGridTestModel;
import com.power.customizingthecloud.utils.SoftKeyboardTool;
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
    private List<NineGridTestModel> mList = new ArrayList<>();
    private String[] mUrls = new String[]{"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=202447557,2967022603&fm=27&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=104961686,3757525983&fm=27&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=569334953,1638673400&fm=27&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2043305675,3979419376&fm=200&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=266745161,658804068&fm=27&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=222615259,2947254622&fm=27&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=950771854,530317119&fm=27&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=2557022909,3736713361&fm=27&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=1830359176,654163576&fm=200&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=4193964417,1586871857&fm=27&gp=0.jpg",
    };
    private BaseSelectPopupWindow popWiw;// 昵称 编辑框

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
        NineGridTestModel model1 = new NineGridTestModel();
        model1.urlList.add(mUrls[0]);
        mList.add(model1);

        NineGridTestModel model2 = new NineGridTestModel();
        model2.urlList.add(mUrls[0]);
        model2.urlList.add(mUrls[1]);
        mList.add(model2);

        //        NineGridTestModel model4 = new NineGridTestModel();
        //        for (int i = 0; i < mUrls.length; i++) {
        //            model4.urlList.add(mUrls[i]);
        //        }
        //        model4.isShowAll = false;
        //        mList.add(model4);
        //
        //        NineGridTestModel model5 = new NineGridTestModel();
        //        for (int i = 0; i < mUrls.length; i++) {
        //            model5.urlList.add(mUrls[i]);
        //        }
        //        model5.isShowAll = true;//显示全部图片
        //        mList.add(model5);

        NineGridTestModel model6 = new NineGridTestModel();
        for (int i = 0; i < 9; i++) {
            model6.urlList.add(mUrls[i]);
        }
        mList.add(model6);

        NineGridTestModel model7 = new NineGridTestModel();
        for (int i = 3; i < 7; i++) {
            model7.urlList.add(mUrls[i]);
        }
        mList.add(model7);

        NineGridTestModel model8 = new NineGridTestModel();
        for (int i = 3; i < 6; i++) {
            model8.urlList.add(mUrls[i]);
        }
        mList.add(model8);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        mRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        mRecycler.setNestedScrollingEnabled(false);
        MyAdapter myAdapter = new MyAdapter(R.layout.item_circle_friend, mList);
        mRecycler.setAdapter(myAdapter);
    }

    private class MyAdapter extends BaseQuickAdapter<NineGridTestModel, BaseViewHolder> {

        public MyAdapter(@LayoutRes int layoutResId, @Nullable List<NineGridTestModel> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, NineGridTestModel item) {
            NineGridTestLayout nineGridlayout = helper.getView(R.id.nine_gridlayout);
            nineGridlayout.setIsShowAll(mList.get(helper.getAdapterPosition()).isShowAll);
            nineGridlayout.setUrlList(mList.get(helper.getAdapterPosition()).urlList);
            final RecyclerView recycler_chat = helper.getView(R.id.recycler_chat);
            recycler_chat.setLayoutManager(new LinearLayoutManager(mContext));
            recycler_chat.setNestedScrollingEnabled(false);
            List<String> list = new ArrayList<>();
            list.add("");
            list.add("");
            ChatAdapter chatAdapter = new ChatAdapter(R.layout.item_market_chat, list);
            recycler_chat.setAdapter(chatAdapter);
            chatAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    showCommentPopWindow(view);
                }
            });
            final ImageView iv_like = helper.getView(R.id.iv_like);
            if (item.isLike()) {
                iv_like.setImageResource(R.drawable.ganji_like3);
            } else {
                iv_like.setImageResource(R.drawable.ganji_like2);
            }
            helper.getView(R.id.ll_like).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mList.get(helper.getAdapterPosition()).setLike(!mList.get(helper.getAdapterPosition()).isLike());
                    notifyDataSetChanged();
                }
            });
        }
    }

    private void showCommentPopWindow(View view) {
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
                startActivity(new Intent(mContext, FaDongTaiActivity.class));
            }
        });
        dialog.getView(R.id.tv_takephoto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                startActivity(new Intent(mContext, FaDongTaiActivity.class));
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
