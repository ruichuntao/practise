package rui.adapter;

import androidx.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import rui.todd.R;

public class RecyclerAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public RecyclerAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        TextView textView = helper.getView(R.id.tv);
        textView.setText(item);
    }
}
