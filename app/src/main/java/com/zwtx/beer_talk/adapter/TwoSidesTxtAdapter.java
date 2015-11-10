package com.zwtx.beer_talk.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zwtx.beer_talk.R;
import com.zwtx.beer_talk.adapter.holder.TwoSidesTxtHolder;

import java.util.ArrayList;
import java.util.List;

public class TwoSidesTxtAdapter extends RecyclerView.Adapter<TwoSidesTxtHolder> {
    private List<String> mList = new ArrayList<>();
    private Context context;
    private OnItemClickListener listener;
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    public TwoSidesTxtAdapter(Context context) {
        this.context = context;
    }
    public void setDataChange(List<String> list) {
        mList = list;
        notifyDataSetChanged();
    }
    @Override
    public TwoSidesTxtHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_two_sides_txt, viewGroup, false);
        return new TwoSidesTxtHolder(view);
    }

    @Override
    public void onBindViewHolder(final TwoSidesTxtHolder twoSidesTxtHolder, final int i) {
        String string = mList.get(i);
        if (listener != null) {
            twoSidesTxtHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(twoSidesTxtHolder.itemView, i);
                }
            });
        }
        if (string.contains(":")) {
            String[] strings = string.split(":");
            twoSidesTxtHolder.mLeftTxt.setText(strings[0]);
            twoSidesTxtHolder.mRightTxt.setText(strings[1]);
        }else {
            twoSidesTxtHolder.mLeftTxt.setText(string);
        }
        if (i == mList.size()) {
            twoSidesTxtHolder.divider.setVisibility(View.GONE);
        }

    }


    @Override
    public int getItemCount() {
        return mList.size();
    }
}
