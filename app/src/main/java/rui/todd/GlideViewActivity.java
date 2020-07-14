package rui.todd;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

public class GlideViewActivity extends AppCompatActivity {

    @BindView(R.id.img1) ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_view);
        ButterKnife.bind(this);
        img.setImageDrawable(getResources().getDrawable(R.drawable.diamond_new));
        LinearLayout linearLayout = new LinearLayout(this);
    }

    @OnClick(R.id.img1)
    public void imgClick() {
        img.setImageDrawable(getResources().getDrawable(R.drawable.zelda));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @OnLongClick(R.id.img1)
    public boolean imgLong() {
        Toast.makeText(this, "长按", Toast.LENGTH_SHORT).show();
        return true;

    }


}
