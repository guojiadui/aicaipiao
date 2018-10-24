package com.caipiao.caipiao.mvp.home.presenter;

import android.content.Context;

import com.caipiao.caipiao.http.OkGoCallback;
import com.caipiao.caipiao.mvp.base.BasePresenter;
import com.caipiao.caipiao.mvp.bean.LotteryListBean;
import com.caipiao.caipiao.mvp.home.baseview.IHomeHallView;
import com.caipiao.caipiao.mvp.home.bean.LunboBean;
import com.caipiao.caipiao.mvp.home.model.HomeModel;
import com.caipiao.caipiao.mvp.model.LotteryModel;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

public class HomeHallPresenter extends BasePresenter<IHomeHallView> {

    public static HomeHallPresenter newInstance() {
        return new HomeHallPresenter();
    }

    /**
     * 加载数据
     */
    public void LoadData(final Context context) {
        //轮播图
        HomeModel.getLunbo(context, new OkGoCallback<LunboBean>() {
            @Override
            public void onSuccess(LunboBean response) {
                super.onSuccess(response);
                if (response.isSuccess()) {
                    mRootView.getLubo(response);
                    getlist(context);
                }
            }

            @Override
            public void parseError() {
                mRootView.onError();
            }

            @Override
            public void onError(Response<String> response) {
                mRootView.onError();
            }
        });

    }


    private void getlist(Context context) {
        //彩票
        LotteryModel.getLotteryList(context, new OkGoCallback<LotteryListBean>() {
            @Override
            public void onSuccess(LotteryListBean response) {
                super.onSuccess(response);
                if (response.isSuccess()) {
                    if (response.getContent().size() > 15) {
                        List<LotteryListBean.ContentBean> contentBeans = new ArrayList<>();
                        for (int i = 0; i < 15; i++) {
                            if (i == 14) {
                                LotteryListBean.ContentBean contentBean = new LotteryListBean.ContentBean();
                                contentBean.setLottery(false);
                                contentBean.setName("更多彩种");
                                contentBeans.add(contentBean);
                            } else {
                                contentBeans.add(response.getContent().get(i));
                            }

                        }
                        mRootView.getLottery(contentBeans);
                    } else {
                        mRootView.getLottery(response.getContent());
                    }

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
    }
}
