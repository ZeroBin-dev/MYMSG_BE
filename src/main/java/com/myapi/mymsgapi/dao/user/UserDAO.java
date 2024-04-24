package com.myapi.mymsgapi.dao.user;

import com.myapi.mymsgapi.contoller.user.dto.*;
import com.myapi.mymsgapi.model.UserFriendInfo;
import com.myapi.mymsgapi.model.UserLginInfo;
import com.myapi.mymsgapi.model.UserProfileInfo;
import com.myapi.mymsgapi.model.vo.UserLginHistVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDAO {

  /**
   * 아이디 로그인
   */
  UserLginInfo selectLoginUser(final UserLginReq params);

  /**
   * 비밀번호 오류 카운트 증가
   */
  void updateErrorCount(final UserLginReq params);

  /**
   * 비밀번호 오류 카운트 초기화
   */
  void updateErrorCountZero(final UserLginReq params);

  /**
   * 로그인 이력 쌓기
   */
  void insertLoginUserHist(final UserLginHistVO params);

  /**
   * 회원가입
   */
  void insertUser(final UserRegsReq params);

  /**
   * 아이디 중복 체크
   */
  int dupChkUserId(final String userId);

  List<UserFriendInfo> selectFriendList(final String userId);

  UserProfileInfo selectUserProfile(final String userId);

  int selectFindFriend(final AddFriendReq params);

  void insertFriend(final AddFriendReq params);

  FindFriendRes selectFindFriendId(final FindFriendReq params);

  void updateBookmark(UpdateBookmarkReq params);

  void updateStatMsg(UpdateStatMsgReq params);
}
