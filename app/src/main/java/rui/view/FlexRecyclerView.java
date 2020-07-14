package rui.view;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import rui.todd.R;

public class FlexRecyclerView extends RecyclerView {

    public static final int CONTENT = 0;
    public static final int HEADER = 1;
    public static final int FOOTER = 2;


    public static class Adapter extends RecyclerView.Adapter<Holder> {
        private List<String> list;
        private Context context;
        private View header;
        private View footer;

        public View getHeader() {
            return header;
        }

        public View getFooter() {
            return footer;
        }

        public Adapter(Context context, List<String> list) {
            this.context = context;
            this.list = list;
        }

        @SuppressLint("InflateParams")
        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if (viewType == HEADER) {
                header = LayoutInflater.from(context).inflate(R.layout.item_array_header, null);
                return new Holder(header);
            }
            if (viewType == FOOTER) {
                footer = LayoutInflater.from(context).inflate(R.layout.item_array_footer, null);
                return new Holder(footer);
            }
            return new Holder(LayoutInflater.from(context).inflate(R.layout.item_array, null));
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int position) {
            if (getItemViewType(position) == CONTENT)
                holder.bind(list.get(position - 1));
        }

        @Override
        public int getItemCount() {
            return list.size() + 2;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) return HEADER;
            else if (position == list.size() + 1) return FOOTER;
            return CONTENT;
        }
    }

    private static class Holder extends RecyclerView.ViewHolder {
        private TextView tv;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }

        public void bind(String s) {
            tv.setText(s);
        }

    }

    public FlexRecyclerView(@NonNull Context context) {
        this(context, null);
    }

    public FlexRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlexRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private static final String TAG = "FlexRecyclerView";

    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);
        View head = ((FlexRecyclerView.Adapter) getAdapter()).getHeader();
        View foot = ((FlexRecyclerView.Adapter) getAdapter()).getFooter();
//        Log.e(TAG, "onScrolled: " + "====" + dx);
        Log.e(TAG, "onScrolled: " + "====" + dy);
        if (dy < 0) {
            head.getLayoutParams().height -= dy;
            head.postInvalidate();
            Log.e(TAG, "onScrolled: " + "====" + head.getLayoutParams().height);
        }
    }
}
