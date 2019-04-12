package com.my.reader.clipcanvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * author : xu
 * date : 2019/4/11 10:27
 * description : 测试GradientDrawable.setBounds(int left,int top,int right,int bottom);
 */
public class GradientDrawableTestView extends View {
    private GradientDrawable gradientDrawable;
    private Paint mPaint;

    public GradientDrawableTestView(Context context) {
        super(context);
        init();
    }

    public GradientDrawableTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GradientDrawableTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        int[] color = {0x333333, 0xb0333333};
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, color);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        canvas.translate(400, 400);
        // 绘制坐标线
        mPaint.setColor(Color.RED);
        canvas.drawLine(0, 0, width, 0, mPaint);
        //绘制蓝色Y轴
        mPaint.setColor(Color.BLUE);
        canvas.drawLine(0, 0, 0, height, mPaint);
        canvas.translate(0,10);
        mPaint.setColor(Color.RED);
        canvas.drawLine(0,0,100,0,mPaint);

        // 设置gradientDrawable将被绘制在哪个矩形区域内
        gradientDrawable.setBounds(10, 10, 100, 100);
        gradientDrawable.draw(canvas);
        canvas.translate(400, 400);

        canvas.drawRect(0, 110, 100, 210,mPaint);
        gradientDrawable.draw(canvas);
    }
}
