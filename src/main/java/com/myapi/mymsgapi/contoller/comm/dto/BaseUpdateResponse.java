package com.myapi.mymsgapi.contoller.comm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseUpdateResponse {
  private String succYn = "N";
  private String msg = "";
}
