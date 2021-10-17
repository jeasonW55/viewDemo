package com.example.viewdemo.fragment;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.viewdemo.AbstractActivity;
import com.example.viewdemo.R;
import com.example.viewdemo.annotation.Layout;
import com.example.viewdemo.fragment.adpter.TopPageAdapter;
import com.example.viewdemo.fragment.frags.FifthFragment;
import com.example.viewdemo.fragment.frags.MainFragment;
import com.example.viewdemo.fragment.ui.BottomNavigatorLayout;
import com.example.viewdemo.fragment.ui.FragmentViewPager;
import com.example.viewdemo.fragment.ui.WebBrowser;
import com.example.viewdemo.manager.ActivityRecorder;

/**
 * <pre>
 *   @author wangjishun
 *   time: 2021/10/13
 *   desc: 学习fragment的Activity
 * </pre>
 **/

@SuppressLint("NonConstantResourceId")
@Layout(layout = R.layout.activity_layout_fragment_container)
public class FragmentLearnActivity extends AbstractActivity {

    TopPageAdapter mAdapter;
    FragmentViewPager mPage;

    @Override
    public void initViews() {
        LinearLayout scrollView = findViewById(R.id.bottom_navigator);
        BottomNavigatorLayout v = (BottomNavigatorLayout) LayoutInflater.from(this).inflate(R.layout.navigator_scroll_view, null);
        scrollView.addView(v);
        mAdapter = new TopPageAdapter(getSupportFragmentManager());
        mPage = findViewById(R.id.fragment_container);
        mPage.setAdapter(mAdapter);
        v.setPager(mPage);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityRecorder.SingleTon.getInstance().releaseAll();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        int position = mPage.getCurrentItem();
        int last = mPage.getLastPage();
        Fragment item = mAdapter.getItem(position);
        if (item instanceof MainFragment) {
            super.onBackPressed();
        } else if (item instanceof FifthFragment) {
            WebBrowser browser = ((FifthFragment) item).getBrowser();
            if (browser.canGoBack()) {
                browser.goBack();
            } else {
                mPage.setCurrentItem(last, false);
            }
        } else {
            mPage.setCurrentItem(last, false);
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        switch (newConfig.orientation) {
            case Configuration.ORIENTATION_LANDSCAPE:
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                break;
            case Configuration.ORIENTATION_PORTRAIT:
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
                break;
        }
    }
}
