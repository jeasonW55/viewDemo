package com.example.viewdemo.fragment.frags;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.viewdemo.R;
import com.example.viewdemo.annotation.Layout;
import com.example.viewdemo.fragment.architecture.OriginalFragment;
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
public class FifthFragment extends OriginalFragment implements View.OnClickListener {

    private WebBrowser browser;

    private EditText urlEditAreaView;

    @Override
    public void setTitleBar(ViewGroup vg) {
        super.setTitleBar(vg);
        urlEditAreaView = vg.findViewById(R.id.title_edit_area);
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
        return false;
    }

    @Override
    public boolean isEditAreaVisible() {
        return true;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        browser = view.findViewById(R.id.web_browser);
    }

    @Override
    public void onResume() {
        super.onResume();
        browser.loadUrl(CustomUrl.BAIDU);
        urlEditAreaView.setText(getCurrentUrl());
    }

    public WebBrowser getBrowser() {
        return browser;
    }

    private String getCurrentUrl() {
        return browser.getUrl();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_right_bt:
                if (browser!= null) {
                    String url = urlEditAreaView.getText().toString();
                    browser.loadUrl(url);
                    urlEditAreaView.setText(url);
                }
                break;
            default:
                break;
        }
    }
}
