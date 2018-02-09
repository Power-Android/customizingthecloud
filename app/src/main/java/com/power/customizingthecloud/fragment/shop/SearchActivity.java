package com.power.customizingthecloud.fragment.shop;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.view.FluidLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.tv_search)
    TextView mTvSearch;
    @BindView(R.id.liushiview)
    FluidLayout liushiview;
    @BindView(R.id.ll_hot)
    LinearLayout mLlHot;
    private List<String> hotNameList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        setHot();
        mIvBack.setOnClickListener(this);
    }

    private void setHot() {
        liushiview.removeAllViews();
        hotNameList.add("驴奶粉");
        hotNameList.add("驴奶粉");
        hotNameList.add("驴奶粉");
        hotNameList.add("驴奶粉");
        hotNameList.add("驴奶粉");
        hotNameList.add("驴奶粉");
        hotNameList.add("驴奶粉");
        hotNameList.add("驴奶粉");
        hotNameList.add("驴奶粉");
        hotNameList.add("驴奶粉");
        hotNameList.add("驴奶粉");
        hotNameList.add("驴奶粉");
        hotNameList.add("驴奶粉");
        hotNameList.add("驴奶粉");
        hotNameList.add("驴奶粉");
        hotNameList.add("驴奶粉");
        for (int i = 0; i < hotNameList.size(); i++) {
            final TextView tv = (TextView) View.inflate(this, R.layout.search_hot_item, null);
            tv.setText(hotNameList.get(i));
            FluidLayout.LayoutParams params = new FluidLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(12, 12, 12, 12);
            liushiview.addView(tv, params);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    doSearchHot(tv.getText().toString());
                }
            });
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
