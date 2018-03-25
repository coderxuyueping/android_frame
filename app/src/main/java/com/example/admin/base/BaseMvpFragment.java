package com.example.admin.base;


/**
 * Created by xyp on 2018/3/9.
 */

public abstract class BaseMvpFragment<P extends BasePresenter> extends BaseFragment implements BaseView{
    protected P mPresenter;

    @Override
    protected void init() {
        mPresenter = initPresenter();
        if(mPresenter != null)
            mPresenter.attachView(this);

        super.init();
    }

    protected abstract P initPresenter();


    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mPresenter != null){
            mPresenter.detachView();
            mPresenter = null;
        }
    }
}
