package com.my.reader.clipcanvas;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.ViewTreeObserver;

import com.my.reader.customview.R;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class LightningView extends android.support.v7.widget.AppCompatTextView {
    private Shader mGradient;
    private Shader textGradient;
    private Matrix mGradientMatrix;

    private Paint mPaint;
    private int mViewWidth = 0, mViewHeight = 0;
    private float mTranslateX = 0, mTranslateY = 0;
    private boolean mAnimating = false;
    private Rect rect;
    private Paint textPaint;
    private ValueAnimator valueAnimator;
    private boolean autoRun = true; //是否自动运行动画
    private int mTranslate;
    private int textFullColor;
    private int timeInterval;

    public LightningView(Context context) {
        this(context, null);
    }

    public LightningView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public LightningView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.LightingText);
        autoRun = ta.getBoolean(R.styleable.LightingText_isAutoStart, true);
        textFullColor = ta.getColor(R.styleable.LightingText_lightColor, getResources().getColor(R.color.white));
        timeInterval = ta.getInteger(R.styleable.LightingText_time_interval, 4000);
        ta.recycle();
        rect = new Rect();
        mPaint = new Paint();
        textPaint = new Paint();
        initGradientAnimator();
    }

    public void setAutoRun(boolean autoRun) {
        this.autoRun = autoRun;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        rect.set(0, 0, getWidth(), getHeight());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mViewWidth == 0) {
            mViewWidth = getWidth();
            mViewHeight = getHeight();
            if (mViewWidth > 0) {
                textPaint = getPaint();
                mGradient = new LinearGradient(0, 0, mViewWidth / 3, mViewHeight,
//                        new int[]{getResources().getColor(R.color.colorAccent),textFullColor, getResources().getColor(R.color.colorAccent)},
//                        new int[]{0x00FDC352, 0x99FFE599, 0x99FFE599,  0x99FFE599, 0x00FDC352},
                        new int[]{0x00ffffff, 0x00ffffff, 0xFFFDC352,0xFFFDC352, 0xFFFFE599,0xFFFDC352, 0xFFFDC352, 0x00ffffff, 0x00ffffff},
//                        new float[]{0.2f,0.35f,0.6f},
//                        new float[]{0f, 0.15f, 0.16f, 0.36f,0.40f,0.44f, 0.64f, 0.65f, 1f},
                        new float[]{0f, 0.15f, 0.16f, 0.25f,0.45f,0.65f, 0.74f, 0.75f, 1f},
//                        new float[]{0.2f,       0.35f,      0.5f,        0.7f,      1},
                        Shader.TileMode.CLAMP);
                mPaint.setShader(mGradient);
                mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.LIGHTEN));

                textGradient = new LinearGradient(0, 0, mViewWidth * 2 / 3, mViewHeight,
                        new int[]{getResources().getColor(R.color.white),
                                textFullColor,
                                getResources().getColor(R.color.white)},
                        new float[]{0.26f, 0.4f, 0.55f},
                        Shader.TileMode.CLAMP);
                textPaint.setShader(textGradient);
                mGradientMatrix = new Matrix();
                mGradientMatrix.setTranslate(-mViewWidth, mViewHeight);
                mGradient.setLocalMatrix(mGradientMatrix);
                textGradient.setLocalMatrix(mGradientMatrix);
                rect.set(0, 0, w, h);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mAnimating && mGradientMatrix != null) {
            canvas.drawRect(rect, mPaint);
        }
    }

    private void initGradientAnimator() {
        valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.setDuration(timeInterval);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (Float) animation.getAnimatedValue();
                //❶ 改变每次动画的平移x、y值，范围是[-mViewWidth, mViewWidth]
                mTranslateX = 2 * mViewWidth * v - mViewWidth;
                mTranslateY = mViewHeight * v;
                //❷ 平移matrix, 设置平移量
                if (mGradientMatrix != null) {
                    mGradientMatrix.setTranslate(mTranslateX, mTranslateY);

                }
                //❸ 设置线性变化的matrix
                if (mGradient != null) {
                    mGradient.setLocalMatrix(mGradientMatrix);
                    textGradient.setLocalMatrix(mGradientMatrix);

                }
                //❹ 重绘
                invalidate();
            }
        });
        if (autoRun) {
            valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
            getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                @Override
                public void onGlobalLayout() {
                    getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    mAnimating = true;
                    if (valueAnimator != null) {
                        valueAnimator.start();
                    }
                }
            });
        }
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startAnimation();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopAnimation();
    }

    //停止动画
    public void stopAnimation() {
        if (mAnimating && valueAnimator != null) {
            mAnimating = false;
            valueAnimator.cancel();
            invalidate();
        }
    }

    //开始动画
    public void startAnimation() {
        if (!mAnimating && valueAnimator != null) {
            mAnimating = true;
            valueAnimator.start();
        }
    }
}

    
