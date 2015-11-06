package com.zwtx.beer_talk.biz.Login;

public interface LoginInter {
    /**
     * 点击登录后的网络请求
     * @param name 用户名
     * @param password 密码
     * @param listener 回调监听
     */
    void login(String name,String password,OnLoginListener listener);
}
