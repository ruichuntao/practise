package rui.todd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class SIngleInstance2Activity extends AppCompatActivity {
    private static final String TAG = "LaunchActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_instance);
        Log.d(TAG, "onCreate: SingleInstance2Activity");

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
        Log.d(TAG, "onStart: SingleInstance2Activity");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent: SingleInstance2Activity");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: SingleInstance2Activity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: SingleInstance2Activity");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: SingleInstance2Activity");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: SingleInstance2Activity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: SingleInstance2Activity");
    }
}
