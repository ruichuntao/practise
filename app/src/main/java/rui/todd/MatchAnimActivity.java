package rui.todd;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MatchAnimActivity extends AppCompatActivity {
    @BindView(R.id.wave1)
    ImageView wave1;
    @BindView(R.id.wave2)
    ImageView wave2;
    @BindView(R.id.phone)
    ImageView phone;
    @BindView(R.id.frame)
    View frame;
    @BindView(R.id.frame1)
    View frame1;
//    @BindView(R.id.nswitch)
//    @BindView(R.id.switch_open)
//    TextView switch_open;
//    @BindView(R.id.swtich_close)
//    TextView swtich_close;

    private boolean isShow = false;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            wave2.setVisibility(isShow ? View.VISIBLE : View.GONE);
            isShow = !isShow;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_anim);
        ButterKnife.bind(this);
        Switch nswitch = findViewById(R.id.nswitch);
        TextView switch_open = findViewById(R.id.switch_open);
        TextView switch_close = findViewById(R.id.switch_close);
        nswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    switch_close.setVisibility(View.VISIBLE);
                    switch_open.setVisibility(View.GONE);
                }else {
                    switch_close.setVisibility(View.GONE);
                    switch_open.setVisibility(View.VISIBLE);
                }
            }
        });
    }


    @OnClick(R.id.bt)
    public void click() {
        ObjectAnimator frameAnimX1 = ObjectAnimator.ofFloat(frame, View.SCALE_X, 1, (float) 1.5, (float) 2);
        ObjectAnimator frameAnimY1 = ObjectAnimator.ofFloat(frame, View.SCALE_Y, 1, (float) 1.5, (float) 2);
        ObjectAnimator frameAnimA1 = ObjectAnimator.ofFloat(frame, View.ALPHA, 1, (float) 0.5, (float) 0.1);
        frameAnimX1.setRepeatCount(ObjectAnimator.INFINITE);
        frameAnimY1.setRepeatCount(ObjectAnimator.INFINITE);
        frameAnimA1.setRepeatCount(ObjectAnimator.INFINITE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                frame1.setVisibility(View.VISIBLE);
            }
        }, 1000);
        frameAnimX1.setDuration(2000);
        frameAnimY1.setDuration(2000);
        frameAnimA1.setDuration(2000);
        frameAnimX1.start();
        frameAnimY1.start();
        frameAnimA1.start();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(1);
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 0, 1000);
    }
}
