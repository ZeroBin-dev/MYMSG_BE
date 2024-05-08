package com.myapi.mymsgapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoomInfo {
  private String roomId; // 방번호
  private String roomName; // 방이름
  private String roomProfile; // 방이미지
}
