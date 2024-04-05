package com.myapi.mymsgapi.comm.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.util.ObjectUtils;

public class ObjectUtil {

  private static Gson gson = new Gson();

  public static boolean isEmpty(Object obj) {
    return ObjectUtils.isEmpty(obj);
  }

  public static boolean isNotEmpty(Object obj) {
    return !ObjectUtils.isEmpty(obj);
  }

  public static Object nvl(Object obj, String defaultValue) {
    return isNotEmpty(obj) ? obj : defaultValue;
  }

  public static <T> T jsonToObject(String jsonString, Class<T> type) {
    return (jsonString != null) ? gson.fromJson(jsonString, type) : null;
  }
  public static <T> T jsonToObject(JsonObject jsonObject, Class<T> type) {
    return (jsonObject != null) ? gson.fromJson(jsonObject, type) : null;
  }

  public static JsonObject toJson(final String p) {
    JsonParser jsonParser = new JsonParser();
    Object obj = jsonParser.parse(p);
    JsonObject jo = (JsonObject) obj;
    return jo;
  }
}
