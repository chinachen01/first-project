package com.zwtx.beer_talk.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zwtx.beer_talk.R;
import com.zwtx.beer_talk.adapter.ItemProductListAdapter;
import com.zwtx.beer_talk.bean.SearchProductBean;
import com.zwtx.beer_talk.widget.MeasureListView;

import java.util.ArrayList;
import java.util.List;

public class SearchResultSummaryFragment extends Fragment {
    private MeasureListView mList;
    public static SearchResultSummaryFragment newInstance() {
        Bundle args = new Bundle();
        SearchResultSummaryFragment fragment = new SearchResultSummaryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_summary, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mList = (MeasureListView) view.findViewById(R.id.summary_similar_list);
        ItemProductListAdapter adapter = new ItemProductListAdapter(getActivity(),getSimilarList());
        mList.setAdapter(adapter);

    }
    private List<SearchProductBean> getSimilarList() {
        List<SearchProductBean> list = new ArrayList<>();
        SearchProductBean bean = new SearchProductBean();
        bean.setId(R.drawable.test_stuff);
        bean.setTitle("12年苏格兰威士忌700ml");
        bean.setContent("芝华士12年延续了芝华士始终如一的独特传统");
        list.add(bean);
        bean = new SearchProductBean();
        bean.setId(R.drawable.test_stuff);
        bean.setTitle("12年苏格兰威士忌700ml");
        bean.setContent("芝华士12年延续了芝华士始终如一的独特传统");
        list.add(bean);
        bean = new SearchProductBean();
        bean.setId(R.drawable.test_stuff);
        bean.setTitle("12年苏格兰威士忌700ml");
        bean.setContent("芝华士12年延续了芝华士始终如一的独特传统");
        list.add(bean);
        bean = new SearchProductBean();
        bean.setId(R.drawable.test_stuff);
        bean.setTitle("12年苏格兰威士忌700ml");
        bean.setContent("芝华士12年延续了芝华士始终如一的独特传统");
        list.add(bean);
        bean = new SearchProductBean();
        bean.setId(R.drawable.test_stuff);
        bean.setTitle("12年苏格兰威士忌700ml");
        bean.setContent("芝华士12年延续了芝华士始终如一的独特传统");
        list.add(bean);
        return list;
    }

}
