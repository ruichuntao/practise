package rui.todd;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rui.bean.LoveAnim;

public class LoveAnimActivity extends AppCompatActivity {
    @BindView(R.id.iv)
    ImageView iv;

    @BindView(R.id.bt)
    Button bt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_love_anim);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt)
    public void btclick(){
        LoveAnim loveAnim = new LoveAnim(iv);
        List<Animator> animators = new ArrayList<>();
        animators.add(loveAnim.anim((float) 1, (float) 0.8,200));
        animators.add(loveAnim.anim((float) 0.8, (float) 0.9,600));
        animators.add(loveAnim.anim((float) 0.9, (float) 0.8,200));
        animators.add(loveAnim.anim((float) 0.8, (float) 0.9,500));
        animators.add(loveAnim.anim((float) 0.9, (float) 1.07,100));
        animators.add(loveAnim.anim((float) 1.07, (float) 1,400));
        AnimatorSet set = new AnimatorSet();
        set.playSequentially(animators);
        set.start();
    }
}
