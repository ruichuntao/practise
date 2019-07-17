package rui.todd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import com.bm.library.Info;
import com.bm.library.PhotoView;

public class PhotoViewActivity extends AppCompatActivity {
    private PhotoView photoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);
        photoView = findViewById(R.id.img);
        photoView.enable();
        Info info = photoView.getInfo();
        // 从普通的ImageView中获取Info
        // Info info = PhotoView.getImageViewInfo(ImageView);
        // 从一张图片信息变化到现在的图片，用于图片点击后放大浏览，具体使用可以参照demo的使用
        photoView.animaFrom(info);
        // 从现在的图片变化到所给定的图片信息，用于图片放大后点击缩小到原来的位置，具体使用可以参照demo的使用
        photoView.animaTo(info, new Runnable() {
            @Override
            public void run() {
                //动画完成监听
            }
        });
        // 获取/设置 动画持续时间
//        photoView.setAnimaDuring( int during);
//        int d = photoView.getAnimaDuring();
        // 获取/设置 最大缩放倍数
//        photoView.setMaxScale( float maxScale);
//        float maxScale = photoView.getMaxScale();
        // 设置动画的插入器
//        Interpolator
        photoView.setInterpolator(new LinearInterpolator());
    }
}
