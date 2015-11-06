package com.zwtx.beer_talk.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomePageAdapter extends FragmentPagerAdapter{
    private List<Fragment> list = new ArrayList<>();
    public HomePageAdapter(FragmentManager fm) {
        super(fm);
    }
    /**
     * 更新数据源
     * @param list
     */
    public void setDataChange(List<Fragment> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
