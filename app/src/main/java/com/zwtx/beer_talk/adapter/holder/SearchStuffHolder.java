package com.zwtx.beer_talk.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zwtx.beer_talk.R;

public class SearchStuffHolder extends RecyclerView.ViewHolder {
    public ImageView mImage;
    public TextView mTitleTxt,mContentTxt;
    public View divider;
    public SearchStuffHolder(View itemView) {
        super(itemView);
        mImage = (ImageView) itemView.findViewById(R.id.item_search_stuff_image);
        mTitleTxt = (TextView) itemView.findViewById(R.id.item_search_stuff_title_txt);
        mContentTxt = (TextView) itemView.findViewById(R.id.item_search_stuff_content_txt);
        divider = itemView.findViewById(R.id.item_search_stuff_divider);
    }
}
