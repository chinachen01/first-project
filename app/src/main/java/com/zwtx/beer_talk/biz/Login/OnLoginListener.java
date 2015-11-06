package com.zwtx.beer_talk.biz.Login;

/**
 * Created by chenyong on 2015/11/4.
 */
public interface OnLoginListener {
    /**
     * 登录成功保存账户与密码
     * @param name
     * @param password
     */
    void loginSuccess(String name ,String password);
}
