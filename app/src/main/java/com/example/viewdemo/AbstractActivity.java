package com.example.viewdemo;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.viewdemo.fragment.architecture.FragmentRecorder;
import com.example.viewdemo.fragment.architecture.OriginalFragment;
import com.example.viewdemo.fragment.data.RuntimeData;
import com.example.viewdemo.manager.ActivityRecorder;


/**
 * <pre>
 *   @author wangjishun
 *   time: 2021/10/13
 *   desc: 顶层父类
 * </pre>
 **/
public abstract class AbstractActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRecorder.SingleTon.getInstance().registerActivity(this);
        initViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityRecorder.SingleTon.getInstance().unRegisterActivity(this);
    }

    /**
     * 初始化相关View
     */
    public void initViews() {
        //do nothing
    }

}
