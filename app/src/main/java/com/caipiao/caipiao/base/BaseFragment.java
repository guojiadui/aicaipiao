package com.caipiao.caipiao.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.tu.loadingdialog.LoadingDailog;
import com.blankj.utilcode.util.LogUtils;
import com.caipiao.caipiao.mvp.base.BasePresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<T extends  BasePresenter> extends Fragment {

    /**
     * 表示View是否被初始化
     */
    protected boolean isViewInitiated;
    /**
     * 表示对用户是否可见
     */
    protected boolean isVisibleToUser;
    /**
     * 表示数据是否初始化
     */
    protected boolean isDataInitiated;
    /**
     * 是否懒加载
     */
    protected boolean isLazy = true;
    public Activity mActivity;
    Unbinder unbinder;
    protected View mView;
    LoadingDailog dialog;
    public T presenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(getLayoutId(), container, false);
            unbinder = ButterKnife.bind(this, mView);
            initView(mView);
        } else {
            // 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，
            // 要不然会发生这个rootview已经有parent的错误。
            ViewGroup parent = (ViewGroup) mView.getParent();
            if (parent != null) {
                parent.removeView(mView);
            }
            unbinder = ButterKnife.bind(this, mView);
        }
        initData(savedInstanceState);
        return mView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtils.i(getClass().getSimpleName() + ">>>>>>>>>>>　　onActivityCreated");
        isViewInitiated = true;
        if (isLazy) {
            prepareFetchData();
        } else {
            initData(savedInstanceState);
        }
    }

    /**
     * 判断是否是初始化Fragment
     */
    public boolean hasStarted = false;


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        prepareFetchData();
        if (isVisibleToUser) {
            hasStarted = true;
            LogUtils.i(getClass().getSimpleName() + "：开始界面");
        } else {
            if (hasStarted) {
                hasStarted = false;
                LogUtils.i(getClass().getSimpleName() + "：结束界面");
                closeView();
            }
        }
    }

    //
    public boolean prepareFetchData() {
        return prepareFetchData(false);
    }

    /***
     *
     * @param forceUpdate 表示是否在界面可见的时候是否强制刷新数据
     *  @return 强制刷新是否完成
     */
    public boolean prepareFetchData(boolean forceUpdate) {
        boolean b = isVisibleToUser && isViewInitiated && (!isDataInitiated || forceUpdate);
        if (b) {
            //界面可见的时候再去加载数据
            fetchData();
            isDataInitiated = true;
            return true;
        }
        return false;
    }

    /**
     * 懒加载调用获取数据
     */
    public void fetchData() {
    }

    /**
     * 非懒加载获取数据
     *
     * @param savedInstanceState
     */
    protected void initData(Bundle savedInstanceState) {
    }

    public void closeView() {

    }

    /**
     * @return 获取fragment的布局
     */
    protected abstract int getLayoutId();

    /**
     * 初始化view id
     *
     * @param view fragment的布局
     */
    protected abstract void initView(View view);


    /**
     * 弹出Toast
     *
     * @param text
     */
    public void showToast(String text) {
        Toast toast = Toast.makeText(mActivity, text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public int dpToPx(Context context, float dpValue) {//dp转换为px
        float scale = context.getResources().getDisplayMetrics().density;//获得当前屏幕密度
        return (int) (dpValue * scale + 0.5f);
    }

    public void showloading() {
        if (dialog == null) {
            LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(mActivity)
                    .setMessage("加载中...")
                    .setCancelable(true)
                    .setCancelOutside(true);
            dialog = loadBuilder.create();
        }
        dialog.show();
    }

    public void dismiloading() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

}
