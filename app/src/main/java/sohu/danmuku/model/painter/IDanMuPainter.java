package sohu.danmuku.model.painter;

import android.graphics.Canvas;

import sohu.danmuku.model.DanMuModel;
import sohu.danmuku.model.channel.DanMuChannel;


abstract class IDanMuPainter {

    public abstract void execute(Canvas canvas, DanMuModel danMuView, DanMuChannel danMuChannel);

    public abstract void requestLayout();

    public abstract void setAlpha(int alpha);

    public abstract void hideNormal(boolean hide);

    public abstract void hideAll(boolean hideAll);

}
