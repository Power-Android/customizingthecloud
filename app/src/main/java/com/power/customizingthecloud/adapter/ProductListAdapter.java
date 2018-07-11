package com.power.customizingthecloud.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.bean.MarketShopBean;

import java.util.List;

/**
 * Created by Administrator on 2018/2/23.
 */

public class ProductListAdapter extends BaseQuickAdapter<MarketShopBean.DataBean, BaseViewHolder> {

    private Context mContext;
    private int mPosition;
    private List<MarketShopBean.DataBean> mData;

    public ProductListAdapter(@LayoutRes int layoutResId, @Nullable List<MarketShopBean.DataBean> data, Context context, int position) {
        super(layoutResId, data);
        mContext = context;
        mPosition = position;
        mData = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, MarketShopBean.DataBean item) {
        Glide.with(mContext).load(item.getImage()).into((ImageView) helper.getView(R.id.item_face_iv));
        if (mPosition == 1) {//商品
            helper.setText(R.id.item_name_tv, item.getName())
                    .setText(R.id.item_money_tv, "零售价：￥" + item.getPrice())
                    .setText(R.id.item_shouyi_tv, "销售收益：￥" + item.getDistribution_price());
        } else if (mPosition == 2) {//毛驴
            helper.setText(R.id.item_name_tv, "养殖成本" + item.getPrice())
                    .setText(R.id.item_money_tv, "认养收益率：" + item.getProfit())
                    .setText(R.id.item_shouyi_tv, "销售收益：￥" + item.getDistribution_eselsohr());
        }
    }
}
