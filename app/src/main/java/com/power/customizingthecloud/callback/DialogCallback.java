package com.power.customizingthecloud.callback;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.lzy.okgo.request.base.Request;
import com.power.customizingthecloud.MyApplication;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.login.LoginActivity;

import java.lang.reflect.Type;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017/10/11.
 */

public abstract class DialogCallback<T> extends JsonCallback<T> {

    private LoadingDialog dialog;
    private Class<T> clazz;
    private Type type;
    private int code;


    private void initDialog(Activity activity) {
        if (dialog == null)
            dialog = new LoadingDialog(activity, R.style.dialog);
    }

    public DialogCallback(Class<T> clazz) {
        super();
        this.clazz = clazz;
        initDialog(activity);
    }

    public DialogCallback(Activity activity, Type type) {
        super();
        this.type = type;
        this.activity = activity;
        initDialog(activity);
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
            dialog.setCanceledOnTouchOutside(false);
        }
        // 主要用于在所有请求之前添加公共的请求头或请求参数
        // 例如登录授权的 token
        // 使用的设备信息
        // 可以随意添加,也可以什么都不传
        // 还可以在这里对所有的参数进行加密，均在这里实现
        /*request.headers("header1", "HeaderValue1")//
                .params("params1", "ParamsValue1")//
                .params("token", "3215sdf13ad1f65asd4f3ads1f");*/
    }

    @Override
    public T convertResponse(Response response) throws Throwable {
        ResponseBody body = response.body();
        if (body == null)
            return null;
        code = response.code();
        T data = null;
        Gson gson = new Gson();
        JsonReader jsonReader = new JsonReader(body.charStream());
        if (type != null)
            data = gson.fromJson(jsonReader, type);
        if (clazz != null)
            data = gson.fromJson(jsonReader, clazz);
        return data;
    }

    @Override
    public void onFinish() {
        //网络请求结束后关闭对话框
        if (activity == null) {
            return;
        }
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        if (code == 401) {
            //登录过期
            Toast.makeText(MyApplication.getGloableContext(), "登录已过期，请重新登录", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MyApplication.getGloableContext(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            MyApplication.getGloableContext().startActivity(intent);
        }
    }
}