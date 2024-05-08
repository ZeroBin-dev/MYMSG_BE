package com.myapi.mymsgapi.contoller.chat;

import com.myapi.mymsgapi.contoller.chat.dto.ChatUserInfoReq;
import com.myapi.mymsgapi.contoller.chat.dto.ChatUserJoinReq;
import com.myapi.mymsgapi.contoller.chat.dto.ChatUserJoinRes;
import com.myapi.mymsgapi.contoller.chat.dto.RoomNameChangeReq;
import com.myapi.mymsgapi.contoller.comm.dto.BaseUpdateResponse;
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

  /**
   * 채팅방 나가기
   */
  @ResponseBody
  @PostMapping(value = "/exitRoom")
  @Operation(summary = "채팅방 나가기", description = "채방방 나가기")
  public BaseUpdateResponse exitRoom(@RequestBody @Validated ChatUserInfoReq params) {
    return _chatService.exitRoom(params);
  }

  /**
   * 채팅방 이름변경
   */
  @ResponseBody
  @PostMapping(value = "/updateRoomName")
  @Operation(summary = "채팅방 이름변경", description = "채방방 이름변경")
  public BaseUpdateResponse updateRoomName(@RequestBody @Validated RoomNameChangeReq params) {
    return _chatService.updateRoomName(params);
  }

  /**
   * 채팅방 초대하기
   */
  @ResponseBody
  @PostMapping(value = "/inviteRoom")
  @Operation(summary = "채팅방 초대", description = "채방방 초대하기")
  public BaseUpdateResponse inviteRoom(@RequestBody @Validated ChatUserInfoReq params) {
    return _chatService.inviteRoom(params);
  }




}
