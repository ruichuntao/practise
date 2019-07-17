package rui.todd;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements DialogPopup.dialogListener {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();

        Intent intent = new Intent(this, PictureActivity.class);
        intent.putExtra("picture", b);
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
}
