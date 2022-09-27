package sohu.danmuku.model.collection;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import sohu.danmuku.control.dispatcher.IDanMuDispatcher;
import sohu.danmuku.model.DanMuModel;
import sohu.danmuku.model.channel.DanMuChannel;
import sohu.danmuku.model.utils.DimensionUtil;


public class DanMuProducedPool {

    private final static int MAX_COUNT_IN_SCREEN = 30;

    private final static int DEFAULT_SINGLE_CHANNEL_HEIGHT = 26;

    private IDanMuDispatcher iDanMuDispatcher;

    private volatile ArrayList<DanMuModel> mixedDanMuViewPendingQueue = new ArrayList<>();

    private ReentrantLock reentrantLock = new ReentrantLock();

    private DanMuChannel[] danMuChannels;

    private Context context;

    public DanMuProducedPool(Context context) {
        this.context = context.getApplicationContext();
    }

    public void setDanMuDispatcher(IDanMuDispatcher iDanMuDispatcher) {
        this.iDanMuDispatcher = iDanMuDispatcher;
    }

    public void addDanMuView(int index, DanMuModel danMuView) {
        reentrantLock.lock();
        try {
            if (index > -1) {
                mixedDanMuViewPendingQueue.add(index, danMuView);
            } else {
                mixedDanMuViewPendingQueue.add(danMuView);
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    public void jumpQueue(List<DanMuModel> danMuViews) {
        reentrantLock.lock();
        try {
            mixedDanMuViewPendingQueue.addAll(danMuViews);
        } finally {
            reentrantLock.unlock();
        }
    }

    public synchronized ArrayList<DanMuModel> dispatch() {
        if (isEmpty()) {
            return null;
        }
        ArrayList<DanMuModel> danMuViews = mixedDanMuViewPendingQueue;
        for (int i = 0; i < danMuViews.size(); i++) {
            DanMuModel danMuView = danMuViews.get(i);
            iDanMuDispatcher.dispatch(danMuView, danMuChannels);
        }
        return danMuViews;
    }

    public boolean isEmpty() {
        return mixedDanMuViewPendingQueue.size() == 0;
    }

    public void divide(int width, int height) {
        int singleHeight = DimensionUtil.dpToPx(context, DEFAULT_SINGLE_CHANNEL_HEIGHT);
        int count = 2;

        danMuChannels = new DanMuChannel[count];
        for (int i = 0; i < count; i++) {
            DanMuChannel danMuChannel = new DanMuChannel();
            danMuChannel.width = width;
            danMuChannel.height = singleHeight;
            danMuChannel.topY = i * (singleHeight + DimensionUtil.dpToPx(context, 24));
            danMuChannels[i] = danMuChannel;
        }
    }

    public void clear() {
        mixedDanMuViewPendingQueue.clear();
        context = null;
    }
}
