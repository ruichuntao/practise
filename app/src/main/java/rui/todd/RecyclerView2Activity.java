package rui.todd;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rui.adapter.RecyclerAdapter;
import rui.view.NoFlingRecyclerView;

import static androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_DRAGGING;

public class RecyclerView2Activity extends AppCompatActivity {
    @BindView(R.id.rv1)
    public NoFlingRecyclerView rv1;
    @BindView(R.id.rv2)
    public NoFlingRecyclerView rv2;

    private RecyclerAdapter ra1;
    private RecyclerAdapter ra2;

    private List<String> l1 = new ArrayList<>();
    private List<String> l2 = new ArrayList<>();

    private boolean rv1scroll = false;
    private boolean rv2scroll = false;
    private boolean rv1drag = false;
    private boolean rv2drag = false;
    private boolean drag;
    private boolean drag1;
    private boolean drag2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view2);
        ButterKnife.bind(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);

        rv1.setLayoutManager(layoutManager);
        rv2.setLayoutManager(layoutManager1);
        for (int i = 0; i < 50; i++) {
            l1.add("这是第" + i + "个项目");
            l2.add("这是第" + i + "个项目");
        }
        ra1 = new RecyclerAdapter(R.layout.recycler_item, l1);
        ra2 = new RecyclerAdapter(R.layout.recycler_item, l2);
        rv1.setAdapter(ra1);
        rv2.setAdapter(ra2);

        rv1.setOnFlingListener(new RecyclerView.OnFlingListener() {
            @Override
            public boolean onFling(int i, int i1) {
                return false;
            }
        });
        RecyclerView.OnScrollListener listener1 = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == SCROLL_STATE_DRAGGING) {
                    if (!drag1) {
                        drag2 = false;
                        drag1 = true;
                    }
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (drag1) rv2.scrollBy(dx, dy);
            }
        };
        rv1.addOnScrollListener(listener1);
        RecyclerView.OnScrollListener listener2 = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == SCROLL_STATE_DRAGGING) {
                    if (!drag2) {
                        drag1 = false;
                        drag2 = true;
                    }
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (drag2) rv1.scrollBy(dx, dy);
            }
        };
        rv2.addOnScrollListener(listener2);
    }
}
