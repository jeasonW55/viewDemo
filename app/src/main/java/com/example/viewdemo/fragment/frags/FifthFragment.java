package com.example.viewdemo.fragment.frags;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.viewdemo.R;
import com.example.viewdemo.annotation.Layout;
import com.example.viewdemo.fragment.architecture.OriginalFragment;
import com.example.viewdemo.fragment.ui.TabBarLayout;
import com.example.viewdemo.fragment.ui.WebBrowser;
import com.example.viewdemo.fragment.util.CustomUrl;

/**
 * <pre>
 *   @author wangjishun
 *   time: 2021/10/14
 *   desc:
 * </pre>
 **/
@SuppressLint("NonConstantResourceId")
@Layout(layout = R.layout.fragment_fifth_layout)
public class FifthFragment extends OriginalFragment implements View.OnClickListener, TabBarLayout.TabItemClickListener {

    private WebBrowser mBrowser;

    private FrameLayout mLayout;

    @Override
    public void setTitleBar(ViewGroup vg) {
        super.setTitleBar(vg);
        TextView titleText = vg.findViewById(R.id.title_bar_text);
        titleText.setText(getStrByResId(R.string.bottom_bt_browser));
        ImageView image = vg.findViewById(R.id.title_right_bt);
        image.setOnClickListener(this);
    }

    @Override
    public boolean isTitleBarGone() {
        return false;
    }

    @Override
    public boolean isBottomNaviGone() {
        return true;
    }

    @Override
    public boolean isTitleTextVisible() {
        return true;
    }

    @Override
    public boolean isEditAreaVisible() {
        return false;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mBrowser = view.findViewById(R.id.web_browser);
        mLayout = view.findViewById(R.id.show_video_player_view);
        TabBarLayout mTabBarLayout = view.findViewById(R.id.tab_bar_container);
        mTabBarLayout.setItemClickListener(this);

        EditText urlText = view.findViewById(R.id.url_show_tv);
        mBrowser.setUrlShowView(urlText);
        mBrowser.loadUrl(CustomUrl.BAIDU);
        mBrowser.setShowVideoView(mLayout);
        mBrowser.initWebBrowserChromeClient();
        urlText.setText(getCurrentUrl());
    }

    public WebBrowser getBrowser() {
        return mBrowser;
    }

    private String getCurrentUrl() {
        return mBrowser.getUrl();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_right_bt:
                //do nothing
                break;
            default:
                break;
        }
    }

    @Override
    public void onTabItemClick(int id) {
        String url = null;
        switch (id) {
            case R.id.tab_bt_1:
                url = CustomUrl.BAIDU;
                break;
            case R.id.tab_bt_2:
                url = CustomUrl.SINA;
                break;
            case R.id.tab_bt_3:
                url = CustomUrl.ZHIHU;
                break;
            case R.id.tab_bt_4:
                url = CustomUrl.TENGXUN;
                break;
            case R.id.tab_bt_5:
                url = CustomUrl.BILIBILI;
                break;
            case R.id.tab_bt_6:
                url = CustomUrl.ANDROID;
                break;
            default:
                break;
        }
        if (mBrowser != null) {
            mBrowser.loadUrl(url);
        }
    }
}
