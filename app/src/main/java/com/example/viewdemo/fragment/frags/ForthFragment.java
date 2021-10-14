package com.example.viewdemo.fragment.frags;

import android.view.ViewGroup;

import com.example.viewdemo.R;
import com.example.viewdemo.annotation.Layout;
import com.example.viewdemo.fragment.architecture.OriginalFragment;

/**
 * <pre>
 *   author: wangjishun
 *   time: 2021/10/14
 *   desc:
 * </pre>
 **/
@Layout(layout =  R.layout.fragment_forth_layout)
public class ForthFragment extends OriginalFragment {
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
