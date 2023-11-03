package com.myapi.mymsgapi.contoller.chat;

import com.myapi.mymsgapi.model.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {
  private final SimpMessageSendingOperations _sendingOperations;

  @MessageMapping("/chat/message")
  public void enter(ChatMessage message) {
    if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
      message.setMessage(message.getSender() + "님이 입장하였습니다.");
    }
    _sendingOperations.convertAndSend("/topic/chat/room/" + message.getRoomId(), message);
  }
}
