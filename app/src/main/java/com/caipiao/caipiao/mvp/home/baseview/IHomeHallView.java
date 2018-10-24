package com.caipiao.caipiao.mvp.home.baseview;

import com.caipiao.caipiao.mvp.base.IBaseView;
import com.caipiao.caipiao.mvp.bean.LotteryListBean;
import com.caipiao.caipiao.mvp.home.bean.LunboBean;

import java.util.List;

public interface IHomeHallView extends IBaseView {

    public  void getLubo(LunboBean lunboBean);
    public  void getLottery(List<LotteryListBean.ContentBean> lunboBean);
    public  void  onError();

}
