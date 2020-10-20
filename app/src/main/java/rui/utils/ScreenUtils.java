package rui.utils;

import rui.todd.ToddApplication;

public class ScreenUtils {
    public static int dp2px(float dp) {
        final float scale = ToddApplication.getApplication().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static int px2dp(float px) {
        final float scale = ToddApplication.getApplication().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }
}
