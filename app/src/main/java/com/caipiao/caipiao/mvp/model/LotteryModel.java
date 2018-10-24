package com.caipiao.caipiao.mvp.model;

import android.content.Context;

import com.caipiao.caipiao.app.Url;
import com.caipiao.caipiao.http.HttpManager;
import com.caipiao.caipiao.http.OkGoCallback;
import com.caipiao.caipiao.mvp.bean.LotteryListBean;
import com.caipiao.caipiao.mvp.bean.OpenPrizeBean;
import com.caipiao.caipiao.mvp.details.bean.OpenPrizeListBean;
import com.lzy.okgo.model.HttpParams;

public class LotteryModel {


    /**
     * 获取彩票列表
     */

    public static String getLotteryList(Context context, OkGoCallback<LotteryListBean> okGoCallback) {
        return HttpManager.getObjectNoLogin(context, LotteryListBean.class, Url.Loctterys, null, okGoCallback);
    }
    /**
     * 获取最新的开奖公告
     */
    public  static  String getOpenPrizeList(Context context,OkGoCallback<OpenPrizeBean> okGoCallback){
        return  HttpManager.getObjectNoLogin(context,OpenPrizeBean.class, Url.OpenPrize, null,okGoCallback);
    }
    /**
     *某一彩票的开奖列表
     */

    public  static  String getPrizeList(Context context, HttpParams httpParams, OkGoCallback<OpenPrizeListBean> okGoCallback){
        return  HttpManager.getObjectNoLogin(context,OpenPrizeListBean.class, Url.OpenPrizeResultList, httpParams,okGoCallback);
    }


}
