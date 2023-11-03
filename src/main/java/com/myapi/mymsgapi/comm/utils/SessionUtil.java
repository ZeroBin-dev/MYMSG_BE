package com.myapi.mymsgapi.comm.utils;

import com.myapi.mymsgapi.comm.session.SessionKeys;
import com.myapi.mymsgapi.comm.session.SessionStore;
import com.myapi.mymsgapi.model.UserVO;

public class SessionUtil {

  public static UserVO getUserVO() {
    UserVO userVO = SessionStore.getAs(SessionKeys.USER_VO, UserVO.class);
    return ObjectUtil.isEmpty(userVO) ? new UserVO() : userVO;
  }

  public static boolean isLogin() {
    return "Y".equals(getUserVO().getLoginYn());
  }

}
