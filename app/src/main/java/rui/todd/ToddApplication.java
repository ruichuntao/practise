package rui.todd;

import android.app.Application;
import android.content.Context;

public class ToddApplication extends Application {

    public static Context application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static Context getApplication() {
        return application;
    }
}
