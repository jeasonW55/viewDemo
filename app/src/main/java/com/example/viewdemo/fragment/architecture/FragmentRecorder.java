package com.example.viewdemo.fragment.architecture;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.lib_base.annotation.FragmentName;
import com.example.lib_base.annotation.Layout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *   @author: wangjishun
 *   time: 2021/10/13
 *   desc: 记录Fragment
 * </pre>
 **/
public final class FragmentRecorder {
    private FragmentRecorder() {}

    private final List<Fragment> fragments = new ArrayList<>();

    private final Map<String, Fragment> fragmentMap = new HashMap<>();

    public boolean isRecord(Fragment fragment) {
        return fragmentMap.containsKey(getFragmentName(fragment));
    }

    public Fragment getFragment(Fragment fragment) {
        return fragmentMap.get(getFragmentName(fragment));
    }

    public static class SingleTon {

        private static final FragmentRecorder INSTANCE = new FragmentRecorder();

        public static FragmentRecorder getInstance() {
            return INSTANCE;
        }
    }

    public android.view.View registerFragment(Fragment fragment, LayoutInflater inflater, ViewGroup container) {
        fragments.add(fragment);

        if (fragment.getClass().isAnnotationPresent(FragmentName.class)) {
            FragmentName fragmentName = fragment.getClass().getAnnotation(FragmentName.class);
            if (fragmentName != null) {
                fragmentMap.put(fragmentName.name(), fragment);
            }
        }
        if (fragment.getClass().isAnnotationPresent(Layout.class)) {
            Layout layout = fragment.getClass().getAnnotation(Layout.class);
            if (layout != null) {
                return inflater.inflate(layout.layout(), container, false);
            }
        }

        return null;
    }

    public void unregister(Fragment fragment) {
        if (fragments.isEmpty()) {
            return;
        }
        fragments.remove(fragment);
    }

    public Fragment getCurrentFragment() {
        if (fragments.isEmpty()) {
            return null;
        }
        return fragments.get(fragments.size() - 1);
    }

    private String getFragmentName(Fragment fragment) {
        if (fragment.getClass().isAnnotationPresent(FragmentName.class)) {
            FragmentName fragmentName = fragment.getClass().getAnnotation(FragmentName.class);
            if (fragmentName != null) {
                return fragmentName.name();

            }
        }
        return null;
    }

    public void releaseAll() {
        fragmentMap.clear();
        fragments.clear();
    }

}
