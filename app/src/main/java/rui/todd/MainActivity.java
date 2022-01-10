package rui.todd;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.ActionBar;
import android.app.Dialog;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.provider.Settings;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ReportFragment;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.FragmentTransitionSupport;
import androidx.viewpager.widget.ViewPager;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.example.apt_annotation.Print;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.io.ByteArrayOutputStream;
import java.util.Collections;
import java.util.Locale;
import java.util.Scanner;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Request;
import rui.dialog.ExpEvaluatedDialog;
import rui.dialog.ExpEvaluationDialog;
import rui.proxy.HookManager;
import rui.service.MessengerService;
import rui.viewmodel.UserModel;

import static androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY;

public class MainActivity extends BaseActivity implements DialogPopup.dialogListener {
    private static final String TAG = "MainActivity";
    @Print
    private String name;
    @Print
    private int age;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    public void clickbubble(View view) {
        startActivity(new Intent(this, BuddleActivity.class));
    }


    public void clickToolbar(View view) {
        startActivity(new Intent(this, ToolBarActivity.class));
    }

    public void Toolbar(View view) {
        startActivity(new Intent(this, ScrollingActivity.class));
    }

    public void dialogfragment(View view) {
        DialogPopup popup = new DialogPopup();
        popup.show(getSupportFragmentManager(), "dialog");
    }

    @Override
    public void getData(String dataName, int year, int month, int day) {
        Toast.makeText(this, dataName + year + month + day, Toast.LENGTH_SHORT).show();
    }

    public void intentSRC(View view) {
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.background);
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
//        byte[] b = baos.toByteArray();

        Intent intent = new Intent(this, PictureActivity.class);
//        intent.putExtra("picture", b);
        startActivity(intent);

    }

    public void svga(View view) {
        startActivity(new Intent(this, SvgaActivity.class));
    }

    public void lifeCycle(View view) {
        startActivity(new Intent(this, LifeCycleActivity.class));
    }

    public void clickViewPager(View view) {
        startActivity(new Intent(this, ViewPagerAcitivity.class));
    }

    public void clickSearch(View view) {
        startActivity(new Intent(this, SearchAnimationActivity.class));
    }

    public void clickOpenBox(View view) {
        startActivity(new Intent(this, OpenBoxActivity.class));
    }

    public void clickPhotoView(View view) {
        startActivity(new Intent(this, PhotoViewActivity.class));
    }

    public void clickGlideView(View view) {
        startActivity(new Intent(this, GlideViewActivity.class));
    }

    private String[] countrySet() {
        return Locale.getISOCountries();
    }

    public void clicklove(View view) {
        startActivity(new Intent(this, LoveAnimActivity.class));

    }

    public void click2Recycler(View view) {
        startActivity(new Intent(this, RecyclerView2Activity.class));
    }

    public void clickMatch(View view) {
        startActivity(new Intent(this, MatchAnimActivity.class));
    }

    public void clickDefine(View view) {
        startActivity(new Intent(this, ScrollColorActivity.class));
    }

    public void clickKeji(View view) {
        startActivity(new Intent(this, KeJiActivity.class));
    }

    public void clickPathKeji(View view) {
        startActivity(new Intent(this, KeJiPathActivity.class));

    }

    public void clickStartAnim(View view) {
        startActivity(new Intent(this, StartAnimActivity.class));
    }

    public void clicknewMatchAnim(View view) {
        startActivity(new Intent(this, MatchNewAnimActivity.class));
    }

    public void clickDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        Dialog dialog = builder.create();
        dialog.setContentView(R.layout.activity_buddle);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = 500;
        params.height = 500;
        window.setGravity(Gravity.TOP);
        dialog.show();
    }


    public void clickCirle(View view) {
        startActivity(new Intent(this, CircleActivity.class));
    }

    public void clickStudy(View view) {
        startActivity(new Intent(this, TestHorRecyclerActivity.class));
    }

    public void clickEmoji(View view) {
        startActivity(new Intent(this, EmojiActivity.class));
    }


    public void clickPlay(View view) {
        startActivity(new Intent(this, AutoVideoActivity.class));
    }

    public void clickScreen(View view) {
        startActivity(new Intent(this, ScreenTimeActivity.class));
    }

    public void clickAlgorithm(View view) {
        startActivity(new Intent(this, MazeActivity.class));
    }


    public void clickScene(View view) {
        startActivity(new Intent(this, SceneActivity.class));
    }

    public void radio(View view) {
        startActivity(new Intent(this, RadioActivity.class));
    }


    public void clickEvent(View view) {
        startActivity(new Intent(this, EventDispatchActivity.class));
    }

    public void clickPull(View view) {
        Intent intent = new Intent(this, PullToZoomActivity.class);
        startActivity(intent);
    }


    public void clickDown(View view) {
        startActivity(new Intent(this, DownloadActivity.class));
    }

    public void clickWebView(View view) {
        startActivity(new Intent(this, WebViewActivity.class));
    }

    public void clickCircularReveal(View view) {
        startActivity(new Intent(this, CircularRevealActivity.class));
    }

    public void clickFragment(View view) {
        startActivity(new Intent(this, FragmentActivity.class));
    }

    public void clickInterestingOpenCloseAni(View view) {
        ExpEvaluationDialog dialog = new ExpEvaluationDialog();
        dialog.show(getSupportFragmentManager(), "ttt");
    }

    public void clickCloseOpen(View view) {
//        LottieAnimationView lottie = new LottieAnimationView(this);
        FrameLayout decorView = (FrameLayout) getWindow().getDecorView();
//        lottie.setAnimation("open_box_lottie.json");
//        lottie.setImageAssetsFolder("images");
//        lottie.setRepeatCount(-1);
//        lottie.playAnimation();
//        lottie.setBackgroundColor(Color.parseColor("#333333"));
        View xx = new View(this);
        xx.setBackgroundColor(Color.BLACK);
        ObjectAnimator animator = ObjectAnimator.ofFloat(xx, "translationY", -200, 200);
        animator.setDuration(2000);
        animator.setRepeatCount(-1);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatMode(ValueAnimator.RESTART);

//        ValueAnimator animator = ValueAnimator.ofFloat(0, -200, 200, 0);
//        animator.setDuration(2000);
//        animator.setRepeatCount(-1);
//        animator.setInterpolator(new LinearInterpolator());
//        animator.setRepeatMode(ValueAnimator.REVERSE);
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                float value = (float) animation.getAnimatedValue();
//                xx.setTranslationY(value);
//            }
//        });
        xx.setOnClickListener(v -> {
            animator.start();
        });
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(300, 300);
        params.gravity = Gravity.CENTER;
        xx.setLayoutParams(params);
        decorView.addView(xx);
    }

    public void clickRate(View view) {
        startActivity(new Intent(this, RateControlActivity.class));
    }

    public void clickVideo(View view) {
        startActivity(new Intent(this, SurfaceMediaActivity.class));
    }

    public void clickBanner(View view) {
        startActivity(new Intent(this, BannerActivity.class));
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    private Messenger mService;

    private boolean bound;

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = new Messenger(service);
            bound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            bound = false;
        }
    };


}
