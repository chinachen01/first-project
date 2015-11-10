package com.zwtx.beer_talk.bean;

/**
 * Created by chenyong on 2015/11/9.
 */
public class SearchProductBean {
    private int id;
    private String title,content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
