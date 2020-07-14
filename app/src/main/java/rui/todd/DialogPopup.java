package rui.todd;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aigestudio.wheelpicker.widgets.WheelDatePicker;

public class DialogPopup extends DialogFragment {

    private dialogListener listener;
    private ImageView close;
    private EditText memoryEt;
    private EditText memoryDate;
    private TextView save;
    private int year, month, day;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog, null);
        close = view.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        memoryEt = view.findViewById(R.id.memory_et);
        memoryDate = view.findViewById(R.id.memory_date);
        memoryDate.setFocusable(false);
        memoryDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final WheelDatePicker picker = new WheelDatePicker(getContext());
                picker.setBackgroundColor(0xffffff);
                picker.setVisibleItemCount(3);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setView(picker);
                builder.setTitle("选择日期").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        year = picker.getCurrentYear();
                        month = picker.getCurrentMonth();
                        day = picker.getCurrentDay();
                        memoryDate.setText(String.valueOf(year) + "/" + String.valueOf(month) + "/" + String.valueOf(day));
                        dialog.dismiss();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                Dialog dialog = builder.create();
                dialog.setCanceledOnTouchOutside(false);
                setCancelable(false);
                dialog.show();
            }
        });
        save = view.findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String memory = memoryEt.getText().toString();
                String date = memoryDate.getText().toString();
                if (!TextUtils.isEmpty(memory) && !TextUtils.isEmpty(date)) {
                    dismiss();
                    listener.getData(memory, year, month, day);
                } else {
                    Toast.makeText(getContext(), "纪念日或日期为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (dialogListener) context;
    }

    public interface dialogListener {
        public void getData(String dataName, int year, int month, int day);
    }
}
