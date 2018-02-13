package com.power.customizingthecloud.activity.mine;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.DonkeyEarsDetailBaen;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DonkeyEarsDetailActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donkey_ears_detail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("驴耳朵记录");

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setNestedScrollingEnabled(false);

        List<DonkeyEarsDetailBaen> list = new ArrayList<>();
        for (int i = 0; i <4; i++) {
            DonkeyEarsDetailBaen detailBaen = new DonkeyEarsDetailBaen();
            detailBaen.setDate("2017.02.1"+ i);
            detailBaen.setContent("您今天签到获得平台奖励一只驴耳朵");
            list.add(detailBaen);
        }
        DonkeyEarsAdapter adapter = new DonkeyEarsAdapter(R.layout.item_donkeyears_detail,list);
        recyclerView.setAdapter(adapter);
    }

    private class DonkeyEarsAdapter extends BaseQuickAdapter<DonkeyEarsDetailBaen,BaseViewHolder>{

        public DonkeyEarsAdapter(@LayoutRes int layoutResId, @Nullable List<DonkeyEarsDetailBaen> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, DonkeyEarsDetailBaen item) {
            helper.setText(R.id.item_date_tv,item.getDate())
                    .setText(R.id.item_content_tv,item.getContent());
        }
    }

    @OnClick(R.id.title_back_iv)
    public void onViewClicked() {
    }
}
