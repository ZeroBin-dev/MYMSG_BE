package com.myapi.mymsgapi.comm.session;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public enum SessionKeys {
  USER_INFO_VO("회원정보", Map.class),
  USER_INFO_VO2("회원정보", Map.class);

  public final String desc;
  public final Class usageClass;

}
