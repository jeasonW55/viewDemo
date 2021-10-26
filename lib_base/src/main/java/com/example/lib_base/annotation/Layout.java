package com.example.lib_base.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * <pre>
 *   author: wangjishun
 *   time: 2021/10/13
 *   desc:
 * </pre>
 **/
@Retention(RetentionPolicy.RUNTIME)
public @interface Layout {
    int layout();
}
