package com.example.viewdemo.fragment.architecture;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.viewdemo.R;
import com.example.viewdemo.fragment.ViewController;
import com.example.viewdemo.fragment.data.RuntimeData;

/**
 * <pre>
 *   @author wangjishun
 *   time: 2021/10/13
 *   desc: 所有fragment的父类
 * </pre>
 **/
public abstract class OriginalFragment extends Fragment implements ViewController {

    public static final String TAG = "wangjishun";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, toString()  + ":\ncreateView");

        View view = FragmentRecorder.SingleTon.getInstance().registerFragment(this, inflater, container);
        initView(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, toString() + ":\nonResume");
        View bottomNavi = getActivity().findViewById(R.id.bottom_navigator);
        if (bottomNavi != null) {
            if (isBottomNaviGone()) {
                bottomNavi.setVisibility(View.GONE);
            } else {
                bottomNavi.setVisibility(View.VISIBLE);
            }
        }

        RelativeLayout titleBar = getActivity().findViewById(R.id.title_bar);
        if (titleBar != null) {
            if (isBottomNaviGone()) {
                titleBar.setVisibility(View.GONE);
            } else {
                titleBar.setVisibility(View.VISIBLE);
                setTitleBar(titleBar);
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, toString() + ":\nonPause");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, toString() + ":\nonDestroyView");
    }

    protected void initView(View view){
        Log.d(TAG, toString()  + ":\ninitView");


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        FragmentRecorder.SingleTon.getInstance().unregister(this);
    }

    public void parseRuntimeData(RuntimeData data) {
        Log.d(TAG, toString()  + ":\nparseData");
    }
}
