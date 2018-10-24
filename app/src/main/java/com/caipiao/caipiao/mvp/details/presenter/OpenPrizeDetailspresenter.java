package com.caipiao.caipiao.mvp.details.presenter;

import android.content.Context;

import com.caipiao.caipiao.http.OkGoCallback;
import com.caipiao.caipiao.mvp.base.BasePresenter;
import com.caipiao.caipiao.mvp.details.baseview.IOpenPrizeDetailsBaseView;
import com.caipiao.caipiao.mvp.details.bean.OpenPrizeListBean;
import com.caipiao.caipiao.mvp.home.presenter.HomeActPesenter;
import com.caipiao.caipiao.mvp.model.LotteryModel;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

public class OpenPrizeDetailspresenter extends BasePresenter<IOpenPrizeDetailsBaseView> {
    public static OpenPrizeDetailspresenter newInstance() {
        return new OpenPrizeDetailspresenter();
    }


    String code;



    int curPage = 1;

    public void LoadDaata(Context context) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("lotCode", code);
        httpParams.put("page", curPage);
        httpParams.put("rows", 10);
        String tag = LotteryModel.getPrizeList(context, httpParams, new OkGoCallback<OpenPrizeListBean>() {
            @Override
            public void onSuccess(OpenPrizeListBean response) {

                if (response.isSuccess()) {
                    if (response.getData().getList().size() > 0) {
                        if (curPage == 1) {
                            mRootView.onSuccess(response.getData().getList(), response.getData().isHasNext());
                        } else {
                            mRootView.onMore(response.getData().getList(), response.getData().isHasNext());
                        }
                        curPage = response.getData().getNextPage();
                    } else {
                        if (curPage == 1) {
                            mRootView.onEmpty();
                        } else {
                            mRootView.onMoreEmpty();
                        }
                    }
                } else {
                    if (curPage == 1) {
                        mRootView.onError();
                    } else {
                        mRootView.onMoreError();
                    }
                }
            }

            @Override
            public void parseError() {
                super.parseError();
                if (curPage == 1) {
                    mRootView.onError();
                } else {
                    mRootView.onMoreError();
                }
            }

            @Override
            public void onError(Response<String> response) {
                if (curPage == 1) {
                    mRootView.onError();
                } else {
                    mRootView.onMoreError();
                }
            }
        });
        net(tag);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }
}
