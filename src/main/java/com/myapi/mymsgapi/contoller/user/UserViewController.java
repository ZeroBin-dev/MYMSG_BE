package com.myapi.mymsgapi.contoller.user;

import com.myapi.mymsgapi.comm.utils.SessionUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
public class UserViewController {

  @RequestMapping(value = "/user/login", method = {RequestMethod.GET, RequestMethod.POST})
  @Operation(summary = "로그인", description = "로그인 화면으로 이동")
  public String userLogin(Model model) {
    SessionUtil.removeUserVO();
    return "views/user/login";
  }

  @GetMapping(value = "/user/chngPw")
  @Operation(summary = "비밀번호 변경", description = "비밀번호 변경")
  public String userChngPw(Model model) {
    model.addAttribute("userId", "testUserId");
    return "views/user/chngPw";
  }


}
