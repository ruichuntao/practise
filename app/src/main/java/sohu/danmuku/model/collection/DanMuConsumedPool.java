package sohu.danmuku.model.collection;

import android.content.Context;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import sohu.danmuku.model.DanMuModel;
import sohu.danmuku.model.channel.DanMuChannel;
import sohu.danmuku.model.painter.DanMuPainter;
import sohu.danmuku.model.painter.R2LPainter;
import sohu.danmuku.model.utils.DimensionUtil;


public final class DanMuConsumedPool {

    private final static int MAX_COUNT_IN_SCREEN = 30;

    private final static int DEFAULT_SINGLE_CHANNEL_HEIGHT = 40;

    private HashMap<Integer, DanMuPainter> danMuPainterHashMap = new HashMap<>();

    private volatile ArrayList<DanMuModel> mixedDanMuViewQueue = new ArrayList<>();


    private DanMuChannel[] danMuChannels;


    private Context context;

    public DanMuConsumedPool(Context c) {
        context = c.getApplicationContext();
        initDefaultPainters();
    }


    public void addPainter(DanMuPainter danMuPainter, int key) {
        if (danMuPainter == null) {
            return;
        }
        if (!danMuPainterHashMap.containsKey(key)) {
            danMuPainterHashMap.put(key, danMuPainter);
        } else {
            throw new IllegalArgumentException("Already has the key of painter");
        }
    }


    public boolean isDrawnQueueEmpty() {
        if (mixedDanMuViewQueue == null || mixedDanMuViewQueue.size() == 0) {
            return true;
        }
        return false;
    }

    public void put(ArrayList<DanMuModel> danMuViews) {
        if (danMuViews != null && danMuViews.size() > 0) {
            mixedDanMuViewQueue = danMuViews;
        }
    }

    public void draw(Canvas canvas) {
        drawEveryElement(mixedDanMuViewQueue, canvas);
    }

    private synchronized void drawEveryElement(ArrayList<DanMuModel> danMuViewQueue, Canvas canvas) {
        if (danMuViewQueue == null || danMuViewQueue.size() == 0) {
            return;
        }
        for (int i = 0; i < danMuViewQueue.size(); i++) {
            DanMuModel danMuView = danMuViewQueue.get(i);
            if (danMuView.isAlive()) {
                DanMuPainter danMuPainter = getPainter(danMuView);
                DanMuChannel danMuChannel = danMuChannels[danMuView.getChannelIndex()];
                danMuChannel.dispatch(danMuView);
                if (danMuView.isAttached()) {
                    performDraw(danMuView, danMuPainter, canvas, danMuChannel);
                }
            } else {
                DanMuModel remove = danMuViewQueue.remove(i--);
                if (!remove.isMine()) {
                    remove.setAlive(true);
                    remove.setAttached(false);
                    remove.setStartPositionX(danMuChannels[danMuView.getChannelIndex()].width);
                    danMuViewQueue.add(remove);
                }
            }
        }
    }


    private void initDefaultPainters() {
        R2LPainter r2LPainter = new R2LPainter();
        danMuPainterHashMap.put(DanMuModel.RIGHT_TO_LEFT, r2LPainter);
    }

    private DanMuPainter getPainter(DanMuModel danMuView) {
        int painterType = danMuView.getDisplayType();
        return danMuPainterHashMap.get(painterType);
    }

    private void performDraw(DanMuModel danMuView, DanMuPainter danMuPainter, Canvas canvas, DanMuChannel danMuChannel) {
        danMuPainter.execute(canvas, danMuView, danMuChannel);
    }

    public void divide(int width, int height) {
        int singleHeight = DimensionUtil.dpToPx(context, DEFAULT_SINGLE_CHANNEL_HEIGHT);
        int count = height / singleHeight;

        danMuChannels = new DanMuChannel[count];
        for (int i = 0; i < count; i++) {
            DanMuChannel danMuChannel = new DanMuChannel();
            danMuChannel.width = width;
            danMuChannel.height = singleHeight;
//            danMuChannel.speed = speedController.getSpeed();

            danMuChannel.topY = i * singleHeight;
//            danMuChannel.space = selectSpaceRandomly();
            danMuChannels[i] = danMuChannel;
        }
    }

    public void hideAll(boolean hide) {
        Set<Integer> danMuPainters = danMuPainterHashMap.keySet();
        Iterator<Integer> iterator = danMuPainters.iterator();
        while (iterator.hasNext()) {
            Integer key = iterator.next();
            danMuPainterHashMap.get(key).hideAll(hide);
        }
    }


}
