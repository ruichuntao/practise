package rui.todd;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Messenger;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.aigestudio.wheelpicker.WheelPicker;
import com.example.apt_annotation.Print;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.ButterKnife;
import rui.bean.Province;
import rui.dialog.ExpEvaluationDialog;
import rui.todd.databinding.LayoutCityWheelPickerBinding;

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


    int selectProvince;
    int selectCity;

    public void clickCompose(View view) {
        LayoutCityWheelPickerBinding bind = LayoutCityWheelPickerBinding.inflate(LayoutInflater.from(this));
        bind.province.setCurved(true);
        bind.city.setCurved(true);
        String json = null;
        try {
            InputStream open = getAssets().open("cityList.json");
            int size = open.available();
            byte[] bytes = new byte[size];
            open.read(bytes);
            open.close();
            json = new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Province> cityJson = new Gson().fromJson(json, new TypeToken<List<Province>>() {
        }.getType());
        int size = cityJson.size();
        List<List<String>> map = new ArrayList<>();
        List<String> province = new ArrayList<>(size);
        for (Province p : cityJson) {
            province.add(p.name);
            List<String> city = new ArrayList<>();
            for (Province.City c : p.subOpt) {
                city.add(c.name);
            }
            map.add(city);
        }
        bind.province.setData(province);
        bind.city.setData(map.get(0));
        bind.province.setOnItemSelectedListener((picker, data, position) -> {
            bind.city.setData(map.get(position));
            selectProvince = position;
        });
        bind.city.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelPicker picker, Object data, int position) {
                selectCity = position;
            }
        });
        Dialog dialog = new BottomSheetDialog(this);
        dialog.getWindow().getDecorView().setPadding(0, 0, 0, 0);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        dialog.setContentView(bind.getRoot());
        bind.cancel.setOnClickListener(v -> dialog.dismiss());
        bind.submit.setOnClickListener(v -> {
            dialog.dismiss();
            Log.e(TAG, "clickCompose: " + province.get(selectProvince) + map.get(selectProvince).get(selectCity));
        });
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
