package com.zwtx.beer_talk.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zwtx.beer_talk.R;

/**
 * Created by chenyong on 2015/11/10.
 */
public class StoryHolder extends RecyclerView.ViewHolder {
    public TextView title, date, content;
    public LinearLayout ly;
    public StoryHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.item_story_title);
        date = (TextView) itemView.findViewById(R.id.item_story_date);
        content = (TextView) itemView.findViewById(R.id.item_story_content);
        ly = (LinearLayout) itemView.findViewById(R.id.item_story_ly);
    }
}
