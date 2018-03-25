package com.example.admin.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

/**
 * Created by xyp on 2018/3/9.
 */

public class ToastUtil {
    private static Toast toast;
    private static long oneTime;
    private static long twoTime;
    private static String oldMsg;

    public static void show(Context context, int resId){
        show(context, context.getApplicationContext().getString(resId), false);
    }

    public static void show(Context context, String msg){
        show(context, msg, false);
    }

    public static void show(Context context, int resId, boolean longShow){
        show(context, context.getApplicationContext().getString(resId), longShow);
    }


    /**
     * 去掉重复显示
     */
    public static void show(final Context context, final String message, final boolean longShow) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                int showTime = longShow ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT;
                if (toast == null) {
                    toast = Toast.makeText(context.getApplicationContext(), message, showTime);
                    toast.show();
                    oldMsg = message;
                    oneTime = System.currentTimeMillis();
                } else {
                    twoTime = System.currentTimeMillis();
                    if (message.equals(oldMsg)) {
                        if (twoTime - oneTime > showTime)
                            toast.show();
                    } else {
                        toast.setText(message);
                        toast.show();
                        oldMsg = message;
                    }
                }
                oneTime = twoTime;
            }
        });
    }
}
