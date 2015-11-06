package com.zwtx.beer_talk.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.zwtx.beer_talk.R;


public class CustomImage extends View {
    //转换图片的类型
    private int type;
    private static final int TYPE_CIRCLE = 0;
    private static final int TYPE_ROUND = 1;
    //图片的宽
    private int mWith;
    //图片的高
    private int mHeight;
    //图片的圆角
    private int mRadius;
    //源图片
    private Bitmap mSrc;

    public CustomImage(Context context) {
        this(context, null);
    }

    public CustomImage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomImage, defStyleAttr, 0);
        int num = a.getIndexCount();
        for (int i = 0; i < num; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.CustomImage_src:
                    mSrc = BitmapFactory.decodeResource(getResources(), a.getResourceId(attr, 0));
                    break;
                case R.styleable.CustomImage_type:
                    type = a.getInt(attr, TYPE_CIRCLE);//默认类型为圆形
                    break;
                case R.styleable.CustomImage_border_radius:
                    mRadius = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f, getResources().getDisplayMetrics()));//默认为10dp
                    break;
            }
        }
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        //测量宽度
        if (widthMode == MeasureSpec.EXACTLY) {
            //精确尺寸与MatchParent
            mWith = widthSize;
        } else {
            //图片的实际尺寸
            int desireByImage = mSrc.getWidth() + getPaddingLeft() + getPaddingRight();
            if (widthMode == MeasureSpec.AT_MOST) {
                mWith = Math.min(desireByImage, widthSize);
            } else {
                mWith = desireByImage;
            }
        }
        //测量高度
        if (heightMode == MeasureSpec.EXACTLY) {
            //精确尺寸与MatchParent
            mHeight = heightSize;
        } else {
            //图片的实际尺寸
            int desireByImage = mSrc.getHeight() + getPaddingTop() + getPaddingBottom();
            if (heightMode == MeasureSpec.AT_MOST) {
                mHeight = Math.min(desireByImage, heightSize);
            } else {
                mHeight = desireByImage;
            }
        }
        setMeasuredDimension(mWith, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        switch (type) {
            case TYPE_CIRCLE:
                int min = Math.min(mWith, mHeight);
                //将图片压缩为长宽相等图片
                mSrc = Bitmap.createScaledBitmap(mSrc, min, min, false);
                //绘制圆形图片
                canvas.drawBitmap(createCircleBitMap(mSrc, min), 0, 0, null);
                break;
            case TYPE_ROUND:
                //绘制圆角图片
                canvas.drawBitmap(createRoundBitMap(mSrc), 0, 0, null);
                break;
        }
    }

    /**
     * 绘制圆形位图
     * @param src 源位图
     * @param min 半径
     * @return BitMap
     */
    private Bitmap createCircleBitMap(Bitmap src, int min) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);//抗锯齿
        //创建与源位图大小一致的位图
        Bitmap target = Bitmap.createBitmap(min, min, Bitmap.Config.ARGB_8888);
        //创建与源位图大小一致的画布
        Canvas canvas = new Canvas(target);
        //画圆形
        canvas.drawCircle(min / 2, min / 2, min / 2, paint);
        //设置SRC_IN这种模式，两个绘制的效果叠加后取交集展现后图
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //绘制源图,得到交集图片
        canvas.drawBitmap(src, 0, 0, paint);
        return target;
    }

    /**
     * 绘制圆角图片
     * @param mSrc 源位图
     * @return Bitmap
     */
    private Bitmap createRoundBitMap(Bitmap mSrc) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);//抗锯齿
        Bitmap target = Bitmap.createBitmap(mSrc.getWidth(), mSrc.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(target);
        RectF rectF = new RectF(0, 0, mSrc.getWidth(), mSrc.getHeight());
        canvas.drawRoundRect(rectF,mRadius,mRadius,paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(mSrc, 0, 0, paint);
        return target;
    }
}