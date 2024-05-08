package com.myapi.mymsgapi.comm.config;

import com.myapi.mymsgapi.comm.handler.ChatHandler;
import com.myapi.mymsgapi.comm.interceptor.HttpSessionInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSockConfig implements WebSocketConfigurer {

  private final ChatHandler _chatHandler;
  private final HttpSessionInterceptor _httpSessionInterceptor;


  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    registry.addHandler(_chatHandler, "ws/chatting/{roomId}")
      .addInterceptors(_httpSessionInterceptor)
      .setAllowedOriginPatterns("*");
  }
}
