package com.example.lib_base.framework;

import android.app.Activity;

import androidx.fragment.app.FragmentActivity;

import com.example.lib_base.annotation.Advertisement;
import com.example.lib_base.annotation.Layout;

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
    public void registerActivity(AbstractActivity activity) {
        if (activity.getClass().isAnnotationPresent(Layout.class)) {
            int layoutId = getLayoutId(activity);
            if (activity.getClass().isAnnotationPresent(Advertisement.class) && activity.needShowAds()) {
                Advertisement advertisement = activity.getClass().getAnnotation(Advertisement.class);
                if (advertisement != null) {
                    int adsLayout = advertisement.adsLayout();
                    activity.setContentView(adsLayout);
                }
            } else {
                activity.setContentView(layoutId);
            }
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

    public void releaseAll() {
        M_ACTIVITIES.clear();
    }

    public static int getLayoutId(Activity activity) {
        int layoutId = 0;
        if (activity.getClass().isAnnotationPresent(Layout.class)) {
            Layout layout = activity.getClass().getAnnotation(Layout.class);
            assert layout != null;
            layoutId = layout.layout();
        }
        return layoutId;
    }

}
