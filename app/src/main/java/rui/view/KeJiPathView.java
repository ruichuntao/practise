package rui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import rui.Interface.IkeJiPath;
import rui.todd.R;

public class KeJiPathView extends AppCompatImageView {
    private static final String TAG = "KeJiPathView";
    private Bitmap bitmap;
    Paint paint;
    Path path;
    float[] pos = new float[2];
    List<XY> list = new ArrayList<>();
    PathMeasure measure;
    private final float length = 35; // 滑动长度
    private final int size = 60; // 图片大小
    private float glength;
    private List<Path> paths = new ArrayList<>();
    int c = 0;
    private List<XY> xies = new ArrayList<>();
    private List<XY> dajihe = new ArrayList<>();
    private boolean isDown = false;
    private IkeJiPath ikeJiPath;
    private int sum = 0;


    private Handler handler1 = new Handler(new WeakReference<Handler.Callback>(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            return false;
        }
    }).get());


    public void setIkeJiPath(IkeJiPath ikeJiPath) {
        this.ikeJiPath = ikeJiPath;
    }

    class XY {
        float x;
        float y;
    }

    public List<XY> getXies() {
        return xies;
    }

    public List<Path> getPaths() {
        return paths;
    }

    public List<XY> getDajihe() {
        dajihe.clear();
        if (paths.size() != 0) {
            for (int j = 0; j < paths.size(); j++) {
                dajihe.add(xies.get(j));
                measure.setPath(paths.get(j), false);
                glength = measure.getLength();
                float count = glength / dp2Px(length);
                for (int i = 1; i < count; i++) {
                    measure.getPosTan(dp2Px(length) * i, pos, null);
                    XY xy = new XY();
                    xy.x = pos[0];
                    xy.y = pos[1];
                    dajihe.add(xy);
                }
//                dajihe.add(jihe);
            }
        }
        return dajihe;
    }

    public KeJiPathView(Context context) {
        this(context, null);
    }

    public KeJiPathView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public KeJiPathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        measure = new PathMeasure();
        if (bitmap == null) {
            bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.about_lespark);
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int sum = 0;
        if (xies.size() != 0) {
            for (int i = 0; i < xies.size(); i++) {
                if (xies.get(i).x < getWidth() - dp2Px(60) && xies.get(i).y > 0 && xies.get(i).y < getHeight() - dp2Px(60)) {
                    canvas.drawBitmap(bitmap, xies.get(i).x, xies.get(i).y, null);
                    sum += 1;
                }
            }
        }
        if (paths.size() != 0) {
            for (int j = 0; j < paths.size(); j++) {
                measure.setPath(paths.get(j), false);
                glength = measure.getLength();
                float count = glength / dp2Px(length);
                for (int i = 0; i < count; i++) {
                    measure.getPosTan(dp2Px(length) * i, pos, null);
                    if (pos[0] < getWidth() - dp2Px(60) && pos[1] > 0 && pos[1] < getHeight() - dp2Px(60)) {
                        canvas.drawBitmap(bitmap, pos[0], pos[1], null);
                        sum++;
                    }
                }

            }
        }
        ikeJiPath.pointCount(sum);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
//                    isDown = true;
                Path path = new Path();
                paths.add(path);
                path.moveTo(event.getX(), event.getY());
                XY xy = new XY();
                xy.x = event.getX();
                xy.y = event.getY();
                xies.add(xy);
                postInvalidate();

                break;
//                postInvalidate();
//                break;
            case MotionEvent.ACTION_MOVE:
                paths.get(paths.size() - 1).lineTo(event.getX(), event.getY());
                postInvalidate();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    public int px2Dp(float px) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    public  int dp2Px(float dp) {
        final float scale = getContext().getResources().getDisplayMetrics().density; //当前屏幕密度因子
        return (int) (dp * scale + 0.5f);
    }
}
