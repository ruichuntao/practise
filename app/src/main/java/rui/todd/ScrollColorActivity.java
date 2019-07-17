package rui.todd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;
import rui.view.ScrollColorView;

public class ScrollColorActivity extends AppCompatActivity {

    @BindView(R.id.sc1)
    ScrollColorView sc1;
    @BindView(R.id.sc2)
    ScrollColorView sc2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_color);
        ButterKnife.bind(this);
        sc1.addSlideListener(new ScrollColorView.SlideListener() {
            @Override
            public void slide(int length) {

            }
        });
        sc2.addSlideListener(new ScrollColorView.SlideListener() {
            @Override
            public void slide(int length) {

            }
        });

    }
}
