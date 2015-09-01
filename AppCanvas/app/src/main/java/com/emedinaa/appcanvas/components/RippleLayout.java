package com.emedinaa.appcanvas.components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;

import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;

/**
 * Created by emedinaa on 25/03/15.
 */
public class RippleLayout extends RelativeLayout
{
    public static final String TAG="RippleLayout";
    private Context context;
    private float mRadius;
    private float mMaxRadius;
    private Paint mPaint;
    private ObjectAnimator mRadiusAnimator;
    private int mRippleColor=0x0F9D58;
    private float mAlphaFactor=0.7f;
    private int mWidth=0;
    private int mHeight=0;
    private float mDownX, mDownY;
    private RadialGradient mRadialGradient;

    public RippleLayout(Context context) {
        super(context);
        init(context);
    }

    public RippleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RippleLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context)
    {
        this.context= context;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setAlpha(100);
        mPaint.setColor(Color.parseColor("#0F9D58"));
        mPaint.setStrokeWidth(2);
        setRippleColor(Color.parseColor("#0F9D58"), 0.7f);

    }
    public void setRippleColor(int rippleColor, float alphaFactor) {
        mRippleColor = rippleColor;
        mAlphaFactor = alphaFactor;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mMaxRadius = (float) Math.sqrt(w * w + h * h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mWidth= getWidth();
        mHeight= getHeight();
        mDownX=mWidth*0.95f;
        mDownY=mHeight*0.95f;

        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(mDownX, mDownY, mRadius, mPaint);

    }

    public void setRadius(final float radius) {
        mRadius = radius;
        if (mRadius > 0) {
            mRadialGradient = new RadialGradient(mDownX, mDownY, mRadius,
                    adjustAlpha(mRippleColor, mAlphaFactor), mRippleColor,
                    Shader.TileMode.MIRROR);
            mPaint.setShader(mRadialGradient);
        }
        invalidate();
    }

    public int adjustAlpha(int color, float factor) {
        int alpha = Math.round(Color.alpha(color) * factor);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }

    public void goAnimation()
    {
        Log.v(TAG, "gotoAnimation ");

        mRadiusAnimator = ObjectAnimator.ofFloat(this, "radius", 0, mHeight*1.2f)
                .setDuration(600);
        mRadiusAnimator
                .setInterpolator(new AccelerateDecelerateInterpolator());

        mRadiusAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                 float percent=(Float)animation.getAnimatedValue();
                Log.v(TAG, "percent "+percent);
            }
        });
        mRadiusAnimator.start();
    }

}
