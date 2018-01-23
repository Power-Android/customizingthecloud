package com.power.customizingthecloud.activity.mine;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.DonkeyEarsBean;
import com.power.customizingthecloud.utils.TUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyDonkeyEarsActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.title_content_right_tv)
    TextView titleContentRightTv;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private TextView numTv;
    private TextView qiandaoTv;
    private RelativeLayout gonglueRl;
    private LinearLayout guizeLl;
    private DonkeyEarsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_donkey_ears);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("我的驴耳朵");
        titleContentRightTv.setVisibility(View.VISIBLE);
        titleContentRightTv.setText("明细");
        titleBackIv.setOnClickListener(this);
        titleContentRightTv.setOnClickListener(this);

        final List<DonkeyEarsBean> list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            DonkeyEarsBean bean = new DonkeyEarsBean();
            bean.setName("精品驴奶粉");
            bean.setYuanjia("原价：￥99.00");
            bean.setXianjia("￥19.9");
            list.add(bean);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setNestedScrollingEnabled(false);
        adapter = new DonkeyEarsAdapter(R.layout.item_donkey_ears, list);
        initHead();
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, final int position) {
                TUtils.showShort(mContext,"点击了---item"+position);
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                TUtils.showShort(mContext,"点击了---立即抢购"+position);
            }
        });
    }

    private void initHead() {
        View headerTop = LayoutInflater.from(mContext).inflate(R.layout.item_donkey_ears_header_top, null, false);
        numTv = (TextView) headerTop.findViewById(R.id.item_num_tv);
        qiandaoTv = (TextView) headerTop.findViewById(R.id.item_qiandao_tv);
        gonglueRl = (RelativeLayout) headerTop.findViewById(R.id.item_gonglue_rl);
        guizeLl = (LinearLayout) headerTop.findViewById(R.id.item_guize_ll);
        qiandaoTv.setOnClickListener(this);
        gonglueRl.setOnClickListener(this);
        guizeLl.setOnClickListener(this);
        adapter.addHeaderView(headerTop);
    }

    private class DonkeyEarsAdapter extends BaseQuickAdapter<DonkeyEarsBean, BaseViewHolder> {

        public DonkeyEarsAdapter(@LayoutRes int layoutResId, @Nullable List<DonkeyEarsBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, DonkeyEarsBean item) {
            helper  .setText(R.id.item_name_tv, item.getName())
                    .setText(R.id.item_yuanjia_tv, item.getYuanjia())
                    .setText(R.id.item_xianjia_tv, item.getXianjia())
                    .addOnClickListener(R.id.item_query_tv);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.title_content_right_tv:
                break;
            case R.id.item_qiandao_tv:
                TUtils.showShort(mContext,"点击了---签到");
                break;
            case R.id.item_gonglue_rl:
                break;
            case R.id.item_guize_ll:
                break;
        }
    }

}
