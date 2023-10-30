package com.myapi.mymsgapi.contoller.comm;

import com.myapi.mymsgapi.comm.constants.Constants;
import com.myapi.mymsgapi.contoller.comm.dto.BaseReq;
import com.myapi.mymsgapi.contoller.comm.dto.BaseRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/main")
public class MainController {

  @ResponseBody
  @PostMapping(value = "", name = "메인 페이지")
  public BaseRes userMain(@RequestBody @Validated BaseReq params) {
    System.out.println("-- main --");
    System.out.println(System.getenv().get("MARIA_DB_USER"));
    System.out.println(System.getenv().get("MARIA_DB_PASS"));
    System.out.println(Constants.BASE_USER_ID);
    System.out.println(Constants.BASE_USER_NAME);

    return new BaseRes();
  }
}
