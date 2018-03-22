package com.power.customizingthecloud.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.DonkeyEarsBean;
import com.power.customizingthecloud.bean.SignBean;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.fragment.home.GoodDetailActivity;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.TUtils;
import com.power.customizingthecloud.utils.Urls;
import com.power.customizingthecloud.view.BaseDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyDonkeyEarsActivity extends BaseActivity implements View.OnClickListener, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.title_content_right_tv)
    TextView titleContentRightTv;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private TextView numTv;
    private TextView qiandaoTv;
    private RelativeLayout gonglueRl;
    private LinearLayout guizeLl;
    private DonkeyEarsAdapter adapter;
    private BaseDialog mDialog;
    private BaseDialog.Builder mBuilder;
    private List<DonkeyEarsBean.DataBean.GoodBean> list = new ArrayList<>();
    private int user_eselsohr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_donkey_ears);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("我的驴耳朵");
        titleContentRightTv.setVisibility(View.VISIBLE);
        titleContentRightTv.setText("明细");
        titleBackIv.setOnClickListener(this);
        titleContentRightTv.setOnClickListener(this);


        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setNestedScrollingEnabled(false);
        initData();
    }

    private void initData() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));

        OkGo.<DonkeyEarsBean>get(Urls.BASEURL + "api/v2/user/eselsohr")
                .tag(this)
                .headers(headers)
                .execute(new DialogCallback<DonkeyEarsBean>(this,DonkeyEarsBean.class) {
                    @Override
                    public void onSuccess(Response<DonkeyEarsBean> response) {
                        DonkeyEarsBean body = response.body();
                        if (body.getCode() == 1){
                            user_eselsohr = body.getData().getUser_eselsohr();
                            list = body.getData().getGood();
                            adapter = new DonkeyEarsAdapter(R.layout.item_donkey_ears, list);
                            initHead();
                            recyclerView.setAdapter(adapter);
                            numTv.setText(user_eselsohr+"");
                            adapter.setOnItemClickListener(MyDonkeyEarsActivity.this);
                            adapter.setOnItemChildClickListener(MyDonkeyEarsActivity.this);
                        }
                    }
                });
    }

    private void initHead() {
        View headerTop = LayoutInflater.from(mContext).inflate(R.layout.item_donkey_ears_header_top, null, false);
        numTv = (TextView) headerTop.findViewById(R.id.item_number_tv);
        qiandaoTv = (TextView) headerTop.findViewById(R.id.item_qiandao_tv);
        gonglueRl = (RelativeLayout) headerTop.findViewById(R.id.item_gonglue_rl);
        guizeLl = (LinearLayout) headerTop.findViewById(R.id.item_guize_ll);

        qiandaoTv.setOnClickListener(this);
        gonglueRl.setOnClickListener(this);
        guizeLl.setOnClickListener(this);
        adapter.addHeaderView(headerTop);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//        TUtils.showShort(mContext,"点击了---item"+position);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        startActivity(new Intent(mContext, GoodDetailActivity.class));
    }

    private class DonkeyEarsAdapter extends BaseQuickAdapter<DonkeyEarsBean.DataBean.GoodBean, BaseViewHolder> {

        public DonkeyEarsAdapter(@LayoutRes int layoutResId, @Nullable List<DonkeyEarsBean.DataBean.GoodBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, DonkeyEarsBean.DataBean.GoodBean item) {
            Glide.with(mContext).load(item.getImage()).into((ImageView) helper.getView(R.id.item_img_iv));
            helper  .setText(R.id.item_name_tv, item.getName())
                    .setText(R.id.item_yuanjia_tv, item.getPrice())
                    .setText(R.id.item_xianjia_tv, item.getEselsohr_deduction())
                    .addOnClickListener(R.id.item_query_tv);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.title_content_right_tv:
                startActivity(new Intent(mContext,DonkeyEarsDetailActivity.class));
                break;
            case R.id.item_qiandao_tv:
                initSign();
                break;
            case R.id.item_gonglue_rl:
                showShareDialog();
                break;
            case R.id.item_guize_ll:
                startActivity(new Intent(mContext,ServiceRegulationsActivity.class));
                break;
        }
    }

    private void initSign() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));

        OkGo.<SignBean>get(Urls.BASEURL + "api/v2/sign/store")
                .tag(this)
                .headers(headers)
                .execute(new DialogCallback<SignBean>(this,SignBean.class) {
                    @Override
                    public void onSuccess(Response<SignBean> response) {
                        SignBean body = response.body();
                        if (body.getCode() == 1){
                            TUtils.showShort(mContext,body.getMessage());
                            qiandaoTv.setText("已签到");
                            qiandaoTv.setClickable(false);
                        }else {
                            TUtils.showShort(mContext,body.getMessage());
                        }
                    }
                });
    }

    private void showShareDialog() {
        mBuilder = new BaseDialog.Builder(this);
        mDialog = mBuilder.setViewId(R.layout.diadlog_qdgl)
                //设置dialogpadding
                .setPaddingdp(0, 0, 0, 0)
                //设置显示位置
                .setGravity(Gravity.CENTER)
                //设置动画
                .setAnimation(R.style.Bottom_Top_aniamtion)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(true)
                //设置监听事件
                .builder();
        mDialog.show();
        mDialog.getView(R.id.jump_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
    }

}
