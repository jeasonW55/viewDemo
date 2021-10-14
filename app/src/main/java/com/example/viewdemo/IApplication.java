package com.example.viewdemo;

import android.app.Application;

/**
 * <pre>
 *   author: wangjishun
 *   time: 2021/08/03
 *   desc:
 * </pre>
 **/
public class IApplication extends Application {

    public static IApplication INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }
}
