package com.zwtx.beer_talk.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zwtx.beer_talk.R;
import com.zwtx.beer_talk.biz.Login.LoginBiz;
import com.zwtx.beer_talk.biz.Login.LoginInter;
import com.zwtx.beer_talk.biz.Login.OnLoginListener;
import com.zwtx.beer_talk.constants.SPConstants;
import com.zwtx.beer_talk.ui.base.BaseActivity;
import com.zwtx.beer_talk.utils.SPUtils;

/**
 * 用户的登录界面及相关业务逻辑
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    //文本框
    private EditText mEditName, mEditPassword;
    //清除文本框按键
    private ImageView mImageName, mImagePassword;
    //登录成功或失败信息提示
    private TextView mTextMsg;
    //登录及注册按钮
    private Button mLoginBtn, mRegisterBtn;
    //记住密码
    private CheckBox mSaveCheckBtn;
    //实例化业务逻辑类
    LoginInter mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //初始化控件
        initView();
        mLogin =new LoginBiz(this);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mEditName = (EditText) findViewById(R.id.login_username_edit);
        mEditPassword = (EditText) findViewById(R.id.login_password_edit);
        mImageName = (ImageView) findViewById(R.id.login_clear_name_image);
        mImagePassword = (ImageView) findViewById(R.id.login_clear_password_image);
        mTextMsg = (TextView) findViewById(R.id.login_msg_fail_txt);
        mLoginBtn = (Button) findViewById(R.id.login_login_btn);
        mRegisterBtn = (Button) findViewById(R.id.login_register_btn);
        mSaveCheckBtn = (CheckBox) findViewById(R.id.login_save_checkbtn);
        // 初始化账户与密码框
        initEditText();
        /*添加按键点击事件*/
        mLoginBtn.setOnClickListener(this);
        mRegisterBtn.setOnClickListener(this);
        mImageName.setOnClickListener(this);
        mImagePassword.setOnClickListener(this);
        /*添加Edit的文本变更事件*/
        mEditName.addTextChangedListener(new NameEditChange());
        mEditPassword.addTextChangedListener(new PasswordEditChange());
    }

    /**
     * 从sharedPreferences文件获取保存的账户名与密码
     */
    private void initEditText() {
        String name = (String) SPUtils.get(LoginActivity.this, SPConstants.USER_NAME, "");
        String password = (String) SPUtils.get(LoginActivity.this, SPConstants.USER_PASSWORD, "");
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)) {
            mEditName.setText(name);
            mEditPassword.setText(password);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.login_clear_name_image:
//                //点击清除按钮后文本置为空
//                mEditName.setText("");
//                break;
            case R.id.login_clear_password_image:
                mEditPassword.setText("");
                break;
            case R.id.login_login_btn:
                String username = mEditName.getText().toString().trim();
                String password = mEditPassword.getText().toString().trim();
                mLogin.login(username, password, new LoginListenerImpl());
                break;
            case R.id.login_register_btn:
                RegisterActivity.startActivity(LoginActivity.this);
                break;
        }
    }

    private class LoginListenerImpl implements OnLoginListener {
        @Override
        public void loginSuccess(String name, String password) {
                /*如果选择记住账户与密码*/
                if (mSaveCheckBtn.isChecked()) {
                    SPUtils.put(LoginActivity.this, SPConstants.USER_NAME, name);
                    SPUtils.put(LoginActivity.this, SPConstants.USER_PASSWORD, password);
                }
                /*跳转到下一界面*/
                HomeActivity.startActivity(LoginActivity.this);
                finish();
            }
    }

    /**
     * 对外提供方法跳转到该Activity
     *
     * @param activity
     */
    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }


    /**
     * 账户文本的Change事件
     */
    private class NameEditChange implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() > 0) {
                mImageName.setVisibility(View.VISIBLE);
            } else {
                mImageName.setVisibility(View.INVISIBLE);
            }
        }
    }

    /**
     * 密码文本的change事件
     */
    private class PasswordEditChange implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() > 0) {
                mImagePassword.setVisibility(View.VISIBLE);
            } else {
                mImagePassword.setVisibility(View.INVISIBLE);
            }
        }
    }
}
