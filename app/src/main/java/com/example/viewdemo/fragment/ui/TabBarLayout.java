package com.example.viewdemo.fragment.ui;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.viewdemo.R;

/**
 * <pre>
 *   @author wangjishun
 *   time: 2021/10/14
 *   desc: tab 按钮---一长串按钮
 * </pre>
 **/
public class TabBarLayout extends LinearLayout implements View.OnClickListener {

    private final String[] mTabBarTexts = {
            getResources().getString(R.string.tab_bar_baidu),
            getResources().getString(R.string.tab_bar_sina),
            getResources().getString(R.string.tab_bar_zhihu),
            getResources().getString(R.string.tab_bar_tx),
            getResources().getString(R.string.tab_bar_bz),
            getResources().getString(R.string.tab_bar_android)
    };

    private final int[] mTabIds = {
            R.id.tab_bt_1,
            R.id.tab_bt_2,
            R.id.tab_bt_3,
            R.id.tab_bt_4,
            R.id.tab_bt_5,
            R.id.tab_bt_6
    };

    private TabItemClickListener mItemClickListener;

    public TabBarLayout(Context context) {
        super(context);
    }

    public TabBarLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TabBarLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    private void init() {
        setOrientation(HORIZONTAL);
        for (int i = 0;i < mTabBarTexts.length; i++) {
            TextView tv = new TextView(getContext());
            tv.setText(mTabBarTexts[i]);
            tv.setId(mTabIds[i]);
            tv.setOnClickListener(this);
            tv.setTextColor(Color.BLACK);
            LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = getResources().getDimensionPixelOffset(R.dimen.dp_10);
            params.rightMargin = getResources().getDimensionPixelOffset(R.dimen.dp_10);
            tv.setLayoutParams(params);
            tv.setTextColor(Color.BLACK);
            addView(tv);
        }
    }

    @Override
    public void onClick(View view) {
        if (mItemClickListener != null) {
            mItemClickListener.onTabItemClick(view.getId());
        }
    }

    public void setItemClickListener(TabItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

    public interface TabItemClickListener {
        /**
         * tab bar 每个子元素的点击事件
         * @param id view的id
         */
        void onTabItemClick(int id);
    }
}
