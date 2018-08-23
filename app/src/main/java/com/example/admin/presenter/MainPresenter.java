package com.example.admin.presenter;

import android.support.v4.util.ArrayMap;
import android.text.TextUtils;

import com.example.admin.base.AppStatus;
import com.example.admin.base.RxPresenter;
import com.example.admin.model.bean.RoomList;
import com.example.admin.model.bean.Title;
import com.example.admin.model.bean.User;
import com.example.admin.model.db.DataBase;
import com.example.admin.model.http.HttpManager;
import com.example.admin.model.http.SimpleObserver;
import com.example.admin.model.http.upload.ProgressObserver;
import com.example.admin.presenter.contract.MainContract;
import com.example.admin.utils.AppUtil;
import com.example.admin.utils.FileUtil;
import com.example.admin.utils.ToastUtil;

import java.util.List;
import java.util.Map;

import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * Created by xyp on 2018/3/9.
 */

public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter {

    static int age = 18;

    @Override
    public void loadData() {
        Map<String, String> map = new ArrayMap<>();
        map.put("part", "1");
        map.put("page", "1");

        //解析jsonObject
        HttpManager.getInstance()
                .executeGet("http://room.1024.com/live/part_list_11.aspx", map)
                .subscribe(new SimpleObserver<RoomList>(RoomList.class, null) {

                    @Override
                    public void addDisposable(Disposable d) {
                        MainPresenter.this.addDisposable(d);
                    }

                    @Override
                    public void onDataSuccess(RoomList roomList) {
                        RoomList roomList1 = roomList;
                    }

                    @Override
                    public void onError(String msg) {
                        if (!TextUtils.isEmpty(msg))
                            mView.showError(msg);
                    }

                });

        //解析jsonArray
        HttpManager.getInstance().executeGet("http://room.1024.com/live/get_viewinfo_new.aspx")
                .subscribe(new SimpleObserver<Title>(null, Title[].class) {
                    @Override
                    public void onError(String msg) {
                        if (!TextUtils.isEmpty(msg))
                            mView.showError(msg);
                    }

                    @Override
                    public void addDisposable(Disposable d) {
                        MainPresenter.this.addDisposable(d);
                    }

                    @Override
                    public void onDataSuccess(List<Title> t) {
                        List<Title> t1 = t;
                    }
                });

    }

    @Override
    public void loadData1() {
        //添加了公共参数
/*        HttpManager.getInstance()
                .addQueryParameter()
                .executeGet("http://room.9158.com/live/get_viewinfo_new.aspx")
                .subscribe(new SimpleObserver<Title>(null, Title[].class) {
                    @Override
                    public void onError(String msg) {
                        if(!TextUtils.isEmpty(msg))
                            mView.showError(msg);
                    }

                    @Override
                    public void addDisposable(Disposable d) {
                        MainPresenter.this.addDisposable(d);
                    }

                    @Override
                    public void onSuccess(List<Title> t) {
                        List<Title> t1=t;
                    }
                });*/

        Map<String, String> params = new ArrayMap<>();
        params.put("phoneno", "13735504252");
        HttpManager.getInstance()
                .addCommonParams(params)
                .executeAesPost("http://61.164.160.53:8080/account/checkphone", params)
                .subscribe(new SimpleObserver<ResponseBody>(ResponseBody.class, null) {
                    @Override
                    public void onError(String msg) {

                    }

                    @Override
                    public void addDisposable(Disposable d) {

                    }
                });
    }

    @Override
    public void download() {
        HttpManager.getInstance()
                .downLoadFile("http://mobile.1024.com/1024ChatRoom.apk",
                        FileUtil.getCacheFileByType(AppStatus.getInstance(), "apk_file").getAbsolutePath()
                        , "9158.apk",
                        new ProgressObserver(true) {

                            @Override
                            public void onSuccess() {
                                ToastUtil.show(AppStatus.getInstance(), "下载成功");
                            }

                            @Override
                            public void onError(String msg) {
                                if (!TextUtils.isEmpty(msg))
                                    ToastUtil.show(AppStatus.getInstance(), msg);
                            }

                            @Override
                            public void addDisposable(Disposable d) {
                                MainPresenter.this.addDisposable(d);
                            }

                            @Override
                            public void onProgress(long writtenBytesCount, long totalBytesCount) {
                                super.onProgress(writtenBytesCount, totalBytesCount);
                                mView.updateProgress(writtenBytesCount, totalBytesCount);
                            }
                        });
    }

    @Override
    public void add() {
        User user = new User();
        user.setAge(++age);
        user.setName("徐" + age);
        DataBase.getDataBase().insert(user);
    }

    @Override
    public void delete() {
        DataBase.getDataBase().delete(age);
    }

    @Override
    public void update() {
        DataBase.getDataBase().update(age, "徐da哈");
    }

    @Override
    public void query() {
        DataBase.getDataBase().query();
    }
}
