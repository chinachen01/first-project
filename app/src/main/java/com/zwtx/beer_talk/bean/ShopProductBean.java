package com.zwtx.beer_talk.bean;

import com.zwtx.beer_talk.bean.ProductBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 购物车店铺产品信息对象,包含店铺的店名和经纬度
 */
public class ShopProductBean {
    private ArrayList<ProductBean> products;
    private String name;
    private int longitude;
    private int Latitude;
    private boolean isChecked = true;

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public ArrayList<ProductBean> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<ProductBean> products) {
        this.products = products;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public int getLatitude() {
        return Latitude;
    }

    public void setLatitude(int latitude) {
        Latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
