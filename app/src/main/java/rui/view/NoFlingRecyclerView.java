package rui.view;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;

public class NoFlingRecyclerView extends RecyclerView {
    public NoFlingRecyclerView(@NonNull Context context) {
        super(context);
    }

    public NoFlingRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NoFlingRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean fling(int velocityX, int velocityY) {
        return super.fling(velocityX,velocityY);
    }
}
