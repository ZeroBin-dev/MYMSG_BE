package com.myapi.mymsgapi.service.user;

import com.myapi.mymsgapi.comm.exception.YbBizException;
import com.myapi.mymsgapi.comm.session.SessionKeys;
import com.myapi.mymsgapi.comm.session.SessionStore;
import com.myapi.mymsgapi.comm.types.ExceptType;
import com.myapi.mymsgapi.comm.utils.CryptoUtil;
import com.myapi.mymsgapi.comm.utils.HttpRequestUtil;
import com.myapi.mymsgapi.comm.utils.ObjectUtil;
import com.myapi.mymsgapi.comm.utils.SessionUtil;
import com.myapi.mymsgapi.contoller.comm.dto.BaseUpdateResponse;
import com.myapi.mymsgapi.contoller.user.dto.*;
import com.myapi.mymsgapi.dao.user.UserDAO;
import com.myapi.mymsgapi.model.UserFriendInfo;
import com.myapi.mymsgapi.model.UserLginInfo;
import com.myapi.mymsgapi.model.UserRoomInfo;
import com.myapi.mymsgapi.model.vo.UserLginHistVO;
import com.myapi.mymsgapi.model.vo.UserVO;
import com.myapi.mymsgapi.service.redis.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserDAO _userDAO;
  private final RedisService _redisService;

  @Value("${spring.file.profile-upload-path}")
  private String _profilePath;

  /**
   * 로그인 처리
   */
  @Transactional
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

        // 사용자 정보 가져오기
        this.__setLoginResult(userVO, loginResult);

        // room 정보 가져오기
        this.__setRoomList(userVO, userId);

        // 친구목록 가져오기
        this.__setFriendList(userVO, userId);
      }
    }

    return SessionUtil.getUserVO();
  }

  private void __setLoginResult(UserVO userVO, UserLginInfo loginResult) {
    userVO.setLginYn("Y");
    userVO.setLginData(loginResult);
    SessionUtil.setUserVO(userVO);
  }

  private void __setRoomList(UserVO userVO, String userId) {
    List<UserRoomInfo> userRoomInfo = _redisService.getUserRoomList(userId);
    userVO.setRoomList(userRoomInfo);
    SessionUtil.setUserVO(userVO);
  }

  private void __setFriendList(UserVO userVO, String userId) {
    List<UserFriendInfo> userFriendInfo = _userDAO.selectFriendList(userId);
    userVO.setFriendList(userFriendInfo);
    SessionUtil.setUserVO(userVO);
  }

  /**
   * 회원가입 처리
   */
  @Transactional
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

  /**
   * 친구추가
   */
  @Transactional
  public BaseUpdateResponse addFriend(final AddFriendReq params) {
    BaseUpdateResponse res = new BaseUpdateResponse();
    int count = _userDAO.selectFindFriend(params);
    if (count > 0) {
      res.setSuccYn("N");
      res.setMsg("해당 유저는 이미 친구입니다.");
      return res;
    }

    // 나와 친구 정보를 입력
    _userDAO.insertFriend(params);

    // 유저세션 수정
    UserVO userVO = SessionStore.getAs(SessionKeys.USER_VO, UserVO.class);
    this.__setFriendList(userVO, params.getUserId());

    // 친구정보에도 나를 입력
    AddFriendReq addFriendReq = new AddFriendReq();
    addFriendReq.setUserId(params.getFriendId());
    addFriendReq.setFriendId(params.getUserId());
    _userDAO.insertFriend(addFriendReq);

    res.setSuccYn("Y");
    return res;
  }

  /**
   * 친구찾기
   */
  public FindFriendRes findFriend(final FindFriendReq params) {
    return _userDAO.selectFindFriendId(params);
  }

  /**
   * 즐겨찾기 업데이트
   */
  @Transactional
  public BaseUpdateResponse updateBookmark(final UpdateBookmarkReq params) {
    BaseUpdateResponse res = new BaseUpdateResponse();
    String type = "Y".equals(params.getBookmarkOnOff()) ? "등록" : "해제";

    _userDAO.updateBookmark(params);

    // 유저세션 업데이트
    UserVO userVO = SessionStore.getAs(SessionKeys.USER_VO, UserVO.class);
    this.__setFriendList(userVO, params.getUserId());

    res.setSuccYn("Y");
    res.setMsg("즐겨찾기 " + type + "처리가 완료되었습니다.");
    return res;
  }

  /**
   * 상태메세지 변경
   */
  @Transactional
  public BaseUpdateResponse updateStatMsg(final UpdateStatMsgReq params) {
    BaseUpdateResponse res = new BaseUpdateResponse();

    _userDAO.updateStatMsg(params.getStatMsg(), params.getUserId());

    UserVO userVO = SessionStore.getAs(SessionKeys.USER_VO, UserVO.class);
    userVO.getLginData().setStatMsg(params.getStatMsg());
    SessionUtil.setUserVO(userVO);

    res.setSuccYn("Y");
    res.setMsg("상태메세지 변경이 완료되었습니다.");
    return res;
  }

  /**
   * 프로필사진 변경
   */
  @Transactional
  public BaseUpdateResponse updateProfileImage(MultipartFile file) {
    BaseUpdateResponse res = new BaseUpdateResponse();
    if (file != null && !file.isEmpty() && file.getSize() <= 102400) { // 100kb 이하
      try {
        String uploadDir = _profilePath;
        String fileName = SessionStore.getAs(SessionKeys.USER_VO, UserVO.class).getLginData().getUserId() + ".png";

        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
          Files.createDirectories(uploadPath);
        }
        Path filePath = uploadPath.resolve(fileName);
        File targetFile = filePath.toFile();
        file.transferTo(targetFile);

        // 업로드 성공 메시지 설정
        res.setSuccYn("Y");
        res.setMsg("프로필 이미지가 성공적으로 업로드되었습니다.");
      } catch (Exception e) {
        res.setSuccYn("N");
        res.setMsg(e.getMessage());
      }
    } else {
      res.setSuccYn("N");
      res.setMsg("허용가능한 용량(20kb)을 초과했습니다.");
    }

    return res;
  }

}
