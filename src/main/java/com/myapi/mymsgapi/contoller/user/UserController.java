package com.myapi.mymsgapi.contoller.user;

import com.myapi.mymsgapi.contoller.user.dto.UserLginReq;
import com.myapi.mymsgapi.contoller.comm.dto.BaseUpdateResponse;
import com.myapi.mymsgapi.contoller.user.dto.UserRegsReq;
import com.myapi.mymsgapi.model.UserVO;
import com.myapi.mymsgapi.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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
  public UserVO userLoginProc(@RequestBody @Validated UserLginReq params) {
    return _userService.userLoginProc(params);
  }

  @ResponseBody
  @PostMapping(value = "/user/jnProc")
  @Operation(summary = "회원가입", description = "회원가입 처리")
  public BaseUpdateResponse userJnProc(@RequestBody @Validated UserRegsReq params) {
    return _userService.userJnProc(params);
  }

}
