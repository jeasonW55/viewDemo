package com.example.viewdemo.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewdemo.R;
import com.example.viewdemo.Url;
import com.example.viewdemo.adapter.ExpandedBean;
import com.example.viewdemo.adapter.ExpandedListAdapter;
import com.example.viewdemo.annotation.Layout;
import com.example.viewdemo.js.JSInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *   @author: wangjishun
 *   time: 2021/07/29
 *   desc:
 * </pre>
 **/
@Layout(layout = R.layout.activity_show)
public class ShowViewActivity extends AbstractActivity {

    @Override
    public void initViews() {
        Intent intent = getIntent();
        final String viewType = intent.getStringExtra(MainActivity.NAME);
        if (TextUtils.equals(viewType, MainActivity.SHADOW_TYPE)) {
            findViewById(R.id.shadowView).setVisibility(View.VISIBLE);
        }
        if (TextUtils.equals(viewType, MainActivity.LISTVIEW_TYPE)) {
            RecyclerView recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setVisibility(View.VISIBLE);
            View foot = LayoutInflater.from(this).inflate(R.layout.foot_view, null, false);
            View header = LayoutInflater.from(this).inflate(R.layout.foot_view, null, false);
            ExpandedListAdapter expandedListAdapter = new ExpandedListAdapter.Builder()
                    .setExpandType(ExpandedListAdapter.TYPE_SINGLE)
                    .setOnBindHolderListener(new ExpandedListAdapter.OnBindHolderListener() {
                        @Override
                        public void bindVisibleContent(int position, ViewGroup vg) {
                            vg.removeAllViews();
                            TextView tv = new TextView(vg.getContext());
                            tv.setText("Item " + position);
                            tv.setGravity(Gravity.CENTER);
                            vg.addView(tv);
                            vg.getLayoutParams().height = 150;
                            vg.invalidate();
                        }

                        @Override
                        public void bindExpandedContent(int position, ViewGroup expandedVg) {
                            expandedVg.removeAllViews();
                            TextView tv = new TextView(expandedVg.getContext());
                            tv.setText("Expanded" + position);
                            tv.setGravity(Gravity.CENTER);
                            tv.setTextColor(Color.RED);
                            expandedVg.addView(tv);
                        }
                    })
                    .setOnPosExpandListener(new ExpandedListAdapter.OnPosExpandListener() {
                        @Override
                        public void onPosExpand(int position, boolean isOpen) {

                        }
                    })
                    .setData(test())
                    .setFooterView(foot)
                    .setHeaderView(header)
                    .build();
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(expandedListAdapter);
        }

        if (TextUtils.equals(viewType, MainActivity.WEB_TYPE)) {
            WebView web = findViewById(R.id.webView);
            web.setVisibility(View.VISIBLE);
            dealWebView(web);
        }

        if (TextUtils.equals(viewType, MainActivity.VERSION_TYPE)) {
            View version = findViewById(R.id.versionInfo);
            version.setVisibility(View.VISIBLE);
            TextView versionInfo = version.findViewById(R.id.versionInfoDetail);
            String verInfo = Build.VERSION.SDK_INT + "\n"
                    + Build.MANUFACTURER + "\n"
                    + Build.MODEL + "\n"
                    + Build.TYPE + "\n"
                    + Build.PRODUCT + "\n"
                    + Build.HOST + "\n";
            versionInfo.setText(verInfo);
        }
    }

    private void dealWebView(WebView web) {
        WebSettings settings = web.getSettings();
        settings.setJavaScriptEnabled(true);
        web.setWebViewClient(new SelfDefineClient());
        web.addJavascriptInterface(new JSInterface(), "control");
        web.loadUrl(Url.FILE_URL);
    }

    private List<ExpandedBean> test() {
        List<ExpandedBean> list = new ArrayList<>();
        for (int i = 0; i < 50; ++i) {
            list.add(new ExpandedBean());
        }
        return list;
    }

    public  class SelfDefineClient extends WebViewClient {
        String collectorJs = "https://android_asset/collector-1.js";
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            view.postDelayed(new Runnable() {
                @Override
                public void run() {
                    view.loadUrl("javascript: alert(\"China Number one\")");

                }
            }, 2000);
        }

        @Nullable
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
            return super.shouldInterceptRequest(view, request);
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        WebView viewById = findViewById(R.id.webView);
        if (viewById.getVisibility() == View.VISIBLE && keyCode == KeyEvent.KEYCODE_BACK && viewById.canGoBack()) {
            viewById.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
