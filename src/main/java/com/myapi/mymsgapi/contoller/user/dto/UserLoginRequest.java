package com.myapi.mymsgapi.contoller.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.myapi.mymsgapi.contoller.comm.dto.BaseRequest;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 * 로그인 request
 */

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserLoginRequest extends BaseRequest {

  @NotEmpty
  private String userId; // 사용자ID

  @NotEmpty
  private String userPw; // 사용자PW
}
