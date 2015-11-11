package com.zwtx.beer_talk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zwtx.beer_talk.R;
import com.zwtx.beer_talk.adapter.holder.EvaluationHolder;
import com.zwtx.beer_talk.bean.EvaluationBean;

import java.util.List;

/**
 * 评价列表的适配器
 */
public class EvaluationAdapter extends BaseAdapter {
    private List<EvaluationBean> mList;
    private Context context;
    public EvaluationAdapter(Context context) {
        this.context = context;
    }

    public void setDataChanged(List<EvaluationBean> list) {
        mList = list;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return mList == null?0:mList.size();
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
        EvaluationHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_evaluation, null);
            holder = new EvaluationHolder();
            holder.setId((TextView) convertView.findViewById(R.id.item_evaluation_id_txt));
            holder.setContent((TextView) convertView.findViewById(R.id.item_evaluation_content_txt));
            holder.setDate((TextView) convertView.findViewById(R.id.item_evaluation_date_txt));
            convertView.setTag(holder);
        } else {
            holder = (EvaluationHolder) convertView.getTag();
        }
        EvaluationBean bean = mList.get(position);
        holder.getId().setText(bean.getId());
        holder.getContent().setText(bean.getContent());
        holder.getDate().setText(bean.getDate());
        return convertView;
    }
}
