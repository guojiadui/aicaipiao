package com.caipiao.caipiao.mvp.home.baseview;

import com.caipiao.caipiao.mvp.base.IBaseView;
import com.caipiao.caipiao.mvp.bean.OpenPrizeBean;

import java.util.List;

public interface IHomeNoticeView extends IBaseView {
    public void onSuccess(List<OpenPrizeBean.DataBean> dataBean);
    public void onEmpty( );
    public void onError( );
}
