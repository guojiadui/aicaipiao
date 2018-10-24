package com.caipiao.caipiao.mvp.home.presenter;

import com.caipiao.caipiao.mvp.base.BasePresenter;
import com.caipiao.caipiao.mvp.home.baseview.IMainBaseView;

public class MainPresenter extends BasePresenter<IMainBaseView> {

    public static MainPresenter newInstance() {
        return new MainPresenter();
    }


    public void LoadData() {

    }
}
