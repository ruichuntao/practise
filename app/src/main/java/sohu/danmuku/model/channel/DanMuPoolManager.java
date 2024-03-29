package sohu.danmuku.model.channel;

import android.content.Context;
import android.graphics.Canvas;

import java.util.List;

import sohu.danmuku.control.dispatcher.IDanMuDispatcher;
import sohu.danmuku.model.DanMuModel;
import sohu.danmuku.model.collection.DanMuConsumedPool;
import sohu.danmuku.model.collection.DanMuConsumer;
import sohu.danmuku.model.collection.DanMuProducedPool;
import sohu.danmuku.model.collection.DanMuProducer;
import sohu.danmuku.model.painter.DanMuPainter;
import sohu.danmuku.view.IDanMuParent;

public class DanMuPoolManager implements IDanMuPoolManager {

    private DanMuConsumer danMuConsumer;
    private DanMuProducer danMuProducer;

    private DanMuConsumedPool danMuConsumedPool;
    private DanMuProducedPool danMuProducedPool;

    private boolean isStart;

    public DanMuPoolManager(Context context, IDanMuParent danMuParent) {
        danMuConsumedPool = new DanMuConsumedPool(context);
        danMuProducedPool = new DanMuProducedPool(context);
        danMuConsumer = new DanMuConsumer(danMuConsumedPool, danMuParent);
        danMuProducer = new DanMuProducer(danMuProducedPool, danMuConsumedPool);
    }

    public void forceSleep() {
        danMuConsumer.forceSleep();
    }

    public void releaseForce() {
        danMuConsumer.releaseForce();
    }

    @Override
    public void hide(boolean hide) {
    }

    @Override
    public void hideAll(boolean hideAll) {
        danMuConsumedPool.hideAll(hideAll);
    }

    @Override
    public void startEngine() {
        if (!isStart) {
            isStart = true;
            danMuConsumer.start();
            danMuProducer.start();
        }
    }

    @Override
    public void setDispatcher(IDanMuDispatcher iDanMuDispatcher) {
        danMuProducedPool.setDanMuDispatcher(iDanMuDispatcher);
    }

    @Override
    public void divide(int width, int height) {
        danMuProducedPool.divide(width, height);
        danMuConsumedPool.divide(width,height);
    }

    @Override
    public void addDanMuView(int index, DanMuModel danMuView) {
        danMuProducer.produce(index, danMuView);
    }

    @Override
    public void jumpQueue(List<DanMuModel> danMuViews) {
        danMuProducer.jumpQueue(danMuViews);
    }

    public void release() {
        isStart = false;
        danMuConsumer.release();
        danMuProducer.release();
        danMuConsumedPool = null;
    }

    /**
     * drawing entrance
     *
     * @param canvas
     */
    public void drawDanMus(Canvas canvas) {
        danMuConsumer.consume(canvas);
    }

    public void addPainter(DanMuPainter danMuPainter, int key) {
        danMuConsumedPool.addPainter(danMuPainter, key);
    }

}
