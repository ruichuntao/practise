package rui.todd;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MatchNewAnimActivity extends AppCompatActivity {

    EditText ed;
    TextView tv;
    ImageView w1;
    ImageView w2;
    ImageView w3;
    ImageView w4;

    private static final String TAG = "MatchNewAnimActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_new_anim);
        ed = findViewById(R.id.ed1);
        tv = findViewById(R.id.tv1);
        w1 = findViewById(R.id.w1);
        w2 = findViewById(R.id.w2);
        w3 = findViewById(R.id.w3);
        w4 = findViewById(R.id.w4);
        animationStart(w1, 3000);
        animationStart(w2, 4000);
        animationStart(w3, 5000);
        animationStart(w4, 6000);
    }

    private void animationStart(ImageView imageView, int time) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_X, 0, -dp2Px(190));
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(-1);
        animator.setDuration(time);
        animator.start();
    }

    public void test(View view) {
        tv.setText(ed.getText());
    }

    public float dp2Px(float dp) {
        final float scale = this.getResources().getDisplayMetrics().density; //当前屏幕密度因子
        return (dp * scale + 0.5f);
    }
}
