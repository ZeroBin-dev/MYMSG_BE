package com.myapi.mymsgapi.contoller.user.dto;

import com.myapi.mymsgapi.contoller.comm.dto.BaseRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 * 로그인 이력 쌓기
 */
@Getter
@Setter
public class UserLginHistReq extends BaseRequest {
  @NotEmpty
  private String userId; // 사용자ID
  @NotEmpty
  private String userPw; // 사용자PW
}
