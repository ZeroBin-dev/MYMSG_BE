package com.myapi.mymsgapi.comm.interceptor;

import com.myapi.mymsgapi.comm.annotation.LoginCheck;
import com.myapi.mymsgapi.comm.exception.YbBizException;
import com.myapi.mymsgapi.comm.types.ExceptType;
import com.myapi.mymsgapi.comm.utils.SessionUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    HandlerMethod handlerMethod = (HandlerMethod) handler;
    LoginCheck loginCheck = handlerMethod.getMethodAnnotation(LoginCheck.class);

    if(loginCheck != null && loginCheck.required()){
      if (SessionUtil.isLogin()) {
        return true;
      } else {
        response.sendRedirect(request.getContextPath() + "/user/login");
        return false; // 더 이상 진행하지 않도록 false 반환
      }
    }

    return true;
  }
}
