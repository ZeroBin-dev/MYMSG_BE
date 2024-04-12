package com.myapi.mymsgapi.contoller.chat;

import com.myapi.mymsgapi.contoller.chat.dto.ChatUserJoinReq;
import com.myapi.mymsgapi.contoller.chat.dto.ChatUserJoinRes;
import com.myapi.mymsgapi.service.chat.ChatService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatApiController {

  private final ChatService _chatService;

  /**
   * 채팅방 만들기
   */
  @ResponseBody
  @PostMapping(value = "/newChat")
  @Operation(summary = "채팅방 만들기", description = "채방방 만들기")
  public ChatUserJoinRes makeNewChat(@RequestBody @Validated ChatUserJoinReq params) {
    return _chatService.makeNewChat(params);
  }

}
