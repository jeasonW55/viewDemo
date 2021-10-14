package com.example.viewdemo.fragment.frags;

import android.annotation.SuppressLint;
import android.view.ViewGroup;

import com.example.viewdemo.R;
import com.example.viewdemo.annotation.Layout;
import com.example.viewdemo.fragment.architecture.OriginalFragment;

@SuppressLint("NonConstantResourceId")
@Layout(layout = R.layout.fragment_second_layout)
public class SecondFragment extends OriginalFragment {

    @Override
    public void setTitleBar(ViewGroup vg) {

    }

    @Override
    public boolean isTitleBarGone() {
        return false;
    }

    @Override
    public boolean isBottomNaviGone() {
        return false;
    }
}
