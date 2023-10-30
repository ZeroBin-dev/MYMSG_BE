package com.myapi.mymsgapi.contoller.comm;

import com.myapi.mymsgapi.contoller.comm.dto.CnstReq;
import com.myapi.mymsgapi.contoller.comm.dto.CnstRes;
import com.myapi.mymsgapi.service.CnstService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/cnst")
public class CnstController {

  private final CnstService _cnstService;

  @ResponseBody
  @PostMapping(value = "/inf", name = "공통 상수 조회")
  public CnstRes cnstInf(@RequestBody @Validated CnstReq params) {
    return _cnstService.cnstInf(params);
  }

}