package com.myapi.mymsgapi.contoller.user.dto;

import com.myapi.mymsgapi.contoller.comm.dto.BaseRes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRes extends BaseRes {
  private String loginYn; // 로그인 성공 여부
  private String pwErrCnt; // 비밀번호 오류 횟수
}
