package com.myapi.mymsgapi.comm.config;

import com.myapi.mymsgapi.comm.handler.ChatHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class ChatConfig implements WebSocketConfigurer {

  private final ChatHandler _chatHandler;

  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    registry.addHandler(_chatHandler, "ws/chatting/{roomId}")
      .setAllowedOriginPatterns("*");
  }
}
