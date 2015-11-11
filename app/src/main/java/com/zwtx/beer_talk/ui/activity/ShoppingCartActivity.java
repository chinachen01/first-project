package com.zwtx.beer_talk.ui.activity;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import com.zwtx.beer_talk.R;
import com.zwtx.beer_talk.adapter.ShoppingStorageAdapter;
import com.zwtx.beer_talk.bean.ProductBean;
import com.zwtx.beer_talk.ui.base.BaseActivity;

/**
 * 购物车界面
 */
public class ShoppingCartActivity extends BaseActivity {
	private ListView mShoppingStorageListView;
	private TextView mTotalText;
	private CheckBox mAllSelectCheck;
    private ShoppingStorageAdapter mAdapter;
    private Button mCommitBtn,mCompleteBtn;
    private TextView mEditTxt;
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shopping_storage);
		init();
	}
    //初始化控件
	private void init() {
		mTotalText = (TextView) findViewById(R.id.shopping_storage_total_price_text);
		mShoppingStorageListView = (ListView) findViewById(R.id.shopping_storage_stuff_listview);
        //设置全选按键的点击事件
		mAllSelectCheck = (CheckBox) findViewById(R.id.shopping_storage_all_select_check);
		mAllSelectCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mAdapter.setAllItemCheck(isChecked);
			}
		});
        //设置适配器
		mAdapter = new ShoppingStorageAdapter(this);
		mShoppingStorageListView.setAdapter(mAdapter);
		mAdapter.setOnCheckListener(new ShoppingStorageAdapter.OnCallBackListener() {

            @Override
            public void setTotalPrice(String total) {
                // TODO Auto-generated method stub
                mTotalText.setText(total);
            }

            @Override
            public String getTotalPrice() {
                // TODO Auto-generated method stub
                return mTotalText.getText().toString().trim();
            }
        });
		mAdapter.setDataChange(getData());
        //设置提交按钮
        mCommitBtn = (Button) findViewById(R.id.shopping_storage_commit);
        mCommitBtn.setOnClickListener(new StorageOnClickListener());
        //设置编辑按钮
        mEditTxt = (TextView) findViewById(R.id.shopping_storage_edit_text);
        mEditTxt.setOnClickListener(new StorageOnClickListener());
        //设置完成按钮
        mCompleteBtn = (Button) findViewById(R.id.shopping_storage_complete);
        mCompleteBtn.setOnClickListener(new StorageOnClickListener());

    }

    /**
     * 提交,编辑按键的点击事件
     */
    class StorageOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.shopping_storage_commit:
                    ArrayList<ProductBean> list = mAdapter.getData();
                    for (ProductBean bean : list) {
                        Log.d("tag", "标题是:" + bean.getTitle() + "数量是:" + bean.getCount() + "价格是:" + mTotalText.getText());
                    }
                    break;
                case R.id.shopping_storage_edit_text:
                    mAdapter.setIsEdit(true);
                    mCompleteBtn.setVisibility(View.VISIBLE);
                    break;
                case R.id.shopping_storage_complete:
                    mAdapter.setIsEdit(false);
                    mCompleteBtn.setVisibility(View.GONE);
                    break;
            }
        }
    }

    /**
     * 对外提供方法,跳转到此Activity
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent(context, ShoppingCartActivity.class);
        context.startActivity(intent);
    }

    //网络获取购物车信息
    private ArrayList<ProductBean> getData() {
		ArrayList<ProductBean> list = new ArrayList<ProductBean>();
		ProductBean bean = new ProductBean();
		bean.setCount(1);
		bean.setImage(R.drawable.listimage1);
		bean.setPrice(1);
		bean.setTitle("火锅");
		list.add(bean);
		bean = new ProductBean();
		bean.setCount(1);
		bean.setImage(R.drawable.listimage2);
		bean.setPrice(1);
		bean.setTitle("中餐");
		list.add(bean);
		bean = new ProductBean();
		bean.setCount(1);
		bean.setImage(R.drawable.listimage3);
		bean.setPrice(1);
		bean.setTitle("西餐");
		list.add(bean);
		bean = new ProductBean();
		bean.setCount(1);
		bean.setImage(R.drawable.listimage4);
		bean.setPrice(1);
		bean.setTitle("寿司");
		list.add(bean);
		bean = new ProductBean();
		bean.setCount(1);
		bean.setImage(R.drawable.listimage2);
		bean.setPrice(1);
		bean.setTitle("鱼刺");
		list.add(bean);
		bean = new ProductBean();
		bean.setCount(1);
		bean.setImage(R.drawable.listimage3);
		bean.setPrice(1);
		bean.setTitle("苹果");
		list.add(bean);
		bean = new ProductBean();
		bean.setCount(1);
		bean.setImage(R.drawable.listimage4);
		bean.setPrice(1);
		bean.setTitle("香蕉");
		list.add(bean);
		bean = new ProductBean();
		bean.setCount(1);
		bean.setImage(R.drawable.listimage2);
		bean.setPrice(1);
		bean.setTitle("栗子");
		list.add(bean);
		bean = new ProductBean();
		bean.setCount(1);
		bean.setImage(R.drawable.listimage3);
		bean.setPrice(1);
		bean.setTitle("橘子");
		list.add(bean);
		bean = new ProductBean();
		bean.setCount(1);
		bean.setImage(R.drawable.listimage4);
		bean.setPrice(1);
		bean.setTitle("葡萄");
		list.add(bean);
		return list;
	}

}
