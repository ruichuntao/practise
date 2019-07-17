package rui.bean;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.widget.ImageView;

public class LoveAnim {
    private ImageView iv;

    public LoveAnim(ImageView iv) {
        this.iv = iv;
    }

    public AnimatorSet anim(float before,float after,int duration){
        ObjectAnimator animator = ObjectAnimator.ofFloat(iv,"scaleX",before,after);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(iv,"scaleY",before,after);
        animator.setDuration(duration);
        animator1.setDuration(duration);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(animator,animator1);
        return set;
    }
}
