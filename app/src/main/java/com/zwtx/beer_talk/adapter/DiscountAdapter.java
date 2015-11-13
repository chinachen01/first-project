package com.zwtx.beer_talk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zwtx.beer_talk.R;
import com.zwtx.beer_talk.bean.DiscountBean;

import java.util.ArrayList;

/**
 * Created by chenyong on 2015/11/13.
 */
public class DiscountAdapter extends BaseAdapter {
    private ArrayList<DiscountBean> mList;
    private Context context;

    public DiscountAdapter(ArrayList<DiscountBean> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    public DiscountAdapter(Context context) {
        this.context = context;
    }

    public void setDataChanged(ArrayList<DiscountBean> list) {
        mList = list;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return mList != null ? mList.size(): 0;
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
        DiscountViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_discount, null);
            holder = new DiscountViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.item_discount_title_txt);
            holder.distance = (TextView) convertView.findViewById(R.id.item_discount_distance_txt);
            holder.content = (TextView) convertView.findViewById(R.id.item_discount_content_txt);
            holder.image = (ImageView) convertView.findViewById(R.id.item_discount_image);
            convertView.setTag(holder);
        } else {
            holder = (DiscountViewHolder) convertView.getTag();
        }
        DiscountBean bean = mList.get(position);
        holder.title.setText(bean.getTitle());
        holder.content.setText(bean.getContent());
        holder.image.setImageResource(bean.getImageID());

        return convertView;
    }
    class DiscountViewHolder {
        public TextView title,distance,content;
        public ImageView image;
    }
}
