package com.my.reader.clipcanvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * author : xu
 * date : 2019/4/12 14:00
 * description : 测试math 相关api
 */
public class MathView extends View {
    private Paint mPaint;

    public MathView(Context context) {
        super(context);
        init();
    }

    public MathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStrokeWidth(10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        // 绘制坐标线
        mPaint.setColor(Color.RED);
        canvas.drawLine(0, 0, width, 0, mPaint);
        //绘制蓝色Y轴
        mPaint.setColor(Color.BLUE);
        canvas.drawLine(0, 0, 0, height, mPaint);

        mPaint.setColor(Color.GREEN);
        canvas.drawLine(100, 0, 100, 100, mPaint);
        canvas.drawLine(50, 100, 100, 100, mPaint);
        canvas.drawLine(100, 0, 50, 100, mPaint);
        Log.e("flag", "--------------------atan2:" + Math.atan2(-100, -100)+"--(float) Math.toDegrees:"+(float) Math.toDegrees(Math.atan2(50, 100)));
    }
}
