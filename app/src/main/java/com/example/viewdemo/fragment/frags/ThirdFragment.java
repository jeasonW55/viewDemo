package com.example.viewdemo.fragment.frags;

import android.view.ViewGroup;

import com.example.viewdemo.R;
import com.example.viewdemo.annotation.Layout;
import com.example.viewdemo.fragment.architecture.OriginalFragment;

@Layout(layout = R.layout.fragment_third_layout)
public class ThirdFragment extends OriginalFragment {
    @Override
    public void setTitleBar(ViewGroup vg) {
        super.setTitleBar(vg);
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
