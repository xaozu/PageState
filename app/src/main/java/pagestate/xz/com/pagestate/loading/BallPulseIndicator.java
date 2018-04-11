package pagestate.xz.com.pagestate.loading;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;

/**
 * loading
 */
public class BallPulseIndicator extends Indicator {

    float scaleFloat=1,degrees;

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(6);
        paint.setColor(Color.GREEN);

        Paint bigPaint = new Paint();
        bigPaint.setStrokeWidth(6);
        bigPaint.setStyle(Paint.Style.STROKE);
        bigPaint.setAntiAlias(true);
        bigPaint.setColor(Color.GRAY);

        float circleSpacing=12;
        float x = (getWidth()) / 2;
        float y=(getHeight()) / 2;
        canvas.translate(x, y);

        canvas.drawCircle(0, 0, x-circleSpacing, bigPaint);
        canvas.save();


        canvas.scale(scaleFloat, scaleFloat);
        canvas.rotate(degrees);
        RectF rectF=new RectF(-x+circleSpacing,-y+circleSpacing,0+x-circleSpacing,0+y-circleSpacing);
        canvas.drawArc(rectF, 0, 45, false, paint);
    }

    @Override
    public ArrayList<ValueAnimator> onCreateAnimators() {
        ArrayList<ValueAnimator> animators=new ArrayList<>();
        ValueAnimator rotateAnim=ValueAnimator.ofFloat(0, 359);
        rotateAnim.setDuration(500);
        rotateAnim.setRepeatCount(-1);
        rotateAnim.setInterpolator(new LinearInterpolator());
        addUpdateListener(rotateAnim,new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                degrees = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animators.add(rotateAnim);
        return animators;
    }

}
