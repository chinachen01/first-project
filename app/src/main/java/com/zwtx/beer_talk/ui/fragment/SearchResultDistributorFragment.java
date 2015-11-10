package com.zwtx.beer_talk.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zwtx.beer_talk.R;

public class SearchResultDistributorFragment extends Fragment{
    public static SearchResultDistributorFragment newInstance() {
        Bundle args = new Bundle();
        SearchResultDistributorFragment fragment = new SearchResultDistributorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_distributor, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

    }
}
