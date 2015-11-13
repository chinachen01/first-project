package com.zwtx.beer_talk.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 酒类信息
 */
public class BeerTypeBean implements Parcelable {

    private String type;
    private ArrayList<BeerBean> beers;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<BeerBean> getBeers() {
        return beers;
    }

    public void setBeers(ArrayList<BeerBean> beers) {
        this.beers = beers;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeList(this.beers);
    }

    public BeerTypeBean() {
    }

    protected BeerTypeBean(Parcel in) {
        this.type = in.readString();
        this.beers = new ArrayList<BeerBean>();
        in.readList(this.beers, List.class.getClassLoader());
    }

    public static final Parcelable.Creator<BeerTypeBean> CREATOR = new Parcelable.Creator<BeerTypeBean>() {
        public BeerTypeBean createFromParcel(Parcel source) {
            return new BeerTypeBean(source);
        }

        public BeerTypeBean[] newArray(int size) {
            return new BeerTypeBean[size];
        }
    };
}
