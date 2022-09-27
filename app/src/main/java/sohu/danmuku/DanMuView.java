package sohu.danmuku;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import sohu.danmuku.control.DanMuController;
import sohu.danmuku.model.DanMuModel;
import sohu.danmuku.view.IDanMuParent;
import sohu.danmuku.view.OnDanMuParentViewTouchCallBackListener;
import sohu.danmuku.view.OnDanMuViewTouchListener;


public class DanMuView extends View implements IDanMuParent {

    private DanMuController danMuController;
    private volatile ArrayList<OnDanMuViewTouchListener> onDanMuViewTouchListeners;
    private OnDanMuParentViewTouchCallBackListener onDanMuParentViewTouchCallBackListener;
    private boolean drawFinished = false;

    private final Object lock = new Object();

    public DanMuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @Override
    public void jumpQueue(List<DanMuModel> danMuViews) {
        danMuController.jumpQueue(danMuViews);
    }

    @Override
    public void addAllTouchListener(List<DanMuModel> onDanMuTouchCallBackListeners) {
        this.onDanMuViewTouchListeners.addAll(onDanMuTouchCallBackListeners);
    }

    public DanMuView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        onDanMuViewTouchListeners = new ArrayList<>();
        if (danMuController == null) {
            danMuController = new DanMuController(this);
        }
    }

    public void prepare() {
        danMuController.prepare();
    }

    public void release() {
        onDetectHasCanTouchedDanMusListener = null;
        onDanMuParentViewTouchCallBackListener = null;
        clear();
        if (danMuController != null) {
            danMuController.release();
        }
        danMuController = null;
    }

    private void addDanMuView(final DanMuModel danMuView) {
        if (danMuView == null) {
            return;
        }
        if (danMuController != null) {
            if (danMuView.enableTouch()) {
                onDanMuViewTouchListeners.add(danMuView);
            }
            danMuController.addDanMuView(-1, danMuView);
        }
    }

    public void setOnDanMuParentViewTouchCallBackListener(OnDanMuParentViewTouchCallBackListener onDanMuParentViewTouchCallBackListener) {
        this.onDanMuParentViewTouchCallBackListener = onDanMuParentViewTouchCallBackListener;
    }

    @Override
    public boolean hasCanTouchDanMus() {
        return onDanMuViewTouchListeners.size() > 0;
    }

    @Override
    public void add(DanMuModel danMuView) {
        danMuView.enableMoving(true);
        addDanMuView(danMuView);
    }

    public void lockDraw() {
        if (!danMuController.isChannelCreated()) {
            return;
        }
        synchronized (lock) {
            this.postInvalidateOnAnimation();
            if ((!drawFinished)) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                }
            }
            drawFinished = false;
        }
    }

    @Override
    public void stop() {
        danMuController.forceSleep();
    }

    @Override
    public void play() {
        danMuController.forceWake();
    }

    private void unLockDraw() {
        synchronized (lock) {
            drawFinished = true;
            lock.notifyAll();
        }
    }

    @Override
    public void clear() {
        onDanMuViewTouchListeners.clear();
    }

    @Override
    public void remove(DanMuModel danMuView) {
        onDanMuViewTouchListeners.remove(danMuView);
    }

    public interface OnDetectHasCanTouchedDanMusListener {
        void hasNoCanTouchedDanMus(boolean hasDanMus);
    }


    @Override
    public void hideNormalDanMuView(boolean hide) {
        danMuController.hide(hide);
    }

    @Override
    public void hideAllDanMuView(boolean hideAll) {
        danMuController.hideAll(hideAll);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (danMuController != null) {
            danMuController.initChannels(canvas);
            danMuController.draw(canvas);
        }
        unLockDraw();
    }

    @Override
    public void add(int index, DanMuModel danMuView) {
        danMuController.addDanMuView(index, danMuView);
    }

    public OnDetectHasCanTouchedDanMusListener onDetectHasCanTouchedDanMusListener;

}
