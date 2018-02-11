package com.power.customizingthecloud.activity.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeedbackActivity extends BaseActivity {

    @BindView(R.id.content_et)
    EditText contentEt;
    @BindView(R.id.uppic_iv)
    ImageView uppicIv;
    @BindView(R.id.phone_et)
    EditText phoneEt;
    @BindView(R.id.commit_tv)
    TextView commitTv;
    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("意见反馈");
    }

    @OnClick({R.id.uppic_iv, R.id.commit_tv,R.id.title_back_iv,R.id.title_content_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.uppic_iv:
                break;
            case R.id.commit_tv:
                break;
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.title_content_tv:
                break;
        }
    }
}
