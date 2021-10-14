package com.example.viewdemo.fragment.frags;

import android.view.View;
import android.view.ViewGroup;

import com.example.viewdemo.R;
import com.example.viewdemo.annotation.Layout;
import com.example.viewdemo.fragment.architecture.OriginalFragment;

/**
 * <pre>
 *   @author wangjishun
 *   time: 2021/10/13
 *   desc: 详细展示页面
 * </pre>
 **/
@Layout(layout = R.layout.fragment_main_next)
public class MainNextFragment extends OriginalFragment {

    @Override
    protected void initView(View view) {
        super.initView(view);

    }

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
        return true;
    }
}
