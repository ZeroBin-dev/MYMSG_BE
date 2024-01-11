package com.myapi.mymsgapi.contoller.user.dto;

import com.myapi.mymsgapi.contoller.comm.dto.BaseRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 회원가입 request
 */

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class UserRegsReq extends BaseRequest {
  @NotEmpty
  private String userId; // 사용자ID

  @NotEmpty
  private String userPw; // 사용자 비밀번호

  @NotEmpty
  private String userPwChk; // 사용자 비밀번호 확인

  @NotEmpty
  private String userName; // 사용자 이름

}
