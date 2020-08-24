package rui.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

import rui.todd.GlideApp;
import rui.todd.ToddApplication;

public class ImageLoader {
    public static void show(ImageView view, String url) {
        Glide.with(ToddApplication.getApplication()).load(url).into(view);
        GlideApp.with(ToddApplication.getApplication()).load(url).into(view);
    }
}
