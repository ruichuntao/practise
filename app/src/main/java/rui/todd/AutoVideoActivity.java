package rui.todd;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;

import rui.adapter.AutoVideoAdapter;

public class AutoVideoActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AutoVideoAdapter adapter;
    private static final String TAG = "AutoVideoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_video);
        Log.e(TAG, "onCreate: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "onRestart: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: ");
    }
}
