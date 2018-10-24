package com.caipiao.caipiao.mvp.home.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.caipiao.caipiao.R;
import com.caipiao.caipiao.base.BaseFragment;
import com.caipiao.caipiao.mvp.bean.LotteryListBean;
import com.caipiao.caipiao.mvp.home.adapter.HomeHallAdapter;
import com.caipiao.caipiao.mvp.home.baseview.IHomeHallView;
import com.caipiao.caipiao.mvp.home.bean.LunboBean;
import com.caipiao.caipiao.mvp.home.presenter.HomeHallPresenter;
import com.caipiao.caipiao.view.DividerGridItemDecoration2;
import com.caipiao.caipiao.view.MultipleStatusView;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.bgabanner.BGABanner;

public class HomeHallFragment extends BaseFragment<HomeHallPresenter> implements IHomeHallView {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.multipleStatusView)
    MultipleStatusView multipleStatusView;

    HomeHallAdapter adapter;
    View view;

    @Override
    protected int getLayoutId() {
        return R.layout.home_hall_fragment_layout;
    }

    @Override
    protected void initView(View view) {
        this.view = view;
        presenter = HomeHallPresenter.newInstance();
        presenter.attachView(this);
        recyclerView.addItemDecoration(new DividerGridItemDecoration2(mActivity));
        recyclerView.setLayoutManager(new GridLayoutManager(mActivity, 3));
        recyclerView.setAdapter(adapter);
        multipleStatusView.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                multipleStatusView.showLoading();
                presenter.LoadData(mActivity);
            }
        });
        multipleStatusView.showLoading();
        presenter.LoadData(mActivity);
    }


    @Override
    public void getLubo(LunboBean lunboBean) {
        if (adapter == null) {
            adapter = new HomeHallAdapter(mActivity, new ArrayList());
            recyclerView.setAdapter(adapter);
        }
        adapter.removeAllHeaderView();

        View head1 = LayoutInflater.from(mActivity).inflate(R.layout.home_head_layout, (ViewGroup) view, false);
        BGABanner banner = head1.findViewById(R.id.banner_guide_content);
        List<View> views = new ArrayList<>();
        views.add(View.inflate(mActivity, R.layout.banner_item, null));
        views.add(View.inflate(mActivity, R.layout.banner_item, null));
        views.add(View.inflate(mActivity, R.layout.banner_item, null));
        banner.setData(views);
        adapter.addHeaderView(head1);
       /* View head2 = LayoutInflater.from(mActivity).inflate(R.layout.home_head_layout2, (ViewGroup) view, false);
        adapter.addHeaderView(head2);*/

    }

    @Override
    public void getLottery(List<LotteryListBean.ContentBean> contentBeans) {
        multipleStatusView.showContent();
        if (adapter == null) {
            adapter = new HomeHallAdapter(mActivity, contentBeans);
            recyclerView.setAdapter(adapter);
            recyclerView.scrollTo(0,0);
        } else {
            adapter.getData().clear();
            adapter.addData(contentBeans);
            recyclerView.scrollTo(0,0);
        }

    }

    @Override
    public void onError() {
        multipleStatusView.showError();
    }
}
