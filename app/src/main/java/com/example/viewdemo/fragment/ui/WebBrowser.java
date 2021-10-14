package com.example.viewdemo.fragment.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * <pre>
 *   author: wangjishun
 *   time: 2021/10/14
 *   desc: web 浏览器
 * </pre>
 **/
public class WebBrowser extends WebView {

    private TextView mUrlShowView;

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
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        setWebViewClient(new WebBrowserClient());
        setWebChromeClient(new WebChromeClient());
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
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//            super.onReceivedSslError(view, handler, error);
            handler.proceed();
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d("url", url);
            setUrlText(url);
            return true;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            Log.d("url", request.getUrl().toString());
            setUrlText(request.getUrl().toString());
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
}
