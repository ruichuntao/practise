package rui.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import rui.Interface.IkeJiPlay;
import rui.todd.R;

public class KejiPlayView extends ViewGroup {

    private Bitmap bitmap;
    private List<KeJiPathView.XY> dajihe = new ArrayList<>();
    int count = 0;
    int xcount = 0;
    private final int size = 30; // 图片大小半径
    //    private final int size = 60; // 图片大小
    private List<ImageView> imageViews = new ArrayList<>();

    public KejiPlayView(Context context) {
        super(context);
    }

    public IkeJiPlay ikeJiPlay;

    public void setIkeJiPlay(IkeJiPlay ikeJiPlay) {
        this.ikeJiPlay = ikeJiPlay;
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == -1) {
                ObjectAnimator animatorTx = ObjectAnimator.ofFloat(KejiPlayView.this, View.SCALE_X, 1.0f, 1.6f);
                ObjectAnimator animatorTy = ObjectAnimator.ofFloat(KejiPlayView.this, View.SCALE_Y, 1.0f, 1.6f);
                ObjectAnimator animatorTa = ObjectAnimator.ofFloat(KejiPlayView.this, View.ALPHA, 1, 0);
                animatorTa.setDuration(500);
                animatorTx.setDuration(500);
                animatorTy.setDuration(500);
                animatorTa.setStartDelay(300);
                animatorTx.setStartDelay(300);
                animatorTy.setStartDelay(300);
                animatorTa.start();
                animatorTx.start();
                animatorTy.start();
                animatorTa.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        ikeJiPlay.animEnd();
                    }
                });
                return false;
            } else {
                imageViews.get(msg.what).setVisibility(VISIBLE);
                ObjectAnimator animatorY = ObjectAnimator.ofFloat(imageViews.get(msg.what), View.SCALE_Y, 1.5f, 1);
                ObjectAnimator animatorX = ObjectAnimator.ofFloat(imageViews.get(msg.what), View.SCALE_X, 1.5f, 1);
                ObjectAnimator animatorA = ObjectAnimator.ofFloat(imageViews.get(msg.what), View.ALPHA, 0.5f, 1);
                animatorX.setDuration(300);
                animatorY.setDuration(300);
                animatorA.setDuration(100);
                animatorA.start();
                animatorX.start();
                animatorY.start();
                return false;
            }
        }
    });

    public void setDajihe(List<KeJiPathView.XY> dajihe) {
        this.dajihe = dajihe;
        xcount = 0;
        count = 0;
        xcount = dajihe.size();
        imageViews.clear();
        Timer timer = new Timer();
        for (int i = 0; i < xcount; i++) {
            ImageView view = new ImageView(getContext());
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(dp2Px(size * 2), dp2Px(size * 2));
            view.setLayoutParams(params);
            view.setBackground(getContext().getResources().getDrawable(R.drawable.keji));
            view.layout((int) (dajihe.get(i).x), (int) (dajihe.get(i).y), (int) (dajihe.get(i).x + dp2Px(size * 2)), (int) (dajihe.get(i).y + dp2Px(size * 2)));
            view.setVisibility(GONE);
            imageViews.add(view);
            addView(view);
        }
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (count == xcount) {
                    timer.cancel();
                    cancel();
                    count = 0;
                    handler.sendEmptyMessage(-1);
                } else {
                    handler.sendEmptyMessage(count);
                    count++;
                }
            }
        };
        timer.schedule(task, 0, 300);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        if (dajihe.size() != 0) {
//            int li = 0;
//            for (int j = 0; j < dajihe.size(); j++) {
//                if (li == count) {
//                    return;
//                } else {
//                    canvas.drawBitmap(bitmap, ((KeJiPathView.XY) dajihe.get(j)).x, ((KeJiPathView.XY) dajihe.get(j)).y, null);
//                }
//                li++;
//            }
//        }

    }

    public KejiPlayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (bitmap == null) {
            bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.keji);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    public int dp2Px(float dp) {
        final float scale = getContext().getResources().getDisplayMetrics().density; //当前屏幕密度因子
        return (int) (dp * scale + 0.5f);
    }
}
