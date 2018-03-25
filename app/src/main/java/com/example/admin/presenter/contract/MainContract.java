package com.example.admin.presenter.contract;

import com.example.admin.base.BasePresenter;
import com.example.admin.base.BaseView;

/**
 * Created by xyp on 2018/3/9.
 */

public interface MainContract {
    interface View extends BaseView{
        void showMsg(String msg);
        void updateProgress(long progress, long total);
    }

    interface Presenter extends BasePresenter<View>{
        void loadData();
        void loadData1();
        void download();

        void add();
        void delete();
        void update();
        void query();
    }
}
