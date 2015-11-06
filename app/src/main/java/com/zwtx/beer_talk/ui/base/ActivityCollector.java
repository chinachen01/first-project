package com.zwtx.beer_talk.ui.base;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityCollector {
    private static List<Activity> activities = new ArrayList<>();
    public static List<Activity> getInstance() {
        return activities;
    }
    /**
     * 遍历所有集合中所有Activity并销毁
     */
    public static void finishAll() {
        for (Activity activity :  ActivityCollector.getInstance()) {
            if (activity != null)
                activity.finish();
        }
    }
}
