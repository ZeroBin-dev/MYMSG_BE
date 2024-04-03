package com.myapi.mymsgapi.contoller.comm;

import com.myapi.mymsgapi.comm.annotation.LoginCheck;
import com.myapi.mymsgapi.comm.session.SessionKeys;
import com.myapi.mymsgapi.comm.session.SessionStore;
import com.myapi.mymsgapi.model.UserVO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

  @GetMapping(value = {"/main", "/index", "/"})
  @LoginCheck(required = true)
  @Operation(summary = "메인", description = "메인페이지 조회")
  public String userMain(final Model model) {
    model.addAttribute("userVo", SessionStore.getAs(SessionKeys.USER_VO, UserVO.class));
    return "index";
  }

  @GetMapping(value = "/chats")
  @LoginCheck(required = true)
  public String chats(final Model model) {
    return "chats";
  }

  @GetMapping(value = "/chat")
  @LoginCheck(required = true)
  public String chat(final Model model) {
    return "chat";
  }

  @GetMapping(value = "/find")
  @LoginCheck(required = true)
  public String find(final Model model) {
    return "find";
  }

  @GetMapping(value = "/more")
  @LoginCheck(required = true)
  public String more(final Model model) {
    return "more";
  }

  @GetMapping(value = "/profile")
  @LoginCheck(required = true)
  public String profile(final Model model) {
    return "profile";
  }

}
