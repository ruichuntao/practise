package rui.adapter;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import rui.bean.MazeBean;
import rui.todd.R;

public class MazeAdapter extends RecyclerView.Adapter<MazeAdapter.MazeHolder> {

    private Context mContext;
    private List<MazeBean> list;

    public MazeAdapter(Context mContext, List<MazeBean> list) {
        this.mContext = mContext;
        this.list = list;
    }


    @NonNull
    @Override
    public MazeHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MazeHolder(LayoutInflater.from(mContext).inflate(R.layout.item_maze_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MazeHolder mazeHolder, int i) {
        if (list.get(i).change) {
            ValueAnimator animator = ObjectAnimator.ofInt(mazeHolder.maze, "backgroundColor", 0xffffffff, 0xffFF3300);
            animator.setEvaluator(new ArgbEvaluator());
            animator.setDuration(500);
            animator.start();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MazeHolder extends RecyclerView.ViewHolder {
        ImageView maze;

        MazeHolder(@NonNull View itemView) {
            super(itemView);
            maze = itemView.findViewById(R.id.maze);
        }
    }

}
