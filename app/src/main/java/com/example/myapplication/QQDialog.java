package com.example.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class QQDialog extends Dialog implements View.OnClickListener {
    private TextView video;
    private TextView photo;
    private TextView cancle;

    public QQDialog(@NonNull Context context) {
        super(context, R.style.DialogStyle);
        setContentView(R.layout.dialog_qq);
        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = UIUtils.getScreenWidth(context);//设置Dialog宽度
        layoutParams.gravity = Gravity.BOTTOM;//设置Dialog显示位置
        window.setAttributes(layoutParams);
        window.setWindowAnimations(R.style.DialogAnim);//设置Dialog动画
        setCanceledOnTouchOutside(true); //点击Dialog外部区域取消
        setCancelable(true);//可以取消
        initViews();
    }

    private void initViews() {
        video = findViewById(R.id.video);
        photo = findViewById(R.id.photo);
        cancle = findViewById(R.id.cancle);

        video.setOnClickListener(this);
        photo.setOnClickListener(this);
        cancle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.video:
                dismiss();
                if (mOnDialogClickListener != null)
                    mOnDialogClickListener.onClickVideo();
                break;
            case R.id.photo:
                dismiss();
                if (mOnDialogClickListener != null)
                    mOnDialogClickListener.onClickPhoto();
                break;
            case R.id.cancle:
                dismiss();
                break;
        }
    }

    public interface OnDialogClickListener {
        void onClickVideo();

        void onClickPhoto();
    }

    private OnDialogClickListener mOnDialogClickListener;

    public void setOnDialogClickListener(OnDialogClickListener onDialogClickListener) {
        mOnDialogClickListener = onDialogClickListener;
    }
}
