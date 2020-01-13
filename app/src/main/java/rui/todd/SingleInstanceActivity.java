package rui.todd;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class SingleInstanceActivity extends AppCompatActivity {
    private static final String TAG = "LaunchActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_instance);
        Log.d(TAG, "onCreate: SingleInstanceActivity");

    }

    public void startTop(View view) {
        startActivity(new Intent(this, SingleTopActivity.class));
    }

    public void startInstance(View view) {
        startActivity(new Intent(this, SingleInstanceActivity.class));

    }

    public void startTask(View view) {
        startActivity(new Intent(this, SingleTaskActivity.class));
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: SingleInstanceActivity");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent: SingleInstanceActivity");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: SingleInstanceActivity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: SingleInstanceActivity");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: SingleInstanceActivity");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: SingleInstanceActivity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: SingleInstanceActivity");
    }
}
