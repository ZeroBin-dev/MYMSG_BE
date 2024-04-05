package com.myapi.mymsgapi.service.user;

import com.myapi.mymsgapi.comm.exception.YbBizException;
import com.myapi.mymsgapi.comm.types.ExceptType;
import com.myapi.mymsgapi.comm.utils.CryptoUtil;
import com.myapi.mymsgapi.comm.utils.HttpRequestUtil;
import com.myapi.mymsgapi.comm.utils.ObjectUtil;
import com.myapi.mymsgapi.comm.utils.SessionUtil;
import com.myapi.mymsgapi.contoller.comm.dto.BaseUpdateResponse;
import com.myapi.mymsgapi.contoller.user.dto.UserLginReq;
import com.myapi.mymsgapi.contoller.user.dto.UserRegsReq;
import com.myapi.mymsgapi.dao.user.UserDAO;
import com.myapi.mymsgapi.model.vo.UserLginHistVO;
import com.myapi.mymsgapi.model.UserLginInfo;
import com.myapi.mymsgapi.model.UserRoomInfo;
import com.myapi.mymsgapi.model.vo.UserVO;
import com.myapi.mymsgapi.service.redis.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserDAO _userDAO;
  private final RedisService _redisService;

  /**
   * 로그인 처리
   */
  public UserVO userLoginProc(final UserLginReq params) {
    String userId = params.getUserId();
    String userPw = params.getUserPw();

    // 사용자 비밀번호 암호화
    params.setUserPw(CryptoUtil.encrypt(userPw));

    // 아이디, 비밀번호 확인 쿼리
    UserLginInfo loginResult = _userDAO.selectLoginUser(params);

    if (loginResult == null || ObjectUtil.isEmpty(loginResult)) {
      // 오류 카운트 증가
      _userDAO.updateErrorCount(params);
      throw new YbBizException(ExceptType.LGIN002); // 아이디 또는 비밀번호를 잘못 입력하였습니다.
    } else {
      // 로그인 이력 쌓기
      UserLginHistVO userLginHistVO = new UserLginHistVO(userId, HttpRequestUtil.getClientIP());
      _userDAO.insertLoginUserHist(userLginHistVO);

      if (loginResult.getPwErrCnt() >= 5) {
        throw new YbBizException(ExceptType.LGIN003); // 비밀번호 5회 이상 잘못 입력하였습니다.
      } else {
        // 비밀번호 오류 카운트 초기화
        _userDAO.updateErrorCountZero(params);

        UserVO userVO = new UserVO();
        userVO.setLginYn("Y");

        // 사용자 정보 가져오기
        userVO.setLginData(loginResult);

        // room 정보 가져오기
        List<UserRoomInfo> userRoomInfo = _redisService.getUserRoomList(userId);
        userVO.setRoomList(userRoomInfo);

        SessionUtil.setUserVO(userVO);
      }
    }

    return SessionUtil.getUserVO();
  }

  /**
   * 회원가입 처리
   */
  public BaseUpdateResponse userJnProc(final UserRegsReq params) {
    BaseUpdateResponse res = new BaseUpdateResponse();
    String userId = params.getUserId(); // 사용자 아이디
    String userPw = params.getUserPw(); // 사용자 비밀번호
    String userPwChk = params.getUserPwChk(); // 사용자 비밀번호 확인

    // 비밀번호 동일 여부 체크
    if (!userPw.equals(userPwChk)) {
      throw new YbBizException(ExceptType.JOIN002); // 비밀번호가 일치 하지 않습니다.
    } else {
      userPw = CryptoUtil.encrypt(userPw);
      params.setUserPw(userPw);
    }

    // 아이디 중복 체크
    if (this.__idDupChk(userId)) {
      throw new YbBizException(ExceptType.JOIN004); // 중복된 ID 입니다.
    }

    try {
      _userDAO.insertUser(params);
    } catch (Exception e) {
      throw new YbBizException(ExceptType.JOIN001); // 회원가입에 실패하였습니다.
    }

    res.setSuccYn("Y");
    return res;
  }

  /**
   * 아이디 중복 체크
   */
  private boolean __idDupChk(final String id) {
    return _userDAO.dupChkUserId(id) > 0;
  }
}
