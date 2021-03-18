package rui.todd;

import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import android.os.Handler;
import android.os.Message;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

import rui.adapter.MazeAdapter;
import rui.bean.MazeBean;

public class MazeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<MazeBean> list = new ArrayList<>();
    private MazeAdapter adapter;
    private int[][] v;
    private int m;
    private int n;
    private List<Integer> order = new ArrayList<>();
    private Timer timer = new Timer();
    int[][] dict = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private boolean isIn(int i, int j) {
        return i >= 0 && i < m && j >= 0 && j < n;
    }

    int idx = 0;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 0) {
                if (idx == order.size()) {
                    timer.cancel();
                    return true;
                }
                int cur = order.get(idx);
                list.get(cur).change = true;
                adapter.notifyItemChanged(order.get(idx));
                idx++;
            }
            return false;
        }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maze);
        recyclerView = findViewById(R.id.recycler_view);
        adapter = new MazeAdapter(this, list);
        GridLayoutManager manager = new GridLayoutManager(this, 10);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        v = new int[10][10];
        m = 10;
        n = 10;
        for (int i = 0; i < 100; i++) {
            MazeBean bean = new MazeBean();
            list.add(bean);
        }
//        dfs(5, 5);
        bfs(5, 5);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
            }
        };
        timer.schedule(task, 0, 500);
    }

    public void bfs(int i, int j) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{i, j});
        v[i][j] = 1;
        while (!queue.isEmpty()) {
            int[] c = queue.poll();
            int x = c[0];
            int y = c[1];
            order.add(x * 10 + y);
            for (int[] d : dict) {
                int nx = x + d[0];
                int ny = y + d[1];
                if (isIn(nx, ny) && v[nx][ny] == 0) {
                    v[nx][ny] = 1;
                    queue.add(new int[]{nx, ny});
                }
            }
        }
    }

    public void dfs(int i, int j) {
        if (isIn(i, j)) {
            if (v[i][j] == 1) return;
            v[i][j] = 1;
            order.add(i * 10 + j);
            dfs(i, j + 1);
            dfs(i + 1, j);
            dfs(i, j - 1);
            dfs(i - 1, j);
        }
    }

}
