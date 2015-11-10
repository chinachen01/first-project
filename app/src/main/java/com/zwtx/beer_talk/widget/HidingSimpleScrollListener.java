package com.zwtx.beer_talk.widget;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * 实现滑动隐藏Toolbar的监听事件,移动时无动画
 */
public abstract class HidingSimpleScrollListener extends RecyclerView.OnScrollListener {
    private boolean controlsVisible =true;
    private int HIDE_THRESHOLD = 20;
    private int scrolledDistance = 0 ;
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int firstVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
        //show views if first item is first visible position and views are hidden
        if (firstVisibleItem == 0) {
            if(!controlsVisible) {
                onShow();
                controlsVisible = true;
            }
        } else {
            if (scrolledDistance > HIDE_THRESHOLD && controlsVisible) {
                onHide();
                controlsVisible = false;
                scrolledDistance = 0;
            } else if (scrolledDistance < -HIDE_THRESHOLD && !controlsVisible) {
                onShow();
                controlsVisible = true;
                scrolledDistance = 0;
            }
            if ((controlsVisible && dy > 0) || (!controlsVisible && dy < 0)) {
                scrolledDistance += dy;
            }
        }

    }

    /**
     * 隐藏Toolbar
     */
    public abstract void onHide();

    /**
     * 显示Toolbar
     */
    public abstract void onShow();
}
