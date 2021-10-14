package com.example.viewdemo.gson;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.StringReader;
import java.lang.reflect.Type;

/**
 * <pre>
 *   author: wangjishun
 *   time: 2021/08/04
 *   desc:
 * </pre>
 **/
public final class GsonUtil {

    private static final Gson gson = new Gson();

    public static <T> T fromJsonToObject(String jsonString, Type typeOfT) {
        try {
            if(TextUtils.isEmpty(jsonString)) {
                return null;
            }
            StringReader builder = new StringReader(jsonString);
            return gson.fromJson(builder, typeOfT);
        }catch (JsonSyntaxException e){
            e.printStackTrace();
        }
        return null;
    }

}
