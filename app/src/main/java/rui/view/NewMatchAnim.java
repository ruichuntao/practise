package rui.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import rui.todd.R;

public class NewMatchAnim extends FrameLayout {

    ImageView circle;
    ImageView wenhao;
    private ProgressView progressView;

    public NewMatchAnim(Context context) {
        this(context, null);
    }

    public NewMatchAnim(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NewMatchAnim(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View v = LayoutInflater.from(context).inflate(R.layout.new_match_anim, null);
        circle = v.findViewById(R.id.circle);
        wenhao = v.findViewById(R.id.wenhao);
        progressView = v.findViewById(R.id.progress_view);
        addView(v);
        ObjectAnimator animator = ObjectAnimator.ofFloat(circle, View.ROTATION, 0, 360);
        animator.setDuration(1000);
        LinearInterpolator interpolator = new LinearInterpolator();
        animator.setInterpolator(interpolator);
        animator.setRepeatCount(-1);
        animator.start();
        wenhao.setOnClickListener(v1 -> {
            animator.end();
            circle.setVisibility(GONE);
            progressView.setVisibility(VISIBLE);
            progressView.startTurn();
        });
    }
    public void setCircle(String url, Drawable drawable) {
        //设置头像
        circle.setImageDrawable(drawable);
    }

    public float dp2Px(float dp) {
        final float scale = getContext().getResources().getDisplayMetrics().density; //当前屏幕密度因子
        return  (dp * scale + 0.5f);
    }
}
