package com.myapi.mymsgapi.service.user;

import com.myapi.mymsgapi.comm.exception.MyBizException;
import com.myapi.mymsgapi.contoller.user.dto.UserLoginReq;
import com.myapi.mymsgapi.contoller.user.dto.UserLoginRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

  private static void __loginProc(final String userId, final String userPw){
    Map<String, String> result = new HashMap<>();
    throw new MyBizException("LGIN001");
  }

}
