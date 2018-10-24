package com.caipiao.caipiao.mvp.details.baseview;



import com.caipiao.caipiao.mvp.base.IBaseView;
import com.caipiao.caipiao.mvp.details.bean.OpenPrizeListBean;

import java.util.List;

public interface IOpenPrizeDetailsBaseView  extends IBaseView {


    public void onSuccess(List<OpenPrizeListBean.DataBean.ListBean> contentBeans, boolean hasNext);

    public void onEmpty();

    public void onError();

    public void onMore(List<OpenPrizeListBean.DataBean.ListBean> contentBeans, boolean hasNext);

    public void onMoreEmpty();

    public void onMoreError();

}
