package rui.todd;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import rui.bean.Box;

public class EventDispatchActivity extends BaseActivity {

    @BindView(R.id.lv_one)
    ListView lv_one;
    @BindView(R.id.lv_two)
    ListView lv_two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_dispatch);
        ButterKnife.bind(this);
        String[] strs1 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, R.layout.simple_text_view, strs1);
        lv_one.setAdapter(adapter1);
        String s2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String[] strs2 = s2.split("");
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, R.layout.simple_text_view, strs2);
        lv_two.setAdapter(adapter2);
    }

}