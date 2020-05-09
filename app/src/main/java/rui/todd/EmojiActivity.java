package rui.todd;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import rui.adapter.EmojiAdapter;

public class EmojiActivity extends AppCompatActivity {
    ViewPager pager;
    public final String emojiMap = "\uD83D\uDE00,\n" +
            "\uD83D\uDE01,\n" +
            "\uD83D\uDE02,\n" +
            "\uD83D\uDE03,\n" +
            "\uD83D\uDE04,\n" +
            "\uD83D\uDE05,\n" +
            "\uD83D\uDE06,\n" +
            "\uD83D\uDE07,\n" +
            "\uD83D\uDE08,\n" +
            "\uD83D\uDE09,\n" +
            "\uD83D\uDE0A,\n" +
            "\uD83D\uDE0B,\n" +
            "\uD83D\uDE0C,\n" +
            "\uD83D\uDE0D,\n" +
            "\uD83D\uDE0E,\n" +
            "\uD83D\uDE0F,\n" +
            "\uD83D\uDE10,\n" +
            "\uD83D\uDE11,\n" +
            "\uD83D\uDE12,\n" +
            "\uD83D\uDE13,\n" +
            "\uD83D\uDE14,\n" +
            "\uD83D\uDE15,\n" +
            "\uD83D\uDE16,\n" +
            "\uD83D\uDE17,\n" +
            "\uD83D\uDE18,\n" +
            "\uD83D\uDE19,\n" +
            "\uD83D\uDE1A,\n" +
            "\uD83D\uDE1B,\n" +
            "\uD83D\uDE1C,\n" +
            "\uD83D\uDE1D,\n" +
            "\uD83D\uDE1E,\n" +
            "\uD83D\uDE1F,\n" +
            "\uD83D\uDE20,\n" +
            "\uD83D\uDE21,\n" +
            "\uD83D\uDE22,\n" +
            "\uD83D\uDE23,\n" +
            "\uD83D\uDE24,\n" +
            "\uD83D\uDE25,\n" +
            "\uD83D\uDE26,\n" +
            "\uD83D\uDE27,\n" +
            "\uD83D\uDE28,\n" +
            "\uD83D\uDE29,\n" +
            "\uD83D\uDE2A,\n" +
            "\uD83D\uDE2B,\n" +
            "\uD83D\uDE2C,\n" +
            "\uD83D\uDE2D,\n" +
            "\uD83D\uDE2E,\n" +
            "\uD83D\uDE2F,\n" +
            "\uD83D\uDE30,\n" +
            "\uD83D\uDE31,\n" +
            "\uD83D\uDE32,\n" +
            "\uD83D\uDE33,\n" +
            "\uD83D\uDE34,\n" +
            "\uD83D\uDE35,\n" +
            "\uD83D\uDE36,\n" +
            "\uD83D\uDE37,\n" +
            "\uD83D\uDE38,\n" +
            "\uD83D\uDE39,\n" +
            "\uD83D\uDE3A,\n" +
            "\uD83D\uDE3B,\n" +
            "\uD83D\uDE3C,\n" +
            "\uD83D\uDE3D,\n" +
            "\uD83D\uDE3E,\n" +
            "\uD83D\uDE3F,\n" +
            "\uD83D\uDE40,\n" +
            "\uD83D\uDE45,\n" +
            "\uD83D\uDE46,\n" +
            "\uD83D\uDE47,\n" +
            "\uD83D\uDE48,\n" +
            "\uD83D\uDE49,\n" +
            "\uD83D\uDE4A,\n" +
            "\uD83D\uDE4B,\n" +
            "\uD83D\uDE4C,\n" +
            "\uD83D\uDE4D,\n" +
            "\uD83D\uDE4E,\n" +
            "\uD83D\uDE4F,\n" +
            "\uD83D\uDC42,\n" +
            "\uD83D\uDC43,\n" +
            "\uD83D\uDC44,\n" +
            "\uD83D\uDC45,\n" +
            "\uD83D\uDC46,\n" +
            "\uD83D\uDC47,\n" +
            "\uD83D\uDC48,\n" +
            "\uD83D\uDC49,\n" +
            "\uD83D\uDC4A,\n" +
            "\uD83D\uDC4B,\n" +
            "\uD83D\uDC4C,\n" +
            "\uD83D\uDC4D,\n" +
            "\uD83D\uDC4E,\n" +
            "\uD83D\uDC4F,\n" +
            "\uD83D\uDC50,\n" +
            "\uD83D\uDC8F,\n" +
            "\uD83D\uDC90,\n" +
            "\uD83D\uDC91,\n" +
            "\uD83D\uDC92,\n" +
            "\uD83D\uDC93,\n" +
            "\uD83D\uDC94,\n" +
            "\uD83D\uDC95,\n" +
            "\uD83D\uDC96,\n" +
            "\uD83D\uDC97,\n" +
            "\uD83D\uDC98,\n" +
            "\uD83D\uDC99,\n" +
            "\uD83D\uDC9A,\n" +
            "\uD83D\uDC9B,\n" +
            "\uD83D\uDC9C";
    private Set<String> emojiSet = new HashSet<>();
    private ImageView[] mPagerDots;
    private LinearLayout dotsContainer;
    private EditText edit;


    public class EmojiGridAdapter extends PagerAdapter {

        private List<View> views;

        public EmojiGridAdapter(List<View> views) {
            this.views = views;
        }

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(views.get(position));
            return views.get(position);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(views.get(position));
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emoji);
        pager = findViewById(R.id.pager);
        edit = findViewById(R.id.edit);
        dotsContainer = findViewById(R.id.dotsContainer);
        List<List<String>> lists = new ArrayList<>();
        String[] map = emojiMap.split(",\n");
        emojiSet.addAll(Arrays.asList(map));
        int len = map.length;
        List<String> tmp = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if (i % 23 == 0 && i != 0) {
                tmp.add("");
                lists.add(tmp);
                tmp = new ArrayList<>();
                tmp.add(map[i]);
            } else {
                tmp.add(map[i]);
            }
        }
        tmp.add("");
        lists.add(tmp);
        mPagerDots = new ImageView[lists.size()];
        List<View> views = new ArrayList<>();
        for (int i = 0; i < lists.size(); i++) {
            View childView = getChildView(lists, i);
            views.add(childView);
            mPagerDots[i] = new ImageView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(dp2Px(3), 0, dp2Px(3), 0);
            mPagerDots[i].setLayoutParams(params);
            mPagerDots[i].setImageResource(i == 0 ? R.drawable.emoji_dot_on : R.drawable.emoji_dot_off);
            dotsContainer.addView(mPagerDots[i]);
        }
        EmojiGridAdapter viewAdapter = new EmojiGridAdapter(views);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < mPagerDots.length; i++) {
                    if (i == position) {
                        mPagerDots[i].setImageResource(R.drawable.emoji_dot_on);
                    } else {
                        mPagerDots[i].setImageResource(R.drawable.emoji_dot_off);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        pager.setAdapter(viewAdapter);
    }

    public interface ClickEmojiListener {
        void onClickEmoji(String emoji);

        void onClickDelete();
    }

    private View getChildView(List<List<String>> lists, int idx) {
        View view = View.inflate(this, R.layout.emoji_one_grid, null);
        NoScrollGridView gridView = view.findViewById(R.id.gift_gridView);
        EmojiAdapter emojiAdapter = new EmojiAdapter(lists.get(idx), this, new ClickEmojiListener() {
            @Override
            public void onClickEmoji(String emoji) {
                edit.getText().append(emoji);
            }

            @Override
            public void onClickDelete() {
                int s = edit.getSelectionStart();
                int e = edit.getSelectionEnd();
                String str = edit.getText().toString();
                //过滤输入为空情况
                if (!TextUtils.isEmpty(str)) {
                    //光标多选删除
                    if (s != e)
                        edit.getText().delete(s, e);
                    else {
                        //判断当前删除的是否是emoji👿
                        if (e > 1) {
                            String cur = edit.getText().toString().substring(e - 2);
                            if (emojiSet.contains(cur)) {
                                edit.getText().delete(e - 2, e);
                            } else {
                                edit.getText().delete(e - 1, e);
                            }
                        } else {
                            edit.getText().delete(e - 1, e);
                        }
                    }
                }
            }
        });
        gridView.setAdapter(emojiAdapter);
        return view;
    }


    public int dp2Px(float dp) {
        final float scale = getResources().getDisplayMetrics().density; //当前屏幕密度因子
        return (int) (dp * scale + 0.5f);
    }

}
