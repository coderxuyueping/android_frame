package com.example.admin.model.http;

import java.util.List;

/**
 * Created by xyp on 2018/8/22.
 */

public abstract class ArrayObserver<T> extends SimpleObserver<T>{
    public ArrayObserver(Class<T[]> tClassList) {
        super(null, tClassList);
    }

    @Override
    public void onDataSuccess(List<T> t) {
        onSuccess(t);
    }

    public abstract void onSuccess(List<T> t);
}
