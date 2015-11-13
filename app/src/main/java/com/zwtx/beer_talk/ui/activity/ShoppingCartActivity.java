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
import com.zwtx.beer_talk.adapter.ShoppingCartProductAdapter;
import com.zwtx.beer_talk.adapter.ShoppingCartShopAdapter;
import com.zwtx.beer_talk.bean.ProductBean;
import com.zwtx.beer_talk.bean.ShopProductBean;
import com.zwtx.beer_talk.ui.base.BaseActivity;
import com.zwtx.beer_talk.widget.MeasureListView;

/**
 * 购物车界面
 */
public class ShoppingCartActivity extends BaseActivity {
	private MeasureListView  mShoppingStorageListView;
	private TextView mTotalText;
	private CheckBox mAllSelectCheck;
    private ShoppingCartShopAdapter mAdapter;
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
		mShoppingStorageListView = (MeasureListView ) findViewById(R.id.shopping_storage_stuff_listview);
        //设置全选按键的点击事件
		mAllSelectCheck = (CheckBox) findViewById(R.id.shopping_storage_all_select_check);
		mAllSelectCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mAdapter.setAllItemCheck(isChecked);
			}
		});
        //设置适配器
		mAdapter = new ShoppingCartShopAdapter(this);
		mShoppingStorageListView.setAdapter(mAdapter);
		mAdapter.setOnCallBackListener(new ShoppingCartShopAdapter.ShopOnCallBackListener() {

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
		mAdapter.setDataChanged(getShopData());
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
                    ArrayList<ShopProductBean> list = mAdapter.getDatas();
                    for (ShopProductBean bean : list) {
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

    private ArrayList<ShopProductBean> getShopData() {
        ArrayList<ShopProductBean> list = new ArrayList<>();
        ShopProductBean shopProductBean = new ShopProductBean();
        shopProductBean.setName("九眼桥酒吧");
        shopProductBean.setProducts(getData());
        list.add(shopProductBean);
        shopProductBean = new ShopProductBean();
        shopProductBean.setName("九眼桥酒吧");
        shopProductBean.setProducts(getData());
        list.add(shopProductBean);
        shopProductBean = new ShopProductBean();
        shopProductBean.setName("九眼桥酒吧");
        shopProductBean.setProducts(getData());
        list.add(shopProductBean);
        shopProductBean = new ShopProductBean();
        shopProductBean.setName("九眼桥酒吧");
        shopProductBean.setProducts(getData());
        list.add(shopProductBean);
        shopProductBean = new ShopProductBean();
        shopProductBean.setName("九眼桥酒吧");
        shopProductBean.setProducts(getData());
        list.add(shopProductBean);
        shopProductBean = new ShopProductBean();
        shopProductBean.setName("九眼桥酒吧");
        shopProductBean.setProducts(getData());
        list.add(shopProductBean);
        return list;
    }
    //网络获取购物车信息
    private ArrayList<ProductBean> getData() {
		ArrayList<ProductBean> list = new ArrayList<ProductBean>();
		ProductBean bean = new ProductBean();
		bean.setCount(1);
		bean.setImage(R.drawable.test_story);
		bean.setPrice(1);
		bean.setTitle("52°五粮液");
		list.add(bean);
		bean = new ProductBean();
		bean.setCount(1);
		bean.setImage(R.drawable.test_story);
		bean.setPrice(1);
		bean.setTitle("52°五粮液");
		list.add(bean);
        bean = new ProductBean();
        bean.setCount(1);
        bean.setImage(R.drawable.test_story);
        bean.setPrice(1);
        bean.setTitle("52°五粮液");
        list.add(bean);
        bean = new ProductBean();
        bean.setCount(1);
        bean.setImage(R.drawable.test_story);
        bean.setPrice(1);
        bean.setTitle("52°五粮液");
        list.add(bean);
		return list;
	}

}
