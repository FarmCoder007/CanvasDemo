package com.my.reader.clipcanvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Region;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * author : xu
 * date : 2019/4/10 11:01
 * description :
 */
public class MaskView extends View {

    private Paint paint;

    public MaskView(Context context) {
        super(context);
        init();
    }

    public MaskView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MaskView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void init() {
        paint = new Paint();
        paint.setColor(Color.RED);
//        paint.setStyle(Paint.Style.STROKE);//设置空心
        paint.setStrokeWidth(3);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//DIFFERENCE是第一次不同于第二次的部分显示出来A-B-------  【绘制A - (B与A重合的部分)】
//REPLACE是显示第二次的B******      【只绘制B】
//REVERSE_DIFFERENCE 是第二次不同于第一次的部分显示--------【绘制B - (A在B中重合的部分)】
//INTERSECT交集显示A-(A-B)******* 【绘制ab交集 【默认的】】
//UNION全部显示A+B******  【绘制a+b】
//XOR补集 就是全集的减去交集剩余部分显示--------
        canvas.save();
        canvas.translate(10, 10);
        // 区域A
        canvas.clipRect(0, 0, 300, 300);
        // 区域B
        canvas.clipRect(200, 200, 400, 400, Region.Op.XOR);

        canvas.clipRect(0, 0, 400, 400);
        canvas.drawColor(Color.BLUE);
        canvas.restore();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        canvas.translate(10, 10);
        canvas.drawRect(0, 0, 300, 300, paint);
        paint.setColor(Color.RED);
        canvas.drawRect(200, 200, 400, 400, paint);
        invalidate();
    }
}
