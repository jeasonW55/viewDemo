package com.example.viewdemo.fragment;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;

import com.example.viewdemo.AbstractActivity;
import com.example.viewdemo.R;
import com.example.viewdemo.annotation.Layout;
import com.example.viewdemo.fragment.frags.MainFragment;
import com.example.viewdemo.manager.ActivityRecorder;

/**
 * <pre>
 *   @author wangjishun
 *   time: 2021/10/13
 *   desc:
 * </pre>
 **/

@SuppressLint("NonConstantResourceId")
@Layout(layout = R.layout.activity_layout_fragment_container)
public class FragmentLearnActivity extends AbstractActivity {

    @Override
    public void initViews() {
        HorizontalScrollView scrollView = findViewById(R.id.bottom_navigator);
        View v = LayoutInflater.from(this).inflate(R.layout.navigator_scroll_view, null);
        scrollView.addView(v);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MainFragment mainFragment = new MainFragment();
        ActivityRecorder.SingleTon.getInstance().startFragment(R.id.fragment_container,
                mainFragment, false, null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityRecorder.SingleTon.getInstance().releaseAll();
    }
}
