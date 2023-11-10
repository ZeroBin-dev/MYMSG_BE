package com.myapi.mymsgapi.contoller.user.dto;

import com.google.gson.annotations.SerializedName;
import com.myapi.mymsgapi.contoller.comm.dto.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Getter
@Setter
public class UserLoginResponse extends BaseResponse {

  @SerializedName("userData")
  private UserLoginResponseIn userData;

  public static class UserLoginResponseIn{
    private String loginYn; // 로그인 성공 여부
    private String pwErrCnt; // 비밀번호 오류 횟수
  }

}
