package rui.todd;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rui.view.FlexRecyclerView;

public class PullToZoomActivity extends BaseActivity {
    @BindView(R.id.recyclerView)
    FlexRecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_zoom);
        ButterKnife.bind(this);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("" + i);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Adapter adapter = new Adapter(list, this);
        recyclerView.setAdapter(adapter);
    }

    static class Adapter extends RecyclerView.Adapter<Holder> {
        List<String> list;
        Context context;

        public Adapter(List<String> list, Context context) {
            this.list = list;
            this.context = context;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0)
                return 0;
            else
                return 1;
        }

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if (viewType == 0)
                return new Holder(LayoutInflater.from(context).inflate(R.layout.flex_header_layout, null));
            return new Holder(LayoutInflater.from(context).inflate(R.layout.item_array, null));
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int position) {
            if (position != 0)
                holder.bind(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    static class Holder extends RecyclerView.ViewHolder {
        TextView tv;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
        public void bind(String s) {
            tv.setText(s);

        }

    }

}