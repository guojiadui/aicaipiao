package com.caipiao.caipiao.http;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.LogUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

public class HttpManager {

    public static <T> String getObjectNoLogin(final Context context, final Class<T> c, String url, HttpParams params, final OkGoCallback<T> back) {
        LogUtils.e(url);
        OkGo.<String>get(url).tag(url).params(params).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                LogUtils.e(response.body());
                try {
                    T t = JSON.parseObject(response.body(), c);
                    if (back != null) {
                        back.onSuccess(t);
                    }
                } catch (Exception e) {
                    if (back != null) {
                        back.parseError();
                    }
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                if (back != null) {
                    back.onError(response);
                }
            }
        });
        return url;
    }
    public static <T> Object postObject(final Context context, final Class<T> c, String url, HttpParams params, final OkGoCallback<T> back) {
        OkGo.<String>post(url).tag(url).params(params).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                try {
                    T t = JSON.parseObject(response.body(), c);
                    if (back != null) {
                        back.onSuccess(t);
                    }
                } catch (Exception e) {
                    if (back != null) {
                        back.parseError();
                    }
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                if (back != null) {
                    back.onError(response);
                }
            }
        });
        return url;
    }


}
