package com.zwtx.beer_talk.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.zwtx.beer_talk.R;
import com.zwtx.beer_talk.adapter.EvaluationAdapter;
import com.zwtx.beer_talk.bean.EvaluationBean;
import com.zwtx.beer_talk.ui.base.BaseActivity;
import com.zwtx.beer_talk.utils.L;
import com.zwtx.beer_talk.widget.CustomPopupWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * 商城商品的详情界面(包含商城,购物车)
 */
public class ProductDetailActivity extends BaseActivity {
    private ListView mEvaluationList;
    private TextView mTitleTxt;
    private Button mShoppingCartBtn, mBuyBtn, mShareBtn;
    private CustomPopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        initView();
    }

    private void initView() {
        mEvaluationList = (ListView) findViewById(R.id.product_detail_list);
        mTitleTxt = (TextView) findViewById(R.id.product_detail_title_txt);
        mTitleTxt.setText(Html.fromHtml("52度五粮液 500ml <br> <font color=red>官方自营产品,品质有保证</font>"));
        mShoppingCartBtn = (Button) findViewById(R.id.product_detail_shopping_cart_btn);
        mShoppingCartBtn.setOnClickListener(new ProductDetailOnClickListener());
        mBuyBtn = (Button) findViewById(R.id.product_detail_buy_btn);
        mBuyBtn.setOnClickListener(new ProductDetailOnClickListener());
        mShareBtn = (Button) findViewById(R.id.product_detail_share_btn);
        mShareBtn.setOnClickListener(new ProductDetailOnClickListener());
        EvaluationAdapter adapter = new EvaluationAdapter(ProductDetailActivity.this);
        mEvaluationList.setAdapter(adapter);
        adapter.setDataChanged(getList());
        View view = LayoutInflater.from(ProductDetailActivity.this).inflate(R.layout.popuewindow_product_buy, null);
        mPopupWindow = new CustomPopupWindow(view);
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, ProductDetailActivity.class);
        context.startActivity(intent);
    }

    private List<EvaluationBean> getList() {
        List<EvaluationBean> list = new ArrayList<>();
        EvaluationBean bean = new EvaluationBean();
        bean.setId("1303546465");
        bean.setContent("很好,是好酒,下次还会再买");
        bean.setDate("2015-06-14 18:20:14");
        list.add(bean);
        bean = new EvaluationBean();
        bean.setId("1303546465");
        bean.setContent("很好,是好酒,下次还会再买");
        bean.setDate("2015-06-14 18:20:14");
        list.add(bean);
        bean = new EvaluationBean();
        bean.setId("1303546465");
        bean.setContent("很好,是好酒,下次还会再买");
        bean.setDate("2015-06-14 18:20:14");
        list.add(bean);
        bean = new EvaluationBean();
        bean.setId("1303546465");
        bean.setContent("很好,是好酒,下次还会再买");
        bean.setDate("2015-06-14 18:20:14");
        list.add(bean);
        bean = new EvaluationBean();
        bean.setId("1303546465");
        bean.setContent("很好,是好酒,下次还会再买");
        bean.setDate("2015-06-14 18:20:14");
        list.add(bean);
        return list;
    }

    private class ProductDetailOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.product_detail_shopping_cart_btn:
                    ShoppingCartActivity.startActivity(ProductDetailActivity.this);
                    break;
                case R.id.product_detail_buy_btn:
                    if (!mPopupWindow.isShowing()) {
                        mPopupWindow.showAsDropDown(mBuyBtn, 0, 0);
                    } else {
                        mPopupWindow.dismiss();
                    }

                    break;
                case R.id.product_detail_share_btn:
                    break;
            }
        }
    }
}
