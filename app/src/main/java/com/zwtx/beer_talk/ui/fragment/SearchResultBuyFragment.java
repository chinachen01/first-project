package com.zwtx.beer_talk.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zwtx.beer_talk.R;
import com.zwtx.beer_talk.ui.activity.ProductDetailActivity;

/**
 * Created by chenyong on 2015/11/10.
 */
public class SearchResultBuyFragment extends Fragment {
    public static SearchResultBuyFragment newInstance() {
        Bundle args = new Bundle();
        SearchResultBuyFragment fragment = new SearchResultBuyFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buy, container, false);
        initView(view);
        ProductDetailActivity.startActivity(getActivity());
        return view;
    }

    private void initView(View view) {

    }
}
