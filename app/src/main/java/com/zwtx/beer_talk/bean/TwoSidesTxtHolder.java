package com.zwtx.beer_talk.bean;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.zwtx.beer_talk.R;

public class TwoSidesTxtHolder extends RecyclerView.ViewHolder {
    public TextView mLeftTxt,mRightTxt;
    public View divider;
    public TwoSidesTxtHolder(View itemView) {
            super(itemView);
        mLeftTxt = (TextView) itemView.findViewById(R.id.item_left_txt);
        mRightTxt = (TextView) itemView.findViewById(R.id.item_right_txt);
        divider = itemView.findViewById(R.id.item_divider);
    }
}
