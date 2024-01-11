package com.myapi.mymsgapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRoomInfo {
  private String roomName; // 방 제목
  private String roomId; // 방번호
  private String lastMsg; // 마지막 메시지
  private String lastMsgDt; // 마지막 메시지 시간
  private String memberCount; // 참여인원수
}
