package com.power.customizingthecloud.activity.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RefundGoodActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView mTitleBackIv;
    @BindView(R.id.title_content_tv)
    TextView mTitleContentTv;
    @BindView(R.id.title_tv)
    TextView mTitleTv;
    @BindView(R.id.item_img_iv)
    ImageView mItemImgIv;
    @BindView(R.id.item_name_tv)
    TextView mItemNameTv;
    @BindView(R.id.item_fenlei_tv)
    TextView mItemFenleiTv;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refund_good);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mTitleBackIv.setVisibility(View.VISIBLE);
        mTitleContentTv.setText("申请退款");
    }

    @OnClick({R.id.title_back_iv, R.id.lxmj_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.lxmj_tv:
                break;
        }
    }
}
