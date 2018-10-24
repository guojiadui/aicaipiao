package com.caipiao.caipiao.mvp.home.presenter;

import com.caipiao.caipiao.mvp.base.BasePresenter;
import com.caipiao.caipiao.mvp.home.baseview.IHomeUserView;

public class HomeUserPresenter extends BasePresenter<IHomeUserView> {

    public static HomeUserPresenter newInstance() {
        return new HomeUserPresenter();
    }
}
