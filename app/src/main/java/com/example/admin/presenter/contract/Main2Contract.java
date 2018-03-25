package com.example.admin.presenter.contract;

import com.example.admin.base.BasePresenter;
import com.example.admin.base.BaseView;
import com.example.admin.model.bean.AdInfo;

import java.util.List;

/**
 * Created by xyp on 2018/3/16.
 */

public interface Main2Contract {
    interface View extends BaseView{
        void updateBanner(List<AdInfo> adInfo);
    }

    interface Presenter extends BasePresenter<View>{
        void load();
        void update();
    }
}
