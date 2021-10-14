package com.example.viewdemo.fragment.ui;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.example.viewdemo.R;
import com.example.viewdemo.fragment.util.Constants;


public class BottomNavigatorLayout extends LinearLayout implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    RadioButton firstButton ;
    RadioButton secondButton;
    RadioButton thirdButton;
    RadioButton forthButton;
    ViewPager pager;

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
        RadioGroup btGroup = findViewById(R.id.bottom_bt_group);
        firstButton = findViewById(R.id.bottom_bt_1);
        secondButton = findViewById(R.id.bottom_bt_2);
        thirdButton = findViewById(R.id.bottom_bt_3);
        forthButton = findViewById(R.id.bottom_bt_4);
        btGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.bottom_bt_1:
                firstButton.setChecked(true);
                switchFragment(Constants.PAGE_MAIN);
                break;
            case R.id.bottom_bt_2:
                secondButton.setChecked(true);
                switchFragment(Constants.PAGE_SECOND);
                break;
            case R.id.bottom_bt_3:
                thirdButton.setChecked(true);
                switchFragment(Constants.PAGE_THIRD);
                break;
            case R.id.bottom_bt_4:
                forthButton.setChecked(true);
                switchFragment(Constants.PAGE_FORTH);
                break;
            default:
                break;
        }
    }

    private void switchFragment(int pageOrder) {
        if (pager != null) {
            pager.setCurrentItem(pageOrder);
        }
    }

    public void setPager(ViewPager pager) {
        this.pager = pager;
    }

    public ViewPager getPager() {
        return pager;
    }
}
