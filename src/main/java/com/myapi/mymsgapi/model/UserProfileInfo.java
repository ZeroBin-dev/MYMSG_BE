package com.myapi.mymsgapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileInfo {
  private String userId; // 사용자ID
  private String userName; // 사용자명
  private String regDt; // 등록일
  private String modDt; // 수정일
  private String profilePath; // 이미지경로
  private String statmsg; // 상태메세지
}
