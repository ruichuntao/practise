package rui.todd;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class SearchAnimationActivity extends AppCompatActivity {


    private EditText searchEditText;
    private ImageView back;
    private ImageView searchClear;
    private TextView searchCancel;
    private float qrWidth;
    private float cWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_animation);
        searchEditText = findViewById(R.id.search_editText);
        back = findViewById(R.id.back);
        searchClear = findViewById(R.id.search_clear);
        searchCancel = findViewById(R.id.search_cancel);
        searchCancel.setVisibility(View.GONE);

        float scale = getResources().getDisplayMetrics().density;
        qrWidth = 18 * scale + 0.5f;
        cWidth = 50 * scale + 0.5f;
        searchEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchEditText.setFocusable(true);
                searchEditText.setFocusableInTouchMode(true);
                searchEditText.requestFocus();
                searchEditText.requestFocusFromTouch();
                searchEditText.setCursorVisible(true);
                searchCancel.setVisibility(View.VISIBLE);
                ValueAnimator animator = ValueAnimator.ofFloat(qrWidth, 0).setDuration(250);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        float value = (float) valueAnimator.getAnimatedValue();
                        ViewGroup.LayoutParams layoutParams = back.getLayoutParams();
                        layoutParams.width = (int) value;
                        back.setLayoutParams(layoutParams);
                    }
                });
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                    }
                });
                animator.start();
                ValueAnimator animator1 = ValueAnimator.ofFloat(0, cWidth).setDuration(250);
                animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        float value = (float) valueAnimator.getAnimatedValue();
                        ViewGroup.LayoutParams layoutParams = searchCancel.getLayoutParams();
                        layoutParams.width = (int) value;
                        searchCancel.setLayoutParams(layoutParams);
                    }
                });
                animator1.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                    }
                });
                animator1.start();
            }
        });
        back.setOnClickListener(v -> {
            finish();
        });
        searchCancel.setOnClickListener(v -> {
            searchEditText.setCursorVisible(false);
            searchEditText.setText("");
            ValueAnimator animator = ValueAnimator.ofFloat(0, qrWidth).setDuration(250);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float value = (float) valueAnimator.getAnimatedValue();
                    ViewGroup.LayoutParams layoutParams = back.getLayoutParams();
                    layoutParams.width = (int) value;
                    back.setLayoutParams(layoutParams);
                }
            });
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                }
            });
            animator.start();
            ValueAnimator animator1 = ValueAnimator.ofFloat(cWidth, 0).setDuration(250);
            animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float value = (float) valueAnimator.getAnimatedValue();
                    ViewGroup.LayoutParams layoutParams = searchCancel.getLayoutParams();
                    layoutParams.width = (int) value;
                    searchCancel.setLayoutParams(layoutParams);
                }
            });
            animator1.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);

                }
            });
            animator1.start();
        });
    }
}
