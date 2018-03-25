package com.example.admin.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.admin.xyp_android_frame.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by xyp on 2018/3/9.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected Context context;
    private Unbinder unbinder;
    private LinearLayout content;
    protected ActionBar actionBar;
    private Toolbar toolbar;
    private String title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar();
        setContentView(getLayout());
        context = this;
        unbinder = ButterKnife.bind(this);
        getIntentData();
        init();
    }

    private void initToolbar() {
        ViewGroup vg = (ViewGroup) findViewById(android.R.id.content);
        if (vg != null) {
            vg.removeAllViews();
            content = new LinearLayout(this);
            //主题已设
//            content.setBackgroundColor(getResources().getColor(R.color.bg_primary));
            content.setOrientation(LinearLayout.VERTICAL);
            vg.addView(content);
        }

        View view = LayoutInflater.from(this).inflate(R.layout.toolbar, content, true);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();

        // 如果 initTitle() 传入 null 则不显示 actionBar
        title = initTitle();//防止initTitle调用两次
        if (actionBar != null && title == null) {
            actionBar.hide();
            return;
        }

        if (actionBar != null) {
            actionBar.setTitle(title);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public abstract String initTitle();

    public void setToolbarColor(int color) {
        toolbar.setBackgroundColor(color);
    }

    public void setToolbarTitle(String title) {
        actionBar.setTitle(title);
    }

    @Override
    public void setContentView(int layoutResID) {
        LayoutInflater.from(this).inflate(layoutResID, content, true);
    }

    @Override
    public void setContentView(View view) {
        content.addView(view);
    }

    /**
     * @return layout id
     */
    protected abstract int getLayout();

    protected void getIntentData() {
    }

    protected void init() {
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null)
            unbinder.unbind();
    }

    public void startActivity(Class activityClass){
        Intent intent = new Intent(this, activityClass);
        startActivity(intent);
    }

    public void startActivity(Class activityClass, Bundle bundle){
        Intent intent = new Intent(this, activityClass);
        intent.putExtra("bundle", bundle);
        startActivity(intent);
    }

}
