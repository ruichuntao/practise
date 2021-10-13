package rui.todd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.airbnb.lottie.LottieAnimationView;

import rui.utils.UtilsKt;

import static android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;

public class RateControlActivity extends AppCompatActivity {

    private HorizontalScrollView two;
    private HorizontalScrollView one;
    private LottieAnimationView front;
    private LottieAnimationView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//remove title bar  即隐藏标题栏
        getSupportActionBar().hide();// 隐藏ActionBar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//remove notification bar  即全屏
        setContentView(R.layout.activity_rate_control);
        two = findViewById(R.id.two);
        one = findViewById(R.id.one);
        front = findViewById(R.id.front);
        back = findViewById(R.id.back);
        front.post(() -> {
            final int v1 = front.getWidth() * 4 / 5 - UtilsKt.getScreenWidth();
            final int v2 = front.getWidth() - UtilsKt.getScreenWidth();
            final float rate = (float) v1 / v2;
            two.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
                one.scrollTo((int) (scrollX * rate), 0);
            });
        });
    }
}