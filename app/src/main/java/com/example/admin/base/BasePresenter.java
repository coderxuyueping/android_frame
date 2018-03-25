package com.example.admin.base;

/**
 * Created by xyp on 2018/3/9.
 */

public interface BasePresenter<T extends BaseView> {
    void attachView(T view);
    void detachView();
}
