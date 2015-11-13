package com.zwtx.beer_talk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zwtx.beer_talk.R;
import com.zwtx.beer_talk.adapter.holder.BeerGridAdapter;
import com.zwtx.beer_talk.bean.BeerTypeBean;
import com.zwtx.beer_talk.widget.MeasureGridView;

import java.util.ArrayList;

/**
 * Created by chenyong on 2015/11/13.
 */
public class BeerTypeAdapter extends BaseAdapter {
    private ArrayList<BeerTypeBean> mList;
    private Context context;

    public BeerTypeAdapter(Context context) {
        this.context = context;
    }

    public BeerTypeAdapter(Context context, ArrayList<BeerTypeBean> list) {
        this(context);
        mList = list;
    }

    public void setDataChanged(ArrayList<BeerTypeBean> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList != null ? mList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BeerTypeViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_beer_type_item, null);
            holder = new BeerTypeViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.item_beer_type_txt);
            holder.grid = (MeasureGridView) convertView.findViewById(R.id.item_beer_type_grid);
            convertView.setTag(holder);
        } else {
            holder = (BeerTypeViewHolder) convertView.getTag();
        }
        holder.name.setText(mList.get(position).getType());
        BeerGridAdapter adapter = new BeerGridAdapter(context, mList.get(position).getBeers());
        holder.grid.setAdapter(adapter);
        return convertView;
    }

    class BeerTypeViewHolder {
        public TextView name;
        public MeasureGridView grid;
    }
}
