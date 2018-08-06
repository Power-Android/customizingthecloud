package com.power.customizingthecloud.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import com.power.customizingthecloud.bean.BankNameBean;
import com.power.customizingthecloud.callback.JsonCallback;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.TUtils;
import com.power.customizingthecloud.utils.Urls;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TixianSecondActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.name_et)
    EditText nameEt;
    @BindView(R.id.card_et)
    EditText cardEt;
    @BindView(R.id.bank_tv)
    TextView bank_tv;
    @BindView(R.id.jump_tv)
    TextView jumpTv;
    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    private String type;
    private boolean isBankCard;
    private String bank_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tixian_second);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleBackIv.setOnClickListener(this);
        titleContentTv.setText("个人信息");
        jumpTv.setOnClickListener(this);
        type = getIntent().getStringExtra("type");
        cardEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setBankName();
            }
        });
    }

    private void setBankName() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(this, "token", ""));
        HttpParams params = new HttpParams();
        params.put("bankcard", cardEt.getText().toString());
        OkGo.<BankNameBean>get(Urls.BASEURL + "api/v2/user/verification-bankcard")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new JsonCallback<BankNameBean>(BankNameBean.class) {
                    @Override
                    public void onSuccess(Response<BankNameBean> response) {
                        BankNameBean bankNameBean = response.body();
                        int code = bankNameBean.getCode();
                        if (code == 0) {
                            Toast.makeText(mContext, bankNameBean.getMessage(), Toast.LENGTH_SHORT).show();
                            bank_tv.setText("请选择所在银行");
                            isBankCard = false;
                        } else if (code == 1) {
                            BankNameBean.DataEntity data = bankNameBean.getData();
                            bank_tv.setText(data.getBank_name());
                            bank_type = data.getBank();
                            isBankCard = true;
                        }
                    }

                    @Override
                    public void onError(Response<BankNameBean> response) {
                        super.onError(response);
                        bank_tv.setText("请选择所在银行");
                        isBankCard = false;
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.jump_tv:
                if (TextUtils.isEmpty(nameEt.getText().toString().trim())) {
                    TUtils.showShort(mContext, "请输入持卡人真实姓名");
                    return;
                }
                if (TextUtils.isEmpty(cardEt.getText().toString().trim())) {
                    TUtils.showShort(mContext, "请输入本人银行卡号");
                    return;
                }
                if (!isBankCard) {
                    TUtils.showShort(mContext, "请输入正确的银行卡号");
                    return;
                }
                if (TextUtils.equals("addCard", type)) {
                    Intent intent = new Intent(mContext, BindCartActivity.class);
                    intent.putExtra("card_name", nameEt.getText().toString());
                    intent.putExtra("bank_card", cardEt.getText().toString());
                    intent.putExtra("bank_type", bank_type);
                    startActivityForResult(intent,0);
                } else {
                    startActivity(new Intent(mContext, TixianThreeActivity.class));
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==1){
            finish();
        }
    }
}
