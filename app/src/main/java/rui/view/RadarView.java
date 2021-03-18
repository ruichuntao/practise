package rui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class RadarView extends View {

    int count = 6;
    float angle = ((float) (Math.PI * 2 / count));
    float radius;
    int centerX;
    int centerY;
    String[] titles = {"a", "b", "c", "d", "e", "f"};
    double[] data = {100, 60, 60, 100, 50, 10, 20};
    float maxValue = 100;
    Paint mainPaint, valuePaint, textPaint;


    public RadarView(Context context) {
        this(context, null);
    }

    public RadarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mainPaint = new Paint();
        mainPaint.setColor(Color.BLACK);
        mainPaint.setAntiAlias(true);
        mainPaint.setStrokeWidth(2);
        mainPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        radius = (Math.min(h, w) >> 1) * 0.9f;
        centerX = w / 2;
        centerY = h / 2;
        postInvalidate();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 1; i < count; i++) {
            drawPolygon(canvas);

        }
    }

    void drawPolygon(Canvas canvas) {
        Path path = new Path();
        float r = radius / (count - 1);
        for (int i = 1; i < count; i++) {
            float curR = r * i;
            path.reset();
            for (int j = 0; j < count; j++) {
                if (j == 0) path.moveTo(centerX + curR, centerY);
                else {
                    float x = ((float) (centerX + curR * Math.cos(angle * j)));
                    float y = ((float) (centerY + curR * Math.sin(angle * j)));
                    path.lineTo(x, y);
                }
            }
        }
        path.close();
        canvas.drawPath(path, mainPaint);
    }

}
