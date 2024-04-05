package com.myapi.mymsgapi.contoller.chat;

import com.myapi.mymsgapi.comm.annotation.LoginCheck;
import com.myapi.mymsgapi.comm.session.SessionKeys;
import com.myapi.mymsgapi.comm.session.SessionStore;
import com.myapi.mymsgapi.comm.utils.CryptoUtil;
import com.myapi.mymsgapi.model.UserRoomInfo;
import com.myapi.mymsgapi.model.vo.UserVO;
import com.myapi.mymsgapi.service.chat.ChatService;
import com.myapi.mymsgapi.service.redis.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

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
  public String chat(final Model model, @PathVariable String roomId) {
    model.addAttribute("roomVO", _chatService.getChatMessages(roomId));
    model.addAttribute("userVO", SessionStore.getAs(SessionKeys.USER_VO, UserVO.class));
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
    return "views/chat/more";
  }

  @GetMapping(value = "/profile")
  @LoginCheck(required = true)
  public String profile(final Model model) {
    return "views/chat/profile";
  }

}
