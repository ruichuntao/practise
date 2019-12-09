package rui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import rui.todd.R;

public class CircleImage extends android.support.v7.widget.AppCompatImageView {

    private int shape;
    private final int SQUARE = 1;
    private final int RECTANGLE = 2;
    private final int CIRCLE = 3;
    private Paint mPaint;
    private Xfermode mPorterDuffXfermode;
    private int mSize;

    public CircleImage(Context context) {
        this(context, null);
    }

    public CircleImage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CircleImage, 0, 0);
        shape = array.getInt(R.styleable.CircleImage_shape, CIRCLE);
        initView();
        array.recycle();
    }

    private void initView() {
        if (shape == CIRCLE) {
            mPaint = new Paint();
            mPaint.setDither(true);
            mPaint.setAntiAlias(true);
            mPorterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        if (shape == CIRCLE) {
            Bitmap sourceBitmap = ((BitmapDrawable) drawable).getBitmap();
            Bitmap bitmap = resizeBitmap(sourceBitmap, getWidth(), getHeight());
            drawCircleBitmapByXfermode(canvas, bitmap);
//            drawCircleBitmapByShader(canvas, bitmap);
        }
    }

    private Bitmap resizeBitmap(Bitmap sourceBitmap, int dstWidth, int dstHeight) {
        int width = sourceBitmap.getWidth();
        int height = sourceBitmap.getHeight();

        float widthScale = ((float) dstWidth) / width;
        float heightScale = ((float) dstHeight) / height;

        //取最大缩放比
        float scale = Math.max(widthScale, heightScale);
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        return Bitmap.createBitmap(sourceBitmap, 0, 0, width, height, matrix, true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        mSize = Math.min(width, height);
        if (shape == SQUARE || shape == CIRCLE)
            setMeasuredDimension(mSize, mSize);
    }

    private void drawCircleBitmapByXfermode(Canvas canvas, Bitmap bitmap) {
        int sl = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        canvas.drawCircle(mSize / 2, mSize / 2, mSize / 2, mPaint);
        mPaint.setXfermode(mPorterDuffXfermode);
        canvas.drawBitmap(bitmap, 0, 0, mPaint);
        canvas.restoreToCount(sl);
    }

    private void drawCircleBitmapByShader(Canvas canvas, Bitmap bitmap) {
        BitmapShader shader = new BitmapShader(bitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
        mPaint.setShader(shader);
        canvas.drawCircle(mSize / 2, mSize / 2, mSize / 2, mPaint);
    }
}
