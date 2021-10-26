package com.example.viewdemo.fragment.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.example.lib_base.framework.ActivityRecorder;

/**
 * <pre>
 *   author: wangjishun
 *   time: 2021/10/14
 *   desc: web 浏览器
 * </pre>
 **/
public class WebBrowser extends WebView {

    private TextView mUrlShowView;

    private FrameLayout mShowVideoView;

    public WebBrowser(@NonNull Context context) {
        super(context);
        init();
    }

    public WebBrowser(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WebBrowser(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public void loadUrl(@NonNull String url) {
        super.loadUrl(url);
        Log.d("url", url);
        if (mUrlShowView != null) {
            mUrlShowView.setText(url);
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void init() {
        setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setSupportZoom(true);
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setPluginState(WebSettings.PluginState.ON);
        settings.setAllowFileAccess(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setDefaultTextEncodingName("GBK");
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        setWebViewClient(new WebBrowserClient());
    }

    @Override
    public void onResume() {
        super.onResume();
        setUrlText(getUrl());
    }

    public class WebBrowserClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            setUrlText(url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            setUrlText(url);
        }


        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d("url", url);
            view.loadUrl(url);
            setUrlText(url);
            return true;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return true;
        }
    }

    public void setUrlShowView(TextView mUrlShowView) {
        this.mUrlShowView = mUrlShowView;
    }

    private void setUrlText(String url) {
        if (mUrlShowView != null) {
            mUrlShowView.setText(url);
        }
    }

    public void setShowVideoView(FrameLayout showVideoView) {
        this.mShowVideoView = showVideoView;
    }

    public void initWebBrowserChromeClient() {
        WebBrowserChromeClient chromeClient = new WebBrowserChromeClient();
        chromeClient.setShowVideoView(mShowVideoView);
        chromeClient.setWebView(this);
        setWebChromeClient(chromeClient);
    }

    public static class WebBrowserChromeClient extends WebChromeClient {

        private View mCustomView;

        private CustomViewCallback mCallback;

        private FrameLayout mShowVideoView;

        private WebView mWebView;

        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {
            FragmentActivity currentActivity = ActivityRecorder.SingleTon.getInstance().getCurrentActivity();
            if (currentActivity != null) {
                currentActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }
            mWebView.setVisibility(View.INVISIBLE);

            if (mCustomView != null) {
                callback.onCustomViewHidden();
                return;
            }
            mShowVideoView.addView(view);
            mShowVideoView.bringToFront();
            mCallback = callback;
            mCustomView = view;

            mShowVideoView.setVisibility(View.VISIBLE);
        }

        @Override
        public void onHideCustomView() {
            FragmentActivity currentActivity = ActivityRecorder.SingleTon.getInstance().getCurrentActivity();
            if (currentActivity != null && currentActivity.getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                currentActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
            mCustomView.setVisibility(View.GONE);
            if (mShowVideoView != null) {
                mShowVideoView.removeView(mCustomView);
                mShowVideoView.setVisibility(View.GONE);
            }
            mCustomView = null;
            mCallback.onCustomViewHidden();
            mWebView.setVisibility(View.VISIBLE);
        }

        public void setShowVideoView(FrameLayout videoView) {
            this.mShowVideoView = videoView;
        }

        public void setWebView(WebView webBrowser) {
            this.mWebView = webBrowser;
        }
    }



}
