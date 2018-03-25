package com.example.admin.view.activity;


import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.example.admin.base.AppStatus;
import com.example.admin.base.BaseMvpActivity;
import com.example.admin.model.bean.AdInfo;
import com.example.admin.presenter.Main2Presenter;
import com.example.admin.presenter.contract.Main2Contract;
import com.example.admin.utils.ToastUtil;
import com.example.admin.xyp_android_frame.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class Main2Activity extends BaseMvpActivity<Main2Presenter> implements Main2Contract.View{

    @BindView(R.id.banner)
    Banner banner;

    @Override
    protected void init() {
        super.init();
        banner.start();
    }

    @Override
    public String initTitle() {
        return "图片轮播";
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main2;
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    protected Main2Presenter initPresenter() {
        return new Main2Presenter();
    }

    @Override
    public void updateBanner(List<AdInfo> adInfoList) {
        List<String> url = new ArrayList<>();
        for(AdInfo adInfo : adInfoList){
            url.add(adInfo.getHeadUrl());
        }
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setImages(url);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //Table ForegroundToBackground
        banner.setBannerAnimation(Transformer.ForegroundToBackground);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Log.d("xudaha", path.toString());
                com.example.admin.imageLoader.ImageLoader.load(Main2Activity.this, path.toString(), imageView);
            }
        });
        banner.start();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                ToastUtil.show(AppStatus.getInstance(), position+"");
            }
        });
    }

    @OnClick(R.id.load)
    public void load(){
        mPresenter.load();
    }

    @OnClick(R.id.update)
    public void update(){
        mPresenter.update();
    }

    @Override
    protected void onStart() {
        super.onStart();
        banner.startAutoPlay();//开始轮播
    }

    @Override
    protected void onStop() {
        super.onStop();
        banner.stopAutoPlay();//结束轮播
    }

    @OnClick(R.id.dump)
    public void dump(){
        startActivity(Main3Activity.class);
    }
}
