package com.example.lib_base.framework;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.example.lib_base.ui.AdvertisementLayout;


/**
 * <pre>
 *   @author wangjishun
 *   time: 2021/10/13
 *   desc: 顶层父类
 * </pre>
 **/
public abstract class AbstractActivity extends FragmentActivity {

    private final ActivityRecorder mRecorders = ActivityRecorder.SingleTon.getInstance();

    private final TaskManager mTaskManager = TaskManager.SingleTon.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecorders.registerActivity(this);
        if (needShowAds()) {
            mTaskManager.setHandler(new Handler(getMainLooper()) {
                @Override
                public void handleMessage(@NonNull Message msg) {
                    if (msg.what == AdvertisementLayout.END_ADS) {
                        initViews();
                    }
                }
            });
        } else {
            initViews();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRecorders.unRegisterActivity(this);
        mTaskManager.removeAllCallbacks();
    }

    /**
     * 初始化相关View
     */
    public void initViews() {
        //do nothing
    }

    /**
     * 是否显示间隔动画
     * @return 间隔动画是否会显示
     */
    protected boolean needShowAds() {
        return false;
    }


}
