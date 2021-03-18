package rui.dialog;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.airbnb.lottie.LottieAnimationView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import rui.todd.R;
import rui.utils.UtilsKt;
import rui.view.ScrollViewExtend;

public class ExpEvaluationDialog extends DialogFragment {

    private View mContentView;
    private TextView mTvChildTip;
    private TextView mTvTeacherTip;
    private LottieAnimationView mLottieFace;
    private View mLlTeacher;
    // 第一阶段动画
    private ObjectAnimator mAnimationOne;
    // 第二阶段动画
    private ObjectAnimator mAnimationTwo;
    private ImageView mIvStar;
    private LinearLayout mLlLabel;
    private ScrollViewExtend svContent;
    // 第一阶段移动留出距离
    final float mFaceDistance = UtilsKt.dp2px(113);
    // 第二阶段移动留出距离
    final float mStarDistance = UtilsKt.dp2px(96);
    private LinearLayout mLlChild;

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        getDialog().setCanceledOnTouchOutside(false);
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.BOTTOM;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(params);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.getDecorView().setPadding(0, (int) UtilsKt.dp2px(13), 0, 0);
    }

    private final View.OnClickListener mFaceClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int tag = (int) v.getTag();
            String fileName = "smiley_" + tag + ".json";
            mLottieFace.setAnimation(fileName);
            mLottieFace.setRepeatCount(0);
            mLottieFace.playAnimation();
            mTvChildTip.setTextColor(Color.parseColor("#1e1e1e"));
            String txt = "";
            switch (tag) {
                case 1:
                    txt = "非常不喜欢";
                    break;
                case 2:
                    txt = "不喜欢";
                    break;
                case 3:
                    txt = "一般";
                    break;
                case 4:
                    txt = "喜欢";
                    break;
                case 5:
                    txt = "非常喜欢";
                    break;
                default:
                    txt = "让孩子为这节课打分吧";
                    break;
            }
            mTvChildTip.setText(txt);
            if (mAnimationOne == null) {
                mAnimationOne = ObjectAnimator.ofFloat(mContentView, "translationY", mContentView.getHeight() - mTvChildTip.getBottom() - mFaceDistance, mContentView.getHeight() - mLlTeacher.getBottom() - mStarDistance);
                mAnimationOne.setDuration(500);
                mAnimationOne.start();
                mAnimationOne.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(mLlTeacher, "alpha", 0f, 1f);
                        alphaAnimation.setDuration(500);
                        alphaAnimation.start();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
            }
        }
    };

    private final View.OnClickListener mStarClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int tag = (int) v.getTag();
            int resourceId = 0;
            mTvTeacherTip.setTextColor(Color.parseColor("#1e1e1e"));
            String txt = "";
            switch (tag) {
                case 1:
                    txt = "非常不满意";
                    resourceId = R.drawable.star_1;
                    break;
                case 2:
                    txt = "不满意";
                    resourceId = R.drawable.star_2;
                    break;
                case 3:
                    txt = "一般";
                    resourceId = R.drawable.star_3;
                    break;
                case 4:
                    txt = "满意";
                    resourceId = R.drawable.star_4;
                    break;
                case 5:
                    txt = "非常满意";
                    resourceId = R.drawable.star_5;
                    break;
                default:
                    txt = "为老师评星吧";
                    resourceId = R.drawable.star_default;
                    break;
            }
            mTvTeacherTip.setText(txt);
            mIvStar.setImageResource(resourceId);
            if (mAnimationTwo == null) {
                mAnimationTwo = ObjectAnimator.ofFloat(mContentView, "translationY", mContentView.getHeight() - mLlTeacher.getBottom() - mStarDistance, 0);
                mAnimationTwo.setDuration(500);
                mAnimationTwo.start();
                mAnimationTwo.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(mLlLabel, "alpha", 0f, 1f);
                        alphaAnimation.setDuration(500);
                        alphaAnimation.start();
                        svContent.setScrollEnabled(true);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mContentView == null)
            mContentView = inflater.inflate(R.layout.dialog_evaluation_layout, container);
        mLottieFace = mContentView.findViewById(R.id.face);
        mIvStar = mContentView.findViewById(R.id.iv_star);
        LinearLayout llBtn = mContentView.findViewById(R.id.ll_btn);
        LinearLayout llStarBtn = mContentView.findViewById(R.id.ll_star_btn);
        svContent = mContentView.findViewById(R.id.sv_content);
        svContent.setScrollEnabled(false);
        for (int i = 1; i <= 5; i++) {
            View button = new View(getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT);
            params.weight = 1;
            button.setTag(i);
            button.setOnClickListener(mFaceClickListener);
            llBtn.addView(button, params);
            button = new View(getContext());
            params.weight = 1;
            button.setTag(i);
            button.setOnClickListener(mStarClickListener);
            llStarBtn.addView(button, params);
        }
        mTvChildTip = mContentView.findViewById(R.id.tv_tip);
        mTvTeacherTip = mContentView.findViewById(R.id.tv_teacher_tip);
        mLlTeacher = mContentView.findViewById(R.id.ll_tea);
        mLlLabel = mContentView.findViewById(R.id.ll_label);
        mLlChild = mContentView.findViewById(R.id.ll_child);
        ImageView ivClose = mContentView.findViewById(R.id.iv_close);
        TagFlowLayout difficultyLabel = mContentView.findViewById(R.id.difficulty_label);
        TagFlowLayout evaluationLabel = mContentView.findViewById(R.id.evaluation_label);
        final List<String> data = new ArrayList<>();
        data.add("太难了");
        data.add("刚刚好");
        data.add("太简单");
        DifficultyTagAdapter adapter = new DifficultyTagAdapter(data);
        difficultyLabel.setAdapter(adapter);
        final List<String> list = new ArrayList<>();
        list.add("面无表情");
        list.add("面无表情");
        list.add("面无表情");
        list.add("面无表情");
        list.add("面无表情");
        list.add("面无表情");
        list.add("面无表情");
        list.add("面无表情");
        list.add("面无表情");
        adapter = new DifficultyTagAdapter(list);
        evaluationLabel.setAdapter(adapter);
        ivClose.setOnClickListener(v -> dismiss());
//        mLlChild.setVisibility(View.GONE);
//        mLlTeacher.setAlpha(1);
        mContentView.post(() -> {
            ObjectAnimator animator = ObjectAnimator.ofFloat(mContentView, "translationY", mContentView.getHeight(), mContentView.getHeight() - mTvChildTip.getBottom() -
                    mFaceDistance);
            animator.setDuration(500);
            animator.start();
        });
        return mContentView;
    }


    private static class DifficultyTagAdapter extends TagAdapter<String> {

        public DifficultyTagAdapter(List<String> datas) {
            super(datas);
        }

        @Override
        public View getView(FlowLayout parent, int position, String s) {
            final Context context = parent.getContext();
            TextView tv = (TextView) LayoutInflater.from(context).inflate(R.layout.call_back_item, parent, false);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
            tv.setTextColor(0xff1e1e1e);
            tv.setText(s);
            ViewGroup.MarginLayoutParams params =
                    new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
            params.topMargin = (int) UtilsKt.dp2px(8);
            params.rightMargin = (int) UtilsKt.dp2px(8);
            tv.setLayoutParams(params);
            return tv;
        }
    }
}
