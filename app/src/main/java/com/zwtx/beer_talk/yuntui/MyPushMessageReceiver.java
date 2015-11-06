package com.zwtx.beer_talk.yuntui;

import android.content.Context;
import android.util.Log;

import com.baidu.android.pushservice.PushMessageReceiver;

import java.util.List;

/**
 *
 */
public class MyPushMessageReceiver extends PushMessageReceiver {
    @Override
    public void onBind(Context context, int i, String s, String s1, String s2, String s3) {
        Log.d("tag", ">>>> 0代表启动云推送成功:" + i);
    }

    @Override
    public void onUnbind(Context context, int i, String s) {

    }

    @Override
    public void onSetTags(Context context, int i, List<String> list, List<String> list1, String s) {

    }

    @Override
    public void onDelTags(Context context, int i, List<String> list, List<String> list1, String s) {

    }

    @Override
    public void onListTags(Context context, int i, List<String> list, String s) {

    }
    //接收到穿透消息的回调方法
    @Override
    public void onMessage(Context context, String s, String s1) {
        Log.d("tag", "穿透消息:" + s);
    }

    //点击通知后的回调方法
    @Override
    public void onNotificationClicked(Context context, String s, String s1, String s2) {
        Log.d("tag", "通知点击事件");
    }

    @Override
    public void onNotificationArrived(Context context, String s, String s1, String s2) {

    }
}
