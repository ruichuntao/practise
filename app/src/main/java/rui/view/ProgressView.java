package rui.view;


import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class ProgressView extends View {

    private float distance = 0;
    private Paint paint;
    private Paint paint1;
    private Path path;
    private Path hPath;
    private PathMeasure pathMeasure;
    private float mLength;
    private float[] point = new float[2];
    private float bigCircle = 95f;
    private float banjing = 88;
    private int pathW = 3;
    private int pointW = 6;

    public ProgressView(Context context) {
        this(context, null);
    }

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this, View.ROTATION, 0, -90);
        objectAnimator.start();
        setWillNotDraw(false);
        path = new Path();
        paint = new Paint();
        hPath = new Path();
        paint1 = new Paint();
        paint.setStrokeWidth(dp2Px(pathW));
        paint.setColor(Color.parseColor("#ffffff"));
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint1.setStrokeCap(Paint.Cap.ROUND);
        paint1.setStyle(Paint.Style.FILL_AND_STROKE);
        paint1.setStrokeWidth(dp2Px(pointW));
        paint1.setAntiAlias(true);
        paint1.setColor(Color.parseColor("#ffffff"));
        path.addCircle(dp2Px(bigCircle), dp2Px(bigCircle), dp2Px(banjing), Path.Direction.CW);
        pathMeasure = new PathMeasure();
        pathMeasure.setPath(path, true);
        mLength = pathMeasure.getLength();
    }

    public void startTurn() {
        ValueAnimator animator1 = ValueAnimator.ofFloat(0, 1);
        animator1.setInterpolator(new LinearInterpolator());
        animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                distance = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator1.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }

        });
        animator1.setDuration(10000);
        animator1.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        hPath.reset();
        hPath.lineTo(0,0);
        float stop = mLength * distance;
        pathMeasure.getSegment(0, stop, hPath, true);
        pathMeasure.getPosTan(stop, point, null);
        canvas.drawPoint(point[0], point[1], paint1);
        canvas.drawPath(hPath, paint);
    }

    public float dp2Px(float dp) {
        final float scale = getContext().getResources().getDisplayMetrics().density; //当前屏幕密度因子
        return (dp * scale + 0.5f);
    }

}
