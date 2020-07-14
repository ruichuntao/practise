package rui.todd;

import androidx.appcompat.app.AppCompatActivity;
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
