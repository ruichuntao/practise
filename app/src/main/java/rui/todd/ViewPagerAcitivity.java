package rui.todd;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.PagerTabStrip;
import androidx.viewpager.widget.ViewPager;

public class ViewPagerAcitivity extends AppCompatActivity {
    private ViewPager pager;
    private PagerTabStrip strip;
    private MyAdapter adapter;
    private View[] v = new View[2];
    private String[] titles = {"标题1","标题2"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_acitivity);
        pager = findViewById(R.id.view_pager);
        strip = findViewById(R.id.tab_strip);
        ((ViewPager.LayoutParams) strip.getLayoutParams()).isDecor = true;
        strip.setBackgroundColor(Color.RED);// 背景颜色
        strip.setTextColor(Color.WHITE);// 标题颜色，这里需要带透明度的颜色值
        strip.setTabIndicatorColor(Color.YELLOW);// 指示器颜色，这里需要带透明度的颜色值
        strip.setTextSize(TypedValue.COMPLEX_UNIT_SP, 26);// 字体大小
        View view= LayoutInflater.from(this).inflate(R.layout.activity_svga,null);
        View view1 = LayoutInflater.from(this).inflate(R.layout.activity_scrolling,null);
        v[0] = view;
        v[1] = view1;
        adapter = new MyAdapter(v);
        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(1);
        pager.setCurrentItem(0);
    }

    public class MyAdapter extends PagerAdapter{

        private View[] views;

        public MyAdapter(View[] views) {
            super();
            this.views = views;
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(views[position]);
            return views[position];
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
