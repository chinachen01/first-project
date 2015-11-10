package com.zwtx.beer_talk.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.zwtx.beer_talk.R;
import com.zwtx.beer_talk.bean.ProductBean;

public class ShoppingStorageAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private ArrayList<ProductBean> mList = new ArrayList<>();
    private OnCallBackListener mCallBackListener;
    private boolean mIsChecked = true;
    private boolean mIsEdit = false;

    public ShoppingStorageAdapter(Context context) {
        // TODO Auto-generated constructor stub
        mInflater = LayoutInflater.from(context);
    }

    public void setDataChange(ArrayList<ProductBean> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public void setOnCheckListener(OnCallBackListener l) {
        mCallBackListener = l;
    }

    /**
     * 设置是否全选
     *
     * @param flag
     */
    public void setAllItemCheck(boolean flag) {
        for (ProductBean bean : mList) {
            bean.setIsChecked(flag);
        }
        notifyDataSetChanged();
    }

    /**
     * 删除某项
     *
     * @param index
     */
    private void delItem(int index) {
        mList.remove(index);
        notifyDataSetChanged();
        updateTotalPrice();
    }

    public void setIsEdit(boolean flag) {
        mIsEdit = flag;
        notifyDataSetChanged();
    }

    public ArrayList<ProductBean> getData() {
        return mList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(
                    R.layout.item_shopping_storage_stuff_listview, null);
            CheckBox check = (CheckBox) convertView
                    .findViewById(R.id.item_shopping_storage_stuff_check);
            TextView count = (TextView) convertView
                    .findViewById(R.id.item_shopping_storage_stuff_count_text);
            Button delBtn = (Button) convertView.findViewById(R.id.item_shopping_storage_stuff_del_btn);
            Button reduceBtn = (Button) convertView
                    .findViewById(R.id.item_shopping_storage_stuff_reduce_btn);
            Button addBtn = (Button) convertView
                    .findViewById(R.id.item_shopping_storage_stuff_add_btn);
            ImageView image = (ImageView) convertView
                    .findViewById(R.id.item_shopping_storage_stuff_image);
            TextView title = (TextView) convertView
                    .findViewById(R.id.item_shopping_storage_stuff_title_text);
            TextView price = (TextView) convertView
                    .findViewById(R.id.item_shopping_storage_stuff_price_text);
            holder = new ViewHolder();
            holder.setCheck(check);
            holder.setImage(image);
            holder.setTitle(title);
            holder.setPrice(price);
            holder.setCount(count);
            holder.setDelBtn(delBtn);
            holder.setReduceBtn(reduceBtn);
            holder.setAddBtn(addBtn);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.getImage().setImageResource(mList.get(position).getImage());
        holder.getTitle().setText(mList.get(position).getTitle());
        holder.getPrice().setText(mList.get(position).getPrice() + "");
        holder.getCount().setText(mList.get(position).getCount() + "");
        //删除按键的点击事件
        holder.getDelBtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delItem(position);
            }
        });
        //增加按键的点击事件
        holder.getAddBtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mList.get(position).getIsChecked()) {
                    int num = mList.get(position).getCount();
                    num += 1;
                    mList.get(position).setCount(num);
                    notifyDataSetChanged();
                    updateTotalPrice();
                }
            }
        });
        //减少按键的点击事件
        holder.getReduceBtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = mList.get(position).getCount();
                //在选中状态,且数量大于0时,点击后数量减1
                if (num > 0 && mList.get(position).getIsChecked()) {
                    num -= 1;
                    mList.get(position).setCount(num);
                    notifyDataSetChanged();
                    updateTotalPrice();
                }
            }
        });
        //选择按键的监听事件
        holder.getCheck().setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //回调方法,获得当前的总价格
                mList.get(position).setIsChecked(isChecked);
                updateTotalPrice();
            }
        });
        holder.getCheck().setChecked(mList.get(position).getIsChecked());
        if (mIsEdit)
            holder.getDelBtn().setVisibility(View.VISIBLE);
        else
            holder.getDelBtn().setVisibility(View.INVISIBLE);

        return convertView;
    }

    private void updateTotalPrice() {
        float totalPrice = 0;
        for (ProductBean bean : mList) {
            if (bean.getIsChecked()) {
                totalPrice += bean.getPrice()
                        * bean.getCount();
            }
        }
        //回调方法,设置总价格
        mCallBackListener.setTotalPrice(totalPrice + "");
    }

    class ViewHolder {
        CheckBox check;
        ImageView image;
        TextView title, price, count;
        Button reduceBtn;
        Button addBtn;

        public Button getDelBtn() {
            return delBtn;
        }

        public void setDelBtn(Button delBtn) {
            this.delBtn = delBtn;
        }

        Button delBtn;

        public CheckBox getCheck() {
            return check;
        }

        public void setCheck(CheckBox check) {
            this.check = check;
        }

        public ImageView getImage() {
            return image;
        }

        public void setImage(ImageView image) {
            this.image = image;
        }

        public TextView getTitle() {
            return title;
        }

        public void setTitle(TextView title) {
            this.title = title;
        }

        public TextView getPrice() {
            return price;
        }

        public void setPrice(TextView price) {
            this.price = price;
        }

        public TextView getCount() {
            return count;
        }

        public void setCount(TextView count) {
            this.count = count;
        }

        public Button getReduceBtn() {
            return reduceBtn;
        }

        public void setReduceBtn(Button reduceBtn) {
            this.reduceBtn = reduceBtn;
        }

        public Button getAddBtn() {
            return addBtn;
        }

        public void setAddBtn(Button addBtn) {
            this.addBtn = addBtn;
        }

    }

    /**
     * 购物车的回调接口
     */
    public interface OnCallBackListener {
        void setTotalPrice(String total);

        String getTotalPrice();
    }
}

