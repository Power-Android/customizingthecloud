package com.power.customizingthecloud.im;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.power.customizingthecloud.MyApplication;
import com.power.customizingthecloud.utils.SpUtils;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;

/**
 * admin  2018/6/12
 */
public class RcConnect {

    public static void rongCloudConnection(final Context context,String token) {
        /**
         * <p>连接服务器，在整个应用程序全局，只需要调用一次
         * <p>如果调用此接口遇到连接失败，SDK 会自动启动重连机制进行最多10次重连，分别是1, 2, 4, 8, 16, 32, 64, 128, 256, 512秒后。
         * 在这之后如果仍没有连接成功，还会在当检测到设备网络状态变化时再次进行重连。</p>
         *
         * @param token    从服务端获取的用户身份令牌（Token）。
         * @return RongIM  客户端核心类的实例。
         */
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onSuccess(String userid) {
                setUserInfo();
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.e("MainActivity", "——onError—-" +
                        errorCode);
            }

            @Override
            public void onTokenIncorrect() {
                //Connect Token 失效的状态处理，需要重新获取 Token
            }
        });
    }

    public static void setUserInfo() {
        final String userid = SpUtils.getString(MyApplication.getGloableContext(), "userid", "");
        final String userName = SpUtils.getString(MyApplication.getGloableContext(), "userName", "");
        final String userHead = SpUtils.getString(MyApplication.getGloableContext(), "userHead", "");

        if (TextUtils.isEmpty(userHead)) {
            RongIM.getInstance().setCurrentUserInfo(new UserInfo("user_"+userid, userName, Uri.parse("")));
            RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
                @Override
                public UserInfo getUserInfo(String s) {
                    return new UserInfo("user_"+userid, userName, Uri.parse(""));
                }
            }, true);

        } else {
            RongIM.getInstance().setCurrentUserInfo(new UserInfo("user_"+userid, userName, Uri.parse(userHead)));

            RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
                @Override
                public UserInfo getUserInfo(String s) {
                    return new UserInfo("user_"+userid, userName, Uri.parse(userHead));
                }
            }, true);
        }
    }

}
