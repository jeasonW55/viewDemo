package com.example.viewdemo;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.viewdemo.fragment.architecture.FragmentRecorder;
import com.example.viewdemo.fragment.architecture.OriginalFragment;
import com.example.viewdemo.fragment.data.RuntimeData;
import com.example.viewdemo.manager.ActivityRecorder;


/**
 * <pre>
 *   @author wangjishun
 *   time: 2021/10/13
 *   desc: 顶层父类
 * </pre>
 **/
public abstract class AbstractActivity extends FragmentActivity {

    private static final int INVALID_ID = -1;

    private int mMainFragmentId = INVALID_ID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRecorder.SingleTon.getInstance().registerActivity(this);
        initViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityRecorder.SingleTon.getInstance().unRegisterActivity(this);
    }

    /**
     * 初始化相关View
     */
    public void initViews() {
        //do nothing
    }

    /**
     * 向Activity添加Fragment
     * @param layoutId 要添加fragment的布局
     * @param fragment 目标fragment
     * @param addToBackStack 是否要加入返回栈
     */
    public void addFragment(int layoutId, Fragment fragment, boolean addToBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment currentFragment = FragmentRecorder.SingleTon.getInstance().getCurrentFragment();
        if (currentFragment != null) {
            transaction.hide(currentFragment);
        }
        transaction.add(layoutId, fragment);
        mMainFragmentId = layoutId;
        if (addToBackStack) {
            transaction.addToBackStack(fragment.getClass().toString());
        }
        transaction.commit();
    }

    /**
     * 启动fragment
     * @param fragment 要启动的目标fragment
     * @param addToBackStack 是否加入返回栈
     * @param data
     */
    public void startFragment(Fragment fragment, boolean addToBackStack, RuntimeData data) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment currentFragment = FragmentRecorder.SingleTon.getInstance().getCurrentFragment();
        if (currentFragment != null) {
            transaction.hide(currentFragment);
        }
        if (mMainFragmentId != INVALID_ID) {
            transaction.add(R.id.fragment_container, fragment);
            if (addToBackStack) {
                transaction.addToBackStack(fragment.getClass().toString());
            }
            if (fragment instanceof OriginalFragment) {
                ((OriginalFragment) fragment).parseRuntimeData(data);
            }
            transaction.commit();
        }
    }
}
