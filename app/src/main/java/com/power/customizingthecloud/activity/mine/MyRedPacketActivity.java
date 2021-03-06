package com.power.customizingthecloud.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.RedPacketBean;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.Urls;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyRedPacketActivity extends BaseActivity implements View.OnClickListener, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private List<RedPacketBean.DataBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_red_packet);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("我的礼包");
        titleBackIv.setOnClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setNestedScrollingEnabled(false);
        initData();
    }

    private void initData() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));

        OkGo.<RedPacketBean>get(Urls.BASEURL + "api/v2/package")
                .tag(this)
                .headers(headers)
                .execute(new DialogCallback<RedPacketBean>(this, RedPacketBean.class) {
                    @Override
                    public void onSuccess(Response<RedPacketBean> response) {
                        RedPacketBean body = response.body();
                        if (body.getCode() == 1) {
                            list = body.getData();
                            MyRedPacketAdapter adapter = new MyRedPacketAdapter(R.layout.item_red_packet, MyRedPacketActivity.this.list);
                            recyclerView.setAdapter(adapter);
                            adapter.setOnItemClickListener(MyRedPacketActivity.this);
                            adapter.setOnItemChildClickListener(MyRedPacketActivity.this);
                        }
                    }
                });
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
    }

    /**
     * @param adapter  适配器
     * @param view     视图
     * @param position 位置
     */
    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(mContext, PackageDetailActivity.class);
        intent.putExtra("package_id", list.get(position).getPackage_id() + "");
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) {
            finish();
        }
    }

    private class MyRedPacketAdapter extends BaseQuickAdapter<RedPacketBean.DataBean, BaseViewHolder> {

        public MyRedPacketAdapter(@LayoutRes int layoutResId, @Nullable List<RedPacketBean.DataBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, RedPacketBean.DataBean item) {
            helper.setText(R.id.item_name_tv, item.getName())
                    .setText(R.id.item_date_tv, item.getStart_time() + "-" + item.getEnd_time())
                    .setText(R.id.item_money_tv, "¥" + item.getPrice())
                    //                    .setText(R.id.item_num_tv,item.getNum())
                    .addOnClickListener(R.id.item_liji_lingqu_tv);
            TextView lijilingquTv = helper.getView(R.id.item_liji_lingqu_tv);
            ImageView yiguoqiIv = helper.getView(R.id.yi_guo_qi_iv);
            Glide.with(mContext).load(item.getImage()).into((ImageView) helper.getView(R.id.item_img_iv));
            if (item.getState() == 3) {
                lijilingquTv.setVisibility(View.GONE);
                yiguoqiIv.setVisibility(View.VISIBLE);
            } else if (item.getState() == 2) {
                helper.setVisible(R.id.ll_root, false);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
        }
    }
}
