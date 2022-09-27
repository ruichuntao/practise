package sohu.danmuku.model.channel;

import java.util.List;

import sohu.danmuku.control.dispatcher.IDanMuDispatcher;
import sohu.danmuku.model.DanMuModel;


interface IDanMuPoolManager {

    void addDanMuView(int index, DanMuModel iDanMuView);

    void jumpQueue(List<DanMuModel> danMuViews);

    void divide(int width, int height);

    void setDispatcher(IDanMuDispatcher iDanMuDispatcher);

    void hide(boolean hide);

    void hideAll(boolean hideAll);

    void startEngine();
}
