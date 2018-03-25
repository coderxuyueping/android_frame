package com.example.admin.model.http;

import android.text.TextUtils;
import android.util.Log;

import com.example.admin.utils.GsonUtil;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * Created by xyp on 2018/3/14.
 */

public abstract class SimpleObserver<T> implements Observer<ResponseBody> {
    Class<T> tClass;
    Class<T[]> tClassList;

    //可以处理两种情况：解析jsonObject和解析jsonArray,两个参数必须要有一个为null
    public SimpleObserver(Class<T> tClass, Class<T[]> tClassList) {
        this.tClass = tClass;
        this.tClassList = tClassList;
    }

    @Override
    public void onSubscribe(Disposable d) {
        addDisposable(d);
    }

    @Override
    public void onNext(ResponseBody responseBody) {
        String res = "";
        try {
            res = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(TextUtils.isEmpty(res)){
            onError("请求失败");
            return;
        }

        BaseResponse baseResponse = GsonUtil.getObject(res, BaseResponse.class);
        //如果code跟服务器定下的成功返回不一致
        if(!"A00006".equals(baseResponse.getCode())){
            onError(baseResponse.getMsg());
            return;
        }
        if (tClass != null) {
            T t = GsonUtil.getObject(baseResponse.getData(), tClass);
            onSuccess(t);
        } else {
            List<T> list = GsonUtil.getObjects(baseResponse.getData(), tClassList);
            onSuccess(list);
        }
    }

    @Override
    public void onError(Throwable e) {
        if(e instanceof UnknownHostException){
            onError("网络错误");
        }else{
            onError("");
            Log.d("xudaha", e.toString());
        }
    }

    @Override
    public void onComplete() {
    }




    public void onSuccess(T t) {
    }

    public void onSuccess(List<T> t) {
    }

    public abstract void onError(String msg);

    public abstract void addDisposable(Disposable d);
}
