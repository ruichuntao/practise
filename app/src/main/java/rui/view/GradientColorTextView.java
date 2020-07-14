package rui.view;

import android.content.Context;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import androidx.appcompat.widget.AppCompatTextView;
import android.util.AttributeSet;

public class GradientColorTextView extends AppCompatTextView {
    int s[] = new int[]{0xFFFFEABA, 0xFFDFBB82, 0xFFBE8B49};
    float f[] = new float[]{0,0.5f,1};

    public GradientColorTextView(Context context) {
        super(context);
    }

    public GradientColorTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GradientColorTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            getPaint().setShader(new LinearGradient(
                    0, 0, getWidth(), getHeight(),
                    s, f,
                    Shader.TileMode.CLAMP));
        }
    }
}
