package com.power.customizingthecloud.activity.mine;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.BaseBean;
import com.power.customizingthecloud.bean.ReturnMoneyTypeBean;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.Urls;
import com.power.customizingthecloud.view.BaseDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OnlyTuiMoneyActiviy extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.face_iv)
    ImageView faceIv;
    @BindView(R.id.name_tv)
    TextView nameTv;
    @BindView(R.id.fenlei_tv)
    TextView fenleiTv;
    @BindView(R.id.hwzt_tv)
    TextView hwztTv;
    @BindView(R.id.hwzt_ll)
    RelativeLayout hwztLl;
    @BindView(R.id.reason_tv)
    TextView reasonTv;
    @BindView(R.id.tkyy_ll)
    RelativeLayout tkyyLl;
    @BindView(R.id.money_tv)
    TextView moneyTv;
    @BindView(R.id.instructions_et)
    EditText instructionsEt;
    @BindView(R.id.commit_tv)
    TextView commitTv;
    private BaseDialog mDialog;
    private BaseDialog.Builder mBuilder;
    private int goods_state = 1;
    private TuiReasonAdapter tuiReasonAdapter;
    private List<ReturnMoneyTypeBean.DataEntity.ReaseonEntity> reaseon;
    private int reaseonId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_refund);
        ButterKnife.bind(this);
        initView();
        initTkyyDialog();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("申请退款");
        String name = getIntent().getStringExtra("name");
        String type = getIntent().getStringExtra("type");
        String image = getIntent().getStringExtra("image");
        String price = getIntent().getStringExtra("price");
        nameTv.setText(name);
        moneyTv.setText("¥" + price);
        fenleiTv.setText("商品分类：" + type);
        Glide.with(MyApplication.getGloableContext()).load(image).into(faceIv);
    }

    private void showHwztDialog() {
        mBuilder = new BaseDialog.Builder(this);
        final BaseDialog dialog = mBuilder.setViewId(R.layout.dialog_hwzt)
                //设置dialogpadding
                .setPaddingdp(0, 0, 0, 0)
                //设置显示位置
                .setGravity(Gravity.BOTTOM)
                //设置动画
                .setAnimation(R.style.Bottom_Top_aniamtion)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(true)
                //设置监听事件
                .builder();
        dialog.show();
        dialog.getView(R.id.tv_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        final CheckBox cb_not = dialog.getView(R.id.cb_not);
        final CheckBox cb_already = dialog.getView(R.id.cb_already);
        if (goods_state == 1) {
            cb_not.setChecked(true);
            cb_already.setChecked(false);
        } else if (goods_state == 2) {
            cb_already.setChecked(true);
            cb_not.setChecked(false);
        }
        cb_not.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cb_already.setChecked(false);
                    cb_not.setChecked(true);
                    goods_state = 2;
                }
            }
        });
        cb_already.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cb_not.setChecked(false);
                    cb_already.setChecked(true);
                    goods_state = 1;
                }
            }
        });
    }

    private void showTkyyDialog() {
        mDialog.show();
    }

    private void initTkyyDialog() {
        mBuilder = new BaseDialog.Builder(this);
        mDialog = mBuilder.setViewId(R.layout.dialog_tkyy)
                //设置dialogpadding
                .setPaddingdp(0, 0, 0, 0)
                //设置显示位置
                .setGravity(Gravity.BOTTOM)
                //设置动画
                .setAnimation(R.style.Bottom_Top_aniamtion)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(true)
                //设置监听事件
                .builder();
        mDialog.getView(R.id.tv_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        final RecyclerView recycler = mDialog.getView(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(this, "token", ""));
        HttpParams params = new HttpParams();
        String order_id = getIntent().getStringExtra("order_id");
        params.put("order_id", order_id);
        OkGo.<ReturnMoneyTypeBean>get(Urls.BASEURL + "api/v2/user/order-return")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<ReturnMoneyTypeBean>(OnlyTuiMoneyActiviy.this, ReturnMoneyTypeBean.class) {
                             @Override
                             public void onSuccess(Response<ReturnMoneyTypeBean> response) {
                                 int code = response.code();
                                 ReturnMoneyTypeBean body = response.body();
                                 ReturnMoneyTypeBean.DataEntity data = body.getData();
                                 reaseon = data.getReaseon();
                                 if (reaseon == null || reaseon.size() == 0) {
                                     return;
                                 }
                                 tuiReasonAdapter = new TuiReasonAdapter(R.layout.tkyy_item, reaseon);
                                 recycler.setAdapter(tuiReasonAdapter);
                             }

                             @Override
                             public void onError(Response<ReturnMoneyTypeBean> response) {
                                 super.onError(response);
                             }
                         }
                );
    }

    private class TuiReasonAdapter extends BaseQuickAdapter<ReturnMoneyTypeBean.DataEntity.ReaseonEntity, BaseViewHolder> {

        public TuiReasonAdapter(@LayoutRes int layoutResId, @Nullable List<ReturnMoneyTypeBean.DataEntity.ReaseonEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, final ReturnMoneyTypeBean.DataEntity.ReaseonEntity item) {
            helper.setText(R.id.tv_status, item.getReason_info());
            final CheckBox checkBox = helper.getView(R.id.cbbb);
            if (item.isChecked()) {
                checkBox.setChecked(true);
            } else {
                checkBox.setChecked(false);
            }
            helper.getView(R.id.rl_reson).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < reaseon.size(); i++) {
                        if (i == helper.getAdapterPosition()) {
                            reaseon.get(i).setChecked(true);
                            reaseonId=item.getId();
                        } else {
                            reaseon.get(i).setChecked(false);
                        }
                    }
                    tuiReasonAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    private void commitTuikuan() {
        if (TextUtils.isEmpty(instructionsEt.getText().toString())) {
            Toast.makeText(this, "请填写退款说明", Toast.LENGTH_SHORT).show();
            return;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(this, "token", ""));
        HttpParams params = new HttpParams();
        String order_id = getIntent().getStringExtra("order_id");
        params.put("order_id", order_id);
        params.put("refund_type", "1");
        params.put("goods_state", goods_state);
        params.put("reason_id", reaseonId + "");
        params.put("reason_info", instructionsEt.getText().toString());
        OkGo.<BaseBean>post(Urls.BASEURL + "api/v2/user/order-return-post")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<BaseBean>(OnlyTuiMoneyActiviy.this, BaseBean.class) {
                             @Override
                             public void onSuccess(Response<BaseBean> response) {
                                 int code = response.code();
                                 BaseBean body = response.body();
                                 Toast.makeText(OnlyTuiMoneyActiviy.this, body.getMessage(), Toast.LENGTH_SHORT).show();
                                 finish();
                             }

                             @Override
                             public void onError(Response<BaseBean> response) {
                                 super.onError(response);
                             }
                         }
                );
    }

    @OnClick({R.id.title_back_iv, R.id.hwzt_ll, R.id.tkyy_ll, R.id.commit_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.hwzt_ll:
                showHwztDialog();
                break;
            case R.id.tkyy_ll:
                showTkyyDialog();
                break;
            case R.id.commit_tv:
                commitTuikuan();
                break;
        }
    }
}
