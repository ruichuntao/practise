package rui.todd;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import rui.utils.ScreenUtils;
import rui.view.TouchView;

public class BaseActivity extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    private static View floatView;
    public static int marginTop = 0;
    public static int marginLeft = 0;
    ImageView play;
    ImageView close;
    boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        SparseArray<String> array = new SparseArray<>();
        if (floatView == null) {
            floatView = LayoutInflater.from(this).inflate(R.layout.floating_window_layout, null);
        }
        floatView.setVisibility(View.GONE);
        play = floatView.findViewById(R.id.play);
        close = floatView.findViewById(R.id.close);
        close.setOnClickListener(v -> {
            flag = true;
            removeFloatView();
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        addFloatView();
    }


    // 添加悬浮窗
    protected void addFloatView() {
        if (floatView == null || flag) return;
        if (floatView.getParent() != null)
            ((ViewGroup) floatView.getParent()).removeView(floatView);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                ScreenUtils.dp2px(123), ScreenUtils.dp2px(46));
        ((FrameLayout) getWindow().getDecorView().findViewById(android.R.id.content)).addView(floatView, params);
    }

    //移除float悬浮窗
    protected void removeFloatView() {
        if (floatView == null || floatView.getParent() == null) return;
        floatView.setVisibility(View.GONE);
    }
}