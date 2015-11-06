package com.zwtx.beer_talk.biz.Login;


import android.content.Context;
import android.text.TextUtils;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.zwtx.beer_talk.constants.HttpConstants;
import com.zwtx.beer_talk.manager.HttpManager;
import com.zwtx.beer_talk.utils.T;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginBiz implements LoginInter {
    private Context context;
    public LoginBiz(Context context) {
        this.context = context;
    }
    @Override
    public void login(final String username, final String password, final OnLoginListener listener) {
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
            /*联网判断用户名与密码是否匹配*/
            String url = HttpConstants.BASE_URL + HttpConstants.LOGIN + "username/" + username + "/password/" + password;
            HttpManager.get(url, new JsonHttpResponseHandler("UTF-8") {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                              /*如果匹配*/
                    if (listener != null) {
                        try {
                            String id = response.getString("id");
                            if (!TextUtils.isEmpty(id)) {//登录成功
                                listener.loginSuccess(username, password);
                            }
                        } catch (JSONException e) {
                            T.showShort(context, "用户名或密码错误");
                            e.printStackTrace();
                        }

                    }
                }
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    super.onFailure(statusCode, headers, responseString, throwable);
                    T.showShort(context, "无法连接到网络");
                }
            });

        } else {
            T.showShort(context, "用户名或密码不能为空");
        }
    }
}

