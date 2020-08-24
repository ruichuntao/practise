package rui.bean;

import android.util.Log;

import javax.inject.Inject;

public class Watch {
    @Inject
    public Watch() {
    }

    private static final String TAG = "Watch";

    public void work() {
        Log.e(TAG, "work: ");
    }
}
