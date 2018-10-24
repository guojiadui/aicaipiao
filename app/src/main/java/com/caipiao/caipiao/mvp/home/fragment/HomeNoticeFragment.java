package com.caipiao.caipiao.mvp.home.fragment;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.caipiao.caipiao.R;
import com.caipiao.caipiao.base.BaseFragment;
import com.caipiao.caipiao.mvp.bean.OpenPrizeBean;
import com.caipiao.caipiao.mvp.details.OpenPrizedetailsActivity;
import com.caipiao.caipiao.mvp.home.adapter.OpenPrizeAdapter;
import com.caipiao.caipiao.mvp.home.baseview.IHomeNoticeView;
import com.caipiao.caipiao.mvp.home.presenter.HomeNoticePresenter;
import com.caipiao.caipiao.view.CustomToolbar;
import com.caipiao.caipiao.view.MultipleStatusView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeNoticeFragment extends BaseFragment<HomeNoticePresenter> implements IHomeNoticeView {
    @BindView(R.id.lt_main_title_left)
    TextView ltMainTitleLeft;
    @BindView(R.id.lt_main_title)
    TextView ltMainTitle;
    @BindView(R.id.lt_main_title_right)
    TextView ltMainTitleRight;
    @BindView(R.id.customtoolbar)
    CustomToolbar customtoolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.multipleStatusView)
    MultipleStatusView multipleStatusView;


    @Override
    protected int getLayoutId() {
        return R.layout.home_notice_fragment_layout;
    }

    @Override
    protected void initView(View view) {
        presenter = HomeNoticePresenter.newInstance();
        presenter.attachView(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        recyclerView.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL));
        refreshLayout.setEnableRefresh(true);//是否启用下拉刷新功能
        refreshLayout.setEnableLoadMore(false);//是否启用上拉加载功能
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                presenter.getLoadData(mActivity);
            }
        });
        multipleStatusView.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                multipleStatusView.showLoading();
                presenter.getLoadData(mActivity);
            }
        });

    }
    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        multipleStatusView.showLoading();
        presenter.getLoadData(mActivity);
    }

    @Override
    public void onSuccess(List<OpenPrizeBean.DataBean> dataBean) {
        multipleStatusView.showContent();
        refreshLayout.finishRefresh();
        OpenPrizeAdapter openPrizeAdapter = new OpenPrizeAdapter(mActivity, dataBean);
        openPrizeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                OpenPrizeBean.DataBean dataBean = (OpenPrizeBean.DataBean) adapter.getData().get(position);
                OpenPrizedetailsActivity.start(mActivity,dataBean);
            }
        });
        recyclerView.setAdapter(openPrizeAdapter);
    }

    @Override
    public void onEmpty() {
        refreshLayout.finishRefresh();
        multipleStatusView.showEmpty();
    }

    @Override
    public void onError() {
        refreshLayout.finishRefresh();
        multipleStatusView.showError();
    }
}
