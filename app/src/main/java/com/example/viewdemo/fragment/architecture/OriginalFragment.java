package com.example.viewdemo.fragment.architecture;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.viewdemo.R;
import com.example.viewdemo.anim.SelfAnimator;
import com.example.viewdemo.fragment.ViewController;
import com.example.viewdemo.fragment.data.RuntimeData;
import com.example.viewdemo.fragment.frags.FifthFragment;

/**
 * <pre>
 *   @author wangjishun
 *   time: 2021/10/13
 *   desc: 所有fragment的父类
 * </pre>
 **/
public abstract class OriginalFragment extends Fragment implements ViewController {

    public static final String TAG = "wangjishun";

    private Fragment mCurrent;

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
        mCurrent = this;
        Log.d(TAG, toString() + ":\nonResume");
        if (getActivity() != null) {
            View bottomNavi = getActivity().findViewById(R.id.bottom_navigator);
            int height = getResources().getDimensionPixelOffset(R.dimen.dp_70);
            if (bottomNavi != null) {
                if (isBottomNaviGone()) {
                    bottomNavi.setVisibility(View.GONE);
                } else {
                    bottomNavi.setVisibility(View.VISIBLE);
                }
            }

            RelativeLayout titleBar = getActivity().findViewById(R.id.title_bar);
            if (titleBar != null) {
                if (isTitleBarGone()) {
                    SelfAnimator.verticalExpanded(titleBar, false, titleBar.getMeasuredHeight());
                } else {
                    titleBar.setVisibility(View.VISIBLE);
                    setTitleBar(titleBar);
                }
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
    public void setTitleBar(ViewGroup vg) {
        TextView tv = vg.findViewById(R.id.title_bar_text);
        tv.setText(getStrByResId(R.string.title_default));

        EditText urlEditAreaView = vg.findViewById(R.id.title_edit_area);
        if (isEditAreaVisible()) {
            urlEditAreaView.setVisibility(View.VISIBLE);
        } else {
            urlEditAreaView.setVisibility(View.GONE);
        }

        if (isTitleTextVisible()) {
            tv.setVisibility(View.VISIBLE);
        } else {
            tv.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        FragmentRecorder.SingleTon.getInstance().unregister(this);
    }

    public void parseRuntimeData(RuntimeData data) {
        Log.d(TAG, toString()  + ":\nparseData");
    }

    /**
     * 获取资源文件的字符串
     */
    protected String getStrByResId(int id) {
        return getResources().getString(id);
    }

    public Fragment getCurrent() {
        return mCurrent;
    }

    @Override
    public boolean isEditAreaVisible() {
        return false;
    }

    @Override
    public boolean isTitleTextVisible() {
        return true;
    }
}
