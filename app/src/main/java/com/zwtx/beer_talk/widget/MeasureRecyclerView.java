package com.zwtx.beer_talk.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class MeasureRecyclerView extends RecyclerView{
    public MeasureRecyclerView(Context context) {
        super(context);
    }

    public MeasureRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MeasureRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
