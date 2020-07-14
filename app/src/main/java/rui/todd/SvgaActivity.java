package rui.todd;

import android.graphics.Color;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.LinearLayout;

import com.opensource.svgaplayer.SVGADrawable;
import com.opensource.svgaplayer.SVGADynamicEntity;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;


public class SvgaActivity extends AppCompatActivity {

    private SVGAImageView svgaImageView;
    private SVGAParser parser = new SVGAParser(this);
    private LinearLayout oneMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svga);
        svgaImageView = findViewById(R.id.svga_iv);
        oneMore = findViewById(R.id.oneMore);
    }

    public void animation(View view) {
        final ImageSpan imageSpan = new ImageSpan(this,R.drawable.diamond_new);
        final float scale = getResources().getDisplayMetrics().density;
        oneMore.setVisibility(View.GONE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                oneMore.setVisibility(View.VISIBLE);
            }
        },500);
        parser.parse("openReward.svga", new SVGAParser.ParseCompletion() {
            @Override
            public void onComplete( SVGAVideoEntity svgaVideoEntity) {
                SVGADynamicEntity dynamicItem = new SVGADynamicEntity();
                TextPaint paint = new TextPaint();
                paint.setColor(Color.parseColor("#ad8854"));
                paint.setFakeBoldText(true);
                paint.setTextSize(scale*20+0.5f);
                dynamicItem.setDynamicText("奖励详情",paint,"img_182");
                TextPaint paint1 = new TextPaint();
                paint1.setColor(Color.parseColor("#644821"));
                paint1.setTextSize(scale*15+0.5f);
                dynamicItem.setDynamicText("10000礼物",paint1,"img_180");
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("10-8000钻");
                TextPaint paint2 = new TextPaint();
                paint2.setColor(Color.parseColor("#ad8854"));
                paint2.setTextSize(scale*12+0.5f);
                spannableStringBuilder.setSpan(imageSpan,7,8,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                dynamicItem.setDynamicText(new StaticLayout(spannableStringBuilder,
                        0,
                        spannableStringBuilder.length(),
                        paint2,
                        0,
                        Layout.Alignment.ALIGN_CENTER,
                        1.0f,
                        0.0f,
                        false),"img_184");
                SVGADrawable drawable = new SVGADrawable(svgaVideoEntity, dynamicItem);
                svgaImageView.setImageDrawable(drawable);
                svgaImageView.setLoops(1);
                svgaImageView.setClearsAfterStop(false);
                svgaImageView.setFillMode(SVGAImageView.FillMode.Forward);
                svgaImageView.startAnimation();
            }

            @Override
            public void onError() {

            }
        });
    }

    public void oneMore(View view) {

    }
}
