package com.zwtx.beer_talk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zwtx.beer_talk.R;
import com.zwtx.beer_talk.bean.SearchProductBean;

import java.util.List;

/**
 * Created by chenyong on 2015/11/11.
 */
public class ItemProductListAdapter extends BaseAdapter {
    private List<SearchProductBean> list;
    private Context context;
    public ItemProductListAdapter(Context context,List<SearchProductBean> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_search_product, null);
        SearchProductBean bean = list.get(position);
        TextView title = (TextView) convertView.findViewById(R.id.item_search_stuff_title_txt);
        TextView content = (TextView) convertView.findViewById(R.id.item_search_stuff_content_txt);
        ImageView image = (ImageView) convertView.findViewById(R.id.item_search_stuff_image);
        title.setText(bean.getTitle());
        content.setText(bean.getContent());
        image.setImageResource(bean.getId());
        return convertView;
    }
}
