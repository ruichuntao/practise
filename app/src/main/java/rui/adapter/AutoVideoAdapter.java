package rui.adapter;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import java.util.List;
import java.util.TimerTask;

import rui.todd.R;

public class AutoVideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Uri> list;

    public AutoVideoAdapter(Context context, List<Uri> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(context).inflate(R.layout.video_item_layout, viewGroup, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ViewHolder) viewHolder).bind(list.get(i));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public VideoView videoView;

        public ViewHolder(View view) {
            super(view);
            videoView = view.findViewById(R.id.video_view);
        }

        public void bind(Uri uri) {
            videoView.setVideoURI(uri);
            videoView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                @Override
                public void onViewAttachedToWindow(View v) {
                    videoView.start();
                }

                @Override
                public void onViewDetachedFromWindow(View v) {
                    videoView.stopPlayback();
                }
            });
        }

    }
}
