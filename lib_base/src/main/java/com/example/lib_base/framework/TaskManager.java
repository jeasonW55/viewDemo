package com.example.lib_base.framework;

import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;
import java.util.List;

public final class TaskManager {

    private TaskManager() {}

    public void removeCallback(Runnable task) {
        if (mHandler != null) {
            mHandler.removeCallbacks(task);
        }
    }

    public static class SingleTon {

        private static final TaskManager INSTANCE = new TaskManager();

        public static TaskManager getInstance() {
            return INSTANCE;
        }
    }

    private static final int NUMBER = 8;

    private final List<DelayTask> mTasks = new ArrayList<>(NUMBER);

    private Handler mHandler;

    public void executeDelayedTask(DelayTask task, long delayedTime) {
        mTasks.add(task);
        if (mHandler != null) {
            mHandler.postDelayed(task, delayedTime);
        }
    }

    public void sendMessage(int what, Object obj) {
        if (mHandler != null) {
            Message msg = mHandler.obtainMessage();
            msg.what = what;
            msg.obj = obj;
            mHandler.sendMessage(msg);
        }
    }

    /**
     * 移除handler执行的任务
     */
    public void removeAllCallbacks() {
        if (mHandler == null || mTasks.isEmpty()) {
            return;
        }
        for (DelayTask task: mTasks) {
            mHandler.removeCallbacks(task);
        }
    }

    public void setHandler(Handler handler) {
        this.mHandler = handler;
    }


    public abstract static class DelayTask implements Runnable {}
}
