package com.power.customizingthecloud.activity.mine;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditAddressActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_back_iv) ImageView titleBackIv;
    @BindView(R.id.title_content_tv) TextView titleContentTv;
    @BindView(R.id.title_content_right_tv) TextView titleContentRightTv;
    @BindView(R.id.name_et) EditText nameEt;
    @BindView(R.id.phone_et) EditText phoneEt;
    @BindView(R.id.adress_tv) TextView adressTv;
    @BindView(R.id.address_rl) RelativeLayout addressRl;
    @BindView(R.id.jiedao_tv) TextView jiedaoTv;
    @BindView(R.id.jiedao_rl) RelativeLayout jiedaoRl;
    @BindView(R.id.detail_address_et) EditText detailAddressEt;
    private String type;
    private AddressManageBean.DataBean dataBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleBackIv.setOnClickListener(this);
        titleContentTv.setText("编辑收货地址");
        titleContentRightTv.setVisibility(View.VISIBLE);
        titleContentRightTv.setText("保存");
        titleContentRightTv.setOnClickListener(this);

        type = getIntent().getStringExtra("type");
        if (TextUtils.equals("edit",type)){
            dataBean = (AddressManageBean.DataBean) getIntent().getSerializableExtra("dataBean");
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.title_content_right_tv:
                if (TextUtils.equals("new",type)){
                    initData();
                }else if (TextUtils.equals("edit",type)){
                    initEdit();
                }
                break;
            case R.id.address_rl:
                break;
            case R.id.jiedao_rl:
                break;
        }
    }

    private void initEdit() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));
        HttpParams params = new HttpParams();
        params.put("true_name",nameEt.getText().toString()+"");
        params.put("area_id","1");
        params.put("city_id","2");
        params.put("province_id","3");
        params.put("area_info","北京市 海淀区 西二旗");
        params.put("address","中关村软件园");
        params.put("mobile",phoneEt.getText().toString()+"");
        params.put("address_id",dataBean.getId());

        OkGo.<BaseBean>post(Urls.BASEURL + "api/v2/address/edit")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<BaseBean>(this,BaseBean.class) {
                    @Override
                    public void onSuccess(Response<BaseBean> response) {
                        BaseBean body = response.body();
                        if (body.getCode() ==1){
                            TUtils.showShort(mContext,body.getMessage());
                            finish();
                        }else {
                            TUtils.showShort(mContext,body.getMessage());
                        }
                    }
                });
    }

    private void initData() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));
        HttpParams params = new HttpParams();
        params.put("true_name",nameEt.getText().toString()+"");
        params.put("area_id","1");
        params.put("city_id","2");
        params.put("province_id","3");
        params.put("area_info","北京市 海淀区 西二旗");
        params.put("address","中关村软件园");
        params.put("mobile",phoneEt.getText().toString()+"");

        OkGo.<BaseBean>post(Urls.BASEURL + "api/v2/address/add")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<BaseBean>(this,BaseBean.class) {
                    @Override
                    public void onSuccess(Response<BaseBean> response) {
                        BaseBean body = response.body();
                        if (body.getCode() ==1){
                            TUtils.showShort(mContext,body.getMessage());
                            finish();
                        }else {
                            TUtils.showShort(mContext,body.getMessage());
                        }
                    }
                });
    }
}
