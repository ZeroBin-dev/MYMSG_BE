package com.myapi.mymsgapi.comm.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class HttpRequestUtils {

  public static HttpServletRequest getCurrentRequest() {
    try{
      return ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
    }catch (Exception e){
      return null;
    }
  }

  public static HttpSession getSession() {
    return getCurrentRequest().getSession();
  }

}
