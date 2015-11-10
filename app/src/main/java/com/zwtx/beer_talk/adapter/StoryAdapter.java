package com.zwtx.beer_talk.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zwtx.beer_talk.R;
import com.zwtx.beer_talk.adapter.holder.StoryHolder;
import com.zwtx.beer_talk.bean.StoryBean;

import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter<StoryHolder>{
    private List<StoryBean> list;
    private Context context;
    private StoryOnItemClickListener listener;//回调接口
    /**
     * 注册回调接口
     * @param listener
     */
    public void setOnItemClickListener(StoryOnItemClickListener listener) {
        this.listener= listener;
    }
    public StoryAdapter(Context context) {
        this.context = context;
    }

    public List<StoryBean> getList() {
        return list;
    }
    public void setDataChanged(List<StoryBean> list) {
        this.list = list;
    }
    @Override
    public StoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_story, parent, false);
        return new StoryHolder(view);
    }

    @Override
    public void onBindViewHolder(StoryHolder holder, final int position) {
        StoryBean bean = list.get(position);
        holder.title.setText(bean.getTitle());
        holder.date.setText(bean.getDate());
        holder.content.setText(bean.getContent());
        LinearLayout ly = holder.ly;
        int[] ids = bean.getId();
        if (ids != null) {
            for (int i = 0; i < ids.length; i++) {
                ImageView image = new ImageView(context);
                image.setPadding(5,5,5,5);
                image.setImageResource(R.drawable.test_story);
                ly.addView(image);
            }
        }
        /*添加 Item的点击事件 */
        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (listener != null) {
                    //跳转到商品详情界面
                    listener.startActivity(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }
    public interface StoryOnItemClickListener {
        void startActivity(int position);
    }
}
