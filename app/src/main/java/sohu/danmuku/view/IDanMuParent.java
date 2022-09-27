package sohu.danmuku.view;

import java.util.List;

import sohu.danmuku.model.DanMuModel;


public interface IDanMuParent {
    void add(DanMuModel danMuView);

    void add(int index, DanMuModel danMuView);

    void jumpQueue(List<DanMuModel> danMuViews);

    void addAllTouchListener(List<DanMuModel> onDanMuTouchCallBackListeners);

    void clear();

    void remove(DanMuModel danMuView);

    void lockDraw();

    void stop();

    void play();

    boolean hasCanTouchDanMus();

    void hideNormalDanMuView(boolean hide);

    void hideAllDanMuView(boolean hideAll);

    void release();
}
