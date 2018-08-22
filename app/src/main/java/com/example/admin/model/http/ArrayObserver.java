package com.example.admin.model.http;

/**
 * Created by xyp on 2018/8/22.
 */

public abstract class ArrayObserver<T> extends SimpleObserver<T>{
    public ArrayObserver(Class<T[]> tClassList) {
        super(null, tClassList);
    }
}
