package com.myapi.mymsgapi.comm.fundamental;

import com.myapi.mymsgapi.comm.utils.MapUtil;
import com.myapi.mymsgapi.comm.utils.ObjectUtil;
import com.myapi.mymsgapi.comm.utils.StringUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Custom Map
 */
public class YBMap<T, T1> extends HashMap {

  public String stringValue(final String k) {
    return stringValue(k, "");
  }

  public String stringValue(final String k, final String defaultValue) {
    if (!keySet().contains(k)) {
      return defaultValue;
    }

    return String.valueOf(ObjectUtil.nvl(get(k), defaultValue));
  }

  public boolean boolValue(final String k) {
    if (!keySet().contains(k)) {
      return false;
    }

    return (boolean) get(k);
  }

  public int intValue(final String k) {
    return Integer.parseInt(stringValue(k));
  }

  public int intValue(final String k, final int dv) {
    return Integer.parseInt(StringUtil.nvl(stringValue(k), Integer.toString(dv)));
  }

  public List asList(final String k) {
    return (List) get(k);
  }

  public YBMap asMap(final String k) {
    return MapUtil.toMap(get(k));
  }

}
