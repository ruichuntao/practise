package rui.utils;

import android.content.Context;
import android.os.Handler;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpEngine {
    private volatile static OkHttpEngine mInstance;
    private OkHttpClient mOkHttpClient;
    private Handler mHandler;

    public static OkHttpEngine getInstance(Context context) {
        if (mInstance == null) {
            synchronized (OkHttpEngine.class) {
                if (mInstance == null) mInstance = new OkHttpEngine(context);
            }
        }
        return mInstance;
    }

    private OkHttpEngine(Context context) {
        File sdcache = context.getExternalCacheDir();
        int cacheSize = 10 * 1024 * 1024;
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .cache(new Cache(sdcache.getAbsoluteFile(), cacheSize));
        mOkHttpClient = builder.build();
        mHandler = new Handler();
    }

    public void getAsyncHttp(String url, ResultCallBack callBack) {
        final Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = mOkHttpClient.newCall(request);
        dealResult(call, callBack);
    }

    private void dealResult(Call call, ResultCallBack callBack) {
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                sendFailedCallback(call.request(), e, callBack);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                sendSuccessCallback(response, callBack);
            }

            private void sendSuccessCallback(Response response, ResultCallBack callBack) {
                mHandler.post(() -> {
                    if (callBack != null) {
                        try {
                            callBack.onResponse(response);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            private void sendFailedCallback(Request request, IOException e, ResultCallBack callBack) {
                mHandler.post(() -> {
                    if (callBack != null) {
                        callBack.onError(request, e);
                    }
                });
            }
        });

    }


}

