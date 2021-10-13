package rui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;

public class CornerPathEffectTestView extends View {
    Paint mPaint = new Paint();
    PathEffect mPathEffect = new CornerPathEffect(200);
    Path mPath = new Path();

    public CornerPathEffectTestView(Context context) {
        this(context, null);
    }

    public CornerPathEffectTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint.setStrokeWidth(20);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                mPath.reset();
                mPath.moveTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                break;
        }
        postInvalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制原始路径
        canvas.save();
        mPaint.setColor(Color.BLACK);
        mPaint.setPathEffect(null);
        canvas.drawPath(mPath, mPaint);
        canvas.restore();

        // 绘制带有效果的路径
        canvas.save();
        canvas.translate(0, canvas.getHeight() / 2);
        mPaint.setColor(Color.RED);
        mPaint.setPathEffect(mPathEffect);
        canvas.drawPath(mPath, mPaint);
        canvas.restore();
    }
}
