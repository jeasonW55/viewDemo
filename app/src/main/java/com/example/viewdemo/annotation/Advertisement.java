package com.example.viewdemo.annotation;

import com.example.viewdemo.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Advertisement {
    int adsLayout() default R.layout.layout_show_advertisement;
}
