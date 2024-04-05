package com.myapi.mymsgapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomInfo {
  private String roomId; // 방번호
  private String roomName; // 방이름
}
