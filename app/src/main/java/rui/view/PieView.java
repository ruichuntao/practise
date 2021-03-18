package rui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.List;

import rui.bean.PieData;

public class PieView extends View {

    private int[] colors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};
    float startAngle;
    List<PieData> mData;
    int width, height;
    Paint paint = new Paint();

    public PieView(Context context) {
        this(context, null);
    }

    public PieView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PieView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float curStart = startAngle;
        canvas.translate(((float) (width / 2)), ((float) (height / 2)));
        float r = (float) (Math.min(width, height) / 2 * 0.8);
        RectF rect = new RectF(-r, -r, r, r);
        for (int i = 0; i < mData.size(); i++) {
            PieData pie = mData.get(i);
            paint.setColor(pie.getColor());
            canvas.drawArc(rect,curStart, pie.getAngle(),true,paint);
            curStart += pie.getAngle();
        }
    }

    public void setStartAngle(float startAngle) {
        this.startAngle = startAngle;
    }

    public void setData(List<PieData> mData) {
        this.mData = mData;
        initData(mData);
        invalidate();
    }

    private void initData(List<PieData> mData) {
        float sumValue = 0;
        for (int i = 0; i < mData.size(); i++) {
            PieData pie = mData.get(i);
            sumValue += pie.getValue();
            int j = i % colors.length;
            pie.setColor(colors[j]);
        }
        float sumAngle = 0;
        for (int i = 0; i < mData.size(); i++) {
            PieData pie = mData.get(i);
            float perCent = pie.getValue() / sumValue;
            float angle = perCent * 360;
            pie.setPer(perCent);
            pie.setAngle(angle);
            sumAngle += angle;
        }
    }
}
