package rui.todd;

import android.content.Context;
import android.graphics.PointF;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import rui.adapter.BoxAdapter;
import rui.bean.Box;

public class OpenBoxActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BoxAdapter adapter;
    private List<Box> list = new ArrayList<>();
    private Timer timer;
    private TimerTask timerTask;
    private int pos = 0;
    private static final String TAG = "OpenBoxActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_box);
        recyclerView = findViewById(R.id.recycler_view);
        ScrollLinearLayoutManager lm = new ScrollLinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(lm);
        adapter = new BoxAdapter(R.layout.box_item);
        recyclerView.setAdapter(adapter);
        for (int i = 0;i<50;i++){
            Box b = new Box();
            b.num = i+1;
            list.add(b);
        }
        adapter.setNewData(list);
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                int p = ++pos%50;
                recyclerView.smoothScrollToPosition(p);
                Log.e(TAG, "run: "+p);
            }
        };
        timer.schedule(timerTask,0,1000);
//        Toast.makeText(this,"移动到4",Toast.LENGTH_SHORT).show();
    }

    class ScrollLinearLayoutManager extends LinearLayoutManager {
        private static final float MILLISECONDS_PER_INCH = 300f;
        public ScrollLinearLayoutManager(Context context) {
            super(context);
        }

        @Override
        public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, final int position) {
            LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext())
            {

                @Nullable
                @Override
                public PointF computeScrollVectorForPosition(int targetPosition) {
                    return ScrollLinearLayoutManager.this.computeScrollVectorForPosition(targetPosition);
                }

                @Override
                protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                    return MILLISECONDS_PER_INCH / displayMetrics.densityDpi;
                }

            };
            linearSmoothScroller.setTargetPosition(position);
            startSmoothScroll(linearSmoothScroller);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
        timerTask.cancel();
    }
}
