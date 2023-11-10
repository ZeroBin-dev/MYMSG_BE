package com.myapi.mymsgapi.service.user;

import com.myapi.mymsgapi.comm.exception.ApiException;
import com.myapi.mymsgapi.comm.types.ExceptType;
import com.myapi.mymsgapi.comm.utils.CryptoUtil;
import com.myapi.mymsgapi.comm.utils.SessionUtil;
import com.myapi.mymsgapi.contoller.comm.dto.BaseUpdateResponse;
import com.myapi.mymsgapi.contoller.user.dto.UserJnRequest;
import com.myapi.mymsgapi.contoller.user.dto.UserLoginRequest;
import com.myapi.mymsgapi.contoller.user.dto.UserLoginResponse;
import com.myapi.mymsgapi.dao.user.UserDAO;
import com.myapi.mymsgapi.model.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserDAO _userDAO;


  /**
   * 로그인 처리
   */
  public UserVO userLoginProc(final UserLoginRequest params) {
    String userId = params.getUserId();
    String userPw = params.getUserPw();

    this.__loginProc(userId, userPw);

    return SessionUtil.getUserVO();
  }

  private static void __loginProc(final String userId, final String userPw) {
    UserVO userVO = new UserVO();
    userVO.setLoginYn("Y");
    // 1. 아이디, 비밀번호 확인 쿼리

    // 1-1. 없는 아이디 또는 패스워드 틀림 오류
    // 1-2. 패스워드 5회 이상 오류

    // 2. 세션에 정보 저장
    // 2-1. 회원정보 테이블 조회 -> 데이터 조합
    // 2-2. 세션에 저장, 실패 시 exception

    // throw new ApiException(ExceptType.LGIN001);
    SessionUtil.setUserVO(userVO);
  }

  /**
   *  회원가입 처리
   */
  public BaseUpdateResponse userJnProc(final UserJnRequest params) {
    BaseUpdateResponse res = new BaseUpdateResponse();
    String userId = params.getUserId(); // 사용자 아이디
    String userPw = params.getUserPw(); // 사용자 비밀번호
    String userPwChk = params.getUserPwChk(); // 사용자 비밀번호 확인

    // 비밀번호 동일 여부 체크
    if(!userPw.equals(userPwChk)){
      throw new ApiException(ExceptType.JOIN002); // 비밀번호가 일치 하지 않습니다.
    } else {
      userPw = CryptoUtil.encrypt(userPw);
      params.setUserPw(userPw);
    }

    // 아이디 중복 체크
    if(this.__idDupChk(userId)){
      throw new ApiException(ExceptType.JOIN004); // 중복된 ID 입니다.
    }

    try{
      _userDAO.insertUser(params);
    } catch (Exception e){
      throw new ApiException(ExceptType.JOIN001); // 회원가입에 실패하였습니다.
    }

    res.setSuccYn("Y");
    return res;
  }

  /**
   * 아이디 중복 체크
   */
  private boolean __idDupChk(final String id){
    return _userDAO.dupChkUserId(id) > 0;
  }
}
