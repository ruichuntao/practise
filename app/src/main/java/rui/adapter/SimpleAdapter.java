package rui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import rui.bean.SimpleBean;

public class SimpleAdapter extends BaseQuickAdapter<SimpleBean, BaseViewHolder> {

    public SimpleAdapter(int layoutResId, @Nullable List<SimpleBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, SimpleBean item) {

    }
}
