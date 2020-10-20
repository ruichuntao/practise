package rui.todd;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rui.service.MusicService;
import rui.utils.DownTask;

public class DownloadActivity extends AppCompatActivity {

    private static final String TAG = "DownloadActivity";

    Executor executor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
//        String URL = "http://rm01.sycdn.kuwo.cn/b65de2138d30d6419d2cb5f450a7d1e6/5f44dc8d/resource/n1/94/51/1202618895.mp3";
//        String[] dl = {
//                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1598431528801&di=23d9c32f969e703ad208671a856194c0&imgtype=0&src=http%3A%2F%2Fa3.att.hudong.com%2F14%2F75%2F01300000164186121366756803686.jpg"
//                , "https://dss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3892521478,1695688217&fm=26&gp=0.jpg"
//                , "https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1906469856,4113625838&fm=26&gp=0.jpg"};
//        new DownTask().executeOnExecutor(executor, dl);
//        new Thread(() -> {
//            Intent intent = new Intent(this, MusicService.class);
//            intent.putExtra("url", URL);
//            startService(intent);
//        }).start();

    }


}