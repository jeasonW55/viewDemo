package com.example.viewdemo.fragment;

import android.view.ViewGroup;

public interface ViewController {

    /**
     * 设置title
     * @param vg
     */
    void setTitleBar(ViewGroup vg);

    /**
     * 是否隐藏title
     */
    boolean isTitleBarGone();

    /**
     * 是否隐藏底部导航栏
     */
    boolean isBottomNaviGone();

    boolean isEditAreaVisible();

    boolean isTitleTextVisible();
}
