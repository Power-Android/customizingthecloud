package com.power.customizingthecloud.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.BaseBean;
import com.power.customizingthecloud.bean.MyBankListBean;
import com.power.customizingthecloud.callback.JsonCallback;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.Urls;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TixianThreeActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.card_name_tv)
    TextView cardNameTv;
    @BindView(R.id.money_et)
    EditText moneyEt;
    @BindView(R.id.tixian_tv)
    TextView tixianTv;
    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    private String bank_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tixian_three);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleBackIv.setOnClickListener(this);
        titleContentTv.setText("提现");
        cardNameTv.setOnClickListener(this);
        tixianTv.setOnClickListener(this);
    }

    private void initData() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(this, "token", ""));
        OkGo.<MyBankListBean>get(Urls.BASEURL + "api/v2/user/bankcard-list")
                .tag(this)
                .headers(headers)
                .execute(new JsonCallback<MyBankListBean>(MyBankListBean.class) {
                    @Override
                    public void onSuccess(Response<MyBankListBean> response) {
                        MyBankListBean body = response.body();
                        int code = body.getCode();
                        if (code == 0) {
                            Toast.makeText(mContext, body.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (code == 1) {
                            List<MyBankListBean.DataEntity> data = body.getData();
                            if (data!=null && data.size()>0){
                                String bank_name = data.get(0).getBank_name();
                                bank_id=data.get(0).getId()+"";
                                cardNameTv.setText(bank_name);
                            }
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.card_name_tv:
                startActivityForResult(new Intent(mContext,SelectorBankCardActivity.class),0);
                break;
            case R.id.tixian_tv:
                tixian();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==1){
            bank_id=data.getStringExtra("bank_id");
            cardNameTv.setText(data.getStringExtra("bank_name"));
        }
    }

    private void tixian() {
        if (TextUtils.isEmpty(bank_id)){
            Toast.makeText(this, "请选择提现的银行卡", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(moneyEt.getText().toString())){
            Toast.makeText(this, "请填写提现金额", Toast.LENGTH_SHORT).show();
            return;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(this, "token", ""));
        HttpParams params = new HttpParams();
        params.put("bank_id", bank_id);
        params.put("money", moneyEt.getText().toString());
        params.put("type", getIntent().getStringExtra("type"));
        OkGo.<BaseBean>post(Urls.BASEURL + "api/v2/user/tx-money")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new JsonCallback<BaseBean>(BaseBean.class) {
                    @Override
                    public void onSuccess(Response<BaseBean> response) {
                        BaseBean bankNameBean = response.body();
                        if (bankNameBean==null){
                            return;
                        }
                        int code = bankNameBean.getCode();
                        if (code == 0) {
                            Toast.makeText(mContext, bankNameBean.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (code == 1) {
                            Toast.makeText(mContext, bankNameBean.getMessage(), Toast.LENGTH_SHORT).show();
                            setResult(1,new Intent());
                            finish();
                        }
                    }
                });
    }
}
