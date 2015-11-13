package com.zwtx.beer_talk.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.zwtx.beer_talk.R;
import com.zwtx.beer_talk.bean.BeerBean;
import com.zwtx.beer_talk.bean.BeerTypeBean;
import com.zwtx.beer_talk.ui.base.BackToolBarActivity;
import com.zwtx.beer_talk.ui.base.BaseFragmentActivity;
import com.zwtx.beer_talk.ui.fragment.BeerTypeFragment;
import com.zwtx.beer_talk.utils.L;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyong on 2015/11/13.
 */
public class BeerTypeActivity extends BackToolBarActivity {
    private FrameLayout mFrame;
    private ArrayList<BeerTypeBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment fragment = BeerTypeFragment.newInstance(getData());
        ft.add(R.id.beer_type_frame,fragment);
        ft.commit();
    }

    @Override
    public void setView() {
        setContentView(R.layout.activity_beer_type);
    }

    @Override
    public void setToolbarTitle() {
        Intent intent = getIntent();
        String title = intent.getStringExtra("tag");
        toolbar.setTitle(title);
    }

    public static void startActivity(Context context, String tag) {
        Intent intent = new Intent(context, BeerTypeActivity.class);
        intent.putExtra("tag", tag);
        context.startActivity(intent);
    }
    private ArrayList<BeerTypeBean> getData() {
        ArrayList<BeerTypeBean> list1 = new ArrayList<>();
        BeerTypeBean bean1 = new BeerTypeBean();
        bean1.setType("洋酒");
        ArrayList<BeerBean> list2 = new ArrayList<>();
        BeerBean bean2 = new BeerBean();
        bean2.setName("白兰地");
        list2.add(bean2);
        bean2 = new BeerBean();
        bean2.setName("龙舌兰");
        list2.add(bean2);
        bean2 = new BeerBean();
        bean2.setName("威士忌");
        list2.add(bean2);
        bean2 = new BeerBean();
        bean2.setName("朗姆酒");
        list2.add(bean2);
        bean2 = new BeerBean();
        bean2.setName("伏特加");
        list2.add(bean2);
        bean2 = new BeerBean();
        bean2.setName("力交酒");
        list2.add(bean2);
        bean2 = new BeerBean();
        bean2.setName("金酒");
        list2.add(bean2);
        bean2 = new BeerBean();
        bean2.setName("韩国烧酒");
        list2.add(bean2);
        bean1.setBeers(list2);
        list1.add(bean1);

        bean1 = new BeerTypeBean();
        bean1.setType("白酒");
        list2 = new ArrayList<>();
        bean2 = new BeerBean();
        bean2.setName("茅台");
        list2.add(bean2);
        bean2 = new BeerBean();
        bean2.setName("五粮液");
        list2.add(bean2);
        bean2 = new BeerBean();
        bean2.setName("汾酒");
        list2.add(bean2);
        bean2 = new BeerBean();
        bean2.setName("泸州老窖");
        list2.add(bean2);
        bean2 = new BeerBean();
        bean2.setName("酱香型");
        list2.add(bean2);
        bean2 = new BeerBean();
        bean2.setName("浓香型");
        list2.add(bean2);
        bean2 = new BeerBean();
        bean2.setName("贵州");
        list2.add(bean2);
        bean2 = new BeerBean();
        bean2.setName("四川");
        list2.add(bean2);
        bean1.setBeers(list2);
        list1.add(bean1);
        return list1;
    }
}
