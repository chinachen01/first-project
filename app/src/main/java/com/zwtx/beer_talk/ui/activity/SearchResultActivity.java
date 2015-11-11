package com.zwtx.beer_talk.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.zwtx.beer_talk.R;
import com.zwtx.beer_talk.adapter.PagerAdapter;
import com.zwtx.beer_talk.ui.base.BaseFragmentActivity;
import com.zwtx.beer_talk.ui.fragment.FindFragment;
import com.zwtx.beer_talk.ui.fragment.HomeFragment;
import com.zwtx.beer_talk.ui.fragment.PersonalFragment;
import com.zwtx.beer_talk.ui.fragment.SearchResultBuyFragment;
import com.zwtx.beer_talk.ui.fragment.SearchResultDetailFragment;
import com.zwtx.beer_talk.ui.fragment.SearchResultDistributorFragment;
import com.zwtx.beer_talk.ui.fragment.SearchResultSummaryFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 搜索结果界面
 */
public class SearchResultActivity extends BaseFragmentActivity{
    public static final String UID = "uid";
    private TextView mBackTxt;
    private RadioGroup mRadioGroup;
    private ViewPager mViewPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        initView();
    }
    private void initView() {
        mBackTxt = (TextView) findViewById(R.id.search_result_click_txt);
        mBackTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mRadioGroup = (RadioGroup) findViewById(R.id.search_result_group);
        mViewPage = (ViewPager) findViewById(R.id.search_result_pager);
            /* 适配器 */
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        mViewPage.setAdapter(adapter);
        adapter.setDataChange(getFragmentList());
        //ViewPager滑动事件
        mViewPage.addOnPageChangeListener(new SearchResultPageChangeListener());
        //RadioGroup
        mRadioGroup.setOnCheckedChangeListener(new SearchResultGroupChangeListener());
        //默认选中第一页
        ((RadioButton) mRadioGroup.getChildAt(0)).toggle();

    }

    /**
     * 初始化List<Fragment>
     * @return
     */
    private List<Fragment> getFragmentList() {
        List<Fragment> list = new ArrayList<>();
        list.add(SearchResultSummaryFragment.newInstance());
        list.add(SearchResultDetailFragment.newInstance());
        list.add(SearchResultDistributorFragment.newInstance());
        list.add(SearchResultBuyFragment.newInstance());
        return list;
    }
    /**
     * ViewPage滑动事件处理
     */
    private class SearchResultPageChangeListener implements ViewPager.OnPageChangeListener{

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
    private class SearchResultGroupChangeListener implements RadioGroup.OnCheckedChangeListener{
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

    /**
     * 对外挺方法跳转到此Activity
     * @param context
     * @param uid 商品的id
     */
    public static void startActivity(Context context, int uid) {
        Intent intent = new Intent(context, SearchResultActivity.class);
        intent.putExtra("uid", uid);
        context.startActivity(intent);
    }
}
