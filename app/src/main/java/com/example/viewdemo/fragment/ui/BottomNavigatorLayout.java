package com.example.viewdemo.fragment.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.viewdemo.R;
import com.example.viewdemo.fragment.data.RuntimeData;
import com.example.viewdemo.fragment.frags.MainFragment;
import com.example.viewdemo.fragment.frags.SecondFragment;
import com.example.viewdemo.fragment.frags.ThirdFragment;
import com.example.viewdemo.manager.ActivityRecorder;

public class BottomNavigatorLayout extends LinearLayout implements View.OnClickListener {
    public BottomNavigatorLayout(Context context) {
        super(context);

    }

    public BottomNavigatorLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    public BottomNavigatorLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();
    }

    private void initView() {
        Button firstButton = findViewById(R.id.bottom_bt_1);
        firstButton.setOnClickListener(this);

        Button secondButton = findViewById(R.id.bottom_bt_2);
        secondButton.setOnClickListener(this);

        Button thirdButton = findViewById(R.id.bottom_bt_3);
        thirdButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bottom_bt_1:
                startFragment(new MainFragment(), false);
                break;
            case R.id.bottom_bt_2:
                startFragment(new SecondFragment(), true);
                break;
            case R.id.bottom_bt_3:
                startFragment(new ThirdFragment(), true);
                break;
            default:
                break;
        }
    }

    private void startFragment(Fragment fragment, boolean addToStack) {
        ActivityRecorder.SingleTon.getInstance().startFragment(R.id.fragment_container,
                fragment, addToStack, new RuntimeData());
    }
}
