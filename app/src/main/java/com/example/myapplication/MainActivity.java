package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
    }

    //普通AlertDialog
    public void btn01(View view) {
        new AlertDialog.Builder(context)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("系统提示")
                .setMessage("这是一个普通AlertDialog")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "取消", Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "确定", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    //普通列表AlertDialog
    public void btn02(View view) {
        final String[] fruits = {"apple", "banana", "cherry", "grape", "mango", "pear", "pineapple", "strawberry", "watermelon"};
        new AlertDialog.Builder(context)
                .setTitle("水果列表")
                .setItems(fruits, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, fruits[which], Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    //单选列表AlertDialog
    public void btn03(View view) {
        final String[] fruits = {"apple", "banana", "cherry", "grape", "mango", "pear", "pineapple", "strawberry", "watermelon"};
        new AlertDialog.Builder(context)
                .setTitle("水果列表")
                .setSingleChoiceItems(fruits, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, fruits[which], Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .show();
    }

    //多选列表AlertDialog
    public void btn04(View view) {
        final String[] fruits = {"apple", "banana", "cherry", "grape", "mango", "pear", "pineapple", "strawberry", "watermelon"};
        final boolean[] checkItems = {false, false, false, false, false, false, false, false, false};
        new AlertDialog.Builder(context)
                .setTitle("水果列表")
                .setMultiChoiceItems(fruits, checkItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        checkItems[which] = isChecked;
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String ret = "";
                        for (int i = 0; i < checkItems.length; i++) {
                            if (checkItems[i]) {
                                ret += fruits[i] + " ";
                            }
                        }
                        Toast.makeText(context, ret, Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    //自定义AlertDialog
    public void btn05(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        AlertDialog alertDialog = builder.create();
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_custom_view, null);
        dialogView.findViewById(R.id.btnCancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        dialogView.findViewById(R.id.btnConfirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "确定", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });
        alertDialog.setView(dialogView);
        alertDialog.setCancelable(false);//按返回键是否取消
        alertDialog.setCanceledOnTouchOutside(false);//点击Dialog外部是否取消
        alertDialog.show();
    }

    //普通圆形ProgressDialog
    public void btnProgressDialog01(View view) {
        ProgressDialog.show(MainActivity.this, "提示", "正在加载。。。", false, true);
    }

    private ProgressDialog progressDialog;

    //普通条形ProgressDialog
    public void btnProgressDialog02(View view) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("提示");
        progressDialog.setMessage("正在加载。。。");
        progressDialog.setCancelable(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setIndeterminate(false);//是否显示进度，必须设置为false才显示
        progressDialog.show();
        mHandler.sendEmptyMessageDelayed(22, 100);
    }

    final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 22) {
                progressDialog.setProgress(progressDialog.getProgress() + 1);
                if (progressDialog.getProgress() == 100) {
                    progressDialog.dismiss();
                    mHandler.removeCallbacksAndMessages(null);
                } else {
                    mHandler.sendEmptyMessageDelayed(22, 100);
                }
            }
        }
    };


    //日期DatePickerDialog
    public void btnDateDialog(View view) {
        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String ret = "";
                ret = year + "年" + (month + 1) + "月" + dayOfMonth + "日";
                Toast.makeText(context, ret, Toast.LENGTH_SHORT).show();
            }
        },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        ).show();
    }


    //时间TimePickerDialog
    public void btnTimeDialog(View view) {
        Calendar calendar = Calendar.getInstance();
        new TimePickerDialog(context, android.app.AlertDialog.THEME_HOLO_LIGHT, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String ret = "";
                ret = hourOfDay + "时" + minute + "分";
                Toast.makeText(context, ret, Toast.LENGTH_SHORT).show();
            }
        },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE), true
        ).show();
    }

    //自定义Dialog
    public void btnDialog(View view) {
        QQDialog qqDialog = new QQDialog(context);
        qqDialog.setOnDialogClickListener(new QQDialog.OnDialogClickListener() {
            @Override
            public void onClickVideo() {
                Toast.makeText(context, "点击了视频", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClickPhoto() {
                Toast.makeText(context, "点击了相册", Toast.LENGTH_SHORT).show();
            }
        });
        qqDialog.show();
    }
}