package com.example.viewdemo.fragment.adpter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.viewdemo.fragment.architecture.OriginalFragment;
import com.example.viewdemo.fragment.frags.ForthFragment;
import com.example.viewdemo.fragment.frags.MainFragment;
import com.example.viewdemo.fragment.frags.SecondFragment;
import com.example.viewdemo.fragment.frags.ThirdFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *   @author wangjishun
 *   time: 2021/10/14
 *   desc:
 * </pre>
 **/
public class TopPageAdapter extends FragmentPagerAdapter {

    private final MainFragment mMainFragment;

    private final SecondFragment mSecondFragment;

    private final ThirdFragment mThirdFragment;

    private final ForthFragment mForthFragment;

    private final List<OriginalFragment> mFragmentList;

    public TopPageAdapter(@NonNull FragmentManager fm) {
        this(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    public TopPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        mMainFragment = new MainFragment();
        mSecondFragment = new SecondFragment();
        mThirdFragment = new ThirdFragment();
        mForthFragment = new ForthFragment();
        mFragmentList = new ArrayList<>();
        initFragList();
    }

    private void initFragList() {
        mFragmentList.add(mMainFragment);
        mFragmentList.add(mSecondFragment);
        mFragmentList.add(mThirdFragment);
        mFragmentList.add(mForthFragment);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList == null ? 0 : mFragmentList.size();
    }
}
