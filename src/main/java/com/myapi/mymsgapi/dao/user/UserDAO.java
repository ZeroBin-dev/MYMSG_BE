package com.myapi.mymsgapi.dao.user;

import com.myapi.mymsgapi.contoller.user.dto.UserJnRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDAO {

  /** 회원가입 */
  void insertUser(UserJnRequest params);
  
  /** 아이디 중복 체크 */
  int dupChkUserId(String userId);
}
