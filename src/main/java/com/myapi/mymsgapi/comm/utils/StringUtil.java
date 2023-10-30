package com.myapi.mymsgapi.comm.utils;

public class StringUtil {

  public static boolean isNotEmpty(String str) {
    return str != null && !str.isEmpty();
  }

  public static boolean isEmpty(String str) {
    return !isNotEmpty(str);
  }

  public static String nvl(String str, String defaultValue) {
    return isNotEmpty(str) ? str : defaultValue;
  }

}
