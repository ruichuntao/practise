package rui.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rui.todd.ToddApplication;

public class DownTask extends AsyncTask<String, Integer, Void> {

    int id = 1;

    @Override
    protected Void doInBackground(String... strings) {
        for (String s : strings) {
            httpDown(s);
        }
        return null;
    }

    private void httpDown(String URL) {
        Request request = new Request.Builder().url(URL).build();
        new OkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                InputStream is = null;
                byte[] buffer = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                File file = new File(ToddApplication.application.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath(), id++ + ".jpg");
                try {
                    is = response.body().byteStream();
                    long total = response.body().contentLength();
                    fos = new FileOutputStream(file);
                    long sum = 0;
                    while ((len = is.read(buffer)) != -1) {
                        fos.write(buffer, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                    }
                    fos.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (is != null)
                        is.close();
                    if (fos != null)
                        fos.close();
                }
            }
        });
    }

}
