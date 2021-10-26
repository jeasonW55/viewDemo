package com.example.viewdemo.activities;

import android.content.res.Configuration;
import android.view.WindowManager;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.example.lib_base.framework.AbstractActivity;
import com.example.viewdemo.R;
import com.example.lib_base.annotation.Layout;
import com.example.viewdemo.fragment.ui.WebBrowser;
import com.example.viewdemo.fragment.util.CustomUrl;

@Layout(layout = R.layout.activity_h5_layout)
public class H5FullScreenActivity extends AbstractActivity {

    @Override
    public void initViews() {
        super.initViews();
        WebBrowser browser = findViewById(R.id.browser);
        FrameLayout videoView = findViewById(R.id.video_view);
        browser.setShowVideoView(videoView);
        browser.initWebBrowserChromeClient();
        browser.loadUrl(CustomUrl.BAIDU);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        switch (newConfig.orientation) {
            case Configuration.ORIENTATION_LANDSCAPE:
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                break;
            case Configuration.ORIENTATION_PORTRAIT:
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
                break;
            case Configuration.ORIENTATION_SQUARE:
                break;
            case Configuration.ORIENTATION_UNDEFINED:
                break;
        }
    }
}
