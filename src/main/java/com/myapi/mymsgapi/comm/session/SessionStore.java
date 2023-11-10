package com.myapi.mymsgapi.comm.session;

import com.myapi.mymsgapi.comm.exception.ApiException;
import com.myapi.mymsgapi.comm.types.ExceptType;
import com.myapi.mymsgapi.comm.utils.HttpRequestUtils;
import jakarta.servlet.http.HttpSession;

public class SessionStore {

  private static HttpSession getSession() {
    return HttpRequestUtils.getSession();
  }

  private static boolean exists(final SessionKeys sessionKeys) {
    return getSession().getAttribute(sessionKeys.name()) != null;
  }

  public static void put(final SessionKeys sessionKeys, final Object object) {
    if (!sessionKeys.usageClass.isInstance(object)) {
      throw new ApiException(ExceptType.SESS001); // 세션타입이 일치하지 않습니다.
    }
    getSession().setAttribute(sessionKeys.name(), object);
  }

  public static Object get(final SessionKeys sessionKeys) {
    return exists(sessionKeys) ? getSession().getAttribute(sessionKeys.name()) : null;
  }

  public static <T> T getAs(SessionKeys sessionKeys, final Class<T> clazz) {
    if (!sessionKeys.usageClass.getClass().isInstance(clazz)) {
      throw new ApiException(ExceptType.SESS001); // 세션타입이 일치하지 않습니다.
    }
    return (T) get(sessionKeys);
  }

}
