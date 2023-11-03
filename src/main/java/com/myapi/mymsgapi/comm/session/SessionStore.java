package com.myapi.mymsgapi.comm.session;

import com.myapi.mymsgapi.comm.exception.MyBizException;
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
      throw new MyBizException("세션 타입이 일치하지 않습니다.");
      //throw new ApiException(SystemErrorCode.NO_MATCHING_ERROR);
    }
    getSession().setAttribute(sessionKeys.name(), object);
  }

  public static Object get(final SessionKeys sessionKeys) {
    return exists(sessionKeys) ? getSession().getAttribute(sessionKeys.name()) : null;
  }

  public static <T> T getAs(SessionKeys sessionKeys, final Class<T> clazz) {
    if (!sessionKeys.usageClass.getClass().isInstance(clazz)) {
      throw new MyBizException("세션 타입이 일치하지 않습니다.");
      //throw new ApiException(SystemErrorCode.NO_MATCHING_ERROR);
    }
    return (T) get(sessionKeys);
  }

}
