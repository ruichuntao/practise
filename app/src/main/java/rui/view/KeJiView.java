package rui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import rui.todd.R;

public class KeJiView extends ViewGroup {
    float startX = 0;
    float startY = 0;
    Paint paint;
    private Bitmap baseBitmap;
    private Canvas canvass;
    ImageView keji;
    int yuShu = 0;
    Bitmap bitmap;
    float xx = 0;
    float yy = 0;
    Canvas c;
    ArrayList<XY> p = new ArrayList<>();

    class XY {
         float x;
         float y;
    }

    public KeJiView(Context context) {
        super(context);
    }

    public KeJiView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public KeJiView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (bitmap == null) {
            bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.keji);
        }
        if (p.size() != 0) {
            for (int i = 0; i < p.size(); i++) {
                canvas.drawBitmap(bitmap, p.get(i).x, p.get(i).y, null);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();

//                if (paint ==null){
//                    paint = new Paint();
//                    paint.setColor(Color.parseColor("#fa3e54"));
//                    paint.setStyle(Paint.Style.FILL_AND_STROKE);
//                    paint.setStrokeCap(Paint.Cap.ROUND);
//                    paint.setAntiAlias(true);
//                    paint.setStrokeWidth(10);


//                if (keji == null) {
//                    keji = new ImageView(getContext());
//                    KeJiView.LayoutParams params = getLayoutParams();
//                    params.height = dp2Px(57);
//                    params.width = dp2Px(57);
//                    keji.setLayoutParams(params);
//                    keji.setImageDrawable(getContext().getResources().getDrawable(R.drawable.diamond));
//                }
//                ImageView view = new ImageView(getContext());
//                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(dp2Px(57), dp2Px(57));
//                view.setLayoutParams(params);
//                view.setBackground(getContext().getResources().getDrawable(R.drawable.keji));
//                view.layout((int) (startX - dp2Px(29)), (int) (startY - dp2Px(29)), (int) (startX + dp2Px(29)), (int) (startY + dp2Px(29)));
//                addView(view);
                XY xy = new XY();
                xy.x = startX - dp2Px(29);
                xy.y = startY - dp2Px(29);
//                xx = startX - dp2Px(29);
//                yy = startY - dp2Px(29);
                p.add(xy);
                postInvalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                float x = event.getX();
                float y = event.getY();
//                if (px2Dp((float) Math.sqrt(Math.pow(x - startX, 2) + Math.pow(y - startY, 2))) > 20) {
//                    ImageView view1 = new ImageView(getContext());
//                    ViewGroup.LayoutParams params1 = new ViewGroup.LayoutParams(dp2Px(57), dp2Px(57));
//                    params1.height = dp2Px(57);
//                    params1.width = dp2Px(57);
//                    view1.setLayoutParams(params1);
//                    view1.setBackground(getContext().getResources().getDrawable(R.drawable.diamond));
//                    view1.layout((int) (x - dp2Px(29)), (int) (y - dp2Px(29)), (int) (x + dp2Px(29)), (int) (y + dp2Px(29)));
//                    addView(view1);
//                    startX = x;
//                    startY = y;
//                    postInvalidate();
//                }
//
                float num = px2Dp((float) Math.sqrt(Math.pow(x - startX, 2) + Math.pow(y - startY, 2)));
                float count = (num) / 35;
//                yuShu = (num + yuShu) % 25;
                float perX = Math.abs((x - startX) / count);
                float perY = Math.abs((y - startY) / count);
                if (count > 1) {
                    for (int i = 0; i < count; i++) {
                        if (x - startX > 0) {
                            if (y - startY > 0) {
//                                ImageView view1 = new ImageView(getContext());
//                                ViewGroup.LayoutParams params1 = new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//                                params1.height = dp2Px(57);
//                                params1.width = dp2Px(57);
//                                view1.setLayoutParams(params1);
//                                view1.setBackground(getContext().getResources().getDrawable(R.drawable.keji));
//                                view1.layout((int) (startX - dp2Px(29) + perX), (int) (startY - dp2Px(29) + perY), (int) (startX + dp2Px(29) + perX), (int) (startY + dp2Px(29) + perY));
                                startX = startX + perX;
                                startY = startY + perY;
//                                addView(view1);
                                XY xy1 = new XY();
                                xy1.x = startX - dp2Px(29);
                                xy1.y = startY - dp2Px(29);
//                xx = startX - dp2Px(29);
//                yy = startY - dp2Px(29);
                                p.add(xy1);
                                postInvalidate();
                            } else {
//                                ImageView view1 = new ImageView(getContext());
//                                ViewGroup.LayoutParams params1 = new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//                                params1.height = dp2Px(57);
//                                params1.width = dp2Px(57);
//                                view1.setLayoutParams(params1);
//                                view1.setBackground(getContext().getResources().getDrawable(R.drawable.keji));
//                                view1.layout((int) (startX - dp2Px(29) + perX), (int) (startY - dp2Px(29) - perY), (int) (startX + dp2Px(29) + perX), (int) (startY + dp2Px(29) - perY));
                                startX = startX + perX;
                                startY = startY - perY;
//                                addView(view1);
                                XY xy1 = new XY();
                                xy1.x = startX - dp2Px(29);
                                xy1.y = startY - dp2Px(29);
//                xx = startX - dp2Px(29);
//                yy = startY - dp2Px(29);
                                p.add(xy1);
                                postInvalidate();
                            }
                        } else {
                            if (y - startY > 0) {
//                                ImageView view1 = new ImageView(getContext());
//                                ViewGroup.LayoutParams params1 = new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//                                params1.height = dp2Px(57);
//                                params1.width = dp2Px(57);
//                                view1.setLayoutParams(params1);
//                                view1.setBackground(getContext().getResources().getDrawable(R.drawable.keji));
//                                view1.layout((int) (startX - dp2Px(29) - perX), (int) (startY - dp2Px(29) + perY), (int) (startX + dp2Px(29) - perX), (int) (startY + dp2Px(29) + perY));
                                startX = startX - perX;
                                startY = startY + perY;
//                                addView(view1);
                                XY xy1 = new XY();
                                xy1.x = startX - dp2Px(29);
                                xy1.y = startY - dp2Px(29);
//                xx = startX - dp2Px(29);
//                yy = startY - dp2Px(29);
                                p.add(xy1);
                                postInvalidate();
                            } else {
//                                ImageView view1 = new ImageView(getContext());
//                                ViewGroup.LayoutParams params1 = new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//                                params1.height = dp2Px(57);
//                                params1.width = dp2Px(57);
//                                view1.setLayoutParams(params1);
//                                view1.setBackground(getContext().getResources().getDrawable(R.drawable.keji));
//                                view1.layout((int) (startX - dp2Px(29) - perX), (int) (startY - dp2Px(29) - perY), (int) (startX + dp2Px(29) - perX), (int) (startY + dp2Px(29) - perY));
                                startX = startX - perX;
                                startY = startY - perY;
//                                addView(view1);
                                XY xy1 = new XY();
                                xy1.x = startX - dp2Px(29);
                                xy1.y = startY - dp2Px(29);
//                xx = startX - dp2Px(29);
//                yy = startY - dp2Px(29);
                                p.add(xy1);
                                postInvalidate();
                            }
                        }
                    }
//                    startX = x;
//                    startY = y;
                } else if (count == 1) {
//                    ImageView view1 = new ImageView(getContext());
//                    ViewGroup.LayoutParams params1 = new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//                    params1.height = dp2Px(57);
//                    params1.width = dp2Px(57);
//                    view1.setLayoutParams(params1);
//                    view1.setBackground(getContext().getResources().getDrawable(R.drawable.keji));
//                    view1.layout((int) (x - dp2Px(29)), (int) (y - dp2Px(29)), (int) (x + dp2Px(29)), (int) (y + dp2Px(29)));
//                    addView(view1);
                    startX = x;
                    startY = y;
                    XY xy1 = new XY();
                    xy1.x = startX - dp2Px(29);
                    xy1.y = startY - dp2Px(29);
//                xx = startX - dp2Px(29);
//                yy = startY - dp2Px(29);
                    p.add(xy1);
                    postInvalidate();
                }
//                }
//                setImageBitmap(baseBitmap);
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

    public int dp2Px(float dp) {
        final float scale = getContext().getResources().getDisplayMetrics().density; //当前屏幕密度因子
        return (int) (dp * scale + 0.5f);
    }
}
