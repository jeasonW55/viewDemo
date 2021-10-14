package com.example.viewdemo.fragment;

import android.view.ViewGroup;

public interface ViewController {

    void setTitleBar(ViewGroup vg);

    boolean isTitleBarGone();

    boolean isBottomNaviGone();
}
