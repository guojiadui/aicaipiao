package com.caipiao.caipiao.app;

public class Url {
    /**
     * 域名
     */
    public final static String BASE_URL = "http://xbzhanshi.com";

    /**
     * 非登录接口
     */
    public final static String Loctterys = BASE_URL + "/native/getLoctterys.do";//彩种列表
    public final static String lunbo = BASE_URL + "/native/lunbo.do";//轮播图
    public final static String lottery_png = BASE_URL + "/mobile/v3/images/lottery/";//彩票的icon
    public final static String OpenPrize = BASE_URL + "/mobile/v3/draw_notice_data.do";//所有彩票的最新开奖公告
    public final static String OpenPrizeResultList = BASE_URL + "/mobile/v3/draw_notice_details_data.do";//某一彩票的开奖列表
    /**
     * 登录接口
     */
}
