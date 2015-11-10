package com.zwtx.beer_talk.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zwtx.beer_talk.R;

/**
 * Created by chenyong on 2015/11/10.
 */
public class SearchResultSummaryFragment extends Fragment {
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

    }
}
