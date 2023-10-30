package com.myapi.mymsgapi.contoller.comm;

import com.myapi.mymsgapi.contoller.comm.dto.BaseReq;
import com.myapi.mymsgapi.contoller.comm.dto.BaseRes;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/code")
public class CodeController {

  @ResponseBody
  @PostMapping(value = "", name = "코드 컨트롤러")
  public BaseRes codeMain(@RequestBody @Validated BaseReq params) {
    System.out.println("-- code --");

    return new BaseRes();
  }

}
