package com.zwtx.beer_talk.ui.base;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

/**
 *1.activity全屏显示
 *2.
 */
public class FullScreenBaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.getInstance().add(this);//添加Activity到集合
        //需要在布局文件中设置: android:fitsSystemWindows="true"  android:clipToPadding="true"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
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
