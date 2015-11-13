package com.zwtx.beer_talk.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.zwtx.beer_talk.R;
import com.zwtx.beer_talk.adapter.DiscountAdapter;
import com.zwtx.beer_talk.bean.DiscountBean;
import com.zwtx.beer_talk.ui.base.BackToolBarActivity;
import com.zwtx.beer_talk.utils.T;

import java.util.ArrayList;

public class DiscountActivity extends BackToolBarActivity {
    private ListView mListView;
    // 定位相关
    LocationClient mLocClient;
    public MyLocationListenner myListener = new MyLocationListenner();
    boolean isFirstLoc = true; // 是否首次定位
    // 现在的位置
    double mLat1 ;
    double mLon1 ;
    // 将要去的位置
    double mLat2 = 30.639402;
    double mLon2 =  104.073101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.discount_list_view);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                double[] points = {mLat1,mLon1,mLat2,mLon2};
                OpenBaiduMap.startActivity(DiscountActivity.this, points);
            }
        });
        DiscountAdapter adapter = new DiscountAdapter(DiscountActivity.this);
        mListView.setAdapter(adapter);
        adapter.setDataChanged(getData());
        initLoaction();
    }

    private void initLoaction() {
        // 定位初始化
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();
    }
    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null) {
                return;
            }
            if (isFirstLoc) {
                isFirstLoc = false;
                mLat1 = location.getLatitude();
                mLon1 = location.getLongitude();
                T.showShort(DiscountActivity.this, mLat1 + ">>>>>mLat1");
                T.showShort(DiscountActivity.this, mLon1 + ">>>>>mLon1");
            }
        }
    }

        private ArrayList<DiscountBean> getData() {
        ArrayList<DiscountBean> list = new ArrayList<>();
        DiscountBean bean = new DiscountBean();
        bean.setTitle("九眼桥");
        bean.setContent("全场大促销,买一送一,走过路过不要错过,九眼桥专营烟酒类产品,质量有保证");
        bean.setImageID(R.drawable.test_story);
        list.add(bean);
        bean = new DiscountBean();
        bean.setTitle("九眼桥");
        bean.setContent("全场大促销,买一送一,走过路过不要错过,九眼桥专营烟酒类产品,质量有保证");
        bean.setImageID(R.drawable.test_story);
        list.add(bean);
        bean = new DiscountBean();
        bean.setTitle("九眼桥");
        bean.setContent("全场大促销,买一送一,走过路过不要错过,九眼桥专营烟酒类产品,质量有保证");
        bean.setImageID(R.drawable.test_story);
        list.add(bean);
        bean = new DiscountBean();
        bean.setTitle("九眼桥");
        bean.setContent("全场大促销,买一送一,走过路过不要错过,九眼桥专营烟酒类产品,质量有保证");
        bean.setImageID(R.drawable.test_story);
        list.add(bean);
        return list;
    }
    @Override
    public void setView() {
        setContentView(R.layout.activity_discount);
    }

    @Override
    public void setToolbarTitle() {
       String title =  getIntent().getStringExtra("tag");
        toolbar.setTitle(title);
    }

    public static void startActivity(Context context,String tag) {
        Intent intent = new Intent(context, DiscountActivity.class);
        intent.putExtra("tag", tag);
        context.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocClient.stop();
    }
}
