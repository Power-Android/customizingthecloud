package com.power.customizingthecloud.fragment.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseFragment;

/**
 * Created by Administrator on 2018/1/26.
 */

public class MyInteractionFragment extends BaseFragment {
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(mContext, R.layout.fragment_interraction, null);
        return view;
    }

    @Override
    protected void initLazyData() {

    }
}
