package com.myapi.mymsgapi.comm.interceptor;

import com.myapi.mymsgapi.comm.utils.SessionUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    if (SessionUtil.isLogin()) {
      return true;
    } else {
      request.setAttribute("exception", "AuthenticationException");
      request.getRequestDispatcher("/error").forward(request, response);
      return false;
    }
  }
}
