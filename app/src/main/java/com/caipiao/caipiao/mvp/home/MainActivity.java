package com.caipiao.caipiao.mvp.home;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.caipiao.caipiao.R;
import com.caipiao.caipiao.base.BaseActivity;
import com.caipiao.caipiao.mvp.home.baseview.IMainBaseView;
import com.caipiao.caipiao.mvp.home.fragment.HomeActFragment;
import com.caipiao.caipiao.mvp.home.fragment.HomeHallFragment;
import com.caipiao.caipiao.mvp.home.fragment.HomeNoticeFragment;
import com.caipiao.caipiao.mvp.home.fragment.HomeUserCenterFragment;
import com.caipiao.caipiao.mvp.home.presenter.MainPresenter;
import com.caipiao.caipiao.view.BottomBar;
import com.caipiao.caipiao.view.BottomBarTab;

import butterknife.BindView;


public class MainActivity extends BaseActivity<MainPresenter> implements IMainBaseView {
    @BindView(R.id.fragment_content)
    FrameLayout fragmentContent;
    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;

    HomeHallFragment mHallFragment;
    HomeNoticeFragment mHomeNoticeFragment;
    HomeActFragment mActFragment;
    HomeUserCenterFragment mUserCenterFragment;

    @Override
    public int getLayout() {
        return R.layout.main_layout_activity;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        setPresenter(MainPresenter.newInstance());
        presenter.attachView(this);

        mBottomBar.addItem(new BottomBarTab(this, R.mipmap.ic_launcher, "投注大厅"))
                .addItem(new BottomBarTab(this, R.mipmap.ic_launcher, "开奖公告"))
                .addItem(new BottomBarTab(this, R.mipmap.ic_launcher, "活动"))
                .addItem(new BottomBarTab(this, R.mipmap.ic_launcher, "我的"));
        mBottomBar.setCurrentItem(0);
        switchFragment(0);
        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public boolean onInterceptTouchEvent(int position) {
                return false;
            }

            @Override
            public void onTabSelected(int position, int prePosition) {
                switchFragment(position);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {
            }
        });
    }


    /**
     * 切换Fragment
     *
     * @param
     */
    private void switchFragment(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (position) {
            case 0:
                hideFragments(transaction);
                if (mHallFragment != null) {
                    transaction.show(mHallFragment);
                } else {
                    mHallFragment = new HomeHallFragment();
                    transaction.add(R.id.fragment_content, mHallFragment, HomeHallFragment.class.getName());
                }
                break;
            case 1:
                hideFragments(transaction);
                if (mHomeNoticeFragment != null) {
                    transaction.show(mHomeNoticeFragment);
                } else {
                    mHomeNoticeFragment = new HomeNoticeFragment();
                    transaction.add(R.id.fragment_content, mHomeNoticeFragment, HomeNoticeFragment.class.getName());
                }
                break;
            case 2:
                hideFragments(transaction);
                if (mActFragment != null) {
                    transaction.show(mActFragment);
                } else {
                    mActFragment = new HomeActFragment();
                    transaction.add(R.id.fragment_content, mActFragment, HomeActFragment.class.getName());
                }
                break;
            case 3:
                hideFragments(transaction);
                if (mUserCenterFragment != null) {
                    transaction.show(mUserCenterFragment);
                } else {
                    mUserCenterFragment = new HomeUserCenterFragment();
                    transaction.add(R.id.fragment_content, mUserCenterFragment, HomeUserCenterFragment.class.getName());
                }
                break;

        }
        transaction.commitAllowingStateLoss();
    }


    /**
     * 隐藏所有的Fragment
     *
     * @param transaction transaction
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (mHallFragment != null) {
            transaction.hide(mHallFragment);
        }
        if (mHomeNoticeFragment != null) {
            transaction.hide(mHomeNoticeFragment);
        }
        if (mActFragment != null) {
            transaction.hide(mActFragment);
        }
        if (mUserCenterFragment != null) {
            transaction.hide(mUserCenterFragment);
        }


    }

}
