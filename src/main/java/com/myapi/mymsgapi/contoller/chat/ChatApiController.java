package com.myapi.mymsgapi.contoller.chat;

import com.myapi.mymsgapi.comm.annotation.LoginCheck;
import com.myapi.mymsgapi.contoller.chat.dto.ChatUserJoinReq;
import com.myapi.mymsgapi.contoller.chat.dto.ChatUserJoinRes;
import com.myapi.mymsgapi.model.ChatRoom;
import com.myapi.mymsgapi.service.chat.ChatService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatApiController {

  private final ChatService _chatService;

  @ResponseBody
  @LoginCheck(required = true)
  @PostMapping(value = "/chat/list")
  @Operation(summary = "채팅방 입장", description = "사용자 채팅방 입장")
  public ChatUserJoinRes chatUserJoin(@RequestBody @Validated ChatUserJoinReq params) {
    return _chatService.chatUserJoin(params);
  }

}
