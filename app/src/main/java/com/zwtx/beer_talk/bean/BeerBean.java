package com.zwtx.beer_talk.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by chenyong on 2015/11/13.
 */
public class BeerBean implements Parcelable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
    }

    public BeerBean() {
    }

    protected BeerBean(Parcel in) {
        this.name = in.readString();
    }

    public static final Parcelable.Creator<BeerBean> CREATOR = new Parcelable.Creator<BeerBean>() {
        public BeerBean createFromParcel(Parcel source) {
            return new BeerBean(source);
        }

        public BeerBean[] newArray(int size) {
            return new BeerBean[size];
        }
    };
}
