package com.myapi.mymsgapi.contoller.comm.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BaseRes {
  private String resultCd = "200";
  private String errorYn = "N";
  private String errorCd;
  private String errorMsg;
}
