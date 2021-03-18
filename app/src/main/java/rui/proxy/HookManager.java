package rui.proxy;

import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class HookManager {
    public static void hookOnClickListener(View view) {
        try {
            Method getListenerInfo = View.class.getDeclaredMethod("getListenerInfo");
            getListenerInfo.setAccessible(true);
            Object listenerInfo = getListenerInfo.invoke(view);
            Class<?> listenerInfoClz = Class.forName("android.view.View$ListenerInfo");
            Field onClickListener = listenerInfoClz.getDeclaredField("mOnClickListener");
            onClickListener.setAccessible(true);
            View.OnClickListener originOnClickListener = (View.OnClickListener) onClickListener.get(listenerInfo);
            View.OnClickListener hookedOnClickListener = new OnClickListenerProxy(originOnClickListener);
            onClickListener.set(listenerInfo, hookedOnClickListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
