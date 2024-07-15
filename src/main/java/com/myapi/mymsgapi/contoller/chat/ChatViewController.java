package com.myapi.mymsgapi.contoller.chat;

import com.myapi.mymsgapi.comm.annotation.LoginCheck;
import com.myapi.mymsgapi.comm.session.SessionKeys;
import com.myapi.mymsgapi.comm.session.SessionStore;
import com.myapi.mymsgapi.comm.utils.SystemUtil;
import com.myapi.mymsgapi.model.vo.UserVO;
import com.myapi.mymsgapi.service.chat.ChatService;
import com.myapi.mymsgapi.service.redis.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ChatViewController {

  private final ChatService _chatService;
  private final RedisService _redisService;

  @GetMapping(value = "/chats")
  @LoginCheck(required = true)
  public String chats(final Model model) {
    UserVO userVO = SessionStore.getAs(SessionKeys.USER_VO, UserVO.class);
    userVO.setRoomList(_redisService.getUserRoomList(userVO.getLginData().getUserId()));
    SessionStore.put(SessionKeys.USER_VO, userVO);
    model.addAttribute("userVO", SessionStore.getAs(SessionKeys.USER_VO, UserVO.class));
    return "views/chat/chats";
  }

  @GetMapping(value = "/chat/{roomId}")
  @LoginCheck(required = true)
  public String chat(final Model model, @PathVariable String roomId) throws Exception {
    model.addAttribute("roomVO", _chatService.getChatMessages(roomId));
    model.addAttribute("userVO", SessionStore.getAs(SessionKeys.USER_VO, UserVO.class));
    model.addAttribute("wsUrl", _chatService.getWsUrl());
    return "views/chat/chat";
  }

  @GetMapping(value = "/find")
  @LoginCheck(required = true)
  public String find(final Model model) {
    return "views/chat/find";
  }

  @GetMapping(value = "/more")
  @LoginCheck(required = true)
  public String more(final Model model) {
    model.addAttribute("userVO", SessionStore.getAs(SessionKeys.USER_VO, UserVO.class));
    return "views/chat/more";
  }

  @GetMapping(value = "/userProfile/{userId}")
  @LoginCheck(required = true)
  public String profile(final Model model, @PathVariable String userId) {
    model.addAttribute("profileInfo", _chatService.getUserProfile(userId));
    model.addAttribute("userVO", SessionStore.getAs(SessionKeys.USER_VO, UserVO.class));
    return "views/chat/userProfile";
  }

  @GetMapping(value = "/roomInfo/{roomId}")
  @LoginCheck(required = true)
  public String roomInfo(final Model model, @PathVariable String roomId) {
    model.addAttribute("roomInfo", _chatService.getRoomProfileInfo(roomId));
    return "views/chat/roomInfo";
  }

}
