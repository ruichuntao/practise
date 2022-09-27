package sohu.danmuku;

import android.content.Context;
import android.graphics.Color;

import androidx.core.content.ContextCompat;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import rui.todd.R;
import sohu.danmuku.model.DanMuModel;
import sohu.danmuku.model.DanmakuEntity;
import sohu.danmuku.model.utils.DimensionUtil;
import sohu.danmuku.view.IDanMuParent;
import sohu.danmuku.view.OnDanMuTouchCallBackListener;

public final class DanMuHelper {

    private ArrayList<WeakReference<IDanMuParent>> mDanMuViewParents;
    private WeakReference<Context> mContext;

    public DanMuHelper(Context context) {
        if (mContext == null) {
            this.mContext = new WeakReference<>(context.getApplicationContext());
        }

        this.mDanMuViewParents = new ArrayList<>();
    }

    public void release() {
        if (mDanMuViewParents != null) {
            for (WeakReference<IDanMuParent> danMuViewParentsRef : mDanMuViewParents) {
                if (danMuViewParentsRef != null) {
                    IDanMuParent danMuParent = danMuViewParentsRef.get();
                    if (danMuParent != null)
                        danMuParent.release();
                }
            }
            mDanMuViewParents.clear();
            mDanMuViewParents = null;
        }

        mContext = null;
    }

    public void add(final IDanMuParent danMuViewParent) {
        if (danMuViewParent != null) {
            danMuViewParent.clear();
        }

        if (mDanMuViewParents != null) {
            mDanMuViewParents.add(new WeakReference<>(danMuViewParent));
        }
    }

    public void initDanMu(List<DanmakuEntity> list) {
        for (DanmakuEntity danmakuEntity : list) {
            addDanMu(danmakuEntity, null);
        }
    }

    public void addDanMu(DanmakuEntity danmakuEntity, OnDanMuTouchCallBackListener listener) {
        if (mDanMuViewParents != null) {
            WeakReference<IDanMuParent> danMuViewParent = mDanMuViewParents.get(0);
            DanMuModel danMuView = createDanMuView(danmakuEntity, listener);
            if (danMuViewParent != null && danMuView != null && danMuViewParent.get() != null) {
                danMuViewParent.get().add(0, danMuView);
            }
        }
    }

    private DanMuModel createDanMuView(final DanmakuEntity entity, OnDanMuTouchCallBackListener listener) {
        final DanMuModel danMuView = new DanMuModel();
        danMuView.setDisplayType(DanMuModel.RIGHT_TO_LEFT);
        danMuView.setPriority(DanMuModel.NORMAL);
        danMuView.marginLeft = DimensionUtil.dpToPx(mContext.get(), 30);
        danMuView.setOrder(entity.getOrder());
        String content = entity.getText();
        danMuView.textSize = DimensionUtil.spToPx(mContext.get(), 16);
        danMuView.selectChannel(entity.getIndex());
        if (!entity.isMine()) {
            danMuView.textColor = Color.parseColor("#ffffff");
        } else {
            danMuView.textColor = Color.parseColor("#FFED00");
        }
        danMuView.setMine(entity.isMine());
        danMuView.textMarginLeft = DimensionUtil.dpToPx(mContext.get(), 8);
        danMuView.textMarginRight = DimensionUtil.dpToPx(mContext.get(), 8);
        danMuView.text = content;

        // 弹幕文本背景
        danMuView.textBackground = ContextCompat.getDrawable(mContext.get(), R.drawable.corners_danmu);
        danMuView.textBackgroundMarginLeft = DimensionUtil.dpToPx(mContext.get(), 15);
        danMuView.textBackgroundPaddingTop = DimensionUtil.dpToPx(mContext.get(), 3);
        danMuView.textBackgroundPaddingBottom = DimensionUtil.dpToPx(mContext.get(), 3);
        danMuView.textBackgroundPaddingLeft = DimensionUtil.dpToPx(mContext.get(), 8);
        danMuView.textBackgroundPaddingRight = DimensionUtil.dpToPx(mContext.get(), 8);

        danMuView.enableTouch(true);
        danMuView.setOnTouchCallBackListener(listener);


        return danMuView;
    }
}