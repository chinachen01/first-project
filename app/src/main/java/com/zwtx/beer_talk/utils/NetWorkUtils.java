package com.zwtx.beer_talk.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 *
 */
public class NetWorkUtils {
    /**
     * 获取网络连接状态,如果你想调用此方法,请确认你已注册权限 ACCESS_NETWORK_STATE
     *
     * @param context 引用上下文
     * @return true代表连接可用, false代表连接不可用
     */
    public static boolean getNetWorkState(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;// 网络连接可用
        } else {
            return false;// 网络连接不可用!
        }
    }
}
