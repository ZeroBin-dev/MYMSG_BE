package com.myapi.mymsgapi.comm.config;

import com.myapi.mymsgapi.comm.handler.ChatHandler;
import com.myapi.mymsgapi.comm.interceptor.StompHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

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
