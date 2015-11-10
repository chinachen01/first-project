package com.zwtx.beer_talk.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ScrollView;

import com.zwtx.beer_talk.utils.ScreenUtils;

/**
 * Created by chenyong on 2015/11/9.
 */
public abstract class HidingRecyclerViewScrollListener extends RecyclerView.OnScrollListener {
    private static final float HIDE_THRESHOLD = 10;
    private static final float SHOW_THRESHOLD = 70;

    private int mToolbarOffset = 0;
    private boolean mControlsVisible = true;
    private int mToolbarHeight;
    private boolean mFabVisible = true;
    private int FAB_HIDE_THRESHOLD = 20;
    private int scrolledDistance = 0;

    public HidingRecyclerViewScrollListener(Context context) {
        mToolbarHeight = ScreenUtils.getToolbarHeight(context);
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        int firstVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
        //show views if first item is first visible position and views are hidden

        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            if (firstVisibleItem == 0) {
                setVisible();
            } else {
                if (mControlsVisible) {
                    if (mToolbarOffset > HIDE_THRESHOLD) {
                        setInvisible();
                    } else {
                        setVisible();
                    }
                } else {
                    if ((mToolbarHeight - mToolbarOffset) > SHOW_THRESHOLD) {
                        setVisible();
                    } else {
                        setInvisible();
                    }
                }
            }
        }

    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        clipToolbarOffset();
        onMoved(mToolbarOffset);

        if ((mToolbarOffset < mToolbarHeight && dy > 0) || (mToolbarOffset > 0 && dy < 0)) {
            mToolbarOffset += dy;
        }
        /*  隐藏或显示悬浮按钮*/
        if (scrolledDistance > FAB_HIDE_THRESHOLD && mFabVisible) {
            onFabHide();
            mFabVisible = false;
            scrolledDistance = 0;
        } else if (scrolledDistance < -FAB_HIDE_THRESHOLD && !mFabVisible) {
            onFabShow();
            mFabVisible = true;
            scrolledDistance = 0;
        }
        if ((mFabVisible && dy > 0) || (!mFabVisible && dy < 0)) {
            scrolledDistance += dy;
        }
    }

    private void clipToolbarOffset() {
        if (mToolbarOffset > mToolbarHeight) {
            mToolbarOffset = mToolbarHeight;
        } else if (mToolbarOffset < 0) {
            mToolbarOffset = 0;
        }
    }

    private void setVisible() {
        if (mToolbarOffset > 0) {
            onToolbarShow();
            mToolbarOffset = 0;
        }
        mControlsVisible = true;
    }

    private void setInvisible() {
        if (mToolbarOffset < mToolbarHeight) {
            onToolbarHide();
            mToolbarOffset = mToolbarHeight;
        }
        mControlsVisible = false;
    }

    public abstract void onMoved(int distance);

    /**
     * 显示Toolbar
     */
    public abstract void onToolbarShow();

    /**
     * 隐藏Toolbar
     */
    public abstract void onToolbarHide();

    /**
     * 显示悬浮按钮
     */
    public abstract void onFabShow();

    /**
     * 隐藏悬浮按钮
     */
    public abstract void onFabHide();

}
