package com.myapi.mymsgapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFriendInfo {
  private String userId; // 사용자ID
  private String friendId; // 친구ID
  private String friendName; // 친구이름
  private String friendStatMsg; // 친구상메
  private String bookmarkYn; // 즐겨찾기여부
  private String regDt; // 등록일
}
