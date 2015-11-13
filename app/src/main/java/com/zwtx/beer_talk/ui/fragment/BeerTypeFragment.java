package com.zwtx.beer_talk.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.zwtx.beer_talk.R;
import com.zwtx.beer_talk.adapter.BeerTypeAdapter;
import com.zwtx.beer_talk.bean.BeerBean;
import com.zwtx.beer_talk.bean.BeerTypeBean;
import com.zwtx.beer_talk.utils.L;
import com.zwtx.beer_talk.widget.MeasureListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 包含酒类信息分类的碎片
 */
public class BeerTypeFragment extends Fragment {
    private ArrayList<BeerTypeBean> mList;
    public static final String KEY = "key";
    private MeasureListView mListView;

    public static BeerTypeFragment newInstance(ArrayList<BeerTypeBean> beans) {
        Bundle args = new Bundle();
        BeerTypeFragment fragment = new BeerTypeFragment();
        args.putParcelableArrayList(KEY, beans);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_beer_type, container, false);
        initView(v);
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null){
            mList = bundle.getParcelableArrayList(KEY);
            L.d(mList.size() + ">>>");
        }
    }

    private void initView(View view) {
        mListView = (MeasureListView) view.findViewById(R.id.beer_type_lis);
        BeerTypeAdapter adapter = new BeerTypeAdapter(getActivity(), mList);
        mListView.setAdapter(adapter);
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
