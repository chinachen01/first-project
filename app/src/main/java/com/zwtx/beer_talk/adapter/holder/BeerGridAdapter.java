package com.zwtx.beer_talk.adapter.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zwtx.beer_talk.R;
import com.zwtx.beer_talk.bean.BeerBean;

import java.util.ArrayList;

/**
 * Created by chenyong on 2015/11/13.
 */
public class BeerGridAdapter extends BaseAdapter {
    private ArrayList<BeerBean> mList;
    private Context context;
    public BeerGridAdapter(Context context) {
        this.context = context;
    }
    public BeerGridAdapter(Context context, ArrayList<BeerBean> list) {
        this(context);
        mList = list;
    }
    public void setDataChanged(ArrayList<BeerBean> list) {
        mList = list;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return mList != null ? mList.size():0;
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
        BeerViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_beer_grid, null);
            holder = new BeerViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.item_beer_grid_txt);
            convertView.setTag(holder);
        } else {
            holder = (BeerViewHolder) convertView.getTag();
        }
        holder.name.setText(mList.get(position).getName());
        return convertView;
    }
    class BeerViewHolder {
        public TextView name;
    }
}
