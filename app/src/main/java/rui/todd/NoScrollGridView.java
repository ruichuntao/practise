package rui.todd;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by lgong on 2015/7/14.
 */
public class NoScrollGridView extends GridView {
    protected boolean isOnMeasuring = false;

    public NoScrollGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置不滚动
     */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        isOnMeasuring = true;
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        isOnMeasuring = false;
        super.onLayout(changed, l, t, r, b);
    }

    public boolean isOnMeasuring() {
        return isOnMeasuring;
    }
}
