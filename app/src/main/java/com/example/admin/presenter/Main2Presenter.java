package com.example.admin.presenter;

import com.example.admin.base.RxPresenter;
import com.example.admin.model.bean.AdInfo;
import com.example.admin.model.http.HttpManager;
import com.example.admin.model.http.SimpleObserver;
import com.example.admin.presenter.contract.Main2Contract;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by xyp on 2018/3/16.
 */

public class Main2Presenter extends RxPresenter<Main2Contract.View> implements Main2Contract.Presenter {
    private String url = "http://room.1024.com/live/getad.aspx";
    private List<AdInfo> list = new ArrayList<>();
    @Override
    public void load() {
        HttpManager.getInstance().executeGet(url)
                .subscribe(new SimpleObserver<AdInfo>(null, AdInfo[].class) {
                    @Override
                    public void onError(String msg) {
                        mView.showError(msg);
                    }

                    @Override
                    public void addDisposable(Disposable d) {
                        Main2Presenter.this.addDisposable(d);
                    }

                    @Override
                    public void onDataSuccess(List<AdInfo> t) {
                        list.addAll(t);
                        mView.updateBanner(t);
                    }
                });
    }

    @Override
    public void update() {
        if(list != null){
            list.remove(1);
        }
        mView.updateBanner(list);
    }
}
