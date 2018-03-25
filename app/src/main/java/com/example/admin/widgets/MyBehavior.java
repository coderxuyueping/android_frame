package com.example.admin.widgets;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by xyp on 2018/3/20.
 */

public class MyBehavior extends CoordinatorLayout.Behavior {
    public MyBehavior(Context context, AttributeSet attrs) {
        super();
    }

    //找到监听的是谁，也就是被观察者
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof Button;
    }


    //被观察者的变化
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        child.setX(dependency.getX());
        child.setY(dependency.getY());
        Log.d("xudaha", "dependent_X:" + dependency.getX() + "-----dependent_Y:" + dependency.getY());
        return true;
    }
}
