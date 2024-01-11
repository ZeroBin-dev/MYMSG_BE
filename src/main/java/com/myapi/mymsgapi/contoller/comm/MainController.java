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

  @GetMapping(value = {"/main", "/"})
  @LoginCheck(required = true)
  @Operation(summary = "메인", description = "메인페이지 조회")
  public String userMain(final Model model) {
    model.addAttribute("userVo", SessionStore.getAs(SessionKeys.USER_VO, UserVO.class));
    return "main";
  }

}
