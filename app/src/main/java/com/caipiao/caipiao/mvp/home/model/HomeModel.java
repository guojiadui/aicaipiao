package com.caipiao.caipiao.mvp.home.model;

import android.content.Context;

import com.caipiao.caipiao.app.Url;
import com.caipiao.caipiao.http.HttpManager;
import com.caipiao.caipiao.http.OkGoCallback;
import com.caipiao.caipiao.mvp.home.bean.LunboBean;
import com.lzy.okgo.model.HttpParams;

public class HomeModel {

    /**
     * 轮播图
     */
    public  static void getLunbo(Context context, OkGoCallback<LunboBean> okGoCallback) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("code",5);
        HttpManager.getObjectNoLogin(context, LunboBean.class, Url.lunbo, httpParams, okGoCallback);
    }
}
