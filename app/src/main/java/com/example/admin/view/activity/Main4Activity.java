package com.example.admin.view.activity;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.admin.base.BaseActivity;
import com.example.admin.xyp_android_frame.R;

import butterknife.BindView;

public class Main4Activity extends BaseActivity {

    @BindView(R.id.dependency)
    Button button;

    @Override
    public String initTitle() {
        return "自定义Behavior";
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main4;
    }

    @Override
    protected void init() {
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        button.setX(event.getRawX() - v.getWidth() / 2);
                        button.setY(event.getRawY() - v.getHeight() / 2);
                        break;
                }
                return false;
            }
        });
    }

}
