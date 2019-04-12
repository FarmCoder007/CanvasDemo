package com.my.reader.clipcanvas;

import android.content.Context;
import android.graphics.Canvas;
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
        gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, color);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 设置gradientDrawable将被绘制在哪个矩形区域内
        gradientDrawable.setBounds(0, 0, 100, 100);
        gradientDrawable.draw(canvas);
    }
}
