package com.myapi.mymsgapi.contoller.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.myapi.mymsgapi.contoller.comm.dto.BaseReq;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserLoginReq extends BaseReq {

  @NotEmpty
  private String userId; // 사용자ID

  @NotEmpty
  private String userPw; // 사용자PW
}
