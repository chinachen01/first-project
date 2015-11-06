package com.zwtx.beer_talk.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zwtx.beer_talk.R;
import com.zwtx.beer_talk.bean.User;
import com.zwtx.beer_talk.ui.activity.HelpActivity;

import java.util.ArrayList;
import java.util.List;

public class PersonalFragment extends Fragment{
    private TextView mNameTxt,mLevelTxt,mHelpTxt;
    private List<View> mViewList ;

    public static PersonalFragment newInstance() {
        Bundle args = new Bundle();
        PersonalFragment fragment = new PersonalFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal, container, false);
        initView(view);
        return view;
    }

    /**
     * 初始化控件
     * @param view
     */
    private void initView(View view) {
        mViewList = new ArrayList<>();
        mNameTxt = (TextView) view.findViewById(R.id.personal_user_name_txt);
        mViewList.add(mNameTxt);
        mLevelTxt = (TextView) view.findViewById(R.id.personal_user_level_txt);
        mViewList.add(mLevelTxt);
        mHelpTxt = (TextView) view.findViewById(R.id.personal_help_click_txt);
        mViewList.add(mHelpTxt);
        if (!User.getInstance().isLogin()) {
            mNameTxt.setText("未登录");
        } else {
            mNameTxt.setText(User.getInstance().getName());
            mLevelTxt.setText(User.getInstance().getLevel());
        }
        /* 循环添加点击事件 */
        for (View v : mViewList) {
            v.setOnClickListener(new PersonalOnclickListener());
        }
    }

    private class PersonalOnclickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.personal_help_click_txt:
                    HelpActivity.startAcitivity(getActivity());
                    break;
            }
        }
    }
}
