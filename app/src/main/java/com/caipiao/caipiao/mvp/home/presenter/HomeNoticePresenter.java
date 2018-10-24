package com.caipiao.caipiao.mvp.home.presenter;

import android.content.Context;

import com.caipiao.caipiao.app.Url;
import com.caipiao.caipiao.http.HttpManager;
import com.caipiao.caipiao.http.OkGoCallback;
import com.caipiao.caipiao.mvp.base.BasePresenter;
import com.caipiao.caipiao.mvp.bean.OpenPrizeBean;
import com.caipiao.caipiao.mvp.home.baseview.IHomeNoticeView;
import com.caipiao.caipiao.mvp.model.LotteryModel;
import com.lzy.okgo.model.Response;

public class HomeNoticePresenter extends BasePresenter<IHomeNoticeView> {
    public static HomeNoticePresenter newInstance() {
        return new HomeNoticePresenter();
    }

    public void getLoadData(Context context) {
        String tag = LotteryModel.getOpenPrizeList(context, new OkGoCallback<OpenPrizeBean>() {
            @Override
            public void onSuccess(OpenPrizeBean response) {
                if (response.isSuccess()) {
                    if (response.getData() != null && response.getData().size() > 0) {
                        //获取彩所以码
                        mRootView.onSuccess(response.getData());
                    } else {
                        mRootView.onEmpty();
                    }
                } else {
                    mRootView.onError();
                }
            }

            @Override
            public void parseError() {
                super.parseError();
                mRootView.onError();
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                mRootView.onError();
            }
        });
        net(tag);
    }
}
