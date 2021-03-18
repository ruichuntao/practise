package rui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class ScrollViewExtend extends ScrollView {

	private boolean isScrollEnabled = true;


	public ScrollViewExtend(Context context, AttributeSet attrs) {
		super(context, attrs);

	}

	// 触摸没有反应就可以了
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return this.isScrollEnabled && super.onTouchEvent(event);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) {
		return this.isScrollEnabled && super.onInterceptTouchEvent(event);
	}

	public void setScrollEnabled(boolean b) {
		this.isScrollEnabled = b;
	}
}
