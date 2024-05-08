package com.myapi.mymsgapi.comm.interceptor;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.Map;

@Component
public class HttpSessionInterceptor extends HttpSessionHandshakeInterceptor {

  @Override
  public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
    if (request.getURI().getPath().contains("/ws/chatting")) { // 웹소켓 경로에만 적용
      HttpSession session = getSession(request);
      if (session != null) {
        attributes.put("httpSession", session);
      }
    }
    return super.beforeHandshake(request, response, wsHandler, attributes);
  }

  private HttpSession getSession(ServerHttpRequest request) {
    if (request instanceof ServletServerHttpRequest) {
      HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
      return servletRequest.getSession(false);
    }
    return null;
  }
}
