package com.caipiao.caipiao.mvp.home.presenter;

import com.caipiao.caipiao.mvp.base.BasePresenter;
import com.caipiao.caipiao.mvp.home.baseview.IHomeActView;

public class HomeActPesenter   extends BasePresenter<IHomeActView> {

    public static HomeActPesenter newInstance() {
        return new HomeActPesenter();
    }

}
