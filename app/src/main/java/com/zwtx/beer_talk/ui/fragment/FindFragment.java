package com.zwtx.beer_talk.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zwtx.beer_talk.R;
import com.zwtx.beer_talk.ui.activity.ProductListActivity;

public class FindFragment extends Fragment {
    private ListView mListView;
    private String[] items = {"酒类知识库","编辑精选故事","优惠活动","所有商家","扫一扫","代销活动","聚会活动"};
    public static FindFragment newInstance() {
        Bundle args = new Bundle();
        FindFragment fragment = new FindFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find, container, false);
        initView(view);
        return view;
    }

    /**
     * 初始化控件
     * @param view
     */
    private void initView(View view) {
        mListView = (ListView) view.findViewById(R.id.find_list_view);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, items);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new FindOnItemListener());
    }

    private class FindOnItemListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String tag = (String) parent.getAdapter().getItem(position);
            switch (tag) {
                case "编辑精选故事":
                    ProductListActivity.startActivity(getActivity(), tag);
                    break;
            }
        }
    }
}
