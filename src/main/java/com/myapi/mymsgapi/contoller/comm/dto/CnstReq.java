package com.myapi.mymsgapi.contoller.comm.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CnstReq extends BaseReq{

  @NotEmpty
  private String cnstCd; // 상수코드
}
