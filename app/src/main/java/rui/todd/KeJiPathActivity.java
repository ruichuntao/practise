package rui.todd;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import rui.Interface.IkeJiPath;
import rui.Interface.IkeJiPlay;
import rui.view.KeJiPathView;
import rui.view.KejiPlayView;

public class KeJiPathActivity extends AppCompatActivity {
    @BindView(R.id.keji)
    KeJiPathView keji;
    @BindView(R.id.kejiplay)
    KejiPlayView kejiplay;
    @BindView(R.id.clear)
    Button clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ke_jipath);
        ButterKnife.bind(this);
        keji.setIkeJiPath(new IkeJiPath() {
            @Override
            public void pointCount(int count) {
                clear.setText(String.valueOf(count));
            }
        });
        kejiplay.setIkeJiPlay(new IkeJiPlay() {
            @Override
            public void animEnd() {
                Toast.makeText(KeJiPathActivity.this,"动画结束",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void clickReDraw(View view) {
        kejiplay.setDajihe(keji.getDajihe());
    }

    public void clickClear(View view) {
        keji.getDajihe().clear();
        keji.getXies().clear();
        keji.getPaths().clear();
        keji.postInvalidate();
        kejiplay.postInvalidate();
        kejiplay.removeAllViews();
    }
}
