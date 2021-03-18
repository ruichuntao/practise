package rui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class TouchView2 extends View {
    public TouchView2(Context context) {
        super(context);
    }

    public TouchView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private static final String TAG = "TouchView2";

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "ACTION_DOWN: " );
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "ACTION_UP: " );
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "ACTION_MOVE: " );
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e(TAG, "ACTION_CANCEL: " );
                break;
        }
        return true;

    }
}
