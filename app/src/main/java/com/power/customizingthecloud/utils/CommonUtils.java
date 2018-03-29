package com.power.customizingthecloud.utils;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.login.bean.RegisterBean;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

/*
  动态生成selector
 */
public class CommonUtils {
    /**
     * 用java代码的方式动态生成状态选择器
     */
    public static Drawable generatePressedSelector(Drawable pressed, Drawable normal) {
        StateListDrawable drawable = new StateListDrawable();
        drawable.addState(new int[]{android.R.attr.state_pressed}, pressed);//  状态  , 设置按下的图片
        drawable.addState(new int[]{}, normal);//默认状态,默认状态下的图片

        //根据SDK版本设置状态选择器过度动画/渐变选择器/渐变动画
        if (Build.VERSION.SDK_INT > 10) {
            drawable.setEnterFadeDuration(500);
            drawable.setExitFadeDuration(500);
        }

        return drawable;
    }

    public static void insertCar(final Activity mActivity, String good_id, String good_type) {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mActivity, "token", ""));
        HttpParams params = new HttpParams();
        params.put("good_id", good_id);
        params.put("good_type", good_type);
        params.put("good_num", "1");
        OkGo.<RegisterBean>post(Urls.BASEURL + "api/v2/cart/add")
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<RegisterBean>(mActivity, RegisterBean.class) {
                    @Override
                    public void onSuccess(Response<RegisterBean> response) {
                        RegisterBean bean = response.body();
                        int code = bean.getCode();
                        if (code == 0) {
                            Toast.makeText(mActivity, bean.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (code == 1) {
                            Toast.makeText(mActivity, bean.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public static void insertCar2(final Activity mActivity, String good_id, String good_type,int num) {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mActivity, "token", ""));
        HttpParams params = new HttpParams();
        params.put("good_id", good_id);
        params.put("good_type", good_type);
        params.put("good_num", num+"");
        OkGo.<RegisterBean>post(Urls.BASEURL + "api/v2/cart/add")
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<RegisterBean>(mActivity, RegisterBean.class) {
                    @Override
                    public void onSuccess(Response<RegisterBean> response) {
                        RegisterBean bean = response.body();
                        int code = bean.getCode();
                        if (code == 0) {
                            Toast.makeText(mActivity, bean.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (code == 1) {
                            Toast.makeText(mActivity, bean.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
