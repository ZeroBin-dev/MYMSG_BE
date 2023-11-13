package com.myapi.mymsgapi.contoller.chat;

import com.myapi.mymsgapi.contoller.chat.dto.ChatUserJoinReq;
import com.myapi.mymsgapi.contoller.chat.dto.ChatUserJoinRes;
import com.myapi.mymsgapi.contoller.user.dto.UserLginReq;
import com.myapi.mymsgapi.model.ChatRoom;
import com.myapi.mymsgapi.model.UserVO;
import com.myapi.mymsgapi.service.chat.ChatService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatRoomController {

  private final ChatService _chatService;

  @ResponseBody
  @PostMapping(value = "/chat/userJoin")
  @Operation(summary = "채팅방 입장", description = "사용자 채팅방 입장")
  public ChatUserJoinRes chatUserJoin(@RequestBody @Validated ChatUserJoinReq params) {
    return _chatService.chatUserJoin(params);
  }

  // 채팅 리스트 화면
  @GetMapping("/room")
  public String room() {
    return "chat/room";
  }

  // 모든 채팅방 목록 반환
  @GetMapping("/rooms")
  @ResponseBody
  public List<ChatRoom> rooms() {
    return _chatService.findAllRoom();
  }

  // 채팅방 생성
  @PostMapping("/room")
  @ResponseBody
  public ChatRoom createRoom(@RequestParam String name) {
    return _chatService.createRoom(name);
  }

  // 채팅방 입장 화면
  @GetMapping("/room/enter/{roomId}")
  public String roomDetail(Model model, @PathVariable String roomId) {
    model.addAttribute("roomId", roomId);
    return "chat/roomdetail";
  }

  // 특정 채팅방 조회
  @GetMapping("/room/{roomId}")
  @ResponseBody
  public ChatRoom roomInfo(@PathVariable String roomId) {
    return _chatService.findById(roomId);
  }
}
