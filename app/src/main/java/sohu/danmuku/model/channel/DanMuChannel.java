package sohu.danmuku.model.channel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import sohu.danmuku.model.DanMuModel;


public class DanMuChannel {

    public float speed = 5;
    public int width;
    public int height;
    public int topY;
    public int space = 60;

    public DanMuModel r2lReferenceView;
    private final LinkedList<DanMuModel> myDanMuStack = new LinkedList<>();
    private final Random random = new Random();

    public void dispatch(DanMuModel danMuView) {
        if (danMuView.isAttached()) {
            return;
        }
        danMuView.setSpeed(speed);
        int mDeltaX = 0;
        if (danMuView.isMine()) myDanMuStack.add(danMuView);
        if (r2lReferenceView != null) {
            mDeltaX = (int) (width - r2lReferenceView.getX() - r2lReferenceView.getWidth());
        }
        // todo 随机距离todd
        if (r2lReferenceView == null || !r2lReferenceView.isAlive() || mDeltaX > random.nextInt(1000) + 100) {
            if (!myDanMuStack.isEmpty()) {
                danMuView = myDanMuStack.removeLast();
            }
            danMuView.setAttached(true);
            r2lReferenceView = danMuView;
        }

    }

}
