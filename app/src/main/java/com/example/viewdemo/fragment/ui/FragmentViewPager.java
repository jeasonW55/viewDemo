package com.example.viewdemo.fragment.ui;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * <pre>
 *   author: wangjishun
 *   time: 2021/10/14
 *   desc:
 * </pre>
 **/
public class FragmentViewPager extends ViewPager {

    private int mLastPage = 0;

    public FragmentViewPager(@NonNull Context context) {
        super(context);
    }

    public FragmentViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setLastPage(int mLastPage) {
        this.mLastPage = mLastPage;
    }

    public int getLastPage() {
        return mLastPage;
    }
}
