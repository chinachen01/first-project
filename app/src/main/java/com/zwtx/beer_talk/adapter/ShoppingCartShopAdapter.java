package com.zwtx.beer_talk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.zwtx.beer_talk.R;
import com.zwtx.beer_talk.bean.ProductBean;
import com.zwtx.beer_talk.bean.ShopProductBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyong on 2015/11/12.
 */
public class ShoppingCartShopAdapter extends BaseAdapter {
    private List<ShopProductBean> mList;
    private Context context;

    public void setDataChanged(List<ShopProductBean> list) {
        mList = list;
        notifyDataSetChanged();
    }
    public ShoppingCartShopAdapter(Context context) {
        this.context= context;
    }
    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
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
        ShoppingCartShopViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_shopping_cart_shop, null);
            holder = new ShoppingCartShopViewHolder();
            holder.check = (CheckBox) convertView.findViewById(R.id.item_shopping_cart_shop_check);
            holder.title = (TextView) convertView.findViewById(R.id.item_shopping_cart_shop_name_txt);
            holder.point = (TextView) convertView.findViewById(R.id.item_shopping_cart_shop_point_txt);
            holder.list = (ListView) convertView.findViewById(R.id.item_shopping_cart_shop_list);
            convertView.setTag(holder);
        } else {
            holder = (ShoppingCartShopViewHolder) convertView.getTag();
        }
        ShopProductBean bean = mList.get(position);
        holder.check.setChecked(bean.isChecked());
        holder.title.setText(bean.getName());
        holder.point.setText("1.3km");
        /* 设置ListView */
        ShoppingCartProductAdapter adapter = new ShoppingCartProductAdapter(context);
        holder.list.setAdapter(adapter);
        adapter.setDataChange(bean.getProducts());
        return convertView;
    }
    class ShoppingCartShopViewHolder {
        public CheckBox check;
        public TextView title,point;
        private ListView list;
    }
}
