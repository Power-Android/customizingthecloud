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
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.AddressManageBean;
import com.power.customizingthecloud.utils.TUtils;
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
    private List<AddressManageBean> list;
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

        list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            AddressManageBean bean = new AddressManageBean();
            bean.setName("POWER");
            bean.setPhone("1336666666"+i);
            bean.setAddress("北京市海淀区中关村软件园");
            bean.setIsMoren(i+"");
            list.add(bean);
        }

        adapter = new AddressManageAdapter(R.layout.item_address_manage, list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemChildClickListener(this);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()){
            case R.id.item_del_tv:
                TUtils.showShort(mContext,"点击了---删除");
                showTip(position);
                break;
            case R.id.item_edit_tv:
                TUtils.showShort(mContext,"点击了---编辑");
                startActivity(new Intent(mContext,EditAddressActivity.class));
                break;
            case R.id.item_checkBox:
                TUtils.showShort(mContext,"点击了---默认地址");
                break;
            case R.id.ll_root:
                String type = getIntent().getStringExtra("type");
                if (!TextUtils.isEmpty(type) && type.equals("order")){
                    AddressManagerActivity.this.finish();
                }
                break;
        }
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
                        list.remove(position);
                        adapter.notifyDataSetChanged();
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

    private class AddressManageAdapter extends BaseQuickAdapter<AddressManageBean,BaseViewHolder>{

        public AddressManageAdapter(@LayoutRes int layoutResId, @Nullable List<AddressManageBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, AddressManageBean item) {
            helper.setText(R.id.item_name_tv,item.getName())
                    .setText(R.id.item_phone_tv,item.getPhone())
                    .setText(R.id.item_address_tv,item.getAddress())
                    .addOnClickListener(R.id.item_del_tv)
                    .addOnClickListener(R.id.item_edit_tv)
                    .addOnClickListener(R.id.ll_root)
                    .addOnClickListener(R.id.item_checkBox);
            ImageView morenIv = helper.getView(R.id.item_moren_iv);
            RelativeLayout editRl = helper.getView(R.id.item_edit_rl);
            CheckBox checkBox = helper.getView(R.id.item_checkBox);
            TextView delTv = helper.getView(R.id.item_del_tv);
            TextView editTv = helper.getView(R.id.item_edit_tv);

            if (TextUtils.equals(item.getIsMoren(),"0")) morenIv.setVisibility(View.VISIBLE);
            if (item.isEdit()){
                editRl.setVisibility(View.VISIBLE);
                if (TextUtils.equals("0",item.getIsMoren())) checkBox.setChecked(true);
            }else {
                editRl.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.title_content_right_tv:
                if (!manager){//编辑
                    manager = !manager;
                    titleContentRightTv.setText("完成");
                    xinzengTv.setVisibility(View.VISIBLE);
                    for (int i = 0; i < list.size() ; i++) {
                        AddressManageBean item = list.get(i);
                        item.setEdit(true);
                    }
                    adapter.notifyDataSetChanged();
                }else {
                    manager = !manager;
                    titleContentRightTv.setText("管理");
                    xinzengTv.setVisibility(View.GONE);
                    for (int i = 0; i < list.size() ; i++) {
                        AddressManageBean item = list.get(i);
                        item.setEdit(false);
                    }
                    adapter.notifyDataSetChanged();
                }
                break;
            case R.id.xinzeng_tv:
                startActivity(new Intent(mContext,EditAddressActivity.class));
                break;
        }
    }

}
