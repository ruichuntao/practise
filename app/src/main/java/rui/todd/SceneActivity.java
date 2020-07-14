package rui.todd;

import androidx.transition.Scene;
import androidx.transition.TransitionManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

public class SceneActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private Scene scene1;
    private Scene scene2;
    private Scene scene3;
    private FrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene);
        container = findViewById(R.id.container);
        scene1 = Scene.getSceneForLayout(container, R.layout.layout_scene1, this);
        scene2 = Scene.getSceneForLayout(container, R.layout.layout_scene2, this);
        scene3 = Scene.getSceneForLayout(container, R.layout.layout_scene3, this);
//        RadioButton radio1 = findViewById(R.id.radio1);
//        RadioButton radio2 = findViewById(R.id.radio2);
//        RadioButton radio3 = findViewById(R.id.radio3);
//        radio1.setOnCheckedChangeListener(this);
//        radio2.setOnCheckedChangeListener(this);
//        radio3.setOnCheckedChangeListener(this);
        RadioGroup group = findViewById(R.id.group);
        group.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radio1:
                TransitionManager.go(scene1);
                break;
            case R.id.radio2:
                TransitionManager.go(scene2);
                break;
            case R.id.radio3:
                TransitionManager.go(scene3);
                break;
        }
    }

}