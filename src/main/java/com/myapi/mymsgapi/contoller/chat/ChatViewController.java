package com.myapi.mymsgapi.contoller.chat;

import com.myapi.mymsgapi.comm.utils.SessionUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ChatViewController {

  @RequestMapping(value = "/chat/main", method = {RequestMethod.GET, RequestMethod.POST})
  @Operation(summary = "로그인", description = "로그인 화면으로 이동")
  public String userLogin(Model model) {
    SessionUtil.removeUserVO();
    return "views/chat/chatMain";
  }

}
