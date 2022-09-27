package sohu.danmuku.model.painter;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;

import sohu.danmuku.model.DanMuModel;
import sohu.danmuku.model.channel.DanMuChannel;
import sohu.danmuku.model.utils.PaintUtils;


public class DanMuPainter extends IDanMuPainter {

    protected static TextPaint paint;
    protected static RectF rectF;

    private boolean hide;

    private boolean hideAll;

    static {
        paint = PaintUtils.getPaint();
        rectF = new RectF();
    }

    public DanMuPainter() {
    }

    protected void layout(DanMuModel danMuView, DanMuChannel danMuChannel) {
    }

    private void onLayout(DanMuModel danMuView, DanMuChannel danMuChannel) {
        if (danMuView.isMoving()) {
            layout(danMuView, danMuChannel);
        }
    }

    protected void draw(Canvas canvas, DanMuModel danMuView, DanMuChannel danMuChannel) {
        if (danMuView.textBackground != null) {
            drawTextBackground(danMuView, canvas, danMuChannel);
        }
        if (!TextUtils.isEmpty(danMuView.text)) {
            drawText(danMuView, canvas, danMuChannel);
        }
    }

    protected void drawText(DanMuModel danMuView, Canvas canvas, DanMuChannel danMuChannel) {
        if (TextUtils.isEmpty(danMuView.text)) {
            return;
        }

        paint.setTextSize(danMuView.textSize);
        paint.setColor(danMuView.textColor);
        paint.setStyle(Paint.Style.FILL);

        CharSequence text = danMuView.text;
        StaticLayout staticLayout = new StaticLayout(text,
                paint,
                (int) Math.ceil(StaticLayout.getDesiredWidth(text, paint)),
                Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);

        float x = danMuView.getX()
                + danMuView.textMarginLeft;
//                + danMuView.avatarWidth
//                + danMuView.levelMarginLeft
//                + danMuView.levelBitmapWidth
//                + danMuView.textMarginLeft;

        float top = (int) (danMuView.getY())
                + danMuChannel.height / 2
                - staticLayout.getHeight()/2;

        canvas.save();
        canvas.translate((int) x, top);
        staticLayout.draw(canvas);
        canvas.restore();
    }

    protected void drawTextBackground(DanMuModel danMuView, Canvas canvas, DanMuChannel danMuChannel) {
        CharSequence text = danMuView.text;
        StaticLayout staticLayout = new StaticLayout(text,
                paint,
                (int) Math.ceil(StaticLayout.getDesiredWidth(text, paint)),
                Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);

        int textBackgroundHeight = staticLayout.getHeight()
                + danMuView.textBackgroundPaddingTop
                + danMuView.textBackgroundPaddingBottom;

        float top = danMuView.getY()
                + (danMuChannel.height - textBackgroundHeight) / 2;

        float x = danMuView.getX();
//                + danMuView.marginLeft
//                + danMuView.avatarWidth
//                - danMuView.textBackgroundMarginLeft;

        Rect rectF = new Rect((int)x,
                (int)top,
                (int)(x + staticLayout.getWidth()
//                        + danMuView.levelMarginLeft
//                        + danMuView.levelBitmapWidth
                        + danMuView.textMarginLeft
                        + danMuView.textMarginRight

                ),
//                        + danMuView.textBackgroundMarginLeft
//                        + staticLayout.getWidth()
//                        + danMuView.textBackgroundPaddingRight),
                (int)(top + textBackgroundHeight));

        danMuView.textBackground.setBounds(rectF);
        danMuView.textBackground.draw(canvas);
    }

    @Override
    public void requestLayout() {
    }

    @Override
    public void setAlpha(int alpha) {
    }

    @Override
    public void hideNormal(boolean hide) {
        this.hide = hide;
    }

    @Override
    public void hideAll(boolean hideAll) {
        this.hideAll = hideAll;
    }

    @Override
    public void execute(Canvas canvas, DanMuModel danMuView, DanMuChannel danMuChannel) {
        if ((int) danMuView.getSpeed() == 0) {
            danMuView.setAlive(false);
        }

        onLayout(danMuView, danMuChannel);

        if (hideAll) {
            return;
        }

        if (danMuView.getPriority() == DanMuModel.NORMAL && hide) {
            return;
        }

        draw(canvas, danMuView, danMuChannel);
    }

}
