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
    private int height = -1;
    private int width = -1;

    public CustomPopupWindow(Context context, View contentView) {
        this.contentView = contentView;
        int h = ScreenUtils.getScreenHeight(context);
        int w = ScreenUtils.getScreenWidth(context);
        this.setContentView(contentView);
        //设置弹窗默认为屏幕的宽
        if (height == -1)
            this.setWidth(w);
        //设置弹窗默认为屏幕的高
        if (width == -1)
            this.setHeight(h / 2);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
        /* 设置一个半透明背景,点击back或其他地方才能取消弹窗*/
        ColorDrawable dw = new ColorDrawable(0000000000);
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

//    /**
//     * 显示popupWindow
//     *
//     * @param parent
//     */
//    public void showPopupWindow(View parent) {
//        if (!this.isShowing()) {
//            // 以下拉方式显示popupwindow
//            this.showAsDropDown(parent, parent.getLayoutParams().width / 2, 18);
//        } else {
//            this.dismiss();
//        }
//    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
