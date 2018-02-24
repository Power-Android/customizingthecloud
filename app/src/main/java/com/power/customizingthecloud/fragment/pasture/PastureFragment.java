package com.power.customizingthecloud.fragment.pasture;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseFragment;

/**
 * Created by Administrator on 2018/1/19.
 * 牧场
 */

public class PastureFragment extends BaseFragment {
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pasture, null);
        return view;
    }

    @Override
    protected void initLazyData() {

    }
}
