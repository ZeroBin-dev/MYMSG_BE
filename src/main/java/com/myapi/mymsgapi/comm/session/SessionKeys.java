package com.myapi.mymsgapi.comm.session;

import com.myapi.mymsgapi.model.UserVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SessionKeys {
  USER_VO("회원정보", UserVO.class);

  public final String desc;
  public final Class usageClass;

}
