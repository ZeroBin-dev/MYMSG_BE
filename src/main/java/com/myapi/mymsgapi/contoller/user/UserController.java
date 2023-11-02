package com.myapi.mymsgapi.contoller.user;

import com.myapi.mymsgapi.contoller.user.dto.UserLoginReq;
import com.myapi.mymsgapi.contoller.user.dto.UserLoginRes;
import com.myapi.mymsgapi.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserController {

  private final UserService _userService;

  @ResponseBody
  @PostMapping(value = "/login")
  @Operation(summary = "유저관리", description = "아이디 로그인 처리")
  public UserLoginRes userLogin(@RequestBody @Validated UserLoginReq params){
    return _userService.userLogin(params);
  }

}
