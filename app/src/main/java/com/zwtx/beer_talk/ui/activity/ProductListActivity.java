package com.zwtx.beer_talk.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.zwtx.beer_talk.R;
import com.zwtx.beer_talk.adapter.ItemProductAdapter;
import com.zwtx.beer_talk.adapter.StoryAdapter;
import com.zwtx.beer_talk.bean.SearchProductBean;
import com.zwtx.beer_talk.bean.StoryBean;
import com.zwtx.beer_talk.ui.base.BackToolBarActivity;
import com.zwtx.beer_talk.widget.HidingRecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 显示产品List的界面(搜索结果,推荐等)
 * 界面由Toolbar,fab(可滑动隐藏显示),RecyclerView组成
 */
public class ProductListActivity extends BackToolBarActivity {
    public static final String TAG = "tag";
    public static final String TAG_SEARCH = "查询列表";
    public static final String TAG_STORY = "编辑精选故事";
    private RecyclerView mRecycler;
    private ImageButton mFab;
    private ImageView mShareImage;
    private String mTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mRecycler = (RecyclerView) findViewById(R.id.recycler_view_recycler);
        mFab = (ImageButton) findViewById(R.id.recycler_view_fab);
        mShareImage = (ImageView) findViewById(R.id.recycler_view_share);
        mRecycler.setLayoutManager(new LinearLayoutManager(ProductListActivity.this));
        initRecycler();
        /* 滑动隐藏Toolbar */
        mRecycler.addOnScrollListener(new HidingListener(this));
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mShareImage.getVisibility() == View.INVISIBLE) {
                    mShareImage.setVisibility(View.VISIBLE);
                    TranslateAnimation animation = new TranslateAnimation(0, 0, mShareImage.getHeight(), 0);
                    animation.setDuration(400);
                    mShareImage.startAnimation(animation);
                } else {
                    mShareImage.setVisibility(View.INVISIBLE);
                    TranslateAnimation animation = new TranslateAnimation(0, 0, 0, mShareImage.getHeight());
                    animation.setDuration(400);
                    mShareImage.startAnimation(animation);
                }
            }
        });
    }

    private void initRecycler() {
        switch (mTag) {
            case TAG_SEARCH:
                ItemProductAdapter searchProductAdapter = new ItemProductAdapter();
                mRecycler.setAdapter(searchProductAdapter);
                searchProductAdapter.setDadaChanged(getResultList());
                searchProductAdapter.setOnItemClickListener(new ItemProductAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        //传递uid到下一个Activity,暂时先写死
                        SearchResultActivity.startActivity(ProductListActivity.this, 1);
                    }
                });
                break;

            case TAG_STORY:
                        StoryAdapter storyAdapter = new StoryAdapter(ProductListActivity.this);
                        mRecycler.setAdapter(storyAdapter);
                storyAdapter.setDataChanged(getStoryList());
                storyAdapter.setOnItemClickListener(new StoryAdapter.StoryOnItemClickListener() {
                    @Override
                    public void startActivity(int position) {
                        //传递uid到下一个Activity,暂时先写死
                        SearchResultActivity.startActivity(ProductListActivity.this,1);
                    }
                });
                break;
        }
    }

    /**
     * 对外提供方法跳转到此Activity
     *
     * @param context
     * @param tag     标记,以此来判断该访问何种数据源
     */
    public static void startActivity(Context context, String tag) {
        Intent intent = new Intent(context, ProductListActivity.class);
        intent.putExtra(TAG, tag);
        context.startActivity(intent);
    }

    @Override
    public void setView() {
        setContentView(R.layout.activity_recycler_view);
    }

    @Override
    public void setToolbarTitle() {
        Intent intent = getIntent();
        mTag = intent.getStringExtra(TAG);
        toolbar.setTitle(mTag);
    }

    private class HidingListener extends HidingRecyclerViewScrollListener {

        public HidingListener(Context context) {
            super(context);
        }

        @Override
        public void onMoved(int distance) {
            toolbar.setTranslationY(-distance);
        }

        @Override
        public void onToolbarShow() {
            toolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));

        }

        @Override
        public void onToolbarHide() {
            toolbar.animate().translationY(-toolbar.getHeight()).setInterpolator(new AccelerateInterpolator(2));

        }

        @Override
        public void onFabShow() {
            mFab.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
        }

        @Override
        public void onFabHide() {
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mFab.getLayoutParams();
            int fabBottom = lp.bottomMargin;
            mFab.animate().translationY(mFab.getHeight() + fabBottom).setInterpolator((new AccelerateInterpolator(2))).start();
        }
    }

    private List<StoryBean> getStoryList() {
        List<StoryBean> list = new ArrayList<>();
        StoryBean bean = new StoryBean();
        bean.setTitle("你不知道的茅台酒");
        bean.setDate("2015-06-15 16:30");
        bean.setContent("茅台酒独产于中国贵州省遵义市仁怀市茅台镇，是汉族的特产酒。是与苏格兰威士忌、法国科涅克白兰地齐名的世界三大蒸馏名酒之一，是大曲酱香型白酒的鼻祖，更是中国的国酒。");
        bean.setId(new int[]{R.drawable.test_story, R.drawable.test_story, R.drawable.test_story});
        list.add(bean);
        bean = new StoryBean();
        bean.setTitle("你不知道的茅台酒");
        bean.setDate("2015-06-15 16:30");
        bean.setContent("茅台酒独产于中国贵州省遵义市仁怀市茅台镇，是汉族的特产酒。是与苏格兰威士忌、法国科涅克白兰地齐名的世界三大蒸馏名酒之一，是大曲酱香型白酒的鼻祖，更是中国的国酒。");
        bean.setId(new int[]{R.drawable.test_story, R.drawable.test_story, R.drawable.test_story, R.drawable.test_story, R.drawable.test_story, R.drawable.test_story});
        list.add(bean);
        bean = new StoryBean();
        bean.setTitle("你不知道的茅台酒");
        bean.setDate("2015-06-15 16:30");
        bean.setContent("茅台酒独产于中国贵州省遵义市仁怀市茅台镇，是汉族的特产酒。是与苏格兰威士忌、法国科涅克白兰地齐名的世界三大蒸馏名酒之一，是大曲酱香型白酒的鼻祖，更是中国的国酒。");
        bean.setId(new int[]{R.drawable.test_story, R.drawable.test_story, R.drawable.test_story});
        list.add(bean);
        bean = new StoryBean();
        bean.setTitle("你不知道的茅台酒");
        bean.setDate("2015-06-15 16:30");
        bean.setContent("茅台酒独产于中国贵州省遵义市仁怀市茅台镇，是汉族的特产酒。是与苏格兰威士忌、法国科涅克白兰地齐名的世界三大蒸馏名酒之一，是大曲酱香型白酒的鼻祖，更是中国的国酒。");
        bean.setId(new int[]{R.drawable.test_story, R.drawable.test_story, R.drawable.test_story});
       list.add(bean);
        bean = new StoryBean();
        bean.setTitle("你不知道的茅台酒");
        bean.setDate("2015-06-15 16:30");
        bean.setContent("茅台酒独产于中国贵州省遵义市仁怀市茅台镇，是汉族的特产酒。是与苏格兰威士忌、法国科涅克白兰地齐名的世界三大蒸馏名酒之一，是大曲酱香型白酒的鼻祖，更是中国的国酒。");
        bean.setId(new int[]{R.drawable.test_story, R.drawable.test_story, R.drawable.test_story});
        list.add(bean);

        bean = new StoryBean();
        bean.setTitle("你不知道的茅台酒");
        bean.setDate("2015-06-15 16:30");
        bean.setContent("茅台酒独产于中国贵州省遵义市仁怀市茅台镇，是汉族的特产酒。是与苏格兰威士忌、法国科涅克白兰地齐名的世界三大蒸馏名酒之一，是大曲酱香型白酒的鼻祖，更是中国的国酒。");
        bean.setId(new int[]{R.drawable.test_story, R.drawable.test_story});
        list.add(bean);
        bean = new StoryBean();
        bean.setTitle("你不知道的茅台酒");
        bean.setDate("2015-06-15 16:30");
        bean.setContent("茅台酒独产于中国贵州省遵义市仁怀市茅台镇，是汉族的特产酒。是与苏格兰威士忌、法国科涅克白兰地齐名的世界三大蒸馏名酒之一，是大曲酱香型白酒的鼻祖，更是中国的国酒。");
        bean.setId(new int[]{R.drawable.test_story, R.drawable.test_story, R.drawable.test_story});
        list.add(bean);
        bean = new StoryBean();
        bean.setTitle("你不知道的茅台酒");
        bean.setDate("2015-06-15 16:30");
        bean.setContent("茅台酒独产于中国贵州省遵义市仁怀市茅台镇，是汉族的特产酒。是与苏格兰威士忌、法国科涅克白兰地齐名的世界三大蒸馏名酒之一，是大曲酱香型白酒的鼻祖，更是中国的国酒。");
        list.add(bean);
        bean = new StoryBean();
        bean.setTitle("你不知道的茅台酒");
        bean.setDate("2015-06-15 16:30");
        bean.setContent("茅台酒独产于中国贵州省遵义市仁怀市茅台镇，是汉族的特产酒。是与苏格兰威士忌、法国科涅克白兰地齐名的世界三大蒸馏名酒之一，是大曲酱香型白酒的鼻祖，更是中国的国酒。");
        list.add(bean);
        return list;
    }

    /**
     * @return
     */
    private List<SearchProductBean> getResultList() {
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
        bean = new SearchProductBean();
        bean.setId(R.drawable.test_stuff);
        bean.setTitle("12年苏格兰威士忌700ml");
        bean.setContent("芝华士12年延续了芝华士始终如一的独特传统");
        list.add(bean);
        return list;
    }
}
