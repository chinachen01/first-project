package com.zwtx.beer_talk.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.zwtx.beer_talk.R;
import com.zwtx.beer_talk.constants.HttpConstants;
import com.zwtx.beer_talk.manager.HttpManager;
import com.zwtx.beer_talk.ui.base.BaseActivity;
import com.zwtx.beer_talk.utils.L;
import com.zwtx.beer_talk.utils.T;

import org.apache.http.Header;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 用户的注册界面及相关业务逻辑
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private EditText mPhoneNumberEdit, mPhoneCodeEdit, mUsernameEdit, mPasswordEdit;
    private Button mRegisterBtn, mSendCodeBtn;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    mSendCodeBtn.setText((CharSequence) msg.obj);
                    break;
                case 2:
                    mSendCodeBtn.setText("获取验证码");
                    mSendCodeBtn.setClickable(true);
                    break;
            }
        }
    };
    private String mPhoneNumber, mPassword, mCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        /* 初始化Edit */
        mPhoneNumberEdit = (EditText) findViewById(R.id.register_phone_number_edit);
        mPhoneCodeEdit = (EditText) findViewById(R.id.register_phone_code_edit);
        mUsernameEdit = (EditText) findViewById(R.id.register_username_edit);
        mPasswordEdit = (EditText) findViewById(R.id.register_password_edit);
        /* 初始化Button */
        mRegisterBtn = (Button) findViewById(R.id.register_register_btn);
        mSendCodeBtn = (Button) findViewById(R.id.register_phone_code_send_btn);
        mSendCodeBtn.setOnClickListener(this);
        mRegisterBtn.setOnClickListener(this);
    }

    /**
     * 对外提供方法跳转到此Activity
     *
     * @param activity
     */
    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, RegisterActivity.class);
        activity.startActivity(intent);
    }

    /**
     * 判断字符串中是否含有特殊字符
     *
     * @param string
     * @return
     */
    private boolean isStringMatch(String string) {
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(string);
        if (m.find()) {
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_phone_code_send_btn:
                /* 60s后再发送 */
                setSendCodeBtnFor60s();
                //点击发送验证码的业务逻辑
                String urlCode = HttpConstants.BASE_URL + HttpConstants.CODE + mPhoneNumberEdit.getText().toString().trim();
                L.d(urlCode + ">>>code");
                HttpManager.get(urlCode, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int i, Header[] headers, byte[] bytes) {
                        T.showShort(RegisterActivity.this, "请求成功,请收到短信后填写验证码");
                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        T.showShort(RegisterActivity.this, "发送失败,请检查网络设置");
                    }
                });
                break;
            case R.id.register_register_btn:
                //点击注册后的业务逻辑
                mCode = mPhoneCodeEdit.getText().toString().trim();
                mPassword = mPasswordEdit.getText().toString().trim();
                mPhoneNumber = mPhoneNumberEdit.getText().toString().trim();
                String urlRegister = HttpConstants.BASE_URL + HttpConstants.REGISTER + "mobile/" + mPhoneNumber + "/password/" + mPassword + "/code/" + mCode;
                HttpManager.get(urlRegister, new JsonHttpResponseHandler("UTF-8") {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        T.showShort(RegisterActivity.this, "注册成功");
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);
                        T.showShort(RegisterActivity.this, "注册失败");
                    }
                });
                break;
        }
    }

    /**
     * 设置发送按键60s不能点击
     */
    private void setSendCodeBtnFor60s() {
        mSendCodeBtn.setClickable(false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 60; i++) {
                    Message msg = Message.obtain();
                    msg.obj = 60 - i + "s";
                    msg.what = 1;
                    mHandler.sendMessage(msg);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Message msg = Message.obtain();
                msg.what = 2;
                mHandler.sendMessage(msg);
            }
        }).start();
    }
}
