package com.zwtx.beer_talk.yuyin;

import android.content.Context;
import android.os.Bundle;

import com.baidu.voicerecognition.android.VoiceRecognitionConfig;
import com.baidu.voicerecognition.android.ui.BaiduASRDigitalDialog;
import com.baidu.voicerecognition.android.ui.DialogRecognitionListener;
import com.zwtx.beer_talk.constants.APIConstants;

import java.util.ArrayList;

/**
 * 百度语音帮助类
 */
public class YuYinHelper {
    private BaiduASRDigitalDialog mDialog;
    private DialogRecognitionListener mRecognitionListener;
    private YuYinCallListener mYuYinCallListener;

    public interface YuYinCallListener {
        void setText(String string);
    }

    public YuYinHelper(Context context) {
        Bundle params = new Bundle();
//设置开放平台 API Key
        params.putString(BaiduASRDigitalDialog.PARAM_API_KEY, APIConstants.BAIDU_API_KEY);
//设置开放平台 Secret Key
        params.putString(BaiduASRDigitalDialog.PARAM_SECRET_KEY, APIConstants.BAIDU_SECRET_KEY);
//设置识别领域：搜索、输入、地图、音乐……，可选。默认为输入。
        params.putInt(BaiduASRDigitalDialog.PARAM_PROP, VoiceRecognitionConfig.PROP_SEARCH);
//设置语种类型：中文普通话，中文粤语，英文，可选。默认为中文普通话
        params.putString(BaiduASRDigitalDialog.PARAM_LANGUAGE, VoiceRecognitionConfig.LANGUAGE_CHINESE);
//如果需要语义解析，设置下方参数。领域为输入不支持
//        params.putBoolean(BaiduASRDigitalDialog.PARAM_NLU_ENABLE, true);
// 设置对话框主题，可选。BaiduASRDigitalDialog 提供了蓝、暗、红、绿、橙四中颜色，每种颜色又分亮、暗两种色调。共 8 种主题，开发者可以按需选择，取值参考 BaiduASRDigitalDialog 中前缀为 THEME_的常量。默认为亮蓝色
        params.putInt(BaiduASRDigitalDialog.PARAM_DIALOG_THEME, BaiduASRDigitalDialog.THEME_RED_DEEPBG);
        mDialog = new BaiduASRDigitalDialog(context, params);
        mRecognitionListener = new DialogRecognitionListener() {
            @Override
            public void onResults(Bundle results) {
                // 在Results中获取Key 为DialogRecognitionListener .RESULTS_RECOGNITION的StringArrayList，
                // 可能为空。获取到识别结果后执行相应的业务逻辑即可，此回调会在主线程调用。
                ArrayList<String> rs = results != null ? results
                        .getStringArrayList(RESULTS_RECOGNITION) : null;
                if (rs != null) {
                    //此处处理识别结果，识别结果可能有多个，按置信度从高到低排列，第一个元素是置信度最高的结果。
                    if (mYuYinCallListener != null && rs.size()>0)
                        mYuYinCallListener.setText(rs.get(0));
                }
            }
        };
        mDialog.setDialogRecognitionListener(mRecognitionListener);
    }

    /**
     * 在销毁方法中调用
     */
    public void dismiss() {
        mDialog.dismiss();
    }

    /**
     * 调用语音对话框
     */
    public void showDialog() {
        mDialog.show();
    }
    /**
     * 注册监听
     * @param call
     */
    public void setmYuYinCallListener(YuYinCallListener call) {
        mYuYinCallListener = call;
    }
}
