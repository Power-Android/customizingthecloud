package com.power.customizingthecloud.activity.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
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
import com.power.customizingthecloud.bean.ReturnMoneyDetailBean;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.Urls;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

public class RefundGoodActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView mTitleBackIv;
    @BindView(R.id.title_content_tv)
    TextView mTitleContentTv;
    @BindView(R.id.title_tv)
    TextView mTitleTv;
    @BindView(R.id.tkyy_tv)
    TextView mTkyyTv;
    @BindView(R.id.tkje_tv)
    TextView mTkjeTv;
    @BindView(R.id.sqsj_tv)
    TextView mSqsjTv;
    @BindView(R.id.tkbh_tv)
    TextView mTkbhTv;
    @BindView(R.id.lxmj_tv)
    TextView mLxmjTv;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refund_good);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        String order_id = getIntent().getStringExtra("order_id");
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));
        HttpParams params = new HttpParams();
        params.put("order_id", order_id);
        OkGo.<ReturnMoneyDetailBean>get(Urls.BASEURL + "api/v2/user/order-return-show")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<ReturnMoneyDetailBean>(this, ReturnMoneyDetailBean.class) {
                    @Override
                    public void onSuccess(Response<ReturnMoneyDetailBean> response) {
                        ReturnMoneyDetailBean body = response.body();
                        if (body.getCode() == 1) {
                            ReturnMoneyDetailBean.DataEntity data = body.getData();
                            mTkyyTv.setText(data.getReason_info());
                            mTkjeTv.setText("¥"+data.getRefund_amount());
                            mSqsjTv.setText(data.getCreated_at());
                            mTkbhTv.setText(data.getRefund_sn());
                            ItemAdapter itemAdapter = new ItemAdapter(R.layout.item_tuikuan_detail, data.getGoods());
                            recyclerView.setAdapter(itemAdapter);
                        } else {
                            Toast.makeText(mContext, body.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private class ItemAdapter extends BaseQuickAdapter<ReturnMoneyDetailBean.DataEntity.GoodsEntity, BaseViewHolder> {

        public ItemAdapter(int layoutResId, @Nullable List<ReturnMoneyDetailBean.DataEntity.GoodsEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ReturnMoneyDetailBean.DataEntity.GoodsEntity item) {
            Glide.with(mContext).load(item.getGoods_image()).into((ImageView) helper.getView(R.id.item_img_iv));
            helper.setText(R.id.item_name_tv, item.getGoods_name())
                    .setText(R.id.item_fenlei_tv, "商品分类：" + item.getGoods_class());
        }
    }

    private void initView() {
        mTitleBackIv.setVisibility(View.VISIBLE);
        mTitleContentTv.setText("申请退款");
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setNestedScrollingEnabled(false);
    }

    @OnClick({R.id.title_back_iv, R.id.lxmj_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.lxmj_tv:
                RongIM.getInstance().startConversation(this, Conversation.ConversationType.PRIVATE, "admin_1", "客服牧小童");
                break;
        }
    }
}
