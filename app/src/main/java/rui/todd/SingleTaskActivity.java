package rui.todd;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class SingleTaskActivity extends AppCompatActivity {
    private static final String TAG = "LaunchActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_task);
        Log.d(TAG, "onCreate: SingleTaskActivity");

    }

    public void startTop(View view) {
        Bundle bundle = new Bundle();
        Intent intent = new Intent(this, SingleTopActivity.class);
        startActivity(intent);
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
        Log.d(TAG, "onStart: SingleTaskActivity");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent: SingleTaskActivity");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: SingleTaskActivity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: SingleTaskActivity");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: SingleTaskActivity");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: SingleTaskActivity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: SingleTaskActivity");
    }
}
