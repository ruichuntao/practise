package rui.dialog;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import rui.todd.R;
import rui.utils.UtilsKt;

public class ExpEvaluatedDialog extends DialogFragment {
    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        getDialog().setCanceledOnTouchOutside(false);
        window.setWindowAnimations(R.style.dialogWindowAnim);
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.BOTTOM;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(params);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_evaluated_layout, container);
        view.post(() -> {
            ObjectAnimator animator;
            animator = ObjectAnimator.ofFloat(view, "translationY", view.getHeight(), 0);
            animator.setDuration(500);
            animator.start();
        });
        String s = "11";
        return view;
    }
}
