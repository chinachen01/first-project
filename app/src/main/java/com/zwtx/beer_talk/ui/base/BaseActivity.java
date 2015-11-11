package com.zwtx.beer_talk.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * 继承该类的Activity无Title,activity将添加的list集合中
 */
public class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.getInstance().add(this);//添加Activity到集合
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.getInstance().remove(this);//从结合中移除activity
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
