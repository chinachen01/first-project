package com.zwtx.beer_talk.bean;

/**
 * 用户对象,采用单例模式
 */
public class User {

    private static User user = null;

    private static synchronized void initUser() {
        if (user == null) {
            user = new User();
        }
    }

    public static User getInstance() {
        if (user == null) {
            initUser();
        }
        return user;
    }

    /**
     * 用户是否登录的标记,默认为false
     */
    private boolean isLogin = false;
    /**
     * 用户名,默认为游客
     */
    private String name = "游客";
    /**
     * 会员等级,默认为普通会员
     */
    private String level = "普通会员";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setIsLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }
}
