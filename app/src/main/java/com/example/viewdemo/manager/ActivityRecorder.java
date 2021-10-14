package com.example.viewdemo.manager;

import android.app.Activity;
import android.text.TextUtils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.viewdemo.annotation.FragmentName;
import com.example.viewdemo.annotation.Layout;
import com.example.viewdemo.fragment.architecture.FragmentRecorder;
import com.example.viewdemo.fragment.architecture.OriginalFragment;
import com.example.viewdemo.fragment.data.RuntimeData;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *   @author wangjishun
 *   time: 2021/10/13
 *   desc: 负责记录Activity
 * </pre>
 **/
public final class ActivityRecorder {

    private ActivityRecorder() {
    }

    public static class SingleTon {
        private static final ActivityRecorder INSTANCE = new ActivityRecorder();

        public static ActivityRecorder getInstance() {
            return INSTANCE;
        }
    }


    private final int NUMBER = 8;

    private final List<Activity> M_ACTIVITIES = new ArrayList<>(NUMBER);


    /**
     * 注册activity
     *
     * @param activity 传入的activity
     */
    public void registerActivity(Activity activity) {
        if (activity.getClass().isAnnotationPresent(Layout.class)) {
            Layout layout = activity.getClass().getAnnotation(Layout.class);
            assert layout != null;
            int layoutId = layout.layout();
            activity.setContentView(layoutId);
        }
        M_ACTIVITIES.add(activity);
    }

    /**
     * 取消activity注册
     *
     * @param activity 传入的activity
     */
    public void unRegisterActivity(Activity activity) {
        if (M_ACTIVITIES.isEmpty()) {
            return;
        }
        M_ACTIVITIES.remove(activity);
    }

    public FragmentActivity getCurrentActivity() {
        if (M_ACTIVITIES.isEmpty()) {
            return null;
        }
        return (FragmentActivity) M_ACTIVITIES.get(M_ACTIVITIES.size() - 1);
    }

    /**
     * 启动fragment
     */
    public void startFragment(int containerId, Fragment fragment, boolean addToBackStack, RuntimeData data) {
        Fragment currentFragment = FragmentRecorder.SingleTon.getInstance().getCurrentFragment();
        if (currentFragment != null && TextUtils.equals(fragment.getClass().getName(), currentFragment.getClass().getName())) {
            return;
        }
        FragmentActivity currentActivity = getCurrentActivity();
        if (currentActivity != null) {
            FragmentManager manager = currentActivity.getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            manager.addOnBackStackChangedListener(() -> {
                Fragment current = manager.findFragmentById(containerId);
                if (current != null) {
                    current.onResume();
                }
            });
            if (currentFragment != null) {
                transaction.hide(currentFragment);
            }
            FragmentRecorder recorder = FragmentRecorder.SingleTon.getInstance();
            if (recorder.isRecord(fragment)) {
                transaction.show(recorder.getFragment(fragment));
            } else {
                transaction.add(containerId, fragment);
                if (addToBackStack) {
                    transaction.addToBackStack(fragment.getClass().toString());
                }
            }
            if (data != null && fragment instanceof OriginalFragment) {
                OriginalFragment original = (OriginalFragment) fragment;
                original.parseRuntimeData(data);
            }

            transaction.commit();
        }
    }

    public void releaseAll() {
        M_ACTIVITIES.clear();
    }

}
