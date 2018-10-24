package com.caipiao.caipiao.mvp.base;

import android.view.View;

import com.lzy.okgo.OkGo;

import java.util.HashSet;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class BasePresenter<T extends IBaseView> {
    public T mRootView;
    private HashSet<String> urlList;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void attachView(T mRootView) {
        this.mRootView = mRootView;
    }

    public void detachView() {
        if (urlList != null) {
            for (String d : urlList) {
                OkGo.getInstance().cancelTag(d);
            }
            urlList.clear();
        }
        mRootView = null;
    }


    public void net(String url) {
        if (urlList == null) {
            urlList = new HashSet<>();
        }
        if (!urlList.contains(url)) {
            urlList.add(url);
        }
    }


}
