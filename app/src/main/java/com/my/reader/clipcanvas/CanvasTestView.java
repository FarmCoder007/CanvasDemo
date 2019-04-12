package com.my.reader.clipcanvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * author : xu
 * date : 2019/4/11 16:26
 * description :  测试Canvas相关api
 */
public class CanvasTestView extends View {
    private Paint mPaint;

    public CanvasTestView(Context context) {
        super(context);
        initPaint();
    }

    public CanvasTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public CanvasTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        drawCanvasColor(canvas);
//        drawAxis(canvas);
//        drawPoint(canvas);
//        drawLine(canvas);
        drawPath(canvas);
//        drawText(canvas);
    }

    /**
     * 绘制画布颜色
     */
    private void drawCanvasColor(Canvas canvas) {
        //drawARGB
        canvas.drawARGB(255, 47, 140, 150);
    }

    //绘制点
    private void drawPoint(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        int translateY = height / 5;

        //设置画笔颜色
        mPaint.setColor(Color.RED);
        //设置画笔宽度
        mPaint.setStrokeWidth(200);

        //设置笔触样式
        mPaint.setStrokeCap(Paint.Cap.BUTT);
        //保存当前画布状态（坐标系）
        canvas.save();
        canvas.drawPoint(width / 3, height / 3, mPaint);
        //恢复上次画布保存状态（坐标系）
        canvas.restore();

        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setColor(Color.GREEN);
        canvas.save();
        canvas.translate(0, translateY);
        canvas.drawPoint(width / 3, height / 3, mPaint);
        canvas.restore();

        mPaint.setStrokeCap(Paint.Cap.SQUARE);
        mPaint.setColor(Color.BLUE);
        canvas.save();
        canvas.translate(translateY, translateY);
        canvas.drawPoint(width / 3, height / 3, mPaint);
        canvas.restore();
    }

    /**
     * 绘制坐标系
     */
    private void drawAxis(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        int translate = width / 5;

        //设置画笔宽度
        mPaint.setStrokeWidth(10);
        //设置笔触样式
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        //绘制默认绘图坐标系（此时与Canvas坐标系重合）
        //绘制红色X轴
        mPaint.setColor(Color.RED);
        canvas.drawLine(0, 0, width, 0, mPaint);
        //绘制蓝色Y轴
        mPaint.setColor(Color.BLUE);
        canvas.drawLine(0, 0, 0, height, mPaint);

        //平移  x y 为在横纵坐标系的偏移量
        canvas.translate(translate, translate);
        //绘制平移后绘图坐标系
        //绘制红色X轴
        mPaint.setColor(Color.RED);
        canvas.drawLine(0, 0, width, 0, mPaint);
        //绘制蓝色Y轴
        mPaint.setColor(Color.BLUE);
        canvas.drawLine(0, 0, 0, height, mPaint);


        //先平移 旋转30度
        canvas.translate(translate, translate);
        canvas.rotate(30);

        //绘制平移旋转后绘图坐标系
        //绘制红色X轴
        mPaint.setColor(Color.RED);
        canvas.drawLine(0, 0, width, 0, mPaint);
        //绘制蓝色Y轴
        mPaint.setColor(Color.BLUE);
        canvas.drawLine(0, 0, 0, height, mPaint);

    }

    /**
     * 绘制线
     */
    private void drawLine(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        int startX = width / 5;
        int startY = height / 5;

        //设置画笔颜色
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(20);
        //设置笔触样式
        mPaint.setStrokeCap(Paint.Cap.BUTT);
        //画笔触为BUTT的线
        canvas.drawLine(startX, startY, width - startX, startY, mPaint);

        //向下平移20
        canvas.translate(0, 60);

        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        //画笔触为ROUND的线
        canvas.drawLine(startX, startY, width - startX, startY, mPaint);

        canvas.translate(0, 60);

        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeCap(Paint.Cap.SQUARE);
        //画笔触为SQUARE的线
        canvas.drawLine(startX, startY, width - startX, startY, mPaint);

        canvas.translate(0, 80);

        mPaint.setColor(Color.BLACK);
        float[] pts = new float[]{startX, startY, width - startX, startY, startX, startY + 60, width - startX, startY + 60};
        canvas.drawLines(pts, mPaint);
    }

    //绘制矩形
    private void drawRect(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();

        //设置画笔颜色
        mPaint.setColor(Color.RED);
        //设置画笔宽度
        mPaint.setStrokeWidth(16);
        //设置画笔样式
        mPaint.setStyle(Paint.Style.FILL);

        canvas.drawRect(20, 20, 400, 200, mPaint);
        //绘制矩形 API21
        //canvas.drawRoundRect(220, 220, 400, 400, 10, 10, mPaint);
        RectF rect = new RectF(20, 420, 800, 600);
        canvas.drawRoundRect(rect, 10, 10, mPaint);

        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);

        canvas.drawRect(20, 800, 400, 1000, mPaint);
        RectF rect1 = new RectF(20, 1200, 800, 1400);
        canvas.drawRoundRect(rect1, 10, 10, mPaint);
    }

    //绘制圆形
    private void drawCircle(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        int halfWidth = width / 2;
        int d = height / 3;
        int r = d / 2 - 10;

        //设置画笔颜色
        mPaint.setColor(Color.RED);
        //设置画笔宽度
        mPaint.setStrokeWidth(2);
        //设置画笔样式
        mPaint.setStyle(Paint.Style.FILL);

        //绘制圆
        canvas.drawCircle(halfWidth, (float) (d * 0.5), r, mPaint);

        //绘制两个圆实现圆环效果
        mPaint.setColor(Color.GREEN);
        canvas.drawCircle(halfWidth, (float) (d * 1.5), r, mPaint);
        mPaint.setColor(Color.WHITE);
        canvas.drawCircle(halfWidth, (float) (d * 1.5), r - 40, mPaint);

        //绘制一个圆实现圆环效果
        mPaint.setStrokeWidth(40);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
        //半径减去画笔画笔宽度的一半 圆的大小和前面一致
        canvas.drawCircle(halfWidth, (float) (d * 2.5), r - 20, mPaint);
    }


    //path拐点集合
    List<Point> points = new ArrayList<>();

    //绘制Path
    private void drawPath(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        int deltaX = width / 4;
        int deltaY = (int) (deltaX * 0.75);

        //设置画笔颜色
        mPaint.setColor(Color.GRAY);
        //设置画笔宽度
        mPaint.setStrokeWidth(4);

        //Fill模式画面
        Path path1 = new Path();
        RectF arcRect = new RectF(0, 0, deltaX, deltaY);
        mPaint.setStyle(Paint.Style.STROKE);
        // 添加椭圆的部分
//        path1.addArc(arcRect, 0, 360);
        // 添加矩形的部分
//        path1.addRect(deltaX, 0, deltaX << 1, deltaY, Path.Direction.CCW);
        path1.addCircle(100,100,50, Path.Direction.CCW);
        canvas.translate(200,200);
        path1.addRoundRect(arcRect,38,18, Path.Direction.CCW);
        canvas.drawPath(path1, mPaint);


//        //STROKE模式画线
//        mPaint.setColor(Color.RED);
//        mPaint.setStyle(Paint.Style.STROKE);
//        canvas.translate(0, deltaY << 1);
//        canvas.drawPath(path1, mPaint);
//
//        mPaint.setColor(Color.BLUE);
////        mPaint.setStyle(Paint.Style.FILL);
//        canvas.translate(0, deltaY << 1);
//
//        Path path2 = new Path();
//        path2.lineTo(100, 0);   //画线
//        points.add(new Point(100, 0));  //终点 X: 100 Y: 0
//
//        RectF overRect = new RectF(100, -50, 300, 50);
//        path2.arcTo(overRect, 180, -90); //画椭圆 左下部分
//        points.add(new Point(200, 50)); //终点 X: 250 Y: 50
//
//        overRect = new RectF(200, 0, 400, 100);
//        path2.arcTo(overRect, 180, 90); //画椭圆右上部分
//        points.add(new Point(300, 0));  //终点 X: 300 Y: 0
//
//        path2.lineTo(350, 50);  //画线
//        points.add(new Point(350, 50)); //终点 X: 350 Y: 50
//
//        //画二阶贝塞尔曲线 第一个坐标为控制点 最后为终点
//        path2.quadTo(450, -50, 500, 150);
//        points.add(new Point(500, 150)); //终点 X: 500 Y: 150
//
//        //画三阶阶贝塞尔曲线 前两个坐标为控制点 最后为终点
//        path2.cubicTo(600, 150, 700, 0, 380, -200);
//        points.add(new Point(380, -200)); //终点 X: 480 Y: -200
//
//        path2.rLineTo(0, 100);
//        points.add(new Point(380, -200 + 100)); //终点 X: 480 Y: -100
//        canvas.drawPath(path2, mPaint);
//
//        mPaint.setStrokeWidth(10);
//        mPaint.setColor(Color.RED);
//        mPaint.setStrokeCap(Paint.Cap.ROUND);
//        for (Point point : points) {
//            canvas.drawPoint(point.x, point.y, mPaint);
//        }
    }

    //画布缩放
    private void canvasScale(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        int hWidth = width / 2;
        int hHeight = height / 2;

        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.STROKE);
        //使用path 画布中心的X Y 轴
        Path path = new Path();
        path.moveTo(hWidth, 0);
        path.lineTo(hWidth, height);
        path.moveTo(0, hHeight);
        path.lineTo(width, hHeight);
        canvas.drawPath(path, mPaint);

        //画布平移
        canvas.translate(hWidth, hHeight);

        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(5);
        RectF rectF = new RectF(0, 0, 200, 100);
        canvas.drawRect(rectF, mPaint);

        //默认0,0进行缩放
        canvas.scale(1.5f, 1.5f);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(rectF, mPaint);

        //100,0进行缩放
        canvas.scale(1.5f, 1.5f, 100, 0);
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(rectF, mPaint);

        /*
        当缩放倍数为负数时，会先进行缩放，然后根据不同情况进行图形翻转：
        （设缩放倍数为（a,b），旋转中心为（px，py））：
        a<0，b>0：以px为轴翻转
        a>0，b<0：以py为轴翻转
        a<0，b<0：以旋转中心翻转
         */
        //100,0进行缩放 以Y轴翻转
        canvas.scale(1f, -1f, 100, 0);
        mPaint.setColor(Color.BLACK);
        canvas.drawRect(rectF, mPaint);
    }

    //画布错切
    private void canvasSkew(Canvas canvas) {
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
        RectF rectF = new RectF(0, 0, 400, 200);
        canvas.translate(100, 100);
        canvas.drawRect(rectF, mPaint);

        canvas.save();
        canvas.translate(0, 300);
        //画布X正方向错切45°
        canvas.skew(1f, 0);
        canvas.drawRect(rectF, mPaint);
        canvas.restore();

        canvas.save();
        canvas.translate(0, 600);
        //画布Y负方向错切45°
        canvas.skew(0, -1f);
        canvas.drawRect(rectF, mPaint);
        canvas.restore();

        mPaint.setColor(Color.GREEN);
        canvas.drawLine(0, 400, 600, 400, mPaint);
    }

    //绘制文字
    private void drawText(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        int textHeight = 32;
        int halfWidth = width / 2;

        //设置画笔颜色
        mPaint.setColor(Color.GRAY);
        //设置画笔宽度
        mPaint.setStrokeWidth(2);
        canvas.drawLine(halfWidth, 0, halfWidth, height, mPaint);

        mPaint.setColor(Color.BLACK);
        //设置文本大小
        mPaint.setTextSize(textHeight);
        //绘制普通文本
        canvas.drawText("绘制普通文本", 0, textHeight, mPaint);

        mPaint.setColor(Color.RED);
        mPaint.setTextAlign(Paint.Align.LEFT);
        //存储画布状态 坐标系
        canvas.save();
        //平移
        canvas.translate(halfWidth, textHeight);
        //绘制左对齐文本
        canvas.drawText("绘制左对齐文本", 0, textHeight, mPaint);
        //恢复上次存储状态
        canvas.restore();

        mPaint.setColor(Color.GREEN);
        mPaint.setTextAlign(Paint.Align.CENTER);
        //存储画布状态 坐标系
        canvas.save();
        //平移
        canvas.translate(halfWidth, textHeight * 2);
        //绘制居中对齐文本(对齐是根据绘图坐标系而言)
        canvas.drawText("绘制居中对齐文本", 0, textHeight, mPaint);
        //恢复上次存储状态
        canvas.restore();

        mPaint.setColor(Color.BLUE);
        mPaint.setTextAlign(Paint.Align.RIGHT);
        //存储画布状态 坐标系
        canvas.save();
        //平移
        canvas.translate(halfWidth, textHeight * 3);
        //绘制右对齐文本
        canvas.drawText("绘制右对齐文本", 0, textHeight, mPaint);
        //恢复上次存储状态
        canvas.restore();


        mPaint.setColor(Color.BLACK);
        //恢复默认对齐方式
        mPaint.setTextAlign(Paint.Align.LEFT);
        //设置下划线
        mPaint.setUnderlineText(true);
        //设置加粗
        mPaint.setFakeBoldText(true);
        //设置删除线
        mPaint.setStrikeThruText(true);
        //存储画布状态 坐标系
        canvas.save();
        //平移
        canvas.translate(halfWidth, textHeight * 4);
        //绘制下划线加粗文本
        canvas.drawText("绘制下划线加粗文本", 0, textHeight, mPaint);
        //恢复上次存储状态
        canvas.restore();

        mPaint.reset();
        mPaint.setColor(Color.BLACK);
        //设置文本大小
        mPaint.setTextSize(textHeight);
        //存储画布状态 坐标系
        canvas.save();
        canvas.translate(width / 3, height / 3);
        //旋转
        canvas.rotate(45);
        //绘制倾斜文本
        canvas.drawText("绘制倾斜文本", 0, textHeight, mPaint);
        //恢复上次存储状态
        canvas.restore();

        float[] pos = new float[]{40, 40, 80, 80, 120, 120, 160, 160, 200, 200, 240, 240};
        //存储画布状态 坐标系
        canvas.save();
        canvas.translate(width / 3, height / 2);
        canvas.drawPosText("绘制位置文本", pos, mPaint);
        //恢复上次存储状态
        canvas.restore();

        Path path = new Path();
        path.cubicTo(550, 750, 350, 250, 850, 800);
        //画路径
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, mPaint);
        canvas.drawTextOnPath("绘制文本OnPath绘制文本OnPath绘制文本OnPath绘制文本OnPath绘制文本OnPath", path, 50, 0, mPaint);
    }
}
