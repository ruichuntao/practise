package rui.todd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import rui.adapter.BoxAdapter;
import rui.bean.Box;

public class TestHorRecyclerActivity extends AppCompatActivity {

    private RecyclerView view;
    BoxAdapter adapter;
    List<Box> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_hor_recycler);
        view = findViewById(R.id.recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        adapter = new BoxAdapter(R.layout.box_item);
        view.setAdapter(adapter);
        view.setLayoutManager(manager);
        for (int i = 0;i<50;i++){
            Box b = new Box();
            b.num = i+1;
            list.add(b);
        }
        adapter.setNewData(list);
        View v = LayoutInflater.from(this).inflate(R.layout.activity_circle,null);
        adapter.addHeaderView(v);
    }
}
