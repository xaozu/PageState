package pagestate.xz.com.pagestate.loading;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * creator: zhulunjun
 * time:    2018/4/9
 * describe: 刷新view
 */

public class RefreshView extends View {
    private Paint mPaintCircle, mPaintArc;
    private float radius = 40f;
    private float degrees = 0f;
    private ValueAnimator valueAnimator;

    public RefreshView(Context context) {
        super(context);
        init();
    }

    public RefreshView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RefreshView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaintCircle = new Paint();
        mPaintCircle.setStrokeWidth(6);
        mPaintCircle.setStyle(Paint.Style.STROKE);
        mPaintCircle.setAntiAlias(true);
        mPaintCircle.setColor(Color.GRAY);

        mPaintArc = new Paint();
        mPaintArc.setStrokeWidth(6);
        mPaintArc.setStyle(Paint.Style.STROKE);
        mPaintArc.setAntiAlias(true);
        mPaintArc.setColor(Color.GREEN);

        valueAnimator = ValueAnimator.ofFloat(0, 360);
        valueAnimator.setDuration(500);
        valueAnimator.setRepeatCount(-1);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                degrees = (float) animation.getAnimatedValue();
                setRotation(degrees);
            }
        });

    }

    private RectF oval;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float x = (getMeasuredWidth()) / 2;
        float y = (getMeasuredHeight()) / 2;
        // 画大圆
        canvas.drawCircle(x, y, radius, mPaintCircle);
        canvas.save();

        // 画扇形
        if (oval == null) {
            oval = new RectF();
        }
        oval.set(x - radius, y - radius, x + radius, y + radius);
        canvas.drawArc(oval, 0, 45, false, mPaintArc);

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (valueAnimator != null) valueAnimator.cancel();
    }

    public void startAnim() {
        if (valueAnimator != null) valueAnimator.start();
    }

    public void stopAnim() {
        if (valueAnimator != null && valueAnimator.isRunning()) valueAnimator.cancel();
    }


}
