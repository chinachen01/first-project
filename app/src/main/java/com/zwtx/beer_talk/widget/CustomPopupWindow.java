package com.zwtx.beer_talk.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.zwtx.beer_talk.R;
import com.zwtx.beer_talk.utils.ScreenUtils;

public class CustomPopupWindow extends PopupWindow {
    private View contentView;

    public CustomPopupWindow(View contentView, int width, int height) {
        super(contentView, width, height);
        this.contentView = contentView;
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
        /* 设置一个半透明背景,点击back或其他地方才能取消弹窗*/
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        this.setBackgroundDrawable(dw);
        /* 设置动画效果 */
        this.setAnimationStyle(R.style.AnimationPreView);
                /* 设置监听事件 */
//        LinearLayout addTaskLayout = (LinearLayout) contentView
//                .findViewById(R.id.add_task_layout);
//        LinearLayout teamMemberLayout = (LinearLayout) contentView
//                .findViewById(R.id.team_member_layout);
//        addTaskLayout.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                CustomPopupWindow.this.dismiss();
//            }
//        });
//
//        teamMemberLayout.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                CustomPopupWindow.this.dismiss();
//            }
//        });
    }
    public CustomPopupWindow(View contentView) {
        this(contentView, ScreenUtils.getScreenWidth(contentView.getContext()), ScreenUtils.getScreenHeight(contentView.getContext())/2);
    }
}
