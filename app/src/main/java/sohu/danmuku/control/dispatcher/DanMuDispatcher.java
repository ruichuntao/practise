package sohu.danmuku.control.dispatcher;

import android.content.Context;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;

import java.util.Random;

import sohu.danmuku.model.DanMuModel;
import sohu.danmuku.model.channel.DanMuChannel;
import sohu.danmuku.model.utils.PaintUtils;


public class DanMuDispatcher implements IDanMuDispatcher {

    private Context context;
    protected TextPaint paint;
    private Random random = new Random();

    public DanMuDispatcher(Context context) {
        this.context = context;
        paint = PaintUtils.getPaint();
    }

    @Override
    public synchronized void dispatch(DanMuModel danMuView, DanMuChannel[] danMuChannels) {
        if (!danMuView.isAttached() && danMuChannels != null) {
            DanMuChannel danMuChannel = danMuChannels[danMuView.getChannelIndex()];
            if (danMuChannel == null) {
                return;
            }
            measure(danMuView, danMuChannel);
        }
    }

    private void measure(DanMuModel danMuView, DanMuChannel danMuChannel) {
        if (danMuView.isMeasured()) {
            return;
        }

        CharSequence text = danMuView.text;
        if (!TextUtils.isEmpty(text)) {
            paint.setTextSize(danMuView.textSize);
            StaticLayout staticLayout = new StaticLayout(text,
                    paint,
                    (int) Math.ceil(StaticLayout.getDesiredWidth(text, paint)),
                    Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);

            float textWidth = danMuView.getX()
                    + danMuView.marginLeft
                    + danMuView.avatarWidth
                    + danMuView.levelMarginLeft
                    + danMuView.levelBitmapWidth
                    + danMuView.textMarginLeft
                    + staticLayout.getWidth()
                    + danMuView.textBackgroundPaddingRight;
            danMuView.setWidth((int) textWidth);

            float textHeight = staticLayout.getHeight()
                    + danMuView.textBackgroundPaddingTop
                    + danMuView.textBackgroundPaddingBottom;
            if (danMuView.avatar != null && danMuView.avatarHeight > textHeight) {
                danMuView.setHeight((int) (danMuView.getY() + danMuView.avatarHeight));
            } else {
                danMuView.setHeight((int) (danMuView.getY() + textHeight));
            }
        }
        danMuView.setStartPositionX(danMuChannel.width);
        danMuView.setMeasured(true);
        danMuView.setStartPositionY(danMuChannel.topY);
        danMuView.setAlive(true);
    }

    public void release() {
        context = null;
    }
}
