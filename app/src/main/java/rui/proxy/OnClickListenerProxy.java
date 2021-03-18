package rui.proxy;

import android.view.View;

public class OnClickListenerProxy implements View.OnClickListener {
    private View.OnClickListener mListener;

    public OnClickListenerProxy(View.OnClickListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public void onClick(View v) {
        if (mListener != null) mListener.onClick(v);
    }
}
