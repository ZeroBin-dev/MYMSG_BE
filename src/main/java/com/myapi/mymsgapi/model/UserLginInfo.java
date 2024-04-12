package com.myapi.mymsgapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLginInfo {
  private String userId;
  private String userName;
  private int pwErrCnt;
  private String regDt;
  private String modDt;
  private String profilePath;
  private String statMsg;
}
