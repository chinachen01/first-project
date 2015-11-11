package com.zwtx.beer_talk.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.zwtx.beer_talk.R;
import com.zwtx.beer_talk.adapter.PagerAdapter;
import com.zwtx.beer_talk.ui.base.BaseFragmentActivity;
import com.zwtx.beer_talk.ui.fragment.FindFragment;
import com.zwtx.beer_talk.ui.fragment.HomeFragment;
import com.zwtx.beer_talk.ui.fragment.PersonalFragment;
import com.zwtx.beer_talk.utils.L;

import java.util.ArrayList;
import java.util.List;

/**
 * APP首页,由ViewPager(含3个Fragment)和RadioGroup构成
 */
public class HomeActivity extends BaseFragmentActivity {
    private ViewPager mViewPage;
    private RadioGroup mRadioGroup;
    public final static int SCANNIN_GREQUEST_CODE = 1;
    private HomeFragment.HomeFragmentCallBackListener mListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mViewPage = (ViewPager) findViewById(R.id.home_pager);
        mRadioGroup = (RadioGroup) findViewById(R.id.home_group);
        /* 适配器 */
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        mViewPage.setAdapter(adapter);
        adapter.setDataChange(getFragmentList());
        //ViewPager滑动事件
        mViewPage.addOnPageChangeListener(new HomePageChangeListener());
        //RadioGroup
        mRadioGroup.setOnCheckedChangeListener(new HomeGroupChangeListener());
        //默认选中第一页
        ((RadioButton) mRadioGroup.getChildAt(0)).toggle();

    }

    /**
     * 初始化List<Fragment>
     * @return
     */
    private List<Fragment> getFragmentList() {
        List<Fragment> list = new ArrayList<>();
        list.add(HomeFragment.newInstance());
        list.add(FindFragment.newInstance());
        list.add(PersonalFragment.newInstance());
        return list;
    }

    /**
     * 对外提供方法跳转到此Activity
     * @param activity
     */
    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, HomeActivity.class);
        activity.startActivity(intent);
    }
    public void setHomeFragmentCallBackListner(HomeFragment.HomeFragmentCallBackListener listener) {
        mListener = listener;
    }

    /**
     * ViewPage滑动事件处理
     */
    private class HomePageChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            /* 滑动page时,RadioButton选中对应项*/
            if (position < mRadioGroup.getChildCount())
                ((RadioButton) mRadioGroup.getChildAt(position)).toggle();

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    /**
     * RadioGroup的点击事件处理
     */
    private class HomeGroupChangeListener implements RadioGroup.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            /*循环判断选中的单选按钮,获得对应项后,设置viewpager*/
            int count = group.getChildCount();
            for (int i = 0; i < count; i++) {
                if (checkedId == group.getChildAt(i).getId()) {
                    mViewPage.setCurrentItem(i);
                }
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        L.d("requestCode>>>>" + requestCode);
        L.d("resultCode>>>>" + resultCode);
        L.d("RESULT_OK>>>>>>" + RESULT_OK);
        switch (requestCode) {
            case SCANNIN_GREQUEST_CODE:
                if(resultCode == RESULT_OK){
                    Bundle bundle = data.getExtras();
                    //显示扫描到的内容
                    String result = bundle.getString("result");
                    if (mListener != null) {
                        mListener.setSearchEdit(result);
                    }
                    //显示
                }
                break;
        }
    }
}
