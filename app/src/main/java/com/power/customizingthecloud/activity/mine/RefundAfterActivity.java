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
import com.power.customizingthecloud.bean.RedPacketBean;
import com.power.customizingthecloud.bean.RefundAfterBean;
import com.power.customizingthecloud.utils.TUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RefundAfterActivity extends BaseActivity implements View.OnClickListener, BaseQuickAdapter.OnItemChildClickListener {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refund_after);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("退款/售后");
        titleBackIv.setOnClickListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setNestedScrollingEnabled(false);

        List<RefundAfterBean> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            RefundAfterBean bean = new RefundAfterBean();
            bean.setName("驴奶粉");
            bean.setNum("x1");
            bean.setMoney("￥99.00");
            bean.setFeilei("商品分类：驴奶粉");
            list.add(bean);
        }
        RefundAfterAdapter adapter = new RefundAfterAdapter(R.layout.item_refund_after,list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemChildClickListener(this);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        TUtils.showShort(mContext,"点击了---查看详情");
    }

    private class RefundAfterAdapter extends BaseQuickAdapter<RefundAfterBean,BaseViewHolder>{

        public RefundAfterAdapter(@LayoutRes int layoutResId, @Nullable List<RefundAfterBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, RefundAfterBean item) {
            helper.setText(R.id.item_name_tv,item.getName())
                    .setText(R.id.item_fenlei_tv,item.getFeilei())
                    .setText(R.id.item_money_tv,item.getMoney())
                    .setText(R.id.item_num_tv,item.getNum())
                    .addOnClickListener(R.id.item_use_tv);
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
