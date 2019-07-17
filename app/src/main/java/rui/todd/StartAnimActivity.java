package rui.todd;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rui.adapter.SimpleAdapter;
import rui.bean.SimpleBean;

public class StartAnimActivity extends AppCompatActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private SimpleAdapter adapter;
    private List<SimpleBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_anim);
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        adapter = new SimpleAdapter(R.layout.simple_item,list);
        recyclerView.setAdapter(adapter);
        for (int i = 0;i<50;i++){
            SimpleBean bean = new SimpleBean();
            list.add(bean);
        }
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(StartAnimActivity.this,SimpleAnimActivity.class), ActivityOptionsCompat.makeSceneTransitionAnimation(StartAnimActivity.this,view, "shareNames").toBundle());
            }
        });
    }
}
