package com.power.customizingthecloud.activity.mine;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.CardBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectorBankCardActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector_bank_card);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleBackIv.setOnClickListener(this);
        titleContentTv.setText("选择银行卡");

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setNestedScrollingEnabled(false);

        List<CardBean> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            CardBean bean = new CardBean();
            bean.setName("各种银行储蓄卡"+ i);
            bean.setMoren(i+"");
            list.add(bean);
        }
        SelectCardAdapter adapter = new SelectCardAdapter(R.layout.item_select_card,list);
        recyclerView.setAdapter(adapter);
    }

    private class SelectCardAdapter extends BaseQuickAdapter<CardBean,BaseViewHolder>{

        public SelectCardAdapter(@LayoutRes int layoutResId, @Nullable List<CardBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, CardBean item) {
            helper.setText(R.id.item_name_tv,item.getName());
            CheckBox checkBox = helper.getView(R.id.item_checkbox);
            if (TextUtils.equals("0",item.getMoren())) checkBox.setChecked(true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_back_iv:
                finish();
                break;
        }
    }
}
