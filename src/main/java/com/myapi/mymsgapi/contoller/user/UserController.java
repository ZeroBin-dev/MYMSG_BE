package com.myapi.mymsgapi.contoller.user;

import com.myapi.mymsgapi.contoller.user.dto.UserLoginReq;
import com.myapi.mymsgapi.contoller.user.dto.UserLoginRes;
import com.myapi.mymsgapi.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class UserController {

  private final UserService _userService;

  @GetMapping(value = "/user/login")
  @Operation(summary = "로그인", description = "로그인 화면으로 이동")
  public String userLogin(Model model) {
    return "user/login";
  }

  @ResponseBody
  @PostMapping(value = "/user/loginProc")
  @Operation(summary = "로그인", description = "로그인 처리")
  public UserLoginRes userLoginProc(@RequestBody @Validated UserLoginReq params) {
    return _userService.userLoginProc(params);
  }

}
