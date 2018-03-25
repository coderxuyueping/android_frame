package com.example.admin.base;

import android.app.Application;

import com.bumptech.glide.Glide;
import com.example.admin.xyp_android_frame.EventBusIndex;
import com.squareup.leakcanary.LeakCanary;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by xyp on 2018/3/12.
 */

public class AppStatus extends Application {

    private static AppStatus instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
        //启用EventBus加速,第一次无法生成EEventBusIndex，需要有@Subscribe,然后可以在build中找到
        EventBus.builder().addIndex(new EventBusIndex()).installDefaultEventBus();
    }

    @Subscribe
    public void onEvent(Object o){}
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        // 程序在内存清理的时候执行
        if(level == TRIM_MEMORY_UI_HIDDEN)
            Glide.get(this).clearMemory();
        Glide.get(this).trimMemory(level);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        // 低内存的时候执行
        Glide.get(this).clearMemory();
    }

    public static AppStatus getInstance(){
        return instance;
    }
}
