package com.power.customizingthecloud.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
import com.power.customizingthecloud.bean.AddressManageBean;
import com.power.customizingthecloud.bean.BaseBean;
import com.power.customizingthecloud.bean.PackageDetailBean;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.login.LoginActivity;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.Urls;
import com.power.customizingthecloud.view.BaseDialog;
import com.power.customizingthecloud.view.CommonPopupWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PackageDetailActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_back_iv)
    ImageView mTitleBackIv;
    @BindView(R.id.title_content_tv)
    TextView mTitleContentTv;
    @BindView(R.id.tv_personname)
    TextView mTvPersonname;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.iv_address)
    ImageView mIvAddress;
    @BindView(R.id.iv_good)
    ImageView mIvGood;
    @BindView(R.id.tv_shopname)
    TextView mTvShopname;
    @BindView(R.id.tv_oneprice)
    TextView mTvOneprice;
    @BindView(R.id.tv_count)
    TextView mTvCount;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.iv_time)
    ImageView mIvTime;
    @BindView(R.id.edt_liuyan)
    EditText mEdtLiuyan;
    @BindView(R.id.tv_commit)
    TextView mTvCommit;
    private BaseDialog mDialog;
    private BaseDialog.Builder mBuilder;
    private CommonPopupWindow popupWindow;
    private int mAdressId;
    private boolean hasAddress;
    private String package_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_detail);
        ButterKnife.bind(this);
        mTitleBackIv.setVisibility(View.VISIBLE);
        mTitleBackIv.setOnClickListener(this);
        mTitleContentTv.setText("确认订单");
        mTvCommit.setOnClickListener(this);
        mTvTime.setOnClickListener(this);
        mIvTime.setOnClickListener(this);
        mIvAddress.setOnClickListener(this);
        initData();
    }

    private void initData() {
        package_id = getIntent().getStringExtra("package_id");
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(this, "token", ""));
        HttpParams params = new HttpParams();
        params.put("package_id", package_id);
        OkGo.<PackageDetailBean>get(Urls.BASEURL + "api/v2/package/show")
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<PackageDetailBean>(PackageDetailActivity.this, PackageDetailBean.class) {
                    @Override
                    public void onSuccess(Response<PackageDetailBean> response) {
                        PackageDetailBean bean = response.body();
                        int code = bean.getCode();
                        if (code == 0) {
                            Toast.makeText(mContext, bean.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (code == 1) {
                            PackageDetailBean.DataEntity data = bean.getData();
                            mAdressId = data.getAddress().getId();
                            hasAddress = true;
                            if (!TextUtils.isEmpty(data.getAddress().getTrue_name())) {
                                mTvPersonname.setText("姓名：" + data.getAddress().getTrue_name());
                            } else {
                                hasAddress = false;
                            }
                            if (!TextUtils.isEmpty(data.getAddress().getMobile())) {
                                mTvPhone.setText("电话：" + data.getAddress().getMobile());
                            } else {
                                hasAddress = false;
                            }
                            if (!TextUtils.isEmpty(data.getAddress().getArea_info())) {
                                mTvAddress.setText("地址：" + data.getAddress().getArea_info() + data.getAddress().getAddress());
                            } else {
                                hasAddress = false;
                            }
                            Glide.with(mContext).load(data.getImage()).into(mIvGood);
                            mTvShopname.setText(data.getName());
                            mTvOneprice.setText("¥" + data.getPrice());
                        }
                    }
                });
    }

    //向下弹出
    public void showDownPop(final TextView textView, final List<String> list) {
        if (popupWindow != null && popupWindow.isShowing())
            return;
        popupWindow = new CommonPopupWindow.Builder(this)
                .setView(R.layout.popup_down)
                .setWidthAndHeight(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setAnimationStyle(R.style.AnimDown)
                .setViewOnclickListener(new CommonPopupWindow.ViewInterface() {
                    @Override
                    public void getChildView(View view, int layoutResId) {
                        RecyclerView recycle_view = (RecyclerView) view.findViewById(R.id.recycle_view);
                        recycle_view.setLayoutManager(new LinearLayoutManager(PackageDetailActivity.this));
                        PopupAdapter mAdapter = new PopupAdapter(R.layout.item_pop_textview, list);
                        recycle_view.setAdapter(mAdapter);
                        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                textView.setText(list.get(position));
                                if (popupWindow != null) {
                                    popupWindow.dismiss();
                                }
                            }
                        });
                    }
                })
                .setOutsideTouchable(true)
                .create();
        popupWindow.showAsDropDown(textView);
    }

    private class PopupAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public PopupAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.tv_pop, item);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.tv_commit:
                String userid = SpUtils.getString(mContext, "userid", "");
                if (TextUtils.isEmpty(userid)) {
                    startActivity(new Intent(mContext, LoginActivity.class));
                    overridePendingTransition(R.anim.push_bottom_in, R.anim.push_bottom_out);
                    return;
                }
                getPackage();
                break;
            case R.id.iv_time:
                List<String> list = new ArrayList<String>();
                list.add("工作日");
                list.add("休息日");
                showDownPop(mTvTime, list);
                break;
            case R.id.iv_address:
                Intent intent1 = new Intent(this, AddressManagerActivity.class);
                intent1.putExtra("type", "order");
                startActivityForResult(intent1, 0);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 2) {//修改地址返回
            AddressManageBean.DataBean result = (AddressManageBean.DataBean) data.getSerializableExtra("result");
            if (result != null) {
                hasAddress = true;
                String true_name = result.getTrue_name();
                String area_info = result.getArea_info();
                String address = area_info + result.getAddress();
                String mobile = result.getMobile();
                mAdressId = result.getId();
                hasAddress = true;
                mTvPersonname.setText("姓名：" + true_name);
                mTvPhone.setText("电话：" + mobile);
                mTvAddress.setText("地址：" + address);
            } else {
                mTvPersonname.setText("姓名：暂无");
                mTvPhone.setText("电话：暂无");
                mTvAddress.setText("地址：暂无");
                hasAddress = false;
            }
        }
    }

    private void getPackage() {
        if (!hasAddress) {
            Toast.makeText(this, "请确保姓名、电话、地址都填写完整", Toast.LENGTH_SHORT).show();
            return;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(this, "token", ""));
        HttpParams params = new HttpParams();
        params.put("package_id", package_id);
        if (hasAddress) {
            params.put("address_id", mAdressId + "");
        }
        params.put("shipping_time", mTvTime.getText().toString());
        String liuyan = mEdtLiuyan.getText().toString();
        if (!TextUtils.isEmpty(liuyan)) {
            params.put("order_message", liuyan);
        }
        OkGo.<BaseBean>post(Urls.BASEURL + "api/v2/package/receive")
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<BaseBean>(PackageDetailActivity.this, BaseBean.class) {
                    @Override
                    public void onSuccess(Response<BaseBean> response) {
                        BaseBean bean = response.body();
                        int code = bean.getCode();
                        if (code == 0) {
                            Toast.makeText(mContext, bean.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (code == 1) {
                            Toast.makeText(mContext, bean.getMessage(), Toast.LENGTH_SHORT).show();
                            setResult(1,new Intent());
                            finish();
                            Intent intent = new Intent(PackageDetailActivity.this, MyOrderActivity.class);
                            intent.putExtra("type", "0");
                            startActivity(intent);
                        }
                    }
                });
    }
}
