package com.myapi.mymsgapi.service.user;

import com.myapi.mymsgapi.comm.exception.ApiException;
import com.myapi.mymsgapi.comm.types.ExceptType;
import com.myapi.mymsgapi.contoller.user.dto.UserLoginReq;
import com.myapi.mymsgapi.contoller.user.dto.UserLoginRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

  /**
   * 로그인 처리
   */
  public UserLoginRes userLoginProc(UserLoginReq params) {
    UserLoginRes res = new UserLoginRes();
    String userId = params.getUserId();
    String userPw = params.getUserPw();

    __loginProc(userId, userPw);

    return res;
  }

  private static void __loginProc(final String userId, final String userPw) {
    Map<String, String> result = new HashMap<>();

    // 1. 아이디, 비밀번호 확인 쿼리


    // 1-1. 없는 아이디 또는 패스워드 틀림 오류
    // 1-2. 패스워드 5회 이상 오류

    // 2. 세션에 정보 저장
    // 2-1. 회원정보 테이블 조회 -> 데이터 조합
    // 2-2. 세션에 저장, 실패 시 exception

    throw new ApiException(ExceptType.LGIN001);
  }

}
