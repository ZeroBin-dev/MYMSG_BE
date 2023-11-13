package com.myapi.mymsgapi.comm.utils;

import org.springframework.util.ObjectUtils;

public class ObjectUtil {

  public static boolean isEmpty(Object obj) {
    return ObjectUtils.isEmpty(obj);
  }

  public static boolean isNotEmpty(Object obj) {
    return !ObjectUtils.isEmpty(obj);
  }

  public static Object nvl(Object obj, String defaultValue) {
    return isNotEmpty(obj) ? obj : defaultValue;
  }

}
