package com.zwtx.beer_talk.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.Toast;

import com.baidu.android.pushservice.BasicPushNotificationBuilder;
import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.zwtx.beer_talk.R;
import com.zwtx.beer_talk.constants.APIConstants;
import com.zwtx.beer_talk.constants.SPConstants;
import com.zwtx.beer_talk.manager.UpdateManager;
import com.zwtx.beer_talk.utils.MD5;
import com.zwtx.beer_talk.utils.NetWorkUtils;
import com.zwtx.beer_talk.widget.LoadingAnimatorView;

/**
 *APP加载界面
 */
public class LoadingActivity extends Activity {
    private String mDeviceId;
    LoadingAnimatorView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initYunTuiSong();
        //当前有网络连接,执行相应的网络操作
        if (checkNetWork()) {
            //发送DeviceId
            sendDeviceId();
            //验证app版本
            checkAppUpdate();
        } else {
            Toast.makeText(this, "当前无网络连接", Toast.LENGTH_SHORT).show();
        }
        //启动动画
        initUI();
    }

    /**
     * 初始化云推送
     */
    private void initYunTuiSong() {
        //启动云推送
        PushManager.startWork(getApplicationContext(), PushConstants.LOGIN_TYPE_API_KEY, APIConstants.BAIDU_API_KEY);
        //设置通知栏样式
        BasicPushNotificationBuilder cBuilder = new BasicPushNotificationBuilder();
        cBuilder.setStatusbarIcon(R.mipmap.notification_icon);
        PushManager.setNotificationBuilder(this, 1, cBuilder);
    }

    /**
     * 初始化界面,启动动画
     */
    private void initUI() {
        view = new LoadingAnimatorView(this);
        view.setBitmap(R.drawable.loading_background, R.drawable.loading_bitmap_start, R.drawable.loading_bitmap_loading, R.drawable.loading_bitmap_end);
        WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        //动态图片的高度设为屏幕的1/4
        int y = windowManager.getDefaultDisplay().getHeight() / 4;
        view.setY(y);
        view.setOnFinishedListener(new LoadingAnimatorView.OnFinishedListener() {
            @Override
            public void onFinish() {
                //跳转到下一界面
                finish();
//                //启动登录界面
//                LoginActivity.startActivity(LoadingActivity.this);
                //启动首页
                HomeActivity.startActivity(LoadingActivity.this);
            }
        });
        setContentView(view);
    }

    /**
     * 检查是程序是否有网络连接
     */
    private boolean checkNetWork() {
        if (!NetWorkUtils.getNetWorkState(this)) {
            Toast.makeText(this, "当前无网络连接", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    /**
     * @return 返回一个32位的16禁止字符串, 该字符串为设备的唯一ID(通过MD5加密)
     */
    private String getDeviceId() {
        //获取手机的IMEI号,可能为空
        TelephonyManager mTelephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        String deviceId = mTelephonyManager.getDeviceId();
        if (TextUtils.equals(deviceId, "0000000000000")) {
            deviceId = "35" + //we make this look like a valid IMEI
                    Build.BOARD.length() % 10 +
                    Build.BRAND.length() % 10 +
                    Build.CPU_ABI.length() % 10 +
                    Build.DEVICE.length() % 10 +
                    Build.DISPLAY.length() % 10 +
                    Build.HOST.length() % 10 +
                    Build.ID.length() % 10 +
                    Build.MANUFACTURER.length() % 10 +
                    Build.MODEL.length() % 10 +
                    Build.PRODUCT.length() % 10 +
                    Build.TAGS.length() % 10 +
                    Build.TYPE.length() % 10 +
                    Build.USER.length() % 10; //13 digits
        }
        return MD5.getMD5(deviceId.getBytes());
    }

    /**
     * 检查软件版本,并更新
     */
    private void checkAppUpdate() {
        // 检查软件更新
        UpdateManager manager = new UpdateManager(LoadingActivity.this);
        manager.checkUpdate();
    }

    /**
     * 通过http请求,传输设备ID到后台
     */
    private void sendDeviceId() {
        mDeviceId = getDeviceId();
        SharedPreferences shared = getSharedPreferences(SPConstants.SHAREDPREFER_NAME, MODE_PRIVATE);
        //第一次打开App,将DeviceId发送到后台
        if (!shared.getBoolean(SPConstants.IS_DEVICE_REGIST, false)) {
            //请求成功后,将标记改为true
            SharedPreferences.Editor editor = shared.edit();
            editor.putBoolean(SPConstants.IS_DEVICE_REGIST, true);
            editor.commit();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (view != null) {
            view.recycleView();
            view = null;
        }
    }
}
