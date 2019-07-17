package rui.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import rui.bean.Box;
import rui.todd.R;

public class BoxAdapter extends BaseQuickAdapter<Box, BaseViewHolder> {
    private TextView num_tv;

    public BoxAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, Box item) {
        num_tv = helper.getView(R.id.number);
        num_tv.setText(item.num+"");
    }
}
