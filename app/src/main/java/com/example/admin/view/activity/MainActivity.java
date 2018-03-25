package com.example.admin.view.activity;

import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.admin.base.BaseMvpActivity;
import com.example.admin.imageLoader.ImageLoader;
import com.example.admin.presenter.MainPresenter;
import com.example.admin.presenter.contract.MainContract;
import com.example.admin.utils.ToastUtil;
import com.example.admin.xyp_android_frame.R;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * android开发框架：MVP-RXJava-Retrofit-butterKnife-Glide-GreenDao
 */
public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainContract.View{

    @BindView(R.id.iv)
    ImageView imageView;
    @BindView(R.id.progress)
    ProgressBar progressBar;

    private String gifUrl = "https://baobao-3d.bj.bcebos.com/look/200/2006.gif";
    private String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1520844124063&di=4e5e14190b2d00e973fe582f3cd591f0&imgtype=0&src=http%3A%2F%2Fwww.taopic.com%2Fuploads%2Fallimg%2F140320%2F235013-14032020515270.jpg";

    @Override
    protected void init() {
        super.init();
        //功能防抖，3s内禁止重复点击
        RxView.clicks(imageView)
                .throttleFirst(3, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        Log.d("xudaha", "点击");
                    }
                });
    }

    @Override
    public String initTitle() {
        return "标题";
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(context, msg , Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMsg(String msg) {
        ToastUtil.show(context, "哈哈哈哈");
    }

    @Override
    public void updateProgress(long progress, long total) {
        progressBar.setMax((int)total);
        progressBar.setProgress((int)progress);
        Log.d("xudaha", progress+"");
    }

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    /**
     * 通过butterKnife注入
     */
    @OnClick(R.id.btn)
    public void getData(){
        mPresenter.loadData();
    }

    @OnClick(R.id.btn1)
    public void loadImage(){
        ToastUtil.show(this, "show");
        ImageLoader.loadGif(this, gifUrl, imageView);
    }

    @OnClick(R.id.btn2)
    public void loadData(){
        mPresenter.loadData1();
    }

    @OnClick(R.id.download)
    public void download(){
        mPresenter.download();
    }

    @OnClick(R.id.add)
    public void add(){
        mPresenter.add();
    }

    @OnClick(R.id.delete)
    public void delete(){
        mPresenter.delete();
    }

    @OnClick(R.id.update)
    public void update(){
        mPresenter.update();
    }

    @OnClick(R.id.query)
    public void query(){
        startActivity(Main2Activity.class);
    }

}
