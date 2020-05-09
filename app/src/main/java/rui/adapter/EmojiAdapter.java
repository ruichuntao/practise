package rui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import rui.todd.EmojiActivity;
import rui.todd.R;

public class EmojiAdapter extends BaseAdapter {

    public List<String> list;
    private Context mContext;
    private EmojiActivity.ClickEmojiListener listener;

    public EmojiAdapter(List<String> list, Context context, EmojiActivity.ClickEmojiListener emojilistener) {
        this.list = list;
        mContext = context;
        listener = emojilistener;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.emoji_item_layout, null);
            holder = new ViewHolder();
            holder.emoji = convertView.findViewById(R.id.emoji_tv);
            holder.back = convertView.findViewById(R.id.back_iv);
            convertView.setTag(holder);
        } else
            holder = ((ViewHolder) convertView.getTag());
        if (position == list.size() - 1) {
            holder.back.setVisibility(View.VISIBLE);
            holder.emoji.setVisibility(View.GONE);
        } else {
            holder.back.setVisibility(View.GONE);
            holder.emoji.setVisibility(View.VISIBLE);
            holder.emoji.setText(list.get(position));
        }
        holder.emoji.setOnClickListener(v -> {
            listener.onClickEmoji(list.get(position));
        });
        holder.back.setOnClickListener(v -> {
            listener.onClickDelete();
        });
        return convertView;
    }

    static final class ViewHolder {
        TextView emoji;
        ImageView back;
    }
}
