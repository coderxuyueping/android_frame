package com.example.admin.model.http.download;

import com.example.admin.model.http.upload.ProgressObserver;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.Nullable;
import io.reactivex.functions.Consumer;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ForwardingSink;
import okio.ForwardingSource;
import okio.Okio;
import okio.Sink;
import okio.Source;

/**
 * Created by xyp on 2018/3/14.
 */

public class ProgressResponseBody extends ResponseBody {
    private ResponseBody responseBody;
    private ProgressObserver progressListener;
    private BufferedSource bufferedSource;

    public ProgressResponseBody(ResponseBody responseBody, ProgressObserver progressListener) {
        this.responseBody = responseBody;
        this.progressListener = progressListener;
    }

    @Nullable
    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength(){
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if(bufferedSource == null){
            bufferedSource = Okio.buffer(source(responseBody.source()));
        }
        return bufferedSource;
    }

    private Source source(Source source) {
        return new ForwardingSource(source) {

            long downloadBytesRead = 0L;
            //总字节长度，避免多次调用contentLength()方法
            long totalBytesCount = 0L;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
                downloadBytesRead += bytesRead != -1 ? bytesRead : 0;
                //获得contentLength的值，后续不再调用
                if (totalBytesCount == 0) {
                    totalBytesCount = responseBody.contentLength();
                }
                progressListener.onProgress(downloadBytesRead, totalBytesCount);
                return bytesRead;
            }
        };
    }
}
