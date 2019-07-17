package rui.todd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;
import rui.view.KeJiView;

public class KeJiActivity extends AppCompatActivity {
    @BindView(R.id.keji)
    KeJiView keji;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ke_ji);
        ButterKnife.bind(this);
    }
}
