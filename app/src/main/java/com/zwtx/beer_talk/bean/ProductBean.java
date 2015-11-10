package com.zwtx.beer_talk.bean;

import android.widget.ImageView;
import android.widget.TextView;

public class ProductBean {
	/** 商品的标题 */
	private String title;
	/** 商品的价格 */
	private float price;
	/** 商品的图片及数量 */
	private int image,count;
    /** 是否被选中 */
    private boolean isChecked = true;
    public boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public int getImage() {
		return image;
	}
	public void setImage(int image) {
		this.image = image;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
