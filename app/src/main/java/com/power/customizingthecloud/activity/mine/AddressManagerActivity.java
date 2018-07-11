package com.power.customizingthecloud.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.AddressManageBean;
import com.power.customizingthecloud.bean.BaseBean;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.TUtils;
import com.power.customizingthecloud.utils.Urls;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.NormalAlertDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddressManagerActivity extends BaseActivity implements View.OnClickListener, BaseQuickAdapter.OnItemChildClickListener {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.title_content_right_tv)
    TextView titleContentRightTv;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.xinzeng_tv)
    TextView xinzengTv;
    private boolean manager;
    private List<AddressManageBean.DataBean> list = new ArrayList<>();
    private AddressManageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_manager);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleBackIv.setOnClickListener(this);
        titleContentTv.setText("收货地址");
        titleContentRightTv.setVisibility(View.VISIBLE);
        titleContentRightTv.setOnClickListener(this);
        titleContentRightTv.setText("管理");
        xinzengTv.setOnClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setNestedScrollingEnabled(false);
        initData();
    }

    private void initData() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));
        OkGo.<AddressManageBean>get(Urls.BASEURL + "api/v2/address")
                .tag(this)
                .headers(headers)
                .execute(new DialogCallback<AddressManageBean>(this, AddressManageBean.class) {
                    @Override
                    public void onSuccess(Response<AddressManageBean> response) {
                        AddressManageBean body = response.body();
                        if (body.getCode() == 1) {
                            list = body.getData();
                            if (list.size() > 0) {
                                adapter = new AddressManageAdapter(R.layout.item_address_manage, list);
                                recyclerView.setAdapter(adapter);
                                adapter.setOnItemChildClickListener(AddressManagerActivity.this);
                            } else {
                                if (adapter != null) {
                                    adapter = new AddressManageAdapter(R.layout.item_address_manage, list);
                                    recyclerView.setAdapter(adapter);
//                                    adapter.notifyDataSetChanged();只这一样竟然不好使，不知道为什么
                                }
                            }
                        }
                    }
                });
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.item_del_tv:
                showTip(position);
                break;
            case R.id.item_edit_tv:
                Intent intent = new Intent(mContext, EditAddressActivity.class);
                intent.putExtra("type", "edit");
                intent.putExtra("dataBean", list.get(position));
                startActivityForResult(intent,0);
                break;
            case R.id.item_checkBox:
                initDefault(position);
                break;
            case R.id.ll_root:
                String type = getIntent().getStringExtra("type");
                if (!TextUtils.isEmpty(type) && type.equals("order")) {
                    Intent intent1 = new Intent();
                    intent1.putExtra("result", list.get(position));
                    setResult(2, intent1);
                    AddressManagerActivity.this.finish();
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent1 = new Intent();
        if (list!=null && list.size() > 0)
            intent1.putExtra("result", list.get(0));
        setResult(2, intent1);
        super.onBackPressed();
    }

    private void initDefault(int position) {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));
        HttpParams params = new HttpParams();
        params.put("address_id", list.get(position).getId());
        if (list.get(position).getIs_default() == 0) {
            params.put("default", "1");
        } else {
            params.put("default", "0");
        }

        OkGo.<BaseBean>get(Urls.BASEURL + "api/v2/address/set-default")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<BaseBean>(this, BaseBean.class) {
                    @Override
                    public void onSuccess(Response<BaseBean> response) {
                        BaseBean body = response.body();
                        if (body.getCode() == 1) {
                            TUtils.showShort(mContext, body.getMessage());
                            initData();
                        }
                    }
                });
    }

    private void showTip(final int position) {
        new NormalAlertDialog.Builder(this)
                .setTitleVisible(false).setTitleText("提示")
                .setTitleTextColor(R.color.text_black)
                .setContentText("您确定要删除此收货地址吗？")
                .setContentTextColor(R.color.text_black)
                .setLeftButtonText("确定")
                .setLeftButtonTextColor(R.color.text_black)
                .setRightButtonText("取消")
                .setRightButtonTextColor(R.color.text_black)
                .setCanceledOnTouchOutside(false)
                .setOnclickListener(new DialogInterface.OnLeftAndRightClickListener<NormalAlertDialog>() {
                    @Override
                    public void clickLeftButton(NormalAlertDialog dialog, View view) {
                        initDel(position);
                        dialog.dismiss();
                    }

                    @Override
                    public void clickRightButton(NormalAlertDialog dialog, View view) {
                        dialog.dismiss();
                    }
                })
                .build()
                .show();
    }

    private void initDel(int position) {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));
        HttpParams params = new HttpParams();
        params.put("address_id", list.get(position).getId());

        OkGo.<BaseBean>get(Urls.BASEURL + "api/v2/address/delete")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<BaseBean>(this, BaseBean.class) {
                    @Override
                    public void onSuccess(Response<BaseBean> response) {
                        BaseBean body = response.body();
                        if (body.getCode() == 1) {
                            initData();
                            TUtils.showShort(mContext, body.getMessage());
                        } else {
                            TUtils.showShort(mContext, body.getMessage());
                        }
                    }
                });
    }

    private class AddressManageAdapter extends BaseQuickAdapter<AddressManageBean.DataBean, BaseViewHolder> {

        public AddressManageAdapter(@LayoutRes int layoutResId, @Nullable List<AddressManageBean.DataBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, AddressManageBean.DataBean item) {
            helper.setText(R.id.item_name_tv, item.getTrue_name())
                    .setText(R.id.item_phone_tv, item.getMobile())
                    .setText(R.id.item_address_tv, item.getArea_info() + " " + item.getAddress())
                    .addOnClickListener(R.id.item_del_tv)
                    .addOnClickListener(R.id.item_edit_tv)
                    .addOnClickListener(R.id.ll_root)
                    .addOnClickListener(R.id.item_checkBox);
            ImageView morenIv = helper.getView(R.id.item_moren_iv);
            RelativeLayout editRl = helper.getView(R.id.item_edit_rl);
            CheckBox checkBox = helper.getView(R.id.item_checkBox);
            TextView delTv = helper.getView(R.id.item_del_tv);
            TextView editTv = helper.getView(R.id.item_edit_tv);

            if (item.getIs_default() == 1)
                morenIv.setVisibility(View.VISIBLE);

            if (item.isEdit()) {
                editRl.setVisibility(View.VISIBLE);
                if (item.getIs_default() == 1)
                    checkBox.setChecked(true);
            } else {
                editRl.setVisibility(View.GONE);
            }

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.title_content_right_tv:
                if (!manager) {//编辑
                    titleContentRightTv.setText("完成");
                    xinzengTv.setVisibility(View.VISIBLE);
                    for (int i = 0; i < list.size(); i++) {
                        AddressManageBean.DataBean item = list.get(i);
                        item.setEdit(true);
                    }
                    if (adapter != null)
                        adapter.notifyDataSetChanged();
                } else {
                    titleContentRightTv.setText("管理");
                    xinzengTv.setVisibility(View.GONE);
                    for (int i = 0; i < list.size(); i++) {
                        AddressManageBean.DataBean item = list.get(i);
                        item.setEdit(false);
                    }
                    if (adapter != null)
                        adapter.notifyDataSetChanged();
                }
                manager = !manager;
                break;
            case R.id.xinzeng_tv:
                Intent intent = new Intent(mContext, EditAddressActivity.class);
                intent.putExtra("type", "new");
                startActivityForResult(intent, 0);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) {
            manager=false;
            xinzengTv.setVisibility(View.GONE);
            initData();
        }
    }
}
