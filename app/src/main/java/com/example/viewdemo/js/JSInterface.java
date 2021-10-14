package com.example.viewdemo.js;

import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.example.viewdemo.IApplication;
import com.example.viewdemo.data.JsMonitorData.JsMonitorData;
import com.example.viewdemo.gson.GsonUtil;
import com.google.gson.reflect.TypeToken;

/**
 * <pre>
 *   author: wangjishun
 *   time: 2021/08/03
 *   desc:
 * </pre>
 **/
public class JSInterface {
    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(IApplication.INSTANCE, toast, Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public void log(String toast ) {
        Log.d("viewdemo",  toast + " jeason");
    }

    @JavascriptInterface
    public void sendResource(String msg) {
        JsMonitorData json = GsonUtil.fromJsonToObject(msg, new TypeToken<JsMonitorData>() {
        }.getType());

//        Log.d("collectorJs-resource", msg);
        Log.d("collectorJs-resource", json.toString());
    }

    @JavascriptInterface
    public void sendError(String msg) {
        Log.d("collectorJs-error", msg);
    }

    @JavascriptInterface
    public void onActionEvent(final String messageString) {// Looper (JavaBridge, tid 5492) {6be5fdf}
        Log.d("collectorJs-resource", messageString);
    }
}
