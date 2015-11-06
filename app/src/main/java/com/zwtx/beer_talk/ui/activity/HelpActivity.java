package com.zwtx.beer_talk.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zwtx.beer_talk.R;
import com.zwtx.beer_talk.adapter.TwoSidesTxtAdapter;
import com.zwtx.beer_talk.ui.base.BackToolBarActivity;
import com.zwtx.beer_talk.utils.T;

import java.util.ArrayList;
import java.util.List;

public class HelpActivity extends BackToolBarActivity {
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.help_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(HelpActivity.this));
        TwoSidesTxtAdapter adapter  = new TwoSidesTxtAdapter(HelpActivity.this);
        mRecyclerView.setAdapter(adapter);
        adapter.setDataChange(getItemList());
        adapter.setOnItemClickListener(new TwoSidesTxtAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                T.showShort(HelpActivity.this, position);
            }
        });
    }

    private List<String> getItemList() {
        List<String> list = new ArrayList<>();
        list.add("开店帮助");
        list.add("联系我们");
        list.add("店铺等级划分说明");
        return list;
    }
    /**
     * 对外提供方法跳转到此Acitivity
     * @param context
     */
    public static void startAcitivity(Context context) {
        Intent intent = new Intent(context, HelpActivity.class);
        context.startActivity(intent);
    }
    @Override
    public void setView() {
        setContentView(R.layout.activity_help);
    }
    @Override
    public void setToolbarTitle() {
        toolbar.setTitle("帮助中心");
    }

}
