package com.myapi.mymsgapi.contoller.comm;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

  @GetMapping(value = "/")
  @Operation(summary = "메인", description = "메인페이지 조회")
  public String main() {
    return "main";
  }

  @GetMapping(value = "/main")
  @Operation(summary = "메인", description = "메인페이지 조회")
  public String userMain() {
    return "main";
  }
}
