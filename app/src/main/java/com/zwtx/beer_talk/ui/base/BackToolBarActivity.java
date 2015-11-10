package com.zwtx.beer_talk.ui.base;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.zwtx.beer_talk.R;

/**
 * 封装有带返回按键的Toolbar的基类,继承此类需实现两个抽象方法
 */
public abstract class BackToolBarActivity extends AppCompatActivity {
    public Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.getInstance().add(this);//添加Activity到集合
        //需要在布局文件中设置: android:fitsSystemWindows="true"  android:clipToPadding="true"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setView();
            /* 初始化Toolbar */
        toolbar = (Toolbar) findViewById(R.id.toolbar_simple);
        setToolbarTitle();
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.selector_back_btn);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.getInstance().remove(this);//从结合中移除activity
    }

    /**
     * 设置ContentView
     * 你应该在此方法中调用setContentView取代在onCreate中
     */
    public abstract void setView();

    /**
     * 设置Toolbar的Title
     */
    public abstract void setToolbarTitle();
}
