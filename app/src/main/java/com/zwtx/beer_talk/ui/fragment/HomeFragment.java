package com.zwtx.beer_talk.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.baidu.voicerecognition.android.ui.BaiduASRDigitalDialog;
import com.baidu.voicerecognition.android.ui.DialogRecognitionListener;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.zwtx.beer_talk.QRcode.activity.QRcodeActivity;
import com.zwtx.beer_talk.R;
import com.zwtx.beer_talk.constants.HttpConstants;
import com.zwtx.beer_talk.manager.HttpManager;
import com.zwtx.beer_talk.ui.activity.HomeActivity;
import com.zwtx.beer_talk.utils.L;
import com.zwtx.beer_talk.utils.T;
import com.zwtx.beer_talk.yuyin.YuYinHelper;

import org.apache.http.Header;
import org.json.JSONObject;

import java.util.Calendar;

/**
 * 显示home的界面及相关业务逻辑
 */
public class HomeFragment extends Fragment {
    private EditText mSearchEdit;
    private TextView mCodeTxt, mVoiceTxt;
    private YuYinHelper helper;
    private HomeActivity activity;
    private DatePicker mDatePicker;
    private Button mSearchBtn;

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        setDaily();
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (HomeActivity) activity;
    }

    /**
     * 初始化控件
     */
    private void initView(View view) {
        /*初始化SearchEdit */
        mSearchEdit = (EditText) view.findViewById(R.id.home_edit_txt);
        //注册扫描二位码的回调监听事件
        activity.setHomeFragmentCallBackListner(new HomeFragmentCallBackListener() {
            @Override
            public void setSearchEdit(String text) {
                mSearchEdit.setText(text);
            }
        });
        /* 初始化语音及二维码识别 */
        mVoiceTxt = (TextView) view.findViewById(R.id.home_speech_click_txt);
        mCodeTxt = (TextView) view.findViewById(R.id.home_code_click_txt);
        mVoiceTxt.setOnClickListener(new HomeClickListener());
        mCodeTxt.setOnClickListener(new HomeClickListener());
        /* 初始化datePicker */
        mDatePicker = (DatePicker) view.findViewById(R.id.home_date_picker);
        /* 初始化搜索按键 */
        mSearchBtn = (Button) view.findViewById(R.id.home_search_btn);
        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText = mSearchEdit.getText().toString().trim();
                if (!TextUtils.isEmpty(searchText)) {
                    HttpManager.get(HttpConstants.TEXT_SEARCH + searchText, new JsonHttpResponseHandler("UTF-8") {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            super.onSuccess(statusCode, headers, response);
                            T.showLong(activity,response.toString());
                            L.d(response.toString());
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                            super.onFailure(statusCode, headers, responseString, throwable);
                        }
                    });
                }
            }
        });
    }

    private void setDaily() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(calendar.YEAR);
        int month = calendar.get(calendar.MONTH);
        int day = calendar.get(calendar.DAY_OF_MONTH);
        mDatePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String daily = monthOfYear + 1 + "月" + dayOfMonth + "日";
                T.showShort(getActivity(), daily);
                HttpManager.get(HttpConstants.DRINK_DATE,new JsonHttpResponseHandler("UTF-8"){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        T.showLong(activity, response.toString());
                        L.d(response.toString());
                    }
                });

            }
        });
    }

    /**
     * 启动语音识别
     */
    private void startRecognizeSpeech() {
        helper = new YuYinHelper(getActivity());
        helper.setmYuYinCallListener(new YuYinHelper.YuYinCallListener() {
            @Override
            public void setText(String string) {
                mSearchEdit.setText(string);
            }
        });
        helper.showDialog();
    }

    private String getStringFromScanCode() {
        String string = "";
        return string;
    }


    /**
     * 按键的点击事件
     */
    class HomeClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.home_speech_click_txt:
                    /* 调用语音识别 */
                    startRecognizeSpeech();
                    break;
                case R.id.home_code_click_txt:
                    /* 调用二维码扫描 */
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), QRcodeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    getActivity().startActivityForResult(intent, HomeActivity.SCANNIN_GREQUEST_CODE);
                    break;
            }
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (helper != null)
            helper.dismiss();
    }
    public interface HomeFragmentCallBackListener {
        void setSearchEdit(String text);
    }
}
