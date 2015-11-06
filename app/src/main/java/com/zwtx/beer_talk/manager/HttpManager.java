package com.zwtx.beer_talk.manager;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

/**
 * 联网操作的类
 */
public class HttpManager {
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(Context context, String url, ResponseHandlerInterface responseHandler) {
        client.get(context, url ,responseHandler);
    }
    public static void get(String url, ResponseHandlerInterface responseHandler) {
        client.get(url ,responseHandler);
    }

    public static void post(Context context,String url, RequestParams requestParams, ResponseHandlerInterface resposeHandler) {
        client.post(context, url, requestParams, resposeHandler);
    }
    public static void post(String url, RequestParams requestParams, ResponseHandlerInterface resposeHandler) {
        client.post(url, requestParams, resposeHandler);
    }
}
