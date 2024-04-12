package com.myapi.mymsgapi.contoller.comm;

import com.myapi.mymsgapi.contoller.comm.dto.CnstRequest;
import com.myapi.mymsgapi.contoller.comm.dto.CnstResponse;
import com.myapi.mymsgapi.service.comm.CnstService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/cnst")
public class CnstController {

  private final CnstService _cnstService;

  @ResponseBody
  @PostMapping(value = "/inf")
  @Operation(summary = "공통 상수", description = "공통 상수 조회")
  public CnstResponse cnstInf(@RequestBody @Validated CnstRequest params) {
    return _cnstService.cnstInf(params);
  }

}
