package com.myapi.mymsgapi.comm.utils;

import org.springframework.util.ObjectUtils;

public class ObjectUtil {

  public static boolean isEmpty(Object obj) {
    return ObjectUtils.isEmpty(obj);
  }

  public static boolean isNotEmpty(Object obj) {
    return !ObjectUtils.isEmpty(obj);
  }

}
