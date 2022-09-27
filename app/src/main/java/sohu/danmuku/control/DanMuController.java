package sohu.danmuku.control;

import android.graphics.Canvas;
import android.view.View;

import java.util.List;

import sohu.danmuku.control.dispatcher.DanMuDispatcher;
import sohu.danmuku.model.DanMuModel;
import sohu.danmuku.model.channel.DanMuPoolManager;
import sohu.danmuku.model.painter.DanMuPainter;
import sohu.danmuku.view.IDanMuParent;


public final class DanMuController {

    private DanMuPoolManager danMuPoolManager;
    private DanMuDispatcher danMuRandomDispatcher;
    private boolean channelCreated = false;

    public DanMuController(View view) {

        if (danMuPoolManager == null) {
            danMuPoolManager = new DanMuPoolManager(view.getContext(), (IDanMuParent) view);
        }
        if (danMuRandomDispatcher == null) {
            danMuRandomDispatcher = new DanMuDispatcher(view.getContext());
        }
        danMuPoolManager.setDispatcher(danMuRandomDispatcher);
    }

    public void forceSleep() {
        danMuPoolManager.forceSleep();
    }

    public void forceWake() {
        if (danMuPoolManager != null) {
            danMuPoolManager.releaseForce();
        }
    }


    public void prepare() {
        danMuPoolManager.startEngine();
    }

    public void addDanMuView(int index, DanMuModel danMuView) {
        danMuPoolManager.addDanMuView(index, danMuView);
    }

    public void jumpQueue(List<DanMuModel> danMuViews) {
        danMuPoolManager.jumpQueue(danMuViews);
    }

    public void addPainter(DanMuPainter danMuPainter, int key) {
        danMuPoolManager.addPainter(danMuPainter, key);
    }

    public boolean isChannelCreated() {
        return channelCreated;
    }

    public void hide(boolean hide) {
        if (danMuPoolManager != null) {
            danMuPoolManager.hide(hide);
        }
    }

    public void hideAll(boolean hideAll) {
        if (danMuPoolManager != null) {
            danMuPoolManager.hideAll(hideAll);
        }
    }

    public void initChannels(Canvas canvas) {
        if (!channelCreated) {
            danMuPoolManager.divide(canvas.getWidth(), canvas.getHeight());
            channelCreated = true;
        }
    }

    public void draw(Canvas canvas) {
        danMuPoolManager.drawDanMus(canvas);
    }

    public void release() {
        if (danMuPoolManager != null) {
            danMuPoolManager.release();
            danMuPoolManager = null;
        }
        if (danMuRandomDispatcher != null) {
            danMuRandomDispatcher.release();
        }
    }
}
