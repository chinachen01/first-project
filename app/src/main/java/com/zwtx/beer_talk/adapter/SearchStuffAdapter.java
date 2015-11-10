package com.zwtx.beer_talk.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zwtx.beer_talk.R;
import com.zwtx.beer_talk.adapter.holder.SearchStuffHolder;
import com.zwtx.beer_talk.bean.SearchProductBean;

import java.util.List;
public class SearchStuffAdapter extends RecyclerView.Adapter<SearchStuffHolder> {
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    private OnItemClickListener listener;
    private List<SearchProductBean> mList;

    /**
     * 注册回调监听
     * @param listener
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    @Override
    public SearchStuffHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_stuff, parent, false);
        return new SearchStuffHolder(view);
    }

    public void setDadaChanged(List<SearchProductBean> list) {
        mList = list;
    }

    @Override
    public void onBindViewHolder(SearchStuffHolder holder, final int position) {
        SearchProductBean bean = mList.get(position);
        holder.mImage.setImageResource(bean.getId());
        holder.mTitleTxt.setText(bean.getTitle());
        holder.mContentTxt.setText(bean.getContent());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }
}
